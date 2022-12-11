import java.util.List;

public interface IOrderController {
    void createOrder(Order order);
    List<OrderExecution> execute(String stock);

    List<OrderExecution> executeAll();

}
