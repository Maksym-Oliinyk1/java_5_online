package ua.com.alevel;

import ua.com.alevel.controller.TaxPayerController;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws IOException {
        System.out.println("Create Reade Update Delete");
        TaxPayerController taxPayerController = new TaxPayerController();
        taxPayerController.start();
    }
}