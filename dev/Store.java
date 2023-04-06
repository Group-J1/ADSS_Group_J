public class Store {

    private Shelf[] shelves;

    public Store(int numberOfShelves) {
        this.shelves = new Shelf[numberOfShelves];
    }

    public Shelf[] getShelves() {
        return shelves;
    }

    public void updateStoreShelvesNumber(int newNumberOfShelves){
        Shelf[] newShelves = new Shelf[newNumberOfShelves];
        for(int i = 0; i<this.shelves.length; i++){
            newShelves[i] = this.shelves[i];
        }
        this.shelves = newShelves;
    }
}

