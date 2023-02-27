package ua.com.alevel.service;

import ua.com.alevel.entity.TaxPayer;

import java.util.UUID;

// service class
public class TaxPayerService {

    private TaxPayer[] taxPayers = new TaxPayer[10];

    public void create(TaxPayer taxPayer) {
        taxPayer.setId(generateId());
        for (int i = 0; i < taxPayers.length; i++) {
            if (taxPayers[i] == null) {
                taxPayers[i] = taxPayer;
                break;
            }
        }
    }

    public void update(TaxPayer student) {}

    public void delete(String id) {}

    public TaxPayer findById(String id) {
        for (TaxPayer student : taxPayers) {
            if (student.getId().equals(id)) {
                return student;
            }
        }
        return null;
    }

    public TaxPayer[] findAll() {
        return taxPayers;
    }

    private String generateId() {
        String id = UUID.randomUUID().toString();
        for (TaxPayer taxPayer : taxPayers) {
            if (taxPayer != null && taxPayer.getId().equals(id)) {
                return generateId();
            }
        }
        return id;
    }
}