package lab4;

import java.time.LocalDate;
import java.util.Scanner;

public class Menu {
    public static void mainMenu()
    {
        Scanner sc = new Scanner(System.in);

        boolean Exsit = false;
        while (!Exsit){
            System.out.println("===========================");
            System.out.println("welcome to main menu");
            System.out.println("===========================");
            System.out.println("1.Admin login");
            System.out.println("2.Employee login");
            System.out.println("3.Exit");
            System.out.println("===========================");
            System.out.println("enter your choice:");
            try {


            String choice = sc.nextLine();
            switch (Integer.parseInt(choice)){
            case 1: adminMenu();
            break;
            case 2: employeeMenu();
            break;
            case 3: Exsit=true;
                System.out.println("Exiting:");
            break;
            default:
                System.out.println("invalid number try again");
                break;

            }}catch (NumberFormatException e){
                System.out.println("invalid input please try to enter a number");
            }
        }
    }
    public static void adminMenu() {
        boolean exit = false;
        AdminRole admin = new AdminRole();
        Scanner sc = new Scanner(System.in);

        while (!exit) {
            System.out.println("===========================");
            System.out.println("Welcome to Admin Menu");
            System.out.println("===========================");
            System.out.println("1. Add Employee");
            System.out.println("2. List of Employees");
            System.out.println("3. Remove Employee");
            System.out.println("4. Log out");
            System.out.println("===========================");
            System.out.print("Enter your choice: ");

            try {
                String choice = sc.nextLine();
                switch (Integer.parseInt(choice)) {
                    case 1:
                        String id, name, email, address, phone;

                        do {
                            System.out.print("enter employee ID: ");
                            id = sc.nextLine();
                            if (id.isEmpty()) System.out.println("Invalid ID try again.");
                        } while (id.isEmpty());

                        do {
                            System.out.print("enter name: ");
                            name = sc.nextLine();
                            if (!Validations.nameValid(name))
                                System.out.println("Invalid name try again.");
                        } while (!Validations.nameValid(name));

                        do {
                            System.out.print("enter email: ");
                            email = sc.nextLine();
                            if (!Validations.emailValid(email))
                                System.out.println("Invalid email try again.");
                        } while (!Validations.emailValid(email));

                        do {
                            System.out.print("enter Address: ");
                            address = sc.nextLine();
                            if (address.isEmpty())
                                System.out.println("Invalid address try again.");
                        } while (address.isEmpty());

                        do {
                            System.out.print("enter Phone Number: ");
                            phone = sc.nextLine();
                            if (!Validations.phoneValid(phone))
                                System.out.println("Invalid phone try again.");
                        } while (!Validations.phoneValid(phone));

                        admin.addEmployee(id, name, email, address, phone);
                        System.out.println("Employee added successfully!");
                        break;

                    case 2:
                        EmployeeUser[] employees = admin.getListOfEmployees();
                        System.out.println("List of Employees:");
                        for (EmployeeUser emp : employees) {
                            System.out.println(emp.lineRepresentation());
                        }
                        break;

                    case 3:
                        System.out.print("Enter Employee ID to remove: ");
                        String removeId = sc.nextLine();
                        if (admin.getListOfEmployees().length == 0) {
                            System.out.println("No employees to remove.");
                        } else {
                            admin.removeEmployee(removeId);
                            System.out.println("employee removed successfully");
                        }
                        break;

                    case 4:
                        exit = true;
                        admin.logout();
                        System.out.println("logged out from Admin Menu.");
                        break;

                    default:
                        System.out.println("Invalid choice try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input Please enter a number.");
            }
        }
    }

    public static void employeeMenu()
    { boolean exit = false;
        EmployeeRole employee = new EmployeeRole();
        Scanner sc = new Scanner(System.in);

        while (!exit) {
            System.out.println("===========================");
            System.out.println("welcome to Employee Menu");
            System.out.println("===========================");
            System.out.println("1. add Product");
            System.out.println("2. list of Products");
            System.out.println("3. purchase Product");
            System.out.println("4. return Product");
            System.out.println("5. apply Payment");
            System.out.println("6. log out");
            System.out.println("===========================");
            System.out.print("enter your choice: ");

            try {
                String choice = sc.nextLine();
                switch (Integer.parseInt(choice)) {
                    case 1:
                        String productID, productName, manufacturerName, supplierName;
                        int quantity;

                        do {
                            System.out.print("enter product ID: ");
                            productID = sc.nextLine();
                            if (productID.isEmpty()) System.out.println("Invalid ID try again.");
                        } while (productID.isEmpty());

                        do {
                            System.out.print("enter product name: ");
                            productName = sc.nextLine();
                            if (productName.isEmpty()) System.out.println("Invalid name try again.");
                        } while (productName.isEmpty());

                        do {
                            System.out.print("enter manufacturer name: ");
                            manufacturerName = sc.nextLine();
                            if (manufacturerName.isEmpty()) System.out.println("Invalid name try again.");
                        } while (manufacturerName.isEmpty());

                        do {
                            System.out.print("enter supplier name: ");
                            supplierName = sc.nextLine();
                            if (supplierName.isEmpty()) System.out.println("Invalid name try again.");
                        } while (supplierName.isEmpty());

                        do {
                            System.out.print("enter quantity: ");
                            quantity = Integer.parseInt(sc.nextLine());
                            if (!Validations.quantityValid(quantity))
                                System.out.println("Invalid quantity must be >=0.");
                        } while (!Validations.quantityValid(quantity));

                        employee.addProduct(productID, productName, manufacturerName, supplierName, quantity);
                        System.out.println("product added successfully");
                        break;

                    case 2:
                        Product[] products = employee.getListOfProducts();
                        System.out.println("list of Products:");
                        for (Product p : products) {
                            System.out.println(p.lineRepresentation());
                        }
                        break;

                    case 3:
                        System.out.print("enter customer SSN: ");
                        String customerSSN = sc.nextLine();
                        System.out.print("enter product ID to purchase: ");
                        String purchaseProductID = sc.nextLine();
                        LocalDate purchaseDate = LocalDate.now();

                        boolean purchased = employee.purchaseProduct(customerSSN, purchaseProductID, purchaseDate);
                        if (purchased)
                            System.out.println("product purchased successfully!");
                        else
                            System.out.println("product not available or invalid ID.");
                        break;

                    case 4:
                        System.out.print("enter customer SSN: ");
                        String returnSSN = sc.nextLine();
                        System.out.print("enter product ID to return: ");
                        String returnProductID = sc.nextLine();
                        LocalDate purchaseDateReturn = LocalDate.now();
                        LocalDate returnDate = LocalDate.now();

                        double refunded = employee.returnProduct(returnSSN, returnProductID, purchaseDateReturn, returnDate);
                        if (refunded >= 0)
                            System.out.println("product returned successfully Refund: " + refunded);
                        else
                            System.out.println("Invalid return (check date or product).");
                        break;

                    case 5:
                        System.out.print("enter customer SSN: ");
                        String paySSN = sc.nextLine();
                        LocalDate payDate = LocalDate.now();

                        boolean paid = employee.applyPayment(paySSN, payDate);
                        if (paid)
                            System.out.println("Payment applied successfully!");
                        else
                            System.out.println("No unpaid record found for this customer.");
                        break;

                    case 6:
                        exit = true;
                        employee.logout();
                        System.out.println("Logged out from Employee Menu.");
                        break;

                    default:
                        System.out.println("Invalid choice try again.");
                }
            } catch (NumberFormatException e) {
                System.out.println("Invalid input please enter a number.");
            }
        }

    }

}
