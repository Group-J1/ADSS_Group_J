package Business;

public class Chain {
    private int numberOfMarkets;
    private Market[] markets;

    public Chain(Integer numberOfMarkets) {
        /**
         * Creates an instance of the Business.Chain class with the given number of markets.
         * @param numberOfMarkets The number of markets in the chain as an integer.
         * @return None
         * @throws None
         */
        this.numberOfMarkets = numberOfMarkets;
        markets = new Market[numberOfMarkets];
    }

    public Market getMarketByIndex(int index) {
        /**
         * Returns the market object at the specified index.
         * @param index The index of the market object to retrieve.
         * @return The market object at the specified index, or null if the index is out of range.
         * @throws None
         */
        if(index < numberOfMarkets){
            return markets[index];
        }
        return null;
    }
    public Market[] getMarkets() {
        /**
         * Returns an array of all the market objects in the chain.
         * @param None.
         * @return An array of market objects.
         * @throws None
         */
        return markets;
    }


    public int getNumberOfMarkets() {
        /**
         * Returns the number of markets in the chain.
         * @param None
         * @return The number of markets in the chain as an integer.
         * @throws None
         */
        return numberOfMarkets;
    }
}