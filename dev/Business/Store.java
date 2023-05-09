package Business;

public class Store {

    private Shelf[] shelves;
    private int currShelf;
    private int amountOfShelves;

    public Store(int numberOfShelves) {
        /**
         * Constructs a new Business.Store object with the specified number of shelves.
         *
         * @param numberOfShelves the number of shelves to create in the Business.Store object
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
    public Location addProductToStore(Product product){
        /**
         * Adds the specified product to the Business.Store object by placing it on the next available shelf.
         *
         * @param product the product to add to the Business.Store object
         * @return the Business.Location object representing the location where the product was placed in the Business.Store object, or null if no location was available
         */
        Location loc = null;
        boolean running = true;
        while (running) {
            int indexInShelf = shelves[currShelf].nextFreeIndex();
            if (indexInShelf != -1) {
                loc = new Location(currShelf,indexInShelf);
                product.setStoreLocation(loc);
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
    
    
    public void updateStoreShelvesNumber(int NumberOfShelvesToAdd){
        /**
         * Updates the number of shelves in the store by adding the specified number of shelves.
         *
         * @param numberOfShelvesToAdd The number of shelves to add to the store.
         *                             Must be a positive integer.
         */
        Shelf[] newShelves = new Shelf[amountOfShelves + NumberOfShelvesToAdd];
        for (int i = 0 ; i < amountOfShelves + NumberOfShelvesToAdd; i++){
            newShelves[i] = new Shelf(30);
        }
        for(int i = 0; i<this.shelves.length; i++){
            newShelves[i] = this.shelves[i];
        }
        this.shelves = newShelves;
        amountOfShelves = amountOfShelves + NumberOfShelvesToAdd;
    }
}

