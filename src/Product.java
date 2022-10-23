/**
 * 产品父类，也代表咖啡配件
 */
public class Product {

    private String code;

    private String description;

    private double price;

    public Product(String code, String description, double price) {
        this.code = code;
        this.description = description;
        this.price = price;
    }

    public String getCode() {
        return this.code;
    }

    public String getDescription() {
        return this.description;
    }

    public double getPrice() {
        return this.price;
    }

    public boolean equals(Object object) {
        return object instanceof Product && this.getCode().equals(((Product) object).getCode());
    }

    public String toString() {
        return this.getCode() + "_" + this.getDescription() + "_" + this.getPrice();
    }

}
