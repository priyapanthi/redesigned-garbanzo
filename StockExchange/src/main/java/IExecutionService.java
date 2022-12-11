import java.util.List;

public interface IExecutionService {
    List<OrderExecution> executeOrdersForStock(String stock);
    List<OrderExecution> executeAllOrders();

}
