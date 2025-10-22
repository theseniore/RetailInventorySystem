package lab4;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;


public class EmployeeRole {

    // Database for products
    private ProductDatabase productsDatabase;
    
    // Database for customer products
    private CustomerProductDatabase customerProductDatabase;

    // Constructor
    public EmployeeRole() {
        
        // Initialize product database
        this.productsDatabase = new ProductDatabase("Products.txt");
        
        // Read data from file
        this.productsDatabase.readFromFile();
        
        // Initialize customer product database
        this.customerProductDatabase = new CustomerProductDatabase("CustomersProducts.txt");
        
        // Read data from file
        this.customerProductDatabase.readFromFile();
        
    }

    // Method to add product with all details
    public void addProduct(String productID, String productName, String manufacturerName,
                           String supplierName, int quantity,float price) {
        
        // Check if product already exists
        if (!productsDatabase.contains(productID)) {
            
            // Create new product
            Product newProduct = new Product(productID, productName, manufacturerName,
                    supplierName, quantity, price);
            
            // Insert record
            productsDatabase.insertRecord(newProduct);
            
            // Save to file
            productsDatabase.saveToFile();
            
            System.out.println("product added successfully!");
            
        }else{
            
            System.out.println("product id already exists");
            
        }
        
    }

    // Overloaded method to check if product exists
    public boolean addProduct(String productId) {
        
        // Check if product exists
        if (productsDatabase.contains(productId)) {
            
            System.out.println("product id already exists");
            
            return true;
            
        }
        
        return false;
        
    }

    // Get list of all products
    public Product[] getListOfProducts() {
        
        return productsDatabase.returnAllRecords().toArray(new Product[0]);
        
    }

    // Get list of all purchasing operations
    public CustomerProduct[] getListOfPurchasingOperations() {
        
        return customerProductDatabase.returnAllRecords().toArray(new CustomerProduct[0]);
        
    }

    // Purchase product method
    public boolean purchaseProduct(String customerSSN, String productID, LocalDate purchaseDate) {
        
        // Get product from database
        Product product = productsDatabase.getRecord(productID);

        // Check if product exists and has quantity
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

    // Return product method
    public double returnProduct(String customerSSN, String productID, LocalDate purchaseDate, LocalDate returnDate) {
        
        // Get customer product record
        CustomerProduct t= customerProductDatabase.getRecord(customerSSN);

        // Check conditions for invalid return
        if (returnDate.isBefore(purchaseDate)) {
            
            return -1;
            
        }
        
        // Invalid Product check
        Product product = productsDatabase.getRecord(productID);
        
        if (product == null||!t.getProductID().equals(productID)) {
            
            return -1;
            
        }

        // Check if record exists
        if (!customerProductDatabase.contains(customerSSN)) {
            
            return -1;
            
        }
        
        // Check if product is paid
        if(!t.isPaid()){
            
            return -1;
            
        }

        // Check if return is within 14 days
        long daysBetween = ChronoUnit.DAYS.between(purchaseDate, returnDate);
        
        if (daysBetween > 14) {
            
            return -1;
            
        }

        // Process return - increase quantity
        product.setQuantity(product.getQuantity() + 1);
        
        // Delete record
        customerProductDatabase.deleteRecord(customerSSN);

        // Save changes
        productsDatabase.saveToFile();
        
        customerProductDatabase.saveToFile();

        // Return product price
        return product.getPrice();
        
    }

    // Apply payment method
    public boolean applyPayment(String customerSSN, LocalDate purchaseDate) {

        // Get all records
        CustomerProduct[] allRecords = customerProductDatabase.returnAllRecords().toArray(new CustomerProduct[0]);
        
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        // Loop through records
        for (int i = 0; i < allRecords.length; i++) {
            
            CustomerProduct record = allRecords[i];
            
            // Check if record matches
            if (record.getCustomerSSN().equals(customerSSN) &&
                    record.getPurchaseDate().equals(purchaseDate) &&
                    !record.isPaid()) {
                
                // Set as paid
                record.setPaid(true);
                
                // Save to file
                customerProductDatabase.saveToFile();
                
                return true;
                
            }
            
        }
        
        return false;
        
    }

    // Logout method
    public void logout() {
        
        // Save both databases to file
        productsDatabase.saveToFile();
        
        customerProductDatabase.saveToFile();
        
    }
    
}