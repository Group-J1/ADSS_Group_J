public class Location {
    private int[] location;


    public Location(int shelfNumber, int indexInShelf){
        /**
         * Constructs a new Location object with the given shelf number and index in shelf.
         * @param shelfNumber the shelf number of the location, where the shelf number is represented as an integer
         * @param indexInShelf the index in the shelf of the location, where the index in shelf is represented as an integer
         * @throws None
         * @return None
         */
        this.location = new int[] { shelfNumber,indexInShelf};       // shelf number in index 0, index in shelf in index 1.
    }
    public void setLocation(int shelfNumber, int indexInShelf){
        /**
         * Set the location of the product in the store.
         * @param shelfNumber The number of the shelf the product is located on.
         * @param indexInShelf The index of the product's location within the shelf.
         * @throws IndexOutOfBoundsException if either the shelfNumber or indexInShelf is negative.
         * @return None
         */
        this.location[0] = shelfNumber;
        this.location[1] = indexInShelf;

    }

    public int getShelfNumber(){return location[0];}
    public  int getIndexInShelf(){return location[1];}

    public int[] getLocation(){
        /**
         * Returns the location of an item as an array of two integers representing the shelf number and index in shelf, respectively.
         * @return an array of two integers representing the shelf number and index in shelf of the item's location.
         */
        return location;
    }
}
