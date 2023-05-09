package Stock.DataAccess;

import Stock.Business.AProductCategory;
import Resource.Connect;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Locale;
import java.util.Map;

public class CategoryDAO {
    private static CategoryDAO instance  = new CategoryDAO();
    private static Map<String, AProductCategory> CategoryMap;
    private static Connection connection;


    private CategoryDAO(){
        CategoryMap = new HashMap<>();
        connection = Connect.getConnection();
    }

    public static CategoryDAO getInstance(){
        return instance;
    }

    private static AProductCategory getCategory(String category){
        if(CategoryMap.get(category) == null){
            // look for item in the database
            lookForCategory(category);
        }
        return  CategoryMap.get(category);
    }

    private static void lookForCategory(String categoryStr){
        String category;
        double discount;
        try{
            java.sql.Statement statement = connection.createStatement();
            java.sql.ResultSet resultSet = statement.executeQuery("SELECT * FROM Category WHERE Category ==" + categoryStr);
            while(resultSet.next()){
                category = resultSet.getString("ID");
                discount = resultSet.getDouble("Discount");
                AProductCategory found = new AProductCategory(category);
                found.setDiscount(discount);
                CategoryMap.put(category,found);
            }
        } catch (SQLException e) {
            System.out.println("there was a problem with the database");
        }
    }

    public static void writeCategories(){
        for(AProductCategory category: CategoryMap.values()){
            String name;
            double discount;
            try{
                java.sql.Statement statement = connection.createStatement();
                name = category.getName();
                discount = category.getDiscount();
                java.sql.ResultSet resultSet = statement.executeQuery("SELECT * FROM Category WHERE Category ==" + name);
                if(!resultSet.next()){
                    statement.executeUpdate("INSERT INTO Category (Category, Discount) VALUES (" + "'" + name + "'" + "," + discount + ")");
                }
                else{
                    statement.executeUpdate("UPDATE Category SET Discount ="  + discount+ " WHERE Category = "+ name);
                }
            }
            catch (SQLException e) {
                System.out.println("there was a problem with the database");
            }
        }
    }

    public static  void writeNewCategory(String categoryName, double discount){
        AProductCategory category = new AProductCategory(categoryName);
        category.setDiscount(discount);
        CategoryMap.put(categoryName,category);

        // should be added to the db?
    }

    public static void deleteCategory(String categoryName){
        CategoryMap.remove(categoryName);
        try{
            java.sql.Statement statement = connection.createStatement();
            statement.executeQuery("DELETE  FROM Category WHERE Catrgory ==" + categoryName);

        }catch (SQLException e){
            System.out.println("theres a problem with the database");
        }
    }


}
