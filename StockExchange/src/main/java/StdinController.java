import java.util.List;

public class StdinController implements IOrderController{

    IExecutionService executionService;
    IOrderService orderService;

    public StdinController(IExecutionService executionService, IOrderService orderService){
        this.executionService = executionService;
        this.orderService = orderService;
    }
    @Override
    public void createOrder(Order order) {
        orderService.createOrder(order);
    }

    @Override
    public List<OrderExecution> execute(String stock) {
        return executionService.executeOrdersForStock(stock);
    }

    @Override
    public List<OrderExecution> executeAll() {
        return executionService.executeAllOrders();
    }
}
