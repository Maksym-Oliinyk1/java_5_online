package ua.com.alevel.util;

import org.apache.commons.lang3.StringUtils;

import static org.apache.commons.lang3;
public class Master {

    public static void showMessage(String mstr) {
        System.out.println("message - " + StringUtils.upperCase(mstr));
    }
}