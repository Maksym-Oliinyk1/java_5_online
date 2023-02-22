package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

import ua.com.alevel.SumNumbers;
import ua.com.alevel.SumCharacters;
import ua.com.alevel.EndOfLessons;

public class Menu{
    public static void start() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Select exercise");
        String position;
        menu();
            while ((position = reader.readLine()) != null) {
                runNavigation(reader, position);
            }
    }
    public static void menu(){
        System.out.println("To choose first exercise - enter 1");
        System.out.println("To choose second exercise - enter 2");
        System.out.println("To choose thirst exercise - enter 3");
        System.out.println("To exit - enter 0");
    }
    public static void runNavigation(BufferedReader reader, String position) throws IOException {
        switch (position) {
            case "1" -> SumNumbers.countOfNumbers(reader);
            case "2" -> SumCharacters.countOfCharacters(reader);
            case "3" -> EndOfLessons.countOfLessons(reader);
            case "0" -> System.exit(0);
        }
    }
}