package ua.com.alevel.service;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

public class Service {

    public void createFile(File file) {
        try {
            file.createNewFile();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("File created!");
    }

    public void createDir(File file) {
        file.mkdir();
        System.out.println("Folder created!");
    }

    public void readDir(File dir) {
        if (dir.isDirectory()) {
            System.out.println("dir = " + dir);
            File[] files = dir.listFiles();
            for (File file : files) {
                if (file.isDirectory()) {
                    readDir(file);
                } else {
                    System.out.println("file = " + file);
                }
            }
        } else {
            System.out.println("There no such directory");
        }
    }
    public void deleteFile(File file) {
        if (file.delete()) {
            System.out.println("File deleted");
        } else {
            System.out.println("File is not exist");
        }
    }

    public void deleteDir(File dir) {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    deleteDir(file);
                } else {
                    file.delete();
                }
            }
        }
        System.out.println("Folder " + dir.getName() +  " was deleted!");
        dir.delete();
    }

    public void fileMover(File fileToMove, File newLocation) {
        if (!newLocation.exists()) {
            newLocation.mkdir();
        }
        if (fileToMove.renameTo(new File(newLocation, fileToMove.getName()))) {
            System.out.println("File moved!");
        } else {
            System.out.println("File was not moved");
        }
    }

    public void findFile (File directory, String fileName) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.getName().equals(fileName) && !file.isDirectory()) {
                    System.out.println("File found " + fileName);
                }
            }
        }
    }



    public void findFolder (File directory, String fileName) {
        File[] directories = directory.listFiles();
        if (directories != null) {
            for (File directory1 : directories) {
                if (directory1.isDirectory() && directory1.getName().equals(fileName)) {
                    System.out.println("Folder was found: " + directory1.getName());
                }
            }
        }
    }

    public void findText(String text, File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            for (File file : files) {
                if (!file.isDirectory()) {
                    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
                        String line;
                        while ((line = reader.readLine()) != null) {
                            if (line.contains(text)) {
                                System.out.println("File contains this text " + file.getName());
                                break;
                            }
                        }
                    } catch (IOException e) {
                        System.out.println("Unable to read file " + file.getName() + ": " + e.getMessage());
                    }
                }
            }
        }
    }
}
