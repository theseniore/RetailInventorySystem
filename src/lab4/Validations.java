package lab4;

public class Validations {



    public static boolean emailValid(String email){
        return email != null && email.matches("^[\\w.-]+@[\\w.-]+\\.\\w+$");
    }
    public static boolean phoneValid(String phone){
        return phone != null && phone.matches("\\d{11}");
    }
    public static boolean ssnValid(String ssn){
        return ssn != null && ssn.matches("\\d{14}");
    }
    public static boolean nameValid(String name){
        return name != null && name.matches("[a-zA-Z ]+");
    }
    public static boolean quantityValid(int quantity){
        return quantity>=0;
    }
    public static boolean priceValidation(float price) { return price>=0.0; }

    public static boolean existID(String ID){
        EmployeeUserDatabase t=new EmployeeUserDatabase("Employees.txt");
        t.readFromFile();

        return t.contains(ID);
    }
}

