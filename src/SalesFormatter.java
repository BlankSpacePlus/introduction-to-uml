/**
 * Sales格式策略模式接口
 */
public interface SalesFormatter {

    /**
     * Sales信息
     * @param sales 一次交易
     * @return Sales格式化信息
     */
    String formatSales(Sales sales);

}
