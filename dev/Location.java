public class Location {
    private int[] location;


    public Location(int shelfNumber, int indexInShelf){
        this.location = new int[] { shelfNumber,indexInShelf};       // shelf number in index 0, index in shelf in index 1.
    }
    public void setLocation(int shelfNumber, int indexInShelf){
       this.location[0] = shelfNumber;
       this.location[1] = indexInShelf;
       // shelf number in index 0, index in shelf in index 1.
    }

    public int getShelfNumber(){return location[0];}
    public  int getIndexInShelf(){return location[1];}

    public int[] getLocation(){
        return location;
    }
}
