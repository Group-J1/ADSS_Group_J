public class Chain {
    int numberOfMarkets;
    Market[] markets;

    public Chain(Integer numberOfMarkets) {
        this.numberOfMarkets = numberOfMarkets;
        markets = new Market[numberOfMarkets];
    }
    public Market getMarketByIndex(int index){return markets[index];}

    public int getNumberOfMarkets() {
        return numberOfMarkets;
    }
}
