package com.danielwellman.jschallenge.java1.javaio;

import com.danielwellman.jschallenge.java1.IoFacade;
import com.danielwellman.jschallenge.java1.IoFacadeException;

import java.io.*;

public class JavaFileSystemFacade implements IoFacade {

    private final String path;

    public JavaFileSystemFacade() {
        // TODO Configure this per production or test environment
        this("test_folder");
    }

    public JavaFileSystemFacade(String path) {
        this.path = path;
    }

    public String readFile(String filename) {
        File file = fileAt(filename);
        BufferedReader fileReader;
        try {
            fileReader = new BufferedReader(new FileReader(file));
        } catch (FileNotFoundException e) {
            throw new IoFacadeException("File not found: " + e.getMessage());
        }
        try {
            return fileReader.readLine();
        } catch (IOException e) {
            throw new IoFacadeException("Failed to read file: " + e.getMessage());
        } finally {
            try {
                fileReader.close();
            } catch (IOException e) {
                // Silently ignore closing errors?
            }
        }
    }

    public void createFile(String filename, String contents) {
        File file = fileAt(filename);
        FileWriter fileWriter;
        try {
            fileWriter = new FileWriter(file);
        } catch (IOException e) {
            throw new IoFacadeException("Could not create a file: " + e.getMessage());
        }
        try {
            fileWriter.write(contents);
        } catch (IOException e) {
            throw new IoFacadeException("Could not write to file: " + e.getMessage());
        } finally {
            try {
                fileWriter.close();
            } catch (IOException e) {
                // Silently ignore closing a file?
            }
        }
    }

    private File fileAt(String filename) {
        return new File(path, filename);
    }
}
