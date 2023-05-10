package Stock.DataAccess;

import Resource.Connect;

import java.sql.Connection;
import java.sql.SQLException;

public class ProductIDDAO {
    private static ProductIDDAO instance = new ProductIDDAO();
    private static int curr;
    private static Connection connection;

    public static ProductIDDAO getInstance() {
        return instance;
    }

    private ProductIDDAO(){
        connection = Connect.getConnection();

        try{
            java.sql.Statement statement = connection.createStatement();
            java.sql.ResultSet resultSet = statement.executeQuery("SELECT * FROM ProductID WHERE ID =="  + "1" );
            if(resultSet.next()){
                curr = resultSet.getInt("Value");
            }
        }catch (SQLException e){
            System.out.println("there is a problem with the database");
        }
    }

    public static void saveCurrID(){
        try{
            java.sql.Statement statement = connection.createStatement();
            java.sql.ResultSet resultSet = statement.executeQuery("SELECT * FROM ProductID WHERE ID =="  + "1" );
            if(!resultSet.next()){
                statement.executeUpdate("INSERT INTO ProductID (ID, VALUES ) VALUES (" + "1"  + "," + Integer.toString(curr) + ")");
            }
            else{
                statement.executeUpdate("UPDATE ProductID SET Value ="  + Integer.toString(curr) + " WHERE ID = "+ "1");

            }

        }catch (SQLException e){
            System.out.println("there is a problem with the database");
        }
    }

    public static int getCurr() {
        return curr++;

    }
}
