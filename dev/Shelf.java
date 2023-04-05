import java.util.ArrayList;
public class Shelf {
    private ArrayList<Product> items;

    public Shelf() {
        items = new ArrayList<>();
    }

    public ArrayList<Product> getItems() {
        return items;
    }
    public void addItemToShelf(Product newItem){
        this.items.add(newItem);
    }
    public int numberOfItems(){
        return items.size();
    }


}
