package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;

public class EndOfLessons {
    public static void countOfLessons(BufferedReader reader) throws IOException {
        System.out.println("Enter number of lesson: ");
        int lesson = Integer.parseInt(reader.readLine());
        int hours = (lesson * 45 + (lesson/2) * 5 + ((lesson + 1) / 2 - 1) * 15) / 60 + 9;
        int minutes = (lesson * 45 + (lesson/2) * 5 + ((lesson + 1) / 2 - 1) * 15) % 60;
        System.out.println();
        System.out.println("End time of the lesson: " + hours + " " + minutes);
        System.out.println();
        Menu.menu();
    }
}
