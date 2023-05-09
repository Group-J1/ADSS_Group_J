package Stock.DataAccess;

import Stock.Business.Product;
import Resource.Connect;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ExpDateDAO {
    private static ExpDateDAO instance = new ExpDateDAO();
    private  static Map<Integer, Date> ExpDateMap;
    private static  Map<String, Map<Integer,Date>> catalogExpDate;
    private static  Map<Integer,String> qrToCatalogNumber;
    private static Connection connection;

    private ExpDateDAO(){
        connection = Connect.getConnection();
        ExpDateMap = new HashMap<>();
    }

    public static ExpDateDAO getInstance() {
        return instance;
    }

    public static Date getQrExpDate(int qr){

        if (ExpDateMap.get(qr) == null) {
            // read from the db
            lookForExpDate(qr);
        }
        return ExpDateMap.get(qr);
    }

    private static void lookForExpDate(int qr){
        int barcode;
        String catalogNumber;
        Date expDate;

        try{
            java.sql.Statement statement = connection.createStatement();
            java.sql.ResultSet resultSet = statement.executeQuery("SELECT * FROM ExpDates WHERE QRCode ==" + qr);
            while(resultSet.next()){
                barcode = resultSet.getInt("QRCode");
                catalogNumber = resultSet.getString("catalog_number");
                expDate = resultSet.getDate("Date");
                ExpDateMap.put(barcode,expDate);
                qrToCatalogNumber.put(barcode,catalogNumber);

            }

        }catch (SQLException e){
            System.out.println("there is problem with the data base");
        }
    }

    public static Map<Integer,Date> readExpForCatalogNumber(String catalogNumber){
        if(catalogExpDate.get(catalogNumber) == null){
            getExpForCatalogNumber(catalogNumber);
        }
        return catalogExpDate.get(catalogNumber);
    }

    private static void getExpForCatalogNumber(String catalogNumber){
        Map<Integer,Date> productExpDates = new HashMap<>();
        int barcode;
        Date expDate;
        try{
            java.sql.Statement statement = connection.createStatement();
            java.sql.ResultSet resultSet = statement.executeQuery("SELECT * FROM ExpDates WHERE catalog_number ==" + catalogNumber);

            while(resultSet.next()){
                barcode = resultSet.getInt("QRCode");
                expDate = resultSet.getDate("Date");
                productExpDates.put(barcode,expDate);
                ExpDateMap.putIfAbsent(barcode, expDate);
                qrToCatalogNumber.putIfAbsent(barcode,catalogNumber);
            }
            catalogExpDate.put(catalogNumber,productExpDates);


        }
        catch (SQLException e){
            System.out.println("theres problem with the database");
        }

    }

    public static void writeExpDate(){
        for(Integer qr: ExpDateMap.keySet()){
            try{
                java.sql.Statement statement = connection.createStatement();
                java.sql.ResultSet resultSet = statement.executeQuery("SELECT * FROM ExpDate WHERE QRCode ==" + qr);
                if(!resultSet.next()){
                    statement.executeUpdate("INSERT INTO ExpDates (QRCode, catalog_number, Date) VALUES (" + qr  + ","+"'" + qrToCatalogNumber.get(qr) +"'"+","+ExpDateMap.get(qr)+ ")");
                }
                else{
                    statement.executeUpdate("UPDATE Category SET Date ="  + ExpDateMap.get(qr)+ " WHERE QRCode = "+ qr);
                }
            }catch (SQLException e){
                System.out.println("there is a problem with the database");
            }
        }
    }

    public static void writeExpDateForNewQR(int QR,String catalogNumber, Date date){
        if(!ExpDateMap.containsKey(QR)){
            ExpDateMap.put(QR,date);
            qrToCatalogNumber.put(QR,catalogNumber);
            // should be added to the database ?

        }
        else{System.out.println("the qr is not new");}
    }

}