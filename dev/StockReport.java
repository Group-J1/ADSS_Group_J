import java.util.Date;

public class StockReport extends Report{
    public StockReport(AProductCategory category) {
        Date currentDate = new Date();
        this.date = currentDate;
        this.category = category;
    }


}
