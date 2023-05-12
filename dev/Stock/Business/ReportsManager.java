package Stock.Business;

import Stock.DataAccess.*;

public class ReportsManager {
    private static final ProductDAO productDAO = ProductDAO.getInstance();
    private static final ExpDateDAO expDateDAO = ExpDateDAO.getInstance();
    private static final DamagedProductDAO damagedProductDAO = DamagedProductDAO.getInstance();
    private static final ProductDetailsDAO productDetailsDAO = ProductDetailsDAO.getInstance();
    private static final CategoryDAO categoryDAO = CategoryDAO.getInstance();

    private static Market market;



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
    public static void setMarket(Market market) {
        ReportsManager.market = market;
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

    public boolean createShortageReport(){
        Shortages shortages = new Shortages();
        try{System.out.println("-------- Shortage Report --------");
            System.out.println(shortages);
        } catch (Exception e) {
            return false;
        }
        return true;
    }
}
