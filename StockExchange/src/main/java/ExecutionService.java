import java.util.*;

public class ExecutionService implements IExecutionService{

    IOrderService orderService;

    public ExecutionService(IOrderService orderService){
        this.orderService = orderService;
    }

    @Override
    public List<OrderExecution> executeOrdersForStock(String stock) {

        List<OrderExecution> executions = new ArrayList<>();

        TreeSet<Order> buySet = orderService.getOrdersByStockAndType(OrderType.buy, stock);
        if(buySet==null || buySet.isEmpty())
            return executions;

        List<Order> buyRemove = new ArrayList<>();

        for(Order buy: buySet) {
            List<Order> sellRemove = new ArrayList<>();
            TreeSet<Order> sellSet = orderService.getOrdersByStockAndType(OrderType.sell, stock);
            if(sellSet==null || sellSet.isEmpty())
                break;
            Iterator<Order> sellIterator = sellSet.iterator();
            while (sellIterator.hasNext()) {
                Order sell = sellIterator.next();
                if (sell.amount <= buy.amount) {
                    int unitsExecuted = buy.units > sell.units? sell.units : buy.units;
                    int buyUnits = buy.units-unitsExecuted, sellUnits = sell.units-unitsExecuted;
                    executions.add(new OrderExecution(sell.orderId, sell.stock, sell.amount, unitsExecuted));
                    executions.add(new OrderExecution(buy.orderId, buy.stock, sell.amount, unitsExecuted));
                    orderService.updateUnitsInOrder(buy, buy.units-unitsExecuted);
                    orderService.updateUnitsInOrder(sell, sell.units-unitsExecuted);
                    if(buyUnits==0) {
                        buyRemove.add(buy);
                        break;
                    }
                    if(sellUnits==0) sellRemove.add(sell);
                } else {
                    break;
                }

            }
            for(Order removeOrder: sellRemove)
                orderService.removeOrder(removeOrder);

            sellRemove.clear();

        }

        for(Order removeOrder: buyRemove)
            orderService.removeOrder(removeOrder);
        return executions;
    }

    @Override
    public List<OrderExecution> executeAllOrders() {
        List<OrderExecution> executions = new ArrayList<>();
        Map<String, TreeSet<Order>> buyOrders = orderService.getOrdersByType(OrderType.buy);

        for(Map.Entry<String, TreeSet<Order>> entry: buyOrders.entrySet()){
            executions.addAll(executeOrdersForStock(entry.getKey()));
        }

        return executions;
    }
}
