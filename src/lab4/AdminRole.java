package lab4;

import java.util.ArrayList;

public class AdminRole {
    private EmployeeUserDatabase database;

    public AdminRole() {
        this.database = new EmployeeUserDatabase("Employees.txt");
    }

    public void addEmployee(String employeeId, String name, String email, String address, String phoneNumber) {
        if (!database.contains(employeeId)) {
            EmployeeUser newEmployee = new EmployeeUser(employeeId, name, email, address, phoneNumber);
            database.insertRecord(newEmployee);
            database.saveToFile();
        }
    }

    public ArrayList<EmployeeUser> getListOfEmployees() {
        return database.returnAllRecords();
    }

    public void removeEmployee(String key) {
        if (database.contains(key)) {
            database.deleteRecord(key);
            database.saveToFile();
        }
    }

    public void logout() {
        database.saveToFile();
    }
}