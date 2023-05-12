package Stock.Business;

import Stock.DataAccess.CategoryDAO;
import Stock.DataAccess.ProductDAO;
import Stock.DataAccess.ProductDetailsDAO;

public class MarketManager {
    private static final ProductDAO productDAO = ProductDAO.getInstance();
    private static final CategoryDAO categoryDAO = CategoryDAO.getInstance();
    private static final ProductDetailsDAO productDetailsDAO = ProductDetailsDAO.getInstance();

    private static Market market;
    private static MarketManager instance = null;

    private MarketManager(){
        // private constructor
    }

    public static MarketManager getInstance(){
        if(instance == null){
            instance = new MarketManager();
        }
        return instance;
    }

    public static void setMarket(Market market) {
        MarketManager.market = market;
    }

    public void addShelves(int extraShelves){
        market.appendStorage(extraShelves);
        market.appendStore(extraShelves);
        ProductDetailsDAO.setNumOfShelves(ProductDetailsDAO.getNumOfShelves() + extraShelves);
        productDetailsDAO.saveDetails();
    }
    private Product getProductByCategories(String subCategory,String subSubCategory){
        String[] subsubSplited = subSubCategory.split(" ");
        String name = subCategory + " " + Double.toString(Double.parseDouble(subsubSplited[subsubSplited.length - 2]))
                + " " + subsubSplited[subsubSplited.length - 1];
        String productCatalogNumber = UniqueStringGenerator.generateUniqueString(name);
        return productDAO.getProduct(productCatalogNumber);

    }

    public void setDiscountForProduct(String categoryStr, String subCategoryStr, String subSubCategory, double discount){
        Product product = getProductByCategories(subCategoryStr,subSubCategory);
        if(product != null) {
            product.setDiscount(discount);
            productDAO.writeProducts();
        }
        else {
            System.out.println("Product Not Found");
        }
    }

    public void setDiscountForProduct(String catalogNumber, double discount){
        Product product = productDAO.getProduct(catalogNumber);
        if (product != null){
            product.setDiscount(discount);
            productDAO.writeProducts();
        }
        else{
            System.out.println("Product Not Found");
        }
    }

    public void setDiscountForCategory(String categoryStr, double discount){
        AProductCategory category = categoryDAO.getCategory(categoryStr);
        if (category != null){
            category.setDiscount(discount);
            categoryDAO.writeCategories();
        }else {
            System.out.println("Category not found");
        }
    }
}
