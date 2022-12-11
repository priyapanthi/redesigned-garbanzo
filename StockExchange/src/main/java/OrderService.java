import java.util.Map;
import java.util.TreeSet;

public class OrderService implements IOrderService{

    IOrderDao orderDao;

    public OrderService(IOrderDao orderDao){
        this.orderDao = orderDao;
    }

    @Override
    public void createOrder(Order order) {
        orderDao.createOrder(order);
    }

    @Override
    public Map<String, TreeSet<Order>> getOrdersByType(OrderType type) {
        return orderDao.getAllOrders(type);
    }

    @Override
    public TreeSet<Order> getOrdersByStockAndType(OrderType type, String stock) {
        return orderDao.getOrdersOfAStock(stock, type);
    }

    @Override
    public void updateUnitsInOrder(Order order, Integer units) {
        orderDao.updateUnitsInOrder(order.orderId, units, order.type, order.stock);
    }

    @Override
    public void removeOrder(Order order) {
        orderDao.removeOrder(order);
    }

}
