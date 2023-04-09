public class Chain {
    private int numberOfMarkets;
    private Market[] markets;

    public Chain(Integer numberOfMarkets) {
        this.numberOfMarkets = numberOfMarkets;
        markets = new Market[numberOfMarkets];
    }

    public Market getMarketByIndex(int index) {
        if(index < numberOfMarkets){
            return markets[index];
        }
        return null;
    }
    public Market[] getMarkets() {
        return markets;
    }


    public int getNumberOfMarkets() {
        return numberOfMarkets;
    }
}