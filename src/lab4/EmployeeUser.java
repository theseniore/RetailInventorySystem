package lab4;

public class EmployeeUser {
    private String Name;
    private String employeeId;
    private String Email;
    private String Adress;
    private String PhoneNumber;



    public EmployeeUser(String employeeId, String name, String email, String address, String phoneNumber){
        this.employeeId = employeeId;
        this.Name = name;
        this.Email = email;
        this.Adress = address;
        this.PhoneNumber = phoneNumber;
    }
    public String lineRepresentation(){
        return employeeId+","+Name+","+Email+","+Adress+","+PhoneNumber;
    }
    public String getSearchKey(){
        return employeeId;
    }

    //Setters and Getters
    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getEmployeeId() {
        return employeeId;
    }

    public void setEmployeeId(String employeeId) {
        this.employeeId = employeeId;
    }

    public String getEmail() {
        return Email;
    }

    public void setEmail(String email) {
        Email = email;
    }

    public String getAdress() {
        return Adress;
    }

    public void setAdress(String adress) {
        Adress = adress;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }


}
