package Stock.Business;

import Stock.DataAccess.ProductDetailsDAO;

public class Shelf {
    private Product[] items;
//    private int counter;
    private int places;

    public Shelf(int numberOfPlaces) {
        /**
         * Constructs a new Stock.Business.Shelf object with the specified number of places.
         *
         * This constructor creates a new Stock.Business.Shelf object with the specified number of places to hold Stock.Business.Product items. The shelf is
         * initially empty, with all places being null. The number of places must be a non-negative integer.
         *
         * @param numberOfPlaces the number of places on the shelf
         * @throws IllegalArgumentException if the number of places is negative
         */
        items = new Product[numberOfPlaces];
        places = numberOfPlaces;
//        counter = 0;
    }

    public Product[] getItems() {
        return items;
    }
    public void addItemToShelf(Product newItem, int indexInShelfIndex){
        /**
         * Adds a new item to the specified index on the shelf.
         *
         * This method adds the specified Stock.Business.Product item to the shelf at the specified index. If the index is already occupied by
         * another item, the previous item will be overwritten. The counter of items on the shelf is incremented by 1 after the
         * addition of the new item.
         *
         * @param newItem the new Stock.Business.Product item to add to the shelf
         * @param indexInShelfIndex the index in the shelf where the item will be added
         * @throws ArrayIndexOutOfBoundsException if the specified index is out of bounds for the shelf
         */
        this.items[indexInShelfIndex] = newItem;
//        counter++;
        ProductDetailsDAO.getInstance();
        ProductDetailsDAO.updateStorageIndexInShelf();
    }

    public int nextFreeIndex(){
        /**
         * Returns the next free index on the shelf.
         *
         * This method returns the next free index on the shelf. If there are still empty places on the shelf, the index of the
         * first empty place is returned. Otherwise, -1 is returned to indicate that there is no free space on the shelf.
         *
         * @return the index of the next free place on the shelf, or -1 if the shelf is full
         */
        ProductDetailsDAO.getInstance();

        if(ProductDetailsDAO.getStorageIndexInShelf() < places)
            return ProductDetailsDAO.getStorageIndexInShelf();
        ProductDetailsDAO.resetIndexInShelf();
        return -1;    //if no space in the shelf.
    }
}