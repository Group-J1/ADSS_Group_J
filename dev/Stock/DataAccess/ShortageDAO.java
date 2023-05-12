package Stock.DataAccess;

import Resource.Connect;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

public class ShortageDAO {
    private static Connection connection;
    private static ArrayList<String> shortageMap;
    private static ShortageDAO instance = null;

    private ShortageDAO(){
        connection = Connect.getConnection();
        shortageMap = new ArrayList<>();
        loadToShortageMap();
    }

    public static ShortageDAO getInstance() {
        if (instance == null) {
            instance = new ShortageDAO();
        }
        return instance;
    }

    private void loadToShortageMap(){
        try{
            java.sql.Statement statement = connection.createStatement();
            java.sql.ResultSet resultSet = statement.executeQuery("SELECT * FROM Shortages ");
            while(resultSet.next()){
                String catalogNumber = resultSet.getString("CatalogNumber");
                shortageMap.add(catalogNumber);
            }
        }
        catch (SQLException e){
            System.out.println("theres a problem with the database");
        }
    }

    public void writeShortages(){
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

    public ArrayList<String> getShortageMap(){
        return shortageMap;
    }

    public void addToShortages(String catalogNumber){
        shortageMap.add(catalogNumber);
    }

    public Boolean isInShortage(String catalogNumber){
        return shortageMap.contains(catalogNumber);
    }
}


