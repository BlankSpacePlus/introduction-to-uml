/**
 * Sales-Text格式策略模式实现类
 *
 * @see SalesFormatter
 */
public class PlainTextSalesFormatter implements SalesFormatter {
    
    private static PlainTextSalesFormatter singletonInstance;   
    
    private PlainTextSalesFormatter() {   
    }   
       
    public static synchronized PlainTextSalesFormatter getSingletonInstance() {   
        if (singletonInstance == null) {
            singletonInstance = new PlainTextSalesFormatter();
        }
        return singletonInstance;   
    }   

    @Override
    public String formatSales(Sales sales) {   
        StringBuilder string = new StringBuilder();
        int i = 1;
        for (Order order : sales) {
            string.append("---------------------\r\n");
            string.append("Order ").append(i).append("\r\n\r\n");
            for (OrderItem orderItem : order) {
                string.append(orderItem.getQuantity()).append(" ")
                        .append(orderItem.getProduct().getCode()).append(" ")
                        .append(orderItem.getProduct().getPrice()).append("\r\n");
            }
            i++;
            string.append("\r\n" + "Total = ").append(order.getTotalCost()).append("\r\n");
        }  
        return string.toString();
    }   

}
