package Stock.Business;

import Stock.DataAccess.*;

import java.util.ArrayList;

public class ReportsManager {
    private static final ProductDAO productDAO = ProductDAO.getInstance();
    private static final CategoryDAO categoryDAO = CategoryDAO.getInstance();


    private static ReportsManager instance = null;

    private ReportsManager(){
        // private constructor
    }

    public static ReportsManager getInstance() {
        if (instance == null) {
            instance = new ReportsManager();
        }
        return instance;
    }
    public boolean createStockReport(){
        StockReport stockReport = new StockReport(productDAO.getAllProducts(),categoryDAO.getAllTheCategories());
        try{System.out.println("-------- Stock Report --------");
            System.out.println(stockReport);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public String getStockReport(){
        StockReport stockReport = new StockReport(productDAO.getAllProducts(),categoryDAO.getAllTheCategories());
        return "-------- Stock Report --------\n"+ stockReport.toString();

    }

    public boolean createStockReportForCategory(String category){
        ArrayList<String> singleCategory = new ArrayList<>();
        singleCategory.add(category);
        StockReport stockReport = new StockReport(productDAO.getAllProducts(),singleCategory);
        try{System.out.println("-------- Stock Report --------");
            System.out.println(stockReport);
        } catch (Exception e) {
            return false;
        }
        return true;
    }



    public boolean createOrderReport(){
        OrderReport orderReport = new OrderReport(productDAO.getAllProducts());
        try{System.out.println("-------- Order Report --------");
            System.out.println(orderReport);
        } catch (Exception e) {
            return false;
        }
        return true;
    }



    public boolean createDamagedReport(){
        DamagedReport damagedReport = new DamagedReport(productDAO.getAllProducts());
        try{System.out.println("-------- Damaged Report --------");
            System.out.println(damagedReport);
        } catch (Exception e) {
            return false;
        }
        return true;
    }

    public Shortages createShortageReport(){
        Shortages shortages = new Shortages();
        try{System.out.println("-------- Shortage Report --------");
            System.out.println(shortages);
        } catch (Exception e) {
            return null;
        }
        return shortages;
    }
}
