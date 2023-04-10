public class Store {

    private Shelf[] shelves;
    private int currShelf;
    private int amountOfShelves;

    public Store(int numberOfShelves) {
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
        Shelf[] newShelves = new Shelf[amountOfShelves + NumberOfShelvesToAdd];
        for(int i = 0; i<this.shelves.length; i++){
            newShelves[i] = this.shelves[i];
        }
        this.shelves = newShelves;
        amountOfShelves = amountOfShelves + NumberOfShelvesToAdd;
    }
}

