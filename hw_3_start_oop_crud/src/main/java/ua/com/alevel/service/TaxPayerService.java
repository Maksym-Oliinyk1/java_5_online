package ua.com.alevel.service;

import ua.com.alevel.entity.TaxPayer;

import java.util.UUID;

// service class
public class TaxPayerService {
    private TaxPayer[] taxPayers = new TaxPayer[10];
    private int payerCount;

    public TaxPayerService() {
    }

    public void create(TaxPayer taxPayer) {
        taxPayer.setId(generateId());
        increaseSize();
        for (int i = 0; i < taxPayers.length; i++) {
            if (taxPayers[i] == null) {
                taxPayers[i] = taxPayer;
                break;
            }
            payerCount++;
        }
    }

    public void update(TaxPayer taxPayer) {
        for (int i = 0; i < taxPayers.length; i++) {
            try {
                if (taxPayers[i].getId().equals(taxPayer.getId())){
                    taxPayers[i] = taxPayer;
                }
            } catch (Exception e) {
                i++;
            }
        }
    }

    public TaxPayer delete(String id) {
        for (int i = 0; i < taxPayers.length; i++){
            try {
                if (taxPayers[i].equals(taxPayers[Integer.parseInt(id)])){
                    taxPayers[i] = null;
                }
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return null;
    }

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

    public void increaseSize() {
        if (payerCount == taxPayers.length) {
            TaxPayer[] taxPayers1 = new TaxPayer[(taxPayers.length + 10)];
            for (int i = 0; i < taxPayers.length; i++){
                taxPayers1[i] = taxPayers[i];
            }
            this.taxPayers = taxPayers1;
        }
    }
}