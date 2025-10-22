package lab4;

import java.util.ArrayList;

public class AdminRole {

    // Employee database instance
    private EmployeeUserDatabase database;

    // Constructor
    public AdminRole() {
        
        // Initialize employee database
        this.database = new EmployeeUserDatabase("Employees.txt");
        
        // Read data from file
        this.database.readFromFile();
        
    }

    // Method to add employee with all details
    public void addEmployee(String employeeId, String name, String email, String address, String phoneNumber) {
        
        // Check if employee already exists
        if (!database.contains(employeeId)) {
            
            // Create new employee object
            EmployeeUser newEmployee = new EmployeeUser(employeeId, name, email, address, phoneNumber);
            
            // Insert record into database
            database.insertRecord(newEmployee);
            
            // Save changes to file
            database.saveToFile();
            
            System.out.println("Employee added successfully!");
            
        }
        
    }

    // Overloaded method to check if employee exists
    public boolean addEmployee(String employeeeId) {
        
        // Check if employee exists in database
        if(database.contains(employeeeId))
        {
            
            System.out.println("Employee already exists!");
            
            return true;
            
        }
        
        // Return false if employee doesn't exist
        return false;
        
    }

    // Get list of all employees
    public EmployeeUser[] getListOfEmployees() {

        // Return all records as array
        return database.returnAllRecords().toArray(new EmployeeUser[0]);

    }

    // Remove employee method
    public void removeEmployee(String key) {
        
        // Check if employee exists
        if (database.contains(key)) {
            
            // Delete employee record
            database.deleteRecord(key);
            
            // Save changes to file
            database.saveToFile();
            
            System.out.println("employee removed successfully");
            
        }else{
            
            System.out.println("employee not found");
            
        }
        
    }

    // Logout method
    public void logout() {
        
        // Save database to file
        database.saveToFile();
        
    }
    
}