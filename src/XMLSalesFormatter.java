/**
 * Sales-XML格式策略模式实现类
 *
 * @see SalesFormatter
 */
public class XMLSalesFormatter implements SalesFormatter {
    
    private static XMLSalesFormatter singletonInstance;   
    
    private XMLSalesFormatter() {   
    }   
       
    public static synchronized XMLSalesFormatter getSingletonInstance() {   
        if (singletonInstance == null) {
            singletonInstance = new XMLSalesFormatter();
        }
        return singletonInstance;   
    }   

    @Override
    public String formatSales(Sales sales) {   
        StringBuilder string = new StringBuilder();
        string.append("<Sales>\r\n");
        for (Order order : sales) {
            string.append("  <Order total=\"").append(order.getTotalCost()).append("\">\r\n");
            for (OrderItem orderItem : order) {
                string.append("    <OrderItem quantity=\"").append(orderItem.getQuantity())
                        .append("\" price=\"").append(orderItem.getProduct().getPrice())
                        .append("\">").append(orderItem.getProduct().getCode())
                        .append("</OrderItem>\r\n");
            }
            string.append("  </Order>\r\n");
        }  
        string.append("</Sales>\r\n");
        return string.toString();
    }   

}
