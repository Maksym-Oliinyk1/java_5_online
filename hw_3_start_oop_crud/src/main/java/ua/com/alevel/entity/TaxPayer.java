package ua.com.alevel.entity;

public class TaxPayer extends BaseEntity {
    private String firstName;
    private String lastName;
    private double amountOfTax;

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setAmountOfTax(double amountOfTax){
        this.amountOfTax = amountOfTax;
    }
    public String getName(String firstName, String lastName) {
        return this.firstName + " " + this.lastName;
    }
    public double getTax(double amountOfTax) {
        return this.amountOfTax;
    }
}

