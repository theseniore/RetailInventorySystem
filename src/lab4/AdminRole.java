package lab4;

import java.util.ArrayList;

public class AdminRole {
    private EmployeeUserDatabase database;

    public AdminRole() {
        this.database = new EmployeeUserDatabase("Employees.txt");
        this.database.readFromFile();
    }

    public void addEmployee(String employeeId, String name, String email, String address, String phoneNumber) {
        if (!database.contains(employeeId)) {
            EmployeeUser newEmployee = new EmployeeUser(employeeId, name, email, address, phoneNumber);
            database.insertRecord(newEmployee);
            database.saveToFile();
            System.out.println("Employee added successfully!");
        }
    }
    public boolean addEmployee(String employeeeId) {
        if(database.contains(employeeeId))
        {
            System.out.println("Employee already exists!");
            return true;
        }
            return false;
        }


    public EmployeeUser[] getListOfEmployees() {

        return database.returnAllRecords().toArray(new EmployeeUser[0]);

    }

    public void removeEmployee(String key) {
        if (database.contains(key)) {
            database.deleteRecord(key);
            database.saveToFile();
            System.out.println("employee removed successfully");
        }else{
            System.out.println("employee not found");
        }
    }

    public void logout() {
        database.saveToFile();
    }
}