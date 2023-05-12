package Stock.Service;

import Stock.Business.Market;
import Stock.Business.MarketManager;

public class MarketService {

    private static MarketManager marketManager = MarketManager.getInstance();
    private static MarketService instance = null;

    private MarketService(){
        //private constructor
    }

    public static MarketService getInstance(){
        if(instance == null){
            instance = new MarketService();
        }
        return instance;
    }

    public void appendMarket(int extraShelves){
        marketManager.addShelves(extraShelves);
    }

    public void setDiscountForProduct(String categoryStr, String subCategoryStr, String subSubCategoryStr, double discount){
        marketManager.setDiscountForProduct(categoryStr,subCategoryStr,subSubCategoryStr,discount);
    }
    public void setDiscountForProduct(String catalogNumber, double discount){
        marketManager.setDiscountForProduct(catalogNumber,discount);
    }
    public void setDiscountForCategory(String categoryStr, double discount){
        marketManager.setDiscountForCategory(categoryStr,discount);
    }






    }
