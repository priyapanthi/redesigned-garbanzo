public class OrderExecution{
    Long orderId;
    String stock;
    Double amount;
    Integer units;

    public OrderExecution(Long orderId, String stock, Double amount, Integer units){
        this.orderId = orderId;
        this.stock = stock;
        this.amount = amount;
        this.units = units;
    }

    public void print(){
        System.out.println(orderId+" "+stock+" "+amount+" "+units);
    }
}