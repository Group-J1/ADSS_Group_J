package Resource;
import Stock.Business.AProductCategory;
import Stock.Business.AProductSubCategory;
import Stock.Business.Location;
import Stock.Business.Product;
import Stock.DataAccess.CategoryDAO;
import Stock.DataAccess.ProductDAO;
import java.util.Calendar;
import java.util.Date;
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
    public static void loadDataToDatabase(){
        try {
//            Connect.disconnect();
            Connection conn = getConnection();
            Statement stmt = conn.createStatement();
            stmt.executeUpdate("DELETE  FROM ProductID");
            stmt.executeUpdate("DELETE FROM Products");
            stmt.executeUpdate("DELETE  FROM ExpDates");
            stmt.executeUpdate("DELETE  FROM Damaged");
            stmt.executeUpdate("DELETE FROM Shortages");
            stmt.executeUpdate("DELETE FROM Category");
            stmt.executeUpdate("INSERT INTO ProductID (ID, VALUE ) VALUES (" + "1" + "," + 0 + ")");
            stmt.executeUpdate("INSERT INTO ProductID (ID, VALUE ) VALUES (" + "2" + "," + 0 + ")");
            stmt.executeUpdate("INSERT INTO ProductID (ID, VALUE ) VALUES (" + "3" + "," + 0 + ")");
            stmt.executeUpdate("INSERT INTO ProductID (ID, VALUE ) VALUES (" + "4" + "," + 0 + ")");
            stmt.executeUpdate("INSERT INTO ProductID (ID, VALUE ) VALUES (" + "5" + "," + 0 + ")");
            stmt.executeUpdate("INSERT INTO ProductID (ID, VALUE ) VALUES (" + "6" + "," + 0 + ")");
            stmt.executeUpdate("INSERT INTO ProductID (ID, VALUE ) VALUES (" + "7" + "," + 0 + ")");

            AProductCategory Dairy = new AProductCategory("Dairy");
            AProductCategory Meat = new AProductCategory("Meat");
            AProductCategory Fruits = new AProductCategory("Fruits");
            AProductCategory vegetables = new AProductCategory("Vegetables");
            CategoryDAO.getInstance().writeNewCategory(Dairy.getName(),0);
            CategoryDAO.getInstance().writeNewCategory(Meat.getName(),0);
            CategoryDAO.getInstance().writeNewCategory(Fruits.getName(),0);
            CategoryDAO.getInstance().writeNewCategory(vegetables.getName(),0);


            AProductCategory milk = new AProductCategory("Milk 3%");
            AProductCategory Burgers = new AProductCategory("Burgers");
            AProductCategory Apples = new AProductCategory("Apples");
            AProductCategory Yogurt = new AProductCategory("Yogurt");
            AProductCategory Oranges = new AProductCategory("Oranges");
            AProductCategory Beef = new AProductCategory("Beef");
            AProductCategory Cheese = new AProductCategory("Cheese");
            AProductCategory Tomatos = new AProductCategory("Tomatoes");
            AProductCategory Steaks = new AProductCategory("Steaks");


            AProductSubCategory ssc_1l = new AProductSubCategory(1,"l");
            AProductSubCategory ssc_1kg = new AProductSubCategory(1,"kg");
            AProductSubCategory ssc_500gr = new AProductSubCategory(500,"gr");
            AProductSubCategory ssc_250ml = new AProductSubCategory(250,"ml");
            AProductSubCategory ssc_200gr = new AProductSubCategory(200,"gr");
            AProductSubCategory ssc_2kg = new AProductSubCategory(2,"kg");


            Calendar calendar = Calendar.getInstance();
            calendar.set(2023, Calendar.MAY, 17);
            Date expDate1 =  calendar.getTime();

            calendar.set(2023, Calendar.JULY,10);
            Date expDate2 = calendar.getTime();

            calendar.set(2023, Calendar.JUNE,12);
            Date expDate3 = calendar.getTime();

            calendar.set(2023, Calendar.SEPTEMBER,15);
            Date expDate4 = calendar.getTime();

            calendar.set(2023, Calendar.OCTOBER,18);
            Date expDate5 = calendar.getTime();

            calendar.set(2023, Calendar.AUGUST,30);
            Date expDate6 = calendar.getTime();

            calendar.set(2023, Calendar.JULY,25);;
            Date expDate7 = calendar.getTime();

            calendar.set(2024, Calendar.JANUARY,17);
            Date expDate8 = calendar.getTime();

            calendar.set(2024, Calendar.MARCH,3);
            Date expDate9 = calendar.getTime();




            Product product1 = new Product(Dairy,milk,ssc_1l,new Location(0,0),new Location(0,0), "Yotvata",40,10,1.1,expDate1);
            Product product2 = new Product(Meat,Burgers,ssc_1kg,new Location(0,1),new Location(0,1), "BBB",50,15,1.0,expDate2);
            Product product3 = new Product(Fruits,Apples,ssc_500gr,new Location(0,2),new Location(0,2), "Sade",37,7,0.5,expDate3);
            Product product4 = new Product(Dairy,Yogurt,ssc_250ml,new Location(0,3),new Location(0,3), "Tnuva",50,12,0.25,expDate4);
            Product product5 = new Product(Fruits,Oranges,ssc_1kg,new Location(0,4),new Location(0,4), "Sade",40,10,1.0,expDate5);
            Product product6 = new Product(Meat,Beef,ssc_500gr,new Location(0,5),new Location(0,5), "Tivol",34,11,0.5,expDate6);
            Product product7 = new Product(Dairy,Cheese,ssc_200gr,new Location(0,6),new Location(0,6), "Yotvata",50,18,0.2,expDate7);
            Product product8 = new Product(vegetables,Tomatos,ssc_1kg,new Location(0,7),new Location(0,7), "Sade",45,20,1.0,expDate8);
            Product product9 = new Product(Meat,Steaks,ssc_2kg,new Location(0,8),new Location(0,8), "BBB",60,23,2.0,expDate9);

                        // curr product 406
            Product[] p = {product1,product2,product3,product4,product5,product6,product7,product8,product9};
            for(Product product: p){
                product.setCatalogNumber();
            }
            ProductDAO.getInstance().addNewProductToProducts(product1);
            ProductDAO.getInstance().addNewProductToProducts(product2);
            ProductDAO.getInstance().addNewProductToProducts(product3);
            ProductDAO.getInstance().addNewProductToProducts(product4);
            ProductDAO.getInstance().addNewProductToProducts(product5);
            ProductDAO.getInstance().addNewProductToProducts(product6);
            ProductDAO.getInstance().addNewProductToProducts(product7);
            ProductDAO.getInstance().addNewProductToProducts(product8);
            ProductDAO.getInstance().addNewProductToProducts(product9);
            ProductDAO.getInstance().writeProducts();
//            stmt.executeUpdate("INSERT INTO ProductID (ID, VALUE ) VALUES (" + "3" + "," + 0 + ")");
            stmt.executeUpdate("UPDATE ProductID SET VALUE = 18 WHERE ID = 3");





        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
//
    }
    public static void main(String[] args) {

//            Connect.disconnect();
//            Connection conn = getConnection();
//            Statement stmt = conn.createStatement();
//            //            String createDamaged = "CREATE TABLE Damaged ( QRCode INTEGER PRIMARY KEY, catalog_number TEXT, reason TEXT)";
//            String createDamaged = "CREATE TABLE Damaged ( QRCode INTEGER PRIMARY KEY, catalog_number TEXT, reason TEXT)";
//            String createExpDate = "CREATE TABLE ExpDates (QRCode INTEGER PRIMARY KEY, catalog_number TEXT, Date DATE)";
//            String createCategory =  "CREATE TABLE Category (Category TEXT PRIMARY KEY, Discount DOUBLE )";
//            String createProducts = "CREATE TABLE Products(QRCode INTEGER PRIMARY KEY, catalog_number TEXT,Manufacturer TEXT, StorageQuantity INTEGER, StoreQuantity INTEGER, MinimumQuantity INTEGER, ProductDiscount DOUBLE, Weight DOUBLE, Value DOUBLE, Category STRING)";
//            String createShortages = "CREATE TABLE Shortages(CatalogNumber TEXT PRIMARY KEY)";


        // NEED TO CREATE DB TO THE STATIC INT, THE NAME IS: ProductID, the fields are ID, Value
////
//            stmt.executeUpdate(createDamaged);
//            stmt.executeUpdate(createExpDate);
//            stmt.executeUpdate(createCategory);
//            stmt.executeUpdate(createProducts);
//            stmt.executeUpdate(createShortages);

//            String sql = "DROP TABLE IF EXISTS Products";
//            stmt.executeUpdate(sql);
//            createProducts = "CREATE TABLE Products(catalog_number TEXT PRIMARY KEY,Manufacturer TEXT, StorageQuantity INTEGER, StoreQuantity INTEGER, MinimumQuantity INTEGER, ProductDiscount DOUBLE, Weight DOUBLE, Value DOUBLE, Category STRING, StorageLocation TEXT, StoreLocation TEXT)";
//            stmt.executeUpdate(createProducts);
//            stmt.executeUpdate("DELETE  FROM ProductID");
//            stmt.executeUpdate("DELETE FROM Products");
//            stmt.executeUpdate("DELETE  FROM ExpDates");
//            stmt.executeUpdate("DELETE  FROM Damaged");
//            stmt.executeUpdate("DELETE FROM Shortages");
//
//            stmt.executeUpdate("DELETE FROM Category");
//
////            stmt.executeUpdate("CREATE TABLE ProductID ( ID INTEGER PRIMARY KEY, Value  INTEGER )");
//            stmt.executeUpdate("INSERT INTO ProductID (ID, VALUE ) VALUES (" + "1" + "," + 0 + ")");
//            stmt.executeUpdate("INSERT INTO ProductID (ID, VALUE ) VALUES (" + "2" + "," + 0 + ")");
//            stmt.executeUpdate("INSERT INTO ProductID (ID, VALUE ) VALUES (" + "3" + "," + 0 + ")");
//            stmt.executeUpdate("INSERT INTO ProductID (ID, VALUE ) VALUES (" + "4" + "," + 0 + ")");
//            stmt.executeUpdate("INSERT INTO ProductID (ID, VALUE ) VALUES (" + "5" + "," + 0 + ")");
//            stmt.executeUpdate("INSERT INTO ProductID (ID, VALUE ) VALUES (" + "6" + "," + 0 + ")");
//            stmt.executeUpdate("INSERT INTO ProductID (ID, VALUE ) VALUES (" + "7" + "," + 0 + ")");
////
//
//
//            stmt.executeUpdate("INSERT INTO Category (Category, Discount) VALUES (" + "'" + "Dairy" + "'" + "," + 0 + ")");
//            stmt.executeUpdate("INSERT INTO Category (Category, Discount) VALUES (" + "'" + "Fruits" + "'" + "," + 0 + ")");
//            stmt.executeUpdate("INSERT INTO Category (Category, Discount) VALUES (" + "'" + "Meat" + "'" + "," + 0 + ")");

        loadDataToDatabase();

//            stmt.executeUpdate("DELETE FROM Products WHERE catalog_number = null");
//
//            Connect.disconnect();
//        } catch (SQLException e) {
//            Connect.disconnect();
//            System.out.println(e.getMessage());
//        }

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