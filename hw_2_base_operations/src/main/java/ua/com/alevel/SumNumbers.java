package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;

public class SumNumbers {

    public static void countOfNumbers(BufferedReader reader) throws IOException {
        System.out.print("Enter a string: ");
        System.out.println();
        String str = reader.readLine();
        char[] chars = str.toCharArray();
        int sum=0;
        for(int i = 0; i < chars.length; i++) {
            if ((byte)chars[i] >= 0x30 && (byte)chars[i] <= 0x39)
                sum += (byte)chars[i] - 0x30;
        }
        System.out.println("Sum of all natural numbers is " + sum);
        System.out.println();
        Menu.menu();
    }
}
