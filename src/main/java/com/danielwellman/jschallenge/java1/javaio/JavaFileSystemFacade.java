package com.danielwellman.jschallenge.java1.javaio;

import com.danielwellman.jschallenge.java1.FileSystemFacade;
import com.danielwellman.jschallenge.java1.InputReader;
import com.danielwellman.jschallenge.java1.OutputWriter;

import java.io.File;

public class JavaFileSystemFacade implements FileSystemFacade {

    private final String path;

    public JavaFileSystemFacade() {
        // TODO Configure this per production or test environment
        this("test_folder");
    }

    public JavaFileSystemFacade(String path) {
        this.path = path;
    }

    private File fileAt(String filename) {
        return new File(path, filename);
    }

    public OutputWriter createFile(String filename) {
        return new JavaOutputFile(pathForFile(filename));
    }

    private String pathForFile(String filename) {
        // Dodgy...
        return path + "/" + filename;
    }

    public InputReader openFileForRead(String filename) {
        return new JavaReadOnlyFile(pathForFile(filename));
    }
}
