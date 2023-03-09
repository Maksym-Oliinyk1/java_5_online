package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;

public class Service {
    public static void ordinaryReverse(BufferedReader bf) throws IOException {
        System.out.println("Enter the string:");
        String str = bf.readLine();
        String result = "";
        for (int i = 0; i < str.length(); i++){
            result = str.charAt(i) + result;
        }
        System.out.println("Your string: " + result);
    }
    }

