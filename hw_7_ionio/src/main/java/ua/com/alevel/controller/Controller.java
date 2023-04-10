package ua.com.alevel.controller;

import ua.com.alevel.service.Service;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {

    Service service = new Service();

    public void start() {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome");
        System.out.println("Select options:");
        String select;
        menu();
        while (true) {
            try {
                if ((select = bf.readLine()) == null) break;
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            navigation(bf, select);
        }
    }

    private void menu() {
        System.out.println("______________________________hw_7_file_helper____________________________________");
        System.out.println();
        System.out.println("If you to GET THE LIST of files and folders by directory, please enter 1");
        System.out.println();
        System.out.println("If you want to CREATE FILE, please enter 2");
        System.out.println();
        System.out.println("If you want to CREATE FOLDER, please enter 3");
        System.out.println();
        System.out.println("If you want to DELETE FILE , please enter 4");
        System.out.println();
        System.out.println("If you want to DELETE FOLDER , please enter 5");
        System.out.println();
        System.out.println("If you want to MOVE file or folder, please enter 6");
        System.out.println();
        System.out.println("If you want to FIND FILE, please enter 7");
        System.out.println();
        System.out.println("If you want to FIND FOLDER, please enter 8");
        System.out.println();
        System.out.println("If you want to FIND TEXT, please enter 9");
        System.out.println();
        System.out.println("If you want to exit the program, enter 0");
        System.out.println("____________________________________________________________________________");
        System.out.println();
    }

    private void navigation(BufferedReader reader, String select) {
        switch (select) {
            case "1" -> getListOfFiles(reader);
            case "2" -> createFile(reader);
            case "3" -> createDir(reader);
            case "4" -> deleteFIle(reader);
            case "5" -> deleteDir(reader);
            case "6" -> moveFileFolder(reader);
            case "7" -> findFile(reader);
            case "8" -> findFolder(reader);
            case "9" -> findText(reader);
            case "0" -> System.exit(0);
        }
        menu();
    }

    private void getListOfFiles(BufferedReader reader) {
        System.out.println("Please enter the directory");
        try {
            String fileName = reader.readLine();
            service.readDir(new File(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createFile(BufferedReader reader) {
        System.out.println("Enter the directory ");
        String fileName;
        try {
            fileName = reader.readLine();
            service.createFile(new File(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void createDir(BufferedReader reader) {
        System.out.println("Enter the name of folder");
        String fileName;
        try {
            fileName = reader.readLine();
            service.createDir(new File(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteFIle(BufferedReader reader) {
        System.out.println("Enter the directory ");
        String fileName;
        try {
            fileName = reader.readLine();
            service.deleteFile(new File(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void deleteDir(BufferedReader reader) {
        System.out.println("Enter the directory ");
        String fileName;
        try {
            fileName = reader.readLine();
            service.deleteDir(new File(fileName));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }

    private void moveFileFolder(BufferedReader reader) {
        System.out.println("Enter the name of file or folder, you want to move");
        String fileToMove;
        try {
            fileToMove = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Enter the directory where, you want to move");
        String newLocation;
        try {
            newLocation = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        service.fileMover(new File(fileToMove), new File(newLocation));
    }

    private void findFile(BufferedReader reader) {
        System.out.println("Enter the file, which you want to find");
        String file;
        try {
            file = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Enter the directory where you want to find");
        String directory;
        try {
            directory = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        service.findFile(new File(directory), file);
    }

    private void findFolder(BufferedReader reader) {
        System.out.println("Enter the folder, which you want to find");
        String file;
        try {
            file = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Enter the directory where you want to find");
        String directory;
        try {
            directory = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        service.findFolder(new File(directory), file);
    }

    private void findText(BufferedReader reader) {
        System.out.println("Enter the text, you want to find");
        String text;
        try {
            text = reader.readLine();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        System.out.println("Enter the directory, where you what to find");
        File directory;
        try {
            directory = new File(reader.readLine());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        service.findText(text, directory);
    }
}