import java.util.ArrayList;
import java.util.Iterator;

/**
 * 订单类，OrderItem集合
 *
 * @see OrderItem
 */
public class Order implements Iterable<OrderItem> {

    private final ArrayList<OrderItem> items;

    public Order() {
        this.items = new ArrayList<>();
    }

    public void addItem(OrderItem orderItem) {
        this.items.add(orderItem);
    }

    public void removeItem(OrderItem orderItem) {
        this.items.remove(orderItem);
    }

    public Iterator<OrderItem> iterator() {
        return this.items.iterator();
    }

    public OrderItem getItem(Product product) {
        Iterator<OrderItem> iterator = this.items.iterator();
        OrderItem orderItem;
        do {
            if (!iterator.hasNext()) {
                return null;
            }
            orderItem = iterator.next();
        } while (!orderItem.getProduct().equals(product));
        return orderItem;
    }

    public int getNumberOfItems() {
        return this.items.size();
    }

    public double getTotalCost() {
        double totalCost = 0.0D;
        OrderItem orderItem;
        for (Iterator<OrderItem> iterator = this.items.iterator();
             iterator.hasNext(); totalCost += orderItem.getValue()) {
            orderItem = iterator.next();
        }
        return totalCost;
    }

}
