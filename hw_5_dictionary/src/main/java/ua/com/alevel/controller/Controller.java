package ua.com.alevel.controller;

import ua.com.alevel.service.Dictionary;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {
    Dictionary<String, Integer> dictionary = new Dictionary<String, Integer>();
    Dictionary<String, Integer> dictionary2 = new Dictionary<String, Integer>();

    public void start() throws IOException {
        dictionary.put("One", 1);
        dictionary.put("Two", 2);
        dictionary.put("Three", 3);
        dictionary2.put("Four", 4);
        dictionary2.put("Five", 5);
        dictionary2.put("Six", 6);
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome");
        System.out.println("Your dictionary already have 3 elements");
        System.out.println("1. key = 'One' value = 1");
        System.out.println("2. key = 'Two' value = 2");
        System.out.println("3. key = 'Three' value = 3");
        System.out.println();
        System.out.println("Select options:");
        String select;
        menu();
        while ((select = bf.readLine()) != null) {
            nav(bf, select);
        }
    }

    private void menu() {
        System.out.println("---------------------------------------------------------------------------");
        System.out.println();
        System.out.println("If you want to know size, please enter 1");
        System.out.println();
        System.out.println("If you want to know is dictionary empty, please enter 2");
        System.out.println();
        System.out.println("If you want to know is dictionary contains some key, please enter 3");
        System.out.println();
        System.out.println("If you want to know is dictionary contains some value, please enter 4");
        System.out.println();
        System.out.println("If you want to get some value by key, please enter 5");
        System.out.println();
        System.out.println("If you want to put new value, please enter 6");
        System.out.println();
        System.out.println("If you want remove, please enter 7");
        System.out.println();
        System.out.println("If you want to put all (put new elements from another dictionary2), please enter 8");
        System.out.println();
        System.out.println("If you want is dictionary clear, please enter 9");
        System.out.println();
        System.out.println("If you want to get set of key, please enter 10");
        System.out.println();
        System.out.println("If you want to get collection of value, please enter 11");
        System.out.println();
        System.out.println("If you want to exit, please enter 0");
        System.out.println();
        System.out.println("---------------------------------------------------------------------------");
    }

    public void nav(BufferedReader reader, String select) throws IOException {
        switch (select) {
            case "1" -> size();
            case "2" -> isEmpty();
            case "3" -> containsKey(reader);
            case "4" -> containsValue(reader);
            case "5" -> getValue(reader);
            case "6" -> put(reader);
            case "7" -> remove(reader);
            case "8" -> putAll();
            case "9" -> clear();
            case "10" -> keySet();
            case "11" -> values();
            case "0" -> System.exit(0);
        }
        menu();
    }

    private void size() {
        System.out.println("\tSize of dictionary: " + dictionary.getSize());
    }

    private void isEmpty() {
        if (dictionary.isEmpty()) {
            System.out.println("\tSize of dictionary: is empty");
        } else {
            System.out.println("\tSize of dictionary:is not empty");
        }
    }

    private void containsKey(BufferedReader reader) throws IOException {
        System.out.println("Enter key");
        String key = reader.readLine();
        if (dictionary.containsKey(key)) {
            System.out.println("Contains key " + key);
        } else {
            System.out.println("Dictionary does not contains key " + key);
        }
    }

    private void containsValue(BufferedReader reader) throws IOException {
        System.out.println("Enter value");
        String stringValue = reader.readLine();
        int value = Integer.parseInt(stringValue);
        if (dictionary.containsValue(value)) {
            System.out.println("Dictionary contains " + value);
        } else {
            System.out.println("Dictionary does not contains value");
        }
    }

    private void getValue(BufferedReader reader) throws IOException {
        System.out.println("Enter key");
        String key = reader.readLine();
        if (dictionary.get(key) == null) {
            System.out.println("Unknown key");
        } else {
            System.out.println(dictionary.get(key));
        }
    }

    private void put(BufferedReader reader) throws IOException {
        System.out.println("Enter the key");
        String key = reader.readLine();
        System.out.println("Enter the value");
        String stringValue = reader.readLine();
        int value = Integer.parseInt(stringValue);
        if (dictionary.put(key, value)) {
            System.out.println("Successful put");
        } else {
            System.out.println("Wrong");
        }
    }

    private void remove(BufferedReader reader) throws IOException {
        System.out.println("Enter key");
        String key = reader.readLine();
        if (dictionary.remove(key)) {
            System.out.println("Removed successfully");
        } else {
            System.out.println("Wrong. No key.");
        }
    }

    private void putAll() {
        if (dictionary.putAll(dictionary2)) {
            System.out.println("Dictionary was pulled");
        } else {
            System.out.println("Dictionary was already pulled");
        }
    }

    private void clear() {
        if (!dictionary.clear()) {
            System.out.println("Dictionary is already empty");
        } else {
            System.out.println("Dictionary is cleared");
        }
    }

    private void keySet() {
        if (dictionary.keySet().isEmpty()) {
            System.out.println("The key set is empty");
        } else {
            System.out.println(dictionary.keySet());
        }

    }

    private void values() {
        if (dictionary.values().isEmpty()) {
            System.out.println("No one values in dictionary");
        } else {
            System.out.println(dictionary.values());
        }
    }
}

