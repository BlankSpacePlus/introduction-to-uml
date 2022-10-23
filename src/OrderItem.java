/**
 * 订单条目类
 */
public class OrderItem {

    private Product product;

    private int quantity;

    public OrderItem(Product product, int quantity) {
        this.product = product;
        this.quantity = quantity;
    }

    public Product getProduct() {
        return this.product;
    }

    public int getQuantity() {
        return this.quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public double getValue() {
        return this.getProduct().getPrice() * (double) this.getQuantity();
    }

    public String toString() {
        return this.getQuantity() + " " + this.getProduct().getCode() + " " + this.getProduct().getPrice();
    }

}
