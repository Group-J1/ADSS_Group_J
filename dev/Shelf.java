import java.util.ArrayList;
public class Shelf {
    private Product[] items;
    private int counter;


    public Shelf(int numberOfPlaces) {
        items = new Product[numberOfPlaces];
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
        if(counter < 30)
            return counter;
        return -1;    //if no space in the shelf.
    }


}
