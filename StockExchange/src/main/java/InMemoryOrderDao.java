import java.util.*;

public class InMemoryOrderDao implements IOrderDao{

    Map<String, TreeSet<Order>> buyOrders;
    Map<String, TreeSet<Order>> sellOrders;

    Comparator<Order> sellComparator =
            (a,b) -> a.amount.equals(b.amount)?a.time.compareTo(b.time):Double.compare(a.amount,b.amount);


    Comparator<Order> buyComparator =
            (a,b) -> a.amount.equals(b.amount)?a.time.compareTo(b.time):Double.compare(b.amount,a.amount);

    public InMemoryOrderDao(){
        buyOrders = new HashMap<>();
        sellOrders = new HashMap<>();
    }

    @Override
    public void createOrder(Order order) {
        if (order.type.equals(OrderType.sell)) {
            if (!sellOrders.containsKey(order.stock))
                sellOrders.put(order.stock, new TreeSet<>(sellComparator));
            sellOrders.get(order.stock).add(order);

        } else {
            if (!buyOrders.containsKey(order.stock))
                buyOrders.put(order.stock, new TreeSet<>(buyComparator));
            buyOrders.get(order.stock).add(order);
        }
    }

    @Override
    public Map<String, TreeSet<Order>> getAllOrders(OrderType type) {
        if(type.equals(OrderType.sell))
            return sellOrders;
        else if(type.equals(OrderType.buy))
            return buyOrders;
        else throw new RuntimeException("type not supported");
    }

    @Override
    public TreeSet<Order> getOrdersOfAStock(String stock, OrderType type) {
        if(type.equals(OrderType.sell))
            return sellOrders.get(stock);
        else if(type.equals(OrderType.buy))
            return buyOrders.get(stock);
        else throw new RuntimeException("type not supported");
    }

    @Override
    public void updateUnitsInOrder(Long orderId, Integer units, OrderType type, String stock) {
        TreeSet<Order> ordersSet = type.equals(OrderType.sell)?sellOrders.get(stock):buyOrders.get(stock);
        Optional<Order> order = ordersSet.stream().filter(order1 -> order1.orderId.equals(orderId)).findFirst();
        order.ifPresent(order1 -> order1.units = units);
    }

    @Override
    public void removeOrder(Order order) {
        TreeSet<Order> ordersSet = order.type.equals(OrderType.sell)?sellOrders.get(order.stock):buyOrders.get(order.stock);
        ordersSet.remove(order);
    }

}
