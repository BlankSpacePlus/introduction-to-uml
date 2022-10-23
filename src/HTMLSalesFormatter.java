/**
 * Sales-HTML格式策略模式实现类
 *
 * @see SalesFormatter
 */
public class HTMLSalesFormatter implements SalesFormatter {

    private static HTMLSalesFormatter singletonInstance;

    private HTMLSalesFormatter() {
    }

    public static synchronized HTMLSalesFormatter getSingletonInstance() {
        if (singletonInstance == null)
            singletonInstance = new HTMLSalesFormatter();
        return singletonInstance;
    }

    @Override
    public String formatSales(Sales sales) {
        StringBuilder string = new StringBuilder();
        string.append("<html>\r\n  <body>\r\n    <center><h2>Orders</h2></center>\r\n");
        for (Order order : sales) {
            string.append("    <hr>\r\n    <h4>Total = ").append(order.getTotalCost())
                    .append("</h4>\r\n      <p>\r\n");
            for (OrderItem orderItem : order) {
                string.append("        <b>code:</b> ").append(orderItem.getProduct().getCode())
                        .append("<br>\r\n").append("        <b>quantity:</b> ")
                        .append(orderItem.getQuantity()).append("<br>\r\n")
                        .append("        <b>price:</b> ")
                        .append(orderItem.getProduct().getPrice()).append("\r\n");
            }
            string.append("      </p>\r\n");
        }
        string.append("  </body>\r\n</html>\r\n");
        return string.toString();
    }

}
