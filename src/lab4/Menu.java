package lab4;

public class Menu {
    public static void mainMenu()
    {
        System.out.println("Welcome to retail managment system!");
        System.out.println("For Admins enter 1\nFor employees enter 2\nTo exit enter 3");
        // switch case for admin and employees
        switch()
        {
            case 1:
                adminMenu();
                break;
            case 2:
                employeeMenu();
                break;
            case 3:
                return;
            default:
                break;
        }

    }
    public static void adminMenu()
    {

    }
    public static void employeeMenu()
    {

    }

}
