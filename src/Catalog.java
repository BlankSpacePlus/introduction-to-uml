import java.util.ArrayList;
import java.util.Iterator;

/**
 * 产品清单目录类
 *
 * @see Product
 */
public class Catalog implements Iterable<Product> {

    private final ArrayList<Product> products;

    public Catalog() {
        this.products = new ArrayList<>();
    }

    public void addProduct(Product product) {
        this.products.add(product);
    }

    @Override
    public Iterator<Product> iterator() {
        return this.products.iterator();
    }

    public Product getProduct(String productCode) {
        Iterator<Product> iterator = this.products.iterator();
        Product product;
        do {
            if (!iterator.hasNext()) {
                return null;
            }
            product = iterator.next();
        } while (!product.getCode().equals(productCode));
        return product;
    }

    public int getNumberOfProducts() {
        return this.products.size();
    }

}
