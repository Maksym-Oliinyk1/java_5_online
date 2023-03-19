package ua.com.alevel;

public class Service {

    public static String reverse(String src) {
        String result = "";
        for (int i = 0; i < src.length(); i++) {
            result = src.charAt(i) + result;
        }
        return result;
    }

    public static String reverse(String src, String dest) {
        int index = src.indexOf(dest);
        if (index > 0) {
            String newSrc = reverse(dest);
            return src.replace(dest, newSrc);
        }
        return ("Error.Wrong data");
    }

    public static String reverse(String src, int first, int second) {
        if (first < second && first >= 0 && second <= src.length()) {
            String prevReverse = src.substring(first, second);
            String newSrc = reverse(prevReverse);
            return src.replace(prevReverse, newSrc);
        }
        return ("Error. Wrong data");
    }
}

