import java.util.ArrayList;
import java.util.Iterator;

/**
 * 交易类，Order集合
 *
 * @see Order
 */
public class Sales implements Iterable<Order> {

    private ArrayList<Order> orders;

    public Sales() {
        this.orders = new ArrayList<>();
    }

    public void addOrder(Order order) {
        this.orders.add(order);
    }

    public Iterator<Order> iterator() {
        return this.orders.iterator();
    }

    public int getNumberOfOrders() {
        return this.orders.size();
    }

}
