public class AProductSubCategory extends  AProductCategory{

    private double amount;
    private String unit;

    public AProductSubCategory(double amount, String unit) {
        /**
         * Creates an instance of the AProductSubCategory class with the given amount and unit.
         * @param amount The amount of the subcategory as a double.
         * @param unit The unit of measurement for the subcategory as a string.
         * @return None
         * @throws None
         */
        super((amount + " " + unit));
        this.amount = amount;
        this.unit = unit;
    }

    public double getAmount() { /**
     * Returns the amount of this product subcategory.
     *
     * @return The amount of the subcategory as a double.
     *
     * @throws None
     */return amount;}
    public String getUnit() {/**
     * Returns the unit of measurement for this product subcategory.
     *
     * @return The unit of measurement as a string.
     *
     * @throws None
     */ return unit;}

}
