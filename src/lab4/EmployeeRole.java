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
        this.customerProductDatabase = new CustomerProductDatabase("CustomersProducts.txt");
    }

    public void addProduct(String productID, String productName, String manufacturerName,
                           String supplierName, int quantity) {
        if (!productsDatabase.contains(productID)) {
            Product newProduct = new Product(productID, productName, manufacturerName,
                    supplierName, quantity, 0.0f);
            productsDatabase.insertRecord(newProduct);
            productsDatabase.saveToFile();
        }
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
        // Check conditions for invalid return
        if (returnDate.isBefore(purchaseDate)) {
            return -1;
        }

        Product product = productsDatabase.getRecord(productID);
        if (product == null) {
            return -1;
        }

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        String searchKey = customerSSN + "," + productID + "," + purchaseDate.format(formatter);

        if (!customerProductDatabase.contains(searchKey)) {
            return -1;
        }

        // Check if return is within 14 days
        long daysBetween = ChronoUnit.DAYS.between(purchaseDate, returnDate);
        if (daysBetween > 14) {
            return -1;
        }

        // Process return
        product.setQuantity(product.getQuantity() + 1);
        customerProductDatabase.deleteRecord(searchKey);

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