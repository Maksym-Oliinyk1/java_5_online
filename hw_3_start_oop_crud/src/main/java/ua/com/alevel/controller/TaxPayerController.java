package ua.com.alevel.controller;

import ua.com.alevel.entity.TaxPayer;
import ua.com.alevel.service.TaxPayerService;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

// service class
public class TaxPayerController {

    private TaxPayerService taxPayerService = new TaxPayerService();

    public void start() throws IOException {
        BufferedReader bf = new BufferedReader(new InputStreamReader(System.in));
        System.out.println("Welcome to the your first crud application");
        System.out.println("Select options:");
        String select;
        menu();
        while ((select = bf.readLine()) != null) {
            crud(bf, select);
        }
    }

    private void menu() {
        System.out.println();
        System.out.println("If you want create tax payer, please enter 1");
        System.out.println("If you want update tax payer, please enter 2");
        System.out.println("If you want delete tax payer, please enter 3");
        System.out.println("If you want find tax payer, please enter 4");
        System.out.println("If you want find all tax payers, please enter 5");
        System.out.println("If you want close, please enter 6");
    }

    private void crud(BufferedReader reader, String select) throws IOException {
        switch (select) {
            case "1" -> create(reader);
            case "2" -> update(reader);
            case "3" -> delete(reader);
            case "4" -> findById(reader);
            case "5" -> findAll();
            case "6" -> System.exit(0);
        }
        menu();
    }

    private void create(BufferedReader reader) throws IOException {
        System.out.println("Please enter the first name:");
        String firstName = reader.readLine();
        System.out.println("Please enter the last name:");
        String lastName = reader.readLine();
        System.out.println("Please enter your tax");
        String stringTax = reader.readLine();
        double tax = Double.parseDouble(stringTax);
        TaxPayer taxPayer = new TaxPayer();
        taxPayer.setFirstName(firstName);
        taxPayer.setLastName(lastName);
        taxPayer.setTax(tax);
        taxPayerService.create(taxPayer);
    }

    private void update(BufferedReader reader) throws IOException {
        System.out.println("Enter id");
        String id = reader.readLine();
        System.out.println("Enter the new first name");
        String firstName = reader.readLine();
        System.out.println("Enter the new last name");
        String lastName = reader.readLine();
        System.out.println("Enter the new tax");
        String stringTax = reader.readLine();
        double tax = Double.parseDouble(stringTax);
        TaxPayer taxPayer = taxPayerService.findById(id);
        taxPayer.setFirstName(firstName);
        taxPayer.setLastName(lastName);
        taxPayer.setTax(tax);
        taxPayerService.update(taxPayer);
    }

    private void delete(BufferedReader reader) throws IOException {
        System.out.println("Enter ID of tax payer: ");
        String id = reader.readLine();
        TaxPayer taxPayer = taxPayerService.delete(id);
        System.out.println("Tax payer was removed!");

    }

    private void findById(BufferedReader reader) throws IOException {
        System.out.println("Please enter the id:");
        String id = reader.readLine();
        TaxPayer taxPayer = taxPayerService.findById(id);
        System.out.println("Tax Payer = " + taxPayer);
    }

    private void findAll() {
        TaxPayer[] taxPayers = taxPayerService.findAll();
        for (TaxPayer taxPayer : taxPayers) {
            System.out.println("Tax Payers = " + taxPayer);
        }
    }
}
