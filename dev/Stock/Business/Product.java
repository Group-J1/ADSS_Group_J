package Stock.Business;

import Stock.DataAccess.ExpDateDAO;
import Stock.DataAccess.ProductDetailsDAO;

import java.util.*;

public class Product {
//    static int productsCounter = 0;

    private String name;

    // Milk's products
    private AProductCategory category;

    // milk 3%
    private AProductCategory subCategory;

    // 1 liter
    private AProductSubCategory subSubCategory;

    private Location storageLocation;

    private Location storeLocation;


    private String manufacturer;
    private int storeQuantity;
    private int storageQuantity;
    private int minimumQuantity;
    private double weight;
    private double discount;
    private double value;
    private double price;
    private int priority;
    private String catalogNumber;
    private HashMap<Integer ,Date > expirationDates;

    private HashMap<Integer, String> damagedProducts;

    private static final ExpDateDAO expDateDAO = ExpDateDAO.getInstance();


    public Product(AProductCategory category, AProductCategory subCategory, AProductSubCategory subSubCategory,
                   Location storageLocation, Location storeLocation, String manufacturer, int quantity,
                   int minimumQuantity, double weight, Date expiration) {
        /**
         * Constructs a new Stock.Business.Product object with the given parameters.
         *
         * @param category         the category of the product
         * @param subCategory      the sub-category of the product
         * @param subSubCategory   the sub-sub-category of the product
         * @param storageLocation  the location where the product is stored
         * @param storeLocation    the location where the product is sold
         * @param manufacturer     the name of the product's manufacturer
         * @param quantity         the total quantity of the product
         * @param minimumQuantity  the minimum quantity required to keep in stock
         * @param weight           the weight of the product
         * @param expiration       the expiration date of the product
         */
        this.name = subCategory.getName() + " " + subSubCategory.getName();
        this.category = category;
        this.subCategory = subCategory;
        this.subSubCategory = subSubCategory;
        this.storageLocation = storageLocation;
        this.storeLocation = storeLocation;
        this.manufacturer = manufacturer;
        if (quantity > 30) {
            this.storeQuantity = 30;
            this.storageQuantity = quantity - this.storeQuantity;
        }
        else {
            this.storeQuantity = quantity;
            this.storageQuantity = 0;
        }
        this.minimumQuantity = minimumQuantity;
        this.weight = weight;
        expirationDates = new HashMap<>();
        ProductDetailsDAO.getInstance();

        for (int i = 0; i < quantity; i++) {
            this.expirationDates.put(ProductDetailsDAO.getProductId(), expiration);
        }
        damagedProducts = new HashMap<>();
    }

    public String getName() {
        return name;
    }

    public AProductCategory getCategory() {
        return category;
    }

    public AProductCategory getSubCategoryName() {
        return subCategory;
    }

    public AProductSubCategory getSubSubCategory() {
        return subSubCategory;
    }

    public Location getStoreLocation() {
        return storeLocation;
    }

    public void setStoreLocation(Location storeLocation) {
        this.storeLocation = storeLocation;
    }

    public Location getStorageLocation() {
        return storageLocation;
    }


