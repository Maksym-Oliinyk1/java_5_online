package ua.com.alevel;

import ua.com.alevel.service.Service;

public class Main {
    public static void main(String[] args) {
        Service service = new Service();
        try {
            service.start();
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}