package ru.noname070.lab6.server.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.IOException;

import lombok.extern.slf4j.Slf4j;

import java.io.FileInputStream;
import java.io.File;
import java.io.FileOutputStream;

/**
 * file i/o
 */
@Slf4j
public class IOManager {

    /**
     * write raw data to file
     *
     * @param filePath path2file
     * @param rawData  data to write
     * 
     * @throws IOException
     */
    public static void writeToFile(String filePath, String rawData) throws IOException {
        try (BufferedOutputStream bOutputStream = new BufferedOutputStream(new FileOutputStream(filePath))) {
            byte[] bytes = rawData.getBytes();
            bOutputStream.write(bytes);
            bOutputStream.close();
            log.info("data->file in " + filePath);
        }
    }

    /**
     * add raw data to file
     *
     * @param filePath path2file
     * @param rawData  data to add
     * 
     * @throws IOException
     */
    public static void addToFile(String filePath, String rawData) throws IOException {
        try (BufferedOutputStream bOutputStream = new BufferedOutputStream(new FileOutputStream(filePath, true))) {
            byte[] bytes = (rawData + "\n").getBytes();
            bOutputStream.write(bytes);
            bOutputStream.close();
            log.info("add new data to " + filePath);
        }
    }

    /**
     * read raw data to file
     *
     * @param filePath path2file
     * @throws IOException
     */
    public static String readFromFile(String filePath) throws IOException {
        try (BufferedInputStream bInputStream = new BufferedInputStream(new FileInputStream(filePath))) {
            byte[] bytes = bInputStream.readAllBytes();
            bInputStream.close();
            log.info("read rawData from file " + filePath);
            return new String(bytes);
        }
    }

    /**
     * checks the file for accessibility
     *
     * @param filePath path2file
     */
    public static boolean checkFile(String filePath) {
        File f = new File(filePath);
        log.info("Checking access to file " + f.getAbsolutePath());
        return f.canRead() & f.canWrite() & f.isFile() & f.exists();
    }

}
