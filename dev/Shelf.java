import java.util.ArrayList;
public class Shelf {
    private Product[] items;
    private int counter;
    private int places;

    public Shelf(int numberOfPlaces) {
        items = new Product[numberOfPlaces];
        places = numberOfPlaces;
        counter = 0;
    }

    public Product[] getItems() {
        return items;
    }
    public void addItemToShelf(Product newItem, int indexInShelfIndex){
        this.items[indexInShelfIndex] = newItem;
        counter++;
    }

    public int nextFreeIndex(){
        if(counter < places)
            return counter;
        return -1;    //if no space in the shelf.
    }
}