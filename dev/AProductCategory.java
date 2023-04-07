public abstract class AProductCategory {

    protected String name;
    private double discount;

    public AProductCategory(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public double getDiscount() {
        return discount;
    }

    public void setDiscount(double discount) {
        this.discount = discount;
    }
}
