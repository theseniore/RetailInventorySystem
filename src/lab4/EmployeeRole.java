package lab4;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;


public class EmployeeRole {
    private ProductDatabase productsDatabase;
    private CustomerProductDatabase customerProductDatabase;

    public EmployeeRole() {
        this.productsDatabase = new ProductDatabase("Products.txt");
        this.productsDatabase.readFromFile();
        this.customerProductDatabase = new CustomerProductDatabase("CustomersProducts.txt");
        this.customerProductDatabase.readFromFile();
    }

    public void addProduct(String productID, String productName, String manufacturerName,
                           String supplierName, int quantity,float price) {
        if (!productsDatabase.contains(productID)) {
            Product newProduct = new Product(productID, productName, manufacturerName,
                    supplierName, quantity, price);
            productsDatabase.insertRecord(newProduct);
            productsDatabase.saveToFile();
            System.out.println("product added successfully!");
        }else{
            System.out.println("product id already exists");
        }
    }
    public boolean addProduct(String productId) {
        if (productsDatabase.contains(productId)) {
            System.out.println("product id already exists");
            return true;
        }
        return false;
    }

    public Product[] getListOfProducts() {
        return productsDatabase.returnAllRecords().toArray(new Product[0]);
    }

    public CustomerProduct[] getListOfPurchasingOperations() {
        return customerProductDatabase.returnAllRecords().toArray(new CustomerProduct[0]);
    }

    public boolean purchaseProduct(String customerSSN, String productID, LocalDate purchaseDate) {
        Product product = productsDatabase.getRecord(productID);

        if (product == null || product.getQuantity() == 0) {
            return false;
        }

        // Decrease quantity
        product.setQuantity(product.getQuantity() - 1);

        // Add purchasing operation
        CustomerProduct purchase = new CustomerProduct(customerSSN, productID, purchaseDate);
        customerProductDatabase.insertRecord(purchase);

        // Save changes
        productsDatabase.saveToFile();
        customerProductDatabase.saveToFile();

        return true;
    }

    public double returnProduct(String customerSSN, String productID, LocalDate purchaseDate, LocalDate returnDate) {
        CustomerProduct t= customerProductDatabase.getRecord(customerSSN);

        // Check conditions for invalid return
        if (returnDate.isBefore(purchaseDate)) {
            return -1;
        }
        // Invalid Product
        Product product = productsDatabase.getRecord(productID);
        if (product == null||!t.getProductID().equals(productID)) {
            return -1;
        }

        //DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
       // String searchKey = customerSSN + "," + productID + "," + purchaseDate.format(formatter);
        if (!customerProductDatabase.contains(customerSSN)) {
            return -1;
        }
        if(!t.isPaid()){
            return -1;
        }

        // Check if return is within 14 days
        long daysBetween = ChronoUnit.DAYS.between(purchaseDate, returnDate);
        if (daysBetween > 14) {
            return -1;
        }

        // Process return
        product.setQuantity(product.getQuantity() + 1);
        customerProductDatabase.deleteRecord(customerSSN);

        // Save changes
        productsDatabase.saveToFile();
        customerProductDatabase.saveToFile();

        return product.getPrice();
    }

    public boolean applyPayment(String customerSSN, LocalDate purchaseDate) {

        CustomerProduct[] allRecords = customerProductDatabase.returnAllRecords().toArray(new CustomerProduct[0]);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        for (int i = 0; i < allRecords.length; i++) {
            CustomerProduct record = allRecords[i];
            if (record.getCustomerSSN().equals(customerSSN) &&
                    record.getPurchaseDate().equals(purchaseDate) &&
                    !record.isPaid()) {
                record.setPaid(true);
                customerProductDatabase.saveToFile();
                return true;
            }
        }
        return false;
    }

    public void logout() {
        productsDatabase.saveToFile();
        customerProductDatabase.saveToFile();
    }
}