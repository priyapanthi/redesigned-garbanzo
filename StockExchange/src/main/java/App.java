import java.util.List;
import java.util.Scanner;

public class App {
    public static void main(String args[] ) throws Exception {
        Scanner scanner = new Scanner(System.in);

        IOrderController orderController = SEServiceLocator.getInstance().orderController();
        while(true){
            String input = scanner.nextLine();
            String[] inputs = input.split(" ");
            Long orderId = Long.parseLong(inputs[0]);
            String time = inputs[1];
            String stock = inputs[2];
            OrderType orderType = OrderType.valueOf(inputs[3]);
            Integer units = Integer.parseInt(inputs[4]);
            Double amount = Double.parseDouble(inputs[5]);
            Order order = new Order(orderId, stock, time, orderType, amount, units);
            orderController.createOrder(order);
            List<OrderExecution> executions = orderController.execute(stock);
            for(OrderExecution execution: executions){
                execution.print();
            }


        }
    }
}