import java.util.Date;
import java.util.Map;

public class Sale {
//    Fields
    private Map<String,Integer> Items;
    private double total;
    private Date date;
//    Constructor

    public Sale(Map<String, Integer> items, double total) {
        Items = items;
        this.total = total;
        this.date = new Date();
    }
//    Getters

    public Map<String, Integer> getItems() {
        return Items;
    }

    public double getTotal() {
        return total;
    }

    public Date getDate() {
        return date;
    }
//    Setters

    public void setItems(Map<String, Integer> items) {
        Items = items;
    }

    public void setTotal(double total) {
        this.total = total;
    }

    public void setDate(Date date) {
        this.date = date;
    }
}
