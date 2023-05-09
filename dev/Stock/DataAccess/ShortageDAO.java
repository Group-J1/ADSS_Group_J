package Stock.DataAccess;

import Resource.Connect;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class ShortageDAO {
    private static Connection connection;
    private static ArrayList<String> shortageMap;
    private static ShortageDAO instance = new ShortageDAO();

    private ShortageDAO(){
        connection = Connect.getConnection();
        shortageMap = new ArrayList<>();
        loadToShortageMap();
    }

    public static ShortageDAO getInstance() {
        return instance;
    }

    private static void loadToShortageMap(){
        try{
            java.sql.Statement statement = connection.createStatement();
            java.sql.ResultSet resultSet = statement.executeQuery("SELECT * FROM Shortages ");
            while(resultSet.next()){
                String catalogNumber = resultSet.getString("CatalogNumber");
                shortageMap.add(catalogNumber);
            }

        }catch (SQLException e){
            System.out.println("theres a problem with the database");
        }
    }

    public static void writeShortages(){
        try{
            java.sql.Statement statement = connection.createStatement();
            java.sql.ResultSet resultSet = statement.executeQuery("DELETE FROM Shortages");
            for(String catalogNumber: shortageMap){
                statement.executeQuery("DELETE FROM Shortages");
                statement.executeUpdate("INSERT INTO Shortages (CatalogNumber) VALUES (" + "'"+catalogNumber+"'" + ")");
            }


        }
        catch (SQLException e){
            System.out.println("theres a problem with the database");
        }
    }

    public static ArrayList<String> getShortageMap(){
        return shortageMap;
    }

    public static void addToShortages(String catalogNumber){
        shortageMap.add(catalogNumber);
    }

    public static Boolean isInShortage(String catalogNumber){
        return shortageMap.contains(catalogNumber);
    }
}


