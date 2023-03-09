package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import ua.com.alevel.Service;

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
        System.out.println("If you want all types of reverse + choose from which to which symbol to reverse, please enter 3");
        System.out.println("If you want close, please enter 0");
    }

    private void navigation(BufferedReader bf, String select) throws IOException {
        switch (select) {
            case "1" -> Service.ordinaryReverse(bf);
//            case "2" -> Service.substringReverse(bf);
//            case "3" -> Service.supReverse(bf);
            case "0" -> System.exit(0);
        }
    }
}