public class Order{
    Long orderId;
    String stock;
    String time;
    OrderType type;
    Double amount;
    Integer units;

    public Order(Long orderId, String stock, String time, OrderType type, Double amount, Integer units){
        this.orderId = orderId;
        this.stock = stock;
        this.time = time;
        this.type = type;
        this.amount = amount;
        this.units = units;
    }
}