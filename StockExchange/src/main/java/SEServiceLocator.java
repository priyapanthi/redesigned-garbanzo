public class SEServiceLocator {

    private static SEServiceLocator locator;
    IOrderDao orderDao;
    IOrderService orderService;
    IExecutionService executionService;
    IOrderController orderController;

    private SEServiceLocator(){
        this.orderDao = new InMemoryOrderDao();
        this.orderService = new OrderService(orderDao);
        this.executionService = new ExecutionService(orderService);
        this.orderController = new StdinController(executionService, orderService);
    }

    public static synchronized SEServiceLocator getInstance(){
        if(locator==null)
            locator = new SEServiceLocator();
        return locator;
    }

    public IOrderService orderService(){
        return orderService;
    }

    public IExecutionService executionService(){
        return executionService;
    }

    public IOrderController orderController(){
        return orderController;
    }
}
