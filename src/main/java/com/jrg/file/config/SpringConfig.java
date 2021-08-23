package com.jrg.file.config;

import com.jrg.file.filter.LastModifiedFileFilter;
import com.jrg.file.processor.FileProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.integration.annotation.InboundChannelAdapter;
import org.springframework.integration.annotation.IntegrationComponentScan;
import org.springframework.integration.annotation.Poller;
import org.springframework.integration.channel.DirectChannel;
import org.springframework.integration.config.EnableIntegration;
import org.springframework.integration.core.MessageSource;
import org.springframework.integration.dsl.IntegrationFlow;
import org.springframework.integration.dsl.IntegrationFlows;
import org.springframework.integration.file.FileReadingMessageSource;
import org.springframework.integration.file.filters.CompositeFileListFilter;
import org.springframework.integration.file.filters.LastModifiedFileListFilter;
import org.springframework.integration.file.filters.SimplePatternFileListFilter;
import org.springframework.integration.file.transformer.FileToStringTransformer;
import org.springframework.messaging.MessageChannel;

import java.io.File;

@Configuration
@IntegrationComponentScan
public class SpringConfig {

    private static final String DIRECTORY = "/Users/johnrobertgrant/Documents/playground/temp/";

    @Bean
    public IntegrationFlow processFileFlow () {
        return IntegrationFlows
                .from("fileInputChannel")
                .transform(fileToStringTransformer())
                .handle("fileProcessor", "process").get();
    }

    /**
     * DirectChannel is point ot point implementation
     * @return
     */
    @Bean
    public MessageChannel fileInputChannel () {
        return new DirectChannel();
    }

    @Bean
    @InboundChannelAdapter(value="fileInputChannel", poller = @Poller(fixedDelay = "1000"))
    public MessageSource<File> fileReadingMessaqeSource () {

        CompositeFileListFilter<File> filters = new CompositeFileListFilter<>();
        filters.addFilter(new SimplePatternFileListFilter("*.txt"));
        filters.addFilter(new LastModifiedFileFilter());

        FileReadingMessageSource source = new FileReadingMessageSource();
        source.setAutoCreateDirectory(true);
        source.setDirectory (new File (DIRECTORY));
        source.setFilter(filters);

        return source;
    }

    @Bean
    public FileToStringTransformer fileToStringTransformer() {
        return new FileToStringTransformer();
    }


    @Bean
    public FileProcessor fileProcessor() {
        return new FileProcessor();
    }






}
