package Business;

public class AProductCategory {

    protected String name;
    private double discount;

    public AProductCategory(String name) {
        /**
         * Creates an instance of the Bussiness.AProductCategory class with the given name.
         * @param name The name of the product category.
         * @return None
         * @throws None
         */
        this.name = name;
    }


    public String getName() {
        /**
         * Returns the name of this product category.
         * @return The name of the product category.
         * @throws None
         */
        return name;
    }

    public double getDiscount() {
        /**
         * Returns the discount amount for this product category.
         * @return The discount amount as a double.
         * @throws None
         */
        return discount;
    }

    public void setDiscount(double discount) {
        /**
         * Sets the discount amount for this product category.
         * @param discount The discount amount as a double.
         * @return None
         * @throws None
         */
        this.discount = discount;
    }
}
