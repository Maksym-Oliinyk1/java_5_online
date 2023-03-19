package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Controller {

    public void start() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome");
        System.out.println("Select options:");
        String select;
        menu();
        while ((select = bf.readLine()) != null) {
            navigation(bf, select);
        }
    }

    private void menu() {
        System.out.println();
        System.out.println("If you want ordinary revers, please enter 1");
        System.out.println("If you want substring reverse, please enter 2");
        System.out.println("If you want reverse by index, please enter 3");
        System.out.println("If you want close, please enter 0");
    }

    private void navigation(BufferedReader bf, String select) throws IOException {
        switch (select) {
            case "1" -> ordinaryReverse(bf);
            case "2" -> substringReverse(bf);
            case "3" -> superReverse(bf);
            case "0" -> System.exit(0);
        }
    }

    private void ordinaryReverse(BufferedReader bf) throws IOException {
        System.out.println("Enter the string");
        String userString = bf.readLine();
        System.out.println(Service.reverse(userString));
    }

    private void substringReverse(BufferedReader bf) throws IOException {
        System.out.println("Enter the string");
        String userString = bf.readLine();
        System.out.println("Enter the substring");
        String dest = bf.readLine();
        System.out.println(Service.reverse(userString, dest));
    }

    private void superReverse(BufferedReader bf) throws IOException {
        System.out.println("Enter the string");
        String userString = bf.readLine();
        System.out.println("Enter the first index");
        int first = Integer.parseInt(bf.readLine());
        System.out.println("Enter the second index");
        int second = Integer.parseInt(bf.readLine());
        System.out.println(Service.reverse(userString, first, second));
    }
}