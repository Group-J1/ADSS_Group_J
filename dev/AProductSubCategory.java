public abstract class AProductSubCategory extends  AProductCategory{

    private int amount;
    private String unit;

    public AProductSubCategory(int amount, String unit) {
        super((amount + unit));
        this.amount = amount;
        this.unit = unit;
    }

    public int getAmount() { return amount;}
    public String getUnit() { return unit;}

}
