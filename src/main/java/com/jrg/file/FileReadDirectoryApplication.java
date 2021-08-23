package com.jrg.file;

import org.apache.commons.io.FileUtils;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.integration.config.EnableIntegration;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;

@SpringBootApplication(scanBasePackages = "com.jrg.file")
@EnableIntegration
public class FileReadDirectoryApplication {

    private static final String DIRECTORY = "/Users/johnrobertgrant/Documents/playground/temp/";

    public static void main(String[] args) throws IOException, InterruptedException {
        SpringApplication.run(FileReadDirectoryApplication.class, args);
        createFiles();
    }

    private static void createFiles() throws IOException, InterruptedException {
        System.out.println("In create files again--------");
        createFile("file1.txt", "content");
        createFile("file2.txt", "another file");
        appendFile("file1.txt", " modified");
    }

    private static void createFile(String fileName, String content) throws IOException, InterruptedException {
        writeFile(fileName, content, false);
    }

    private static void appendFile(String fileName, String content) throws IOException, InterruptedException {
        writeFile(fileName, content, true);
    }

    private static void writeFile(String fileName, String content, boolean append) throws IOException, InterruptedException {
        File newFile = new File(DIRECTORY + fileName);
        FileUtils.writeStringToFile(newFile, content, Charset.forName("UTF-8"), append);
        Thread.sleep(1000);
    }


}
