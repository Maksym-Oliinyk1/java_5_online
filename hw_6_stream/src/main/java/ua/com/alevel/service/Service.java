package ua.com.alevel.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Service {
    public static void start() throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Enter the string:");
        System.out.println();
        String text = reader.readLine();

        List<String> words = Arrays.stream(text.split("[^\\p{L}]+"))
                .map(String::toLowerCase)
                .toList();

        Map<String, Long> wordCount = words.stream()
                .collect(Collectors.groupingBy(String::toString, Collectors.counting()));

        List<Map.Entry<String, Long>> sortedByCount = wordCount.entrySet().stream()
                .sorted(Map.Entry.comparingByValue(Comparator.reverseOrder()))
                .toList();

        int totalWords = words.size();
        List<String[]> tableData = sortedByCount.stream()
                .map(e -> new String[]{e.getKey(), e.getValue().toString(), String.format("%.2f%%", e.getValue() * 100.0 / totalWords)})
                .toList();

        System.out.printf("%-20s%-20s%-20s\n", "Word", "Amount", "Percentage");
        for (String[] row : tableData) {
            System.out.printf("%-20s%-20s%-20s\n", row[0], row[1], row[2]);
        }
    }
}