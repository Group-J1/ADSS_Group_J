package Stock.DataAccess;

import Resource.Connect;
import Stock.Business.*;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ProductDAO {
    private static ProductDAO instance = new ProductDAO();
    private static Map<String, Product> productMap;
    private static Connection connection;

    private ProductDAO(){
        productMap = new HashMap<>();
        connection = Connect.getConnection();
    }

    public static ProductDAO getInstance() {
        if(instance == null){
            instance = new ProductDAO();
        }
        return instance;
    }
    public static Product getProduct(String catalogNumber){
        if(productMap.get(catalogNumber) == null){
            //read from the database
            lookForProduct(catalogNumber);
        }
        return productMap.get(catalogNumber);
    }
    private static void lookForProduct(String catalogNumber){
        int qr,storageQuantity,storeQuantity,minimumQuantity;
        String catalogNum, manufacturer,category,DamagedReason,storeLocation,storageLocation ;
        // store/storage location are like this :    "1 2"

        double weight, value,discount;
        Date expDate = new Date();

        try{
            java.sql.Statement statement = connection.createStatement();
            java.sql.ResultSet resultSet = statement.executeQuery("SELECT * FROM Products WHERE catalog_number ==" + "'" + catalogNumber + "'");
            while(resultSet.next()){
                storageQuantity = resultSet.getInt("storageQuantity");
                storeQuantity = resultSet.getInt("storeQuantity");
                minimumQuantity = resultSet.getInt("MinimumQuantity");
                catalogNum = resultSet.getString("catalog_number");
                manufacturer = resultSet.getString("Manufacturer");
                category = resultSet.getString("Category");
                weight = resultSet.getDouble("Weight");
                value = resultSet.getDouble("Value");
                discount = resultSet.getDouble("ProductDiscount");
                storeLocation = resultSet.getString("StoreLocation"); // the name of the storeLocation field
                storageLocation = resultSet.getString("StorageLocation");// the name of the storageLocation field
                ExpDateDAO expDateDAO = ExpDateDAO.getInstance();
                HashMap<Integer,Date> exp = ExpDateDAO.readExpForCatalogNumber(catalogNumber);
                CategoryDAO categoryDAO = CategoryDAO.getInstance();
                AProductCategory productCategory = CategoryDAO.getCategory(category);
                DamagedProductDAO damagedProductDAO = DamagedProductDAO.getInstance();
                HashMap<Integer,String> damagedBarcodes = DamagedProductDAO.readDamagedForCatalogNumber(catalogNumber);
                if(productCategory == null){
                    System.out.println("problem with the category");
                }

                String productName = UniqueStringGenerator.convertBackToString(catalogNum);
                String[] words = productName.split(" "); // Split the string on whitespace

                String unit;
                String amount;
                StringBuilder productKind = new StringBuilder();
                AProductSubCategory aProductSubSubCategory;
                AProductCategory aProductSubCategory;

                if (words.length >= 2) {
                    unit = words[words.length - 1];
                    amount = words[words.length - 2];
                    aProductSubSubCategory = new AProductSubCategory(Double.parseDouble(amount), unit);
                    for(int i = 0 ; i < words.length -2; i ++){
                        productKind.append(words[i]);
                    }
                    aProductSubCategory = new AProductCategory(productKind.toString());
                }
                else {
                    System.out.println("problem with subcategory");
                    return;
                }
                String[] storeLocArr = storeLocation.split(" "),storageLocArr = storageLocation.split(" ");
                Location storeLoc,storageLoc;
                storeLoc = new Location(Integer.parseInt(storeLocArr[0]),Integer.parseInt(storeLocArr[1]));
                storageLoc = new Location(Integer.parseInt(storageLocArr[0]),Integer.parseInt(storageLocArr[1]));

                Product product = new Product(productCategory,aProductSubCategory,aProductSubSubCategory,storageLoc,storeLoc,manufacturer,storageQuantity+storeQuantity,minimumQuantity,weight,expDate);
                product.setCatalogNumber();
                product.setExpirationDates(exp);
                product.setDamagedProducts(damagedBarcodes);
                product.setDiscount(discount);
                product.setValue(value);
                productMap.put(catalogNumber,product);

       }

        }catch (SQLException e){
            System.out.println(e.getMessage());
        }
    }

    public static void writeProducts(){
        for(Product product: productMap.values()){
            try{
                java.sql.Statement statement = connection.createStatement();
                java.sql.ResultSet resultSet = statement.executeQuery("SELECT * FROM Products WHERE catalog_number = '" + product.getCatalogNumber() + "'");
                if(!resultSet.next()){
//                    statement.executeUpdate("INSERT INTO Products (catalog_number, Manufacturer, StorageQuantity,StoreQuantity,MinimumQuantity,ProductDiscount,Weight,Value,Category,StorageLocation,StoreLocation) VALUES " +
//                            "(" + product.getCatalogNumber()  + ","+"'" + product.getManufacturer() +"'"+","+product.getStorageQuantity()+ ","+ product.getStoreQuantity() + "," + product.getMinimumQuantity() + ","+ product.getDiscount()+","+product.getWeight()+","+product.getValue()+"," +product.getCategory()+"," + "'"+ product.getStorageLocation().toString()+"'"+","+"'"+product.getStoreLocation().toString()+"'"+ ")");
                    String sql = "INSERT INTO Products (catalog_number, Manufacturer, StorageQuantity, StoreQuantity, MinimumQuantity, ProductDiscount, Weight, Value, Category, StorageLocation, StoreLocation) " +
                            "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

                    try (PreparedStatement stmt = connection.prepareStatement(sql)) {
                        stmt.setString(1, product.getCatalogNumber());
                        stmt.setString(2, product.getManufacturer());
                        stmt.setInt(3, product.getStorageQuantity());
                        stmt.setInt(4, product.getStoreQuantity());
                        stmt.setInt(5, product.getMinimumQuantity());
                        stmt.setDouble(6, product.getDiscount());
                        stmt.setDouble(7, product.getWeight());
                        stmt.setDouble(8, product.getValue());
                        stmt.setString(9, product.getCategory().getName().toString());
                        stmt.setString(10, product.getStorageLocation().toString());
                        stmt.setString(11, product.getStoreLocation().toString());

                        stmt.executeUpdate();
                    } catch (SQLException e) {
                        System.out.println(e.getMessage());
                    }


                }
                else{
                    String updateQuery = "UPDATE Products SET Manufacturer = ?, StorageQuantity = ?, StoreQuantity = ?, MinimumQuantity = ?, ProductDiscount = ?, Weight = ?, Value = ?, Category = ?, StoreLocation = ?, StorageLocation = ? WHERE catalog_number = ?";
                    PreparedStatement preparedStatement = connection.prepareStatement(updateQuery);

                    preparedStatement.setString(1, product.getManufacturer());
                    preparedStatement.setInt(2, product.getStorageQuantity());
                    preparedStatement.setInt(3, product.getStoreQuantity());
                    preparedStatement.setInt(4, product.getMinimumQuantity());
                    preparedStatement.setDouble(5, product.getDiscount());
                    preparedStatement.setDouble(6, product.getWeight());
                    preparedStatement.setDouble(7, product.getValue());
                    preparedStatement.setString(8, product.getCategory().getName().toString());
                    preparedStatement.setString(9, product.getStoreLocation().toString());
                    preparedStatement.setString(10, product.getStorageLocation().toString());
                    preparedStatement.setString(11, product.getCatalogNumber());

                    preparedStatement.executeUpdate();
                }
            }catch (SQLException e){
                System.out.println(e.getMessage());
            }
            ExpDateDAO.getInstance();
            DamagedProductDAO.getInstance();
            ExpDateDAO.updateExpDate(product.getCatalogNumber(),product.getExpirationDates());
            for(Integer qr: product.getDamagedProducts().keySet()){
                DamagedProductDAO.writeDamagedProduct(qr,product.getCatalogNumber(),product.getDamagedProducts().get(qr));
            }

            // for thr checking:
            DamagedProductDAO.writeDamagedProducts();
            ExpDateDAO.writeExpDates();
        }
    }

    public static void addNewProductToProducts(Product product){
        productMap.put(product.getCatalogNumber(),product);
        DamagedProductDAO.getInstance();
        ExpDateDAO.getInstance();
        for(Integer qr: product.getDamagedProducts().keySet()){
            DamagedProductDAO.writeDamagedProduct(qr,product.getCatalogNumber(),product.getDamagedProducts().get(qr));
        }
        for(Integer qr: product.getExpirationDates().keySet()){
            ExpDateDAO.writeExpDateForQR(qr,product.getCatalogNumber(),product.getExpirationDates().get(qr));
        }
        ProductDetailsDAO.getInstance();
        ProductDetailsDAO.saveDetails();

    }

    public static void deleteProduct(Product product){
        productMap.remove(product.getCatalogNumber());
        try{
            java.sql.Statement statement = connection.createStatement();
            java.sql.ResultSet resultSet = statement.executeQuery("DELETE  FROM Products WHERE catalog_number ==" + "'" + product.getCatalogNumber() + "'");
            DamagedProductDAO.getInstance();
            ExpDateDAO.getInstance();
            for(Integer qr: product.getDamagedProducts().keySet()){
                DamagedProductDAO.deleteExpDate(qr);
            }
            for (Integer qr: product.getExpirationDates().keySet()){
                ExpDateDAO.deleteExpDate(qr);
            }
            java.sql.ResultSet resultSet1 = statement.executeQuery("SELECT * FROM Products WHERE Category ==" + "'" + product.getCategory() + "'");
            if(!resultSet1.next()){             // if no items in the category then delete the category
                statement.executeUpdate("DELETE FROM Category WHERE Categoey == "+ "'" + product.getCategory() + "'");
            }

        }catch (SQLException e){
            System.out.println("theres a problem with the database");
        }


    }
}
