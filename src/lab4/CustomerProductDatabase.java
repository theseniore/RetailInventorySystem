package lab4;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDate;
import java.util.ArrayList;
public class CustomerProductDatabase implements Databases <CustomerProduct> {
    private ArrayList<CustomerProduct> records;
    private String filename;
    public CustomerProductDatabase (String filename)
    {
        this.filename = filename;
        this.records = new ArrayList<>();
    }
    public void readFromFile()
    {
        try{
            Path filePath = Paths.get(System.getProperty("user.dir"), this.filename);
            BufferedReader r = new BufferedReader(new FileReader(filePath.toFile()));
            this.records = new ArrayList<CustomerProduct>();
            ArrayList<CustomerProduct> products = new ArrayList<CustomerProduct>();
            String line;
            CustomerProduct p;
            while((line = r.readLine()) != null)
            {
                p = createRecordFrom(line);
                products.add(p);
            }
            this.records = products;
        }
        catch(IOException e)
        {
            System.out.println("The file cannot be opened!");
            return;
        }
    }
    public CustomerProduct createRecordFrom(String line)
    {
        CustomerProduct p;
        String[] data = line.split(",");
        String customerSSN = data[0];
        String productID = data[1];
        String purchaseDate = data[2];
        String[] temp = data[2].split("-");
        purchaseDate = temp[2] +"-"+ temp[1] +"-"+ temp[0];
        LocalDate date = LocalDate.parse(purchaseDate);
        p = new CustomerProduct(customerSSN, productID, date);
        return p;
    }
    public ArrayList<CustomerProduct> returnAllRecords()
    {
        return this.records==null?this.records:null;
    }
    public boolean contains(String key )
    {
        for(CustomerProduct rec: this.records)
        {
            if(rec.getSearchKey().equals(key))
                return true;
        }
        return false;
    }
    public CustomerProduct getRecord(String key)
    {
        for(CustomerProduct rec: this.records)
        {
            if(rec.getSearchKey().equals(key))
                return rec;
        }
        return null;
    }

    public void insertRecord(CustomerProduct record)
    {
        this.records.add(record);
    }
    public void deleteRecord(String key)
    {
        CustomerProduct p = getRecord(key);
        this.records.remove(p);
    }
    public void saveToFile()
    {
        try {
            Path filePath = Paths.get(System.getProperty("user.dir"), this.filename);
            BufferedWriter w = new BufferedWriter(new FileWriter(filePath.toFile()));
            String line;
            for(CustomerProduct rec : this.records)
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
