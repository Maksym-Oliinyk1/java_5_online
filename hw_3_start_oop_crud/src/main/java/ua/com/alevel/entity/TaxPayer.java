package ua.com.alevel.entity;

public class TaxPayer extends BaseEntity {
    private String firstName;
    private String lastName;
    private double tax;

    public void setFirstName(String firstName){
        this.firstName = firstName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }
    public void setTax(double amountOfTax){
        this.tax = amountOfTax;
    }
    public String getName(String firstName, String lastName) {
        return this.firstName + " " + this.lastName;
    }
    public double getTax() {
        return tax;
    }
    @Override
    public String toString() {
        return "Tax Payer{" +
                "First Name = '" + firstName + '\'' +
                ", Last Name = '" + lastName + '\'' +
                ",Tax = " + tax +
                ", ID = '" + getId() + '\'' +
                "} " + super.toString();
    }
}

