public class Storage {
    private Shelf[] shelves;
    private int currShelf;
    private int amountOfShelves;

    public Storage(int numberOfShelves) {
        /**
         * Constructs a new Storage object with the specified number of shelves.
         *
         * @param numberOfShelves the number of shelves to create in the Storage object
         */
        this.shelves = new Shelf[numberOfShelves];
        currShelf = 0;
        amountOfShelves = numberOfShelves;
        for(int i =0; i< numberOfShelves; i++){
            shelves[i] = new Shelf(30);
        }
    }

    public Shelf[] getShelves() {
        return shelves;
    }

    public int getCurrShelf() {
        return currShelf;
    }

    public int getAmountOfShelves() {
        return amountOfShelves;
    }

    // Add new product from UI menu
    public Location addProductToStorage(Product product){
        /**
         * Adds a given Product object to the first available location in the Storage object.
         *
         * @param product the Product object to add to the Storage object
         * @return a Location object representing the location where the product was added, or null if the Storage object is full
         */
        Location loc = null;
        boolean running = true;
        while (running) {
            int indexInShelf = shelves[currShelf].nextFreeIndex();
            if (indexInShelf != -1) {
                loc = new Location(currShelf,indexInShelf);
                product.setStorageLocation(loc);
                shelves[currShelf].addItemToShelf(product,indexInShelf);
                running = false;
            }
            else if (currShelf == amountOfShelves - 1) {
                running = false;

            }
            else if (currShelf < amountOfShelves - 1){
                currShelf++;
            }
        }

        return loc;
    }

    public void updateStorageShelvesNumber(int NumberOfShelvesToAdd){
        /**
         * Updates the number of shelves in the Storage object by adding the specified number of new shelves.
         *
         * @param numberOfShelvesToAdd the number of new shelves to add to the Storage object
         */
        Shelf[] newShelves = new Shelf[amountOfShelves + NumberOfShelvesToAdd];
        for(int i = 0; i < amountOfShelves + NumberOfShelvesToAdd; i++){
            newShelves[i] = newShelves[30];
        }
        for(int i = 0; i<this.shelves.length; i++){
            newShelves[i] = this.shelves[i];
        }
        this.shelves = newShelves;
        amountOfShelves = amountOfShelves + NumberOfShelvesToAdd;
    }
}
