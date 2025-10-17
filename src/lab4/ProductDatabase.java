package lab4;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
public class ProductDatabase implements Databases <Product>{
    private ArrayList<Product> records;
    private String filename;
    public ProductDatabase (String filename)
    {
        this.filename = filename;
        this.records = new ArrayList<>();
    }
    public void readFromFile()
    {
        try{
            Path filePath = Paths.get(System.getProperty("user.dir"), this.filename);
            BufferedReader r = new BufferedReader(new FileReader(filePath.toFile()));
            this.records = new ArrayList<Product>();
            ArrayList<Product> products = new ArrayList<Product>();
            String line;
            Product p;
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
    public Product createRecordFrom(String line)
    {
        Product p;
        String[] data = line.split(",");
        String productID = data[0];
        String productName = data[1];
        String manufacturerName = data[2];
        String supplierName = data[3];
        int quantity = Integer.parseInt(data[4]);
        float price = Float.parseFloat(data[5]);
        p = new Product(productID,productName,manufacturerName,supplierName,quantity,price);
        return p;
    }
    public ArrayList<Product> returnAllRecords()
    {
        return this.records==null?new ArrayList<>():this.records;
    }
    public boolean contains(String key )
    {
        for(Product rec: this.records)
        {
            if(rec.getSearchKey() == key)
                return true;
        }
        return false;
    }
    public Product getRecord(String key)
    {
        for(Product rec: this.records)
        {
            if(rec.getSearchKey() == key)
                return rec;
        }
        return null;
    }
    public void insertRecord(Product record)
    {
        this.records.add(record);
    }
    public void deleteRecord(String key)
    {
        Product p = getRecord(key);
        this.records.remove(p);
    }
    public void saveToFile()
    {
        try {
            Path filePath = Paths.get(System.getProperty("user.dir"), this.filename);
            BufferedWriter w = new BufferedWriter(new FileWriter(filePath.toFile()));
            String line;
            for(Product rec : this.records)
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

