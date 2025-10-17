package lab4;

import java.util.Scanner;

public class Menu {
    public static void mainMenu()
    {
        System.out.println("Welcome to retail managment system!");
        System.out.println("For Admins enter 1\nFor employees enter 2\nTo exit enter 3");
        Scanner in = new Scanner(System.in);
        int n=0;
        while (true){
            System.out.println("enter an integer");
        if (in.hasNextInt()){
n=in.nextInt();
break;
        }
        }
        switch(n)
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
