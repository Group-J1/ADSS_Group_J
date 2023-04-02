public abstract class AProductCategory extends AProduct implements IProductSubCategory{

    private String name;
    private double discount;
    public int getAmount() {
        return amount;
    }

    public int getUnit() {
        return amount;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