    public void setStorageLocation(Location storageLocation) {
        this.storageLocation = storageLocation;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

    public int getStoreQuantity() {
        return storeQuantity;
    }


    // Add to store from storage
    public void addToStore(int howMuchToAddToStore) {
        /**
         * Adds a specified quantity of the product to the store. If the quantity to add is greater than the quantity in storage, nothing happens.
         * @param howMuchToAddToStore the quantity of the product to add to the store
         */
        if (howMuchToAddToStore > this.storageQuantity) {
            return;
        }
        this.storeQuantity += howMuchToAddToStore;
        this.storageQuantity -= howMuchToAddToStore;
    }

    public int getStorageQuantity() {
        return storageQuantity;
    }

    public void addToStorage(int howMuchToAddToStorage) {
        /**
         * Adds a specified quantity of the product to the storage.
         * @param howMuchToAddToStorage the quantity of the product to add to the storage
         */
        this.storageQuantity += howMuchToAddToStorage;
    }

    public int getMinimumQuantity() {
        return minimumQuantity;
    }

    public void setMinimumQuantity(int minimumQuantity) {
        this.minimumQuantity = minimumQuantity;
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }

    public double getValue() {
        return value;
    }

    public void setValue(double value) {
        this.value = value;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public Date getExpiration(int barcode) {
        return this.expirationDates.get(barcode);
    }

    public int getPriority() {
        return priority;
    }

    public String getCatalogNumber() {
        return catalogNumber;
    }

    public void setCatalogNumber() {
        catalogNumber = UniqueStringGenerator.generateUniqueString(name);
    }

    public HashMap<Integer, Date> getExpirationDates() {
        return expirationDates;
    }

    public HashMap<Integer, String> getDamagedProducts() {
        return damagedProducts;
    }

    public void setPriority(Stock stock) {
        priority = stock.getStatusInStock(this);
    }

    public boolean getUniqueProduct(Integer barcode){
        return(expirationDates.containsKey(barcode));
    }

    // Add quantity to exist product from UI menu
    public void addMoreItemsToProduct(int quantity, Date expiration) {
        /**
         * Adds a specified quantity of the product to the storage and store, with the given expiration date.
         * If the quantity to add is greater than the remaining storage capacity, the product is not added to the storage.
         * If the store is already at its maximum capacity of 30, the product is not added to the store.
         *
         * @param quantity   the quantity of the product to add
         * @param expiration the expiration date of the product to add
         */
        addToStorage(quantity);
        // store quantity between 0 - 29
        if (storeQuantity < 30) {
            if (storeQuantity + storageQuantity <= 30) {
                addToStore(storageQuantity);
            }
            else {
                int howMuchToAddToStore = 30 - storeQuantity;
                addToStore(howMuchToAddToStore);
            }
        }
        ProductDetailsDAO.getInstance();
        for (int i = 0; i < quantity; i++) {
            this.expirationDates.put(ProductDetailsDAO.getProductId(), expiration);
        }
        expDateDAO.updateExpDate(this.getCatalogNumber(),getExpirationDates());
    }

    public int[] sellMultipleItemsFromProduct(int quantity) {
        /**
         * Sells a specified quantity of the product from the store, and returns an array of integers representing the IDs of the sold products.
         * If the quantity to sell is greater than the available quantity in the store, returns null.
         * Removes the sold items from the expiration dates map.
         * If the store quantity plus the storage quantity is less than or equal to 30, moves all available items to the store.
         * Otherwise, moves items from the storage to the store until the store is at its maximum capacity of 30.
         *
         * @param quantity the quantity of the product to sell
         * @return an array of integers representing the IDs of the sold products, or null if the requested quantity is not available in the store.
         */
        if (storeQuantity < quantity) {
            return null;
        }
        List<Date> datesList = new ArrayList<>(expirationDates.values());
        int[] sold = new int[quantity];
        Collections.sort(datesList);
        int count = 0;
        for (int i = 0; i < quantity; i++) {
            Date smallestDate = datesList.get(i);
            for (Integer entryInt : expirationDates.keySet()) {
                Date entry = expirationDates.get(entryInt);
                if (entry != null && entry.equals(smallestDate)) {
//
                    sold[count] = Integer.parseInt(entryInt.toString());
//                    expirationDates.remove(Integer.parseInt(entryInt.toString()));
                    count++;
                    if (count == quantity) {
                        break;
                    }
                }
            }
            if (count == quantity) {
                break;
            }
        }
        storeQuantity -= quantity;
        if (storeQuantity + storageQuantity <= 30) {
            addToStore(storageQuantity);
        }
        else {
            int howMuchToAddToStore = 30 - storeQuantity;
            addToStore(howMuchToAddToStore);
        }
        for(int i = 0 ; i < quantity ; i++){
            expirationDates.remove(sold[i]);
        }
        return sold;
    }

    public void markAsDamaged(Integer barcode, String reason){
        /**
         * Marks a product with the given barcode as damaged, with the specified reason.
         * Adds the barcode and reason to the map of damaged products.
         *
         * @param barcode the barcode of the product to mark as damaged
         * @param reason  the reason why the product was marked as damaged
         */
        damagedProducts.put(barcode,reason);
    }

    public void setExpirationDates(HashMap<Integer, Date> expirationDates) {
        this.expirationDates = expirationDates;
    }

    public void setDamagedProducts(HashMap<Integer, String> damagedProducts) {
        this.damagedProducts = damagedProducts;
    }
}
