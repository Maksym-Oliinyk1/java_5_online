package ua.com.alevel;

import java.io.BufferedReader;
import java.io.IOException;

public class SumCharacters {

    static final int MAX_CHAR = 256;

    static void countOfCharacters(BufferedReader reader) throws IOException {
        // Create an array of size 256
        // i.e. ASCII_SIZE
        System.out.println("Enter your string: ");
        String str = reader.readLine();
        int count[] = new int[MAX_CHAR];

        int len = str.length();

        // Initialize count array index
        for (int i = 0; i < len; i++) {
                count[str.charAt(i)]++;
            }
        // Create an array of given String size
        char ch[] = new char[str.length()];
        for (int i = 0; i < len; i++) {
                ch[i] = str.charAt(i);
                int find = 0;
                for (int j = 0; j <= i; j++) {

                    // If any matches found
                    if (Character.isLetter(ch[i])) {
                        if (str.charAt(i) == ch[j])
                        find++;
                    }
                }
                if (find == 1)
                    System.out.println(
                            "Number of characters  "
                                    + str.charAt(i)
                                    + " is: " + count[str.charAt(i)]);
        }
        System.out.println();
        Menu.menu();
    }
}



