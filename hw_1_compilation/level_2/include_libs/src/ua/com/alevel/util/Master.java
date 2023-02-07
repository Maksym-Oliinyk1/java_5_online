package ua.com.alevel.util;

import org.apache.commons.lang3.StringUtils;

public class Master {
    public void printMessage(String message) {
        System.out.println("message = " + message);
        System.out.println("message = " + StringUtils.upperCase(message));
    }
}