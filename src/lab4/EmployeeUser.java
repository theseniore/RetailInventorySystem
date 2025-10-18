package lab4;

public class EmployeeUser {
    private String Name;
    private String employeeId;
    private String Email;
    private String Address;
    private String PhoneNumber;



    public EmployeeUser(String employeeId, String name, String email, String Address, String phoneNumber){
        this.employeeId = employeeId;
        this.Name = name;
        this.Email = email;
        this.Address = Address;
        this.PhoneNumber = phoneNumber;
    }
    public EmployeeUser(String employeeId){
        this.employeeId = employeeId;
    }

    public String lineRepresentation(){
        return employeeId+","+Name+","+Email+","+Address+","+PhoneNumber;
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

    public String getAddress() {
        return Address;
    }

    public void setAddress(String address) {
        Address = address;
    }

    public String getPhoneNumber() {
        return PhoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        PhoneNumber = phoneNumber;
    }


}
