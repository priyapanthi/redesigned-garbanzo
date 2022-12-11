import java.util.Map;
import java.util.TreeSet;

public interface IOrderService {
    void createOrder(Order order);

    Map<String, TreeSet<Order>> getOrdersByType(OrderType type);

    TreeSet<Order> getOrdersByStockAndType(OrderType type, String stock);

    void updateUnitsInOrder(Order order, Integer units);
    void removeOrder(Order order);
}
