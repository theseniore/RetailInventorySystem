package lab4;

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
                System.out.println("Exting:");
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
    {

    }

}
