public class Store {

    private Shelf[] shelves;

    public Store(int numberOfShelves) {
        this.shelves = new Shelf[numberOfShelves];
    }

    public Shelf[] getShelves() {
        return shelves;
    }
}

