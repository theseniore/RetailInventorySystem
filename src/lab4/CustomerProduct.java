package lab4;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class CustomerProduct {
    private String customerSSN;
    private String productID;
    private LocalDate purchaseDate;
    private boolean paid;

    public CustomerProduct(String customerSSN, String productID, LocalDate
            purchaseDate){
        this.customerSSN = customerSSN;
        this.productID = productID;
        this.purchaseDate = purchaseDate;
    }
    public String getCustomerSSN() {
        return customerSSN;
    }
    public String getProductID(){
        return productID;
    }
    public LocalDate getPurchaseDate(){
        return purchaseDate;
    }
    public boolean isPaid(){
        return paid;
    }
    public void setPaid(boolean paid){
        this.paid = paid;
    }
    public String lineRepresentation(){
        StringBuilder Date = new StringBuilder(purchaseDate.toString());
        Date.reverse();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        return customerSSN+","+productID+","+Date+","+paid;
    }

    public String getSearchKey(){
        return customerSSN;
    }


}

