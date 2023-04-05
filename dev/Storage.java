public class Storage {
    private Shelf[] shelves;

    public Storage(int numberOfShelves) {
        this.shelves = new Shelf[numberOfShelves];
    }

    public Shelf[] getShelves() {
        return shelves;
    }

}
