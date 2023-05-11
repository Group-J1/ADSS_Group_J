package Resource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.*;
import java.util.Arrays;

public class Connect {
    private static Connection connection ;

    private Connect(){
        connect();

    }
    public static void connect(){
        try {
            String url = "jdbc:sqlite:dev/Resource/MarketDB.db";
            connection = DriverManager.getConnection(url);
        }
        catch (SQLException e){
            System.out.println(e.getMessage());
            System.out.println(Arrays.toString(e.getStackTrace()));
        }
    }
    public static void disconnect(){
        try{
            connection.close();
        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static Connection getConnection(){
        if(connection == null){
            connect();
        }
        return connection;
    }
//    public static void main(String[] args) {
//        try {
//            Connection conn = getConnection();
//            Statement stmt = conn.createStatement();
//            String createDamaged = "CREATE TABLE Damaged ( QRCode INTEGER PRIMARY KEY, catalog_number TEXT, reason TEXT)";
//            String createExpDate = "CREATE TABLE ExpDates (QRCode INTEGER PRIMARY KEY, catalog_number TEXT, Date DATE)";
//            String createCategory =  "CREATE TABLE Category (Category TEXT PRIMARY KEY, Discount DOUBLE )";
//            String createProducts = "CREATE TABLE Products(QRCode INTEGER PRIMARY KEY, catalog_number TEXT,Manufacturer TEXT, StorageQuantity INTEGER, StoreQuantity INTEGER, MinimumQuantity INTEGER, ProductDiscount DOUBLE, Weight DOUBLE, Value DOUBLE, Category STRING)";
//            String createShortages = "CREATE TABLE Stock.Business.Shortages(CatalogNumber TEXT PRIMARY KEY)";
//

    // NEED TO CREATE DB TO THE STATIC INT, THE NAME IS: ProductID, the fields are ID, Value
//
//            stmt.executeUpdate(createDamaged);
//            stmt.executeUpdate(createExpDate);
//            stmt.executeUpdate(createCategory);
//            stmt.executeUpdate(createProducts);
//            stmt.executeUpdate(createShortages);
//
//            conn.close();
//        } catch (SQLException e) {
//            System.out.println(e.getMessage());
//        }
    public static void main(String[] args) {
        try {
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
//            String sql = "DROP TABLE IF EXISTS Products";
//            stmt.executeUpdate(sql);
//            String createProducts = "CREATE TABLE Products(catalog_number TEXT PRIMARY KEY,Manufacturer TEXT, StorageQuantity INTEGER, StoreQuantity INTEGER, MinimumQuantity INTEGER, ProductDiscount DOUBLE, Weight DOUBLE, Value DOUBLE, Category STRING, StorageLocation TEXT, StoreLocation TEXT)";
//            stmt.executeUpdate(createProducts);
            stmt.executeUpdate("DELETE  FROM ProductID");
            stmt.executeUpdate("DELETE FROM Products");
            stmt.executeUpdate("DELETE  FROM ExpDates");
//            stmt.executeUpdate("CREATE TABLE ProductID ( ID INTEGER PRIMARY KEY, Value  INTEGER )");
//            stmt.executeUpdate("INSERT INTO ProductID (ID, VALUE ) VALUES (" + "1" + "," + 0 + ")");
            stmt.executeUpdate("INSERT INTO ProductID (ID, VALUE ) VALUES (" + "1" + "," + 0 + ")");
            stmt.executeUpdate("INSERT INTO ProductID (ID, VALUE ) VALUES (" + "2" + "," + 0 + ")");
            stmt.executeUpdate("INSERT INTO ProductID (ID, VALUE ) VALUES (" + "3" + "," + 0 + ")");
            stmt.executeUpdate("INSERT INTO ProductID (ID, VALUE ) VALUES (" + "4" + "," + 0 + ")");
            stmt.executeUpdate("INSERT INTO ProductID (ID, VALUE ) VALUES (" + "5" + "," + 0 + ")");



//            stmt.executeUpdate("INSERT INTO Category (Category, Discount) VALUES (" + "'" + "Dairy" + "'" + "," + 0 + ")");
//            stmt.executeUpdate("INSERT INTO Category (Category, Discount) VALUES (" + "'" + "Fruits" + "'" + "," + 0 + ")");
//            stmt.executeUpdate("INSERT INTO Category (Category, Discount) VALUES (" + "'" + "Meat" + "'" + "," + 0 + ")");


//            stmt.executeUpdate("DELETE FROM Products WHERE catalog_number = null");

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}


//12
//1
//12
//no
//1
//1
//Dairy
//Milk 3%
//1 l
//XXX
//23
//1
//12


