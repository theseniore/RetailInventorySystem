package lab4;

import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;

public class EmployeeUserDatabase implements Databases<EmployeeUser> {
private ArrayList<EmployeeUser> records;
private String filename;
public EmployeeUserDatabase(String filename){
    this.filename=filename;
    this.records= new ArrayList<>();
}
public void readFromFile(){
    try {
        Path filePath = Paths.get(System.getProperty("user.dir"), this.filename);
        BufferedReader r = new BufferedReader(new FileReader(filePath.toFile()));
        this.records = new ArrayList<EmployeeUser>();
        ArrayList<EmployeeUser> employeeUsers = new ArrayList<EmployeeUser>();
        String line;
        EmployeeUser e;
        while((line = r.readLine()) != null)
        {
            e = createRecordFrom(line);
            employeeUsers.add(e);
        }
        this.records = employeeUsers;

    }
    catch (IOException e){
        System.out.println("this file cannot be opened!");
        return;
    }
}

    public EmployeeUser createRecordFrom(String line) {
        EmployeeUser e;
        String[] data = line.split(",");
        String employeeId=data[0];
        String Name = data[1];
        String Email = data[2];
        String Address = data[3];
        String PhoneNumber = data[4];
        e = new EmployeeUser(employeeId,Name,Email,Address,PhoneNumber);
        return e;
    }
    public ArrayList<EmployeeUser> returnAllRecords(){
    return this.records==null?new ArrayList<>():this.records;
    }
public boolean contains(String key){

    for (EmployeeUser rec : this.records){
        if (rec.getSearchKey().equals(key))
            return true;
    }
    return false;
}
public EmployeeUser getRecord(String key){
    for (EmployeeUser rec : this.records){
        if (rec.getSearchKey().equals(key))
            return rec;
    }
    return null;
}
public void insertRecord(EmployeeUser record){
    this.records.add(record);
}
public void deleteRecord(String key){
    EmployeeUser e = getRecord(key);
    this.records.remove(e);
}
    public void saveToFile()
    {
        try {
            Path filePath = Paths.get(System.getProperty("user.dir"), this.filename);
            BufferedWriter w = new BufferedWriter(new FileWriter(filePath.toFile()));
            String line;
            for(EmployeeUser rec : this.records)
            {
                line = rec.lineRepresentation();
                w.write(line);
                w.newLine();
            }
        }
        catch(IOException e)
        {
            System.out.println("There is an error while writing in file!");
            return;
        }
    }
}
