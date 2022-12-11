import java.util.Map;
import java.util.TreeSet;

public interface IOrderDao {
    void createOrder(Order order);

    Map<String, TreeSet<Order>> getAllOrders(OrderType type);

    TreeSet<Order> getOrdersOfAStock(String stock, OrderType type);

    void updateUnitsInOrder(Long orderId, Integer units, OrderType type, String stock);

    void removeOrder(Order order);

}
