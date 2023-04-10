import java.util.*;

public class Product {
    static int productsCounter = 0;

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

    public Product(AProductCategory category, AProductCategory subCategory, AProductSubCategory subSubCategory,
                   Location storageLocation, Location storeLocation, String manufacturer, int quantity,
                   int minimumQuantity, double weight, Date expiration) {
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
        for (int i = 0; i < quantity; i++) {
            this.expirationDates.put(++productsCounter, expiration);
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
        for (int i = 0; i < quantity; i++) {
            this.expirationDates.put(++productsCounter, expiration);
        }
    }

    public int[] sellMultipleItemsFromProduct(int quantity) {
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
        damagedProducts.put(barcode,reason);
    }

}
