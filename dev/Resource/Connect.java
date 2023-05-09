package Resource;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;


public class Connect {
    private static Connection connection;

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
//            String createShortages = "CREATE TABLE Shortages(CatalogNumber TEXT PRIMARY KEY)";
//
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
        connect();
    }
}




