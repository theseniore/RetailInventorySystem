package lab4;

import java.util.ArrayList;

public interface Databases <T>{
    public void readFromFile();
    T createRecordFrom(String line);
    ArrayList<T> returnAllRecords();
    boolean contains(String key);
    T getRecord(String key);
    void insertRecord(T record);
    void deleteRecord(String key);
    void saveToFile();
}
