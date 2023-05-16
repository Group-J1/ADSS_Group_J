package Stock.Business;

import Stock.DataAccess.ProductDetailsDAO;

import java.util.ArrayList;
import java.util.Date;

public class Market {
    Store store;
    Storage storage;
    //Sales sales;
    Shortages shortages;
    //Stock stock;
    ArrayList<Report> allReports;

    /**
     * Creates a new Stock.Business.Market object with a specified number of shelves.
     * @param numberOfShelves The number of shelves in the Stock.Business.Market.
     */
    public Market(int numberOfShelves) {
        store = new Store(numberOfShelves);
        storage = new Storage(numberOfShelves);
        //sales = new Sales();
        shortages = new Shortages();
        //stock = new Stock();
        allReports = new ArrayList<>();
    }



    /**
     * Increase the number of shelves in the storage area by the given amount.
     * @param addedShelves the number of shelves to be added to the storage area.
     * @return true if the operation was successful, false otherwise.
     */
    public boolean appendStorage(int addedShelves) {
        storage.updateStorageShelvesNumber(addedShelves);
        return true;
    }

    /**
     * Increases the number of shelves in the store by the specified amount.
     * @param addedShelves the number of shelves to add to the store
     * @return true, indicating that the shelves have been successfully added
     */
    public boolean appendStore(int addedShelves) {
        store.updateStoreShelvesNumber(addedShelves);
        return true;
    }



    /**
     * Prints the list of products that are currently in shortage to the console.
     */
    public void printShortages(){
        System.out.println("-----Shortages-----");
        System.out.println(shortages.toString());
    }

    /**
     * @return Store representing the store of the market.
     */
    public Store getStore() {       // freshie change
        return store;
    }

    /**
     * @return Storage representing the storage of the market.
     */
    public Storage getStorage() {       // freshie change
        return storage;
    }

    /**
     * @return Shortages representing the shortages in the market.
     */
    public Shortages getShortages() { return shortages; }
}