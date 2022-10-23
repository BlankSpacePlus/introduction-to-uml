/**
 * 咖啡机类
 *
 * @see Product
 */
public class CoffeeBrewer extends Product {

    private String model;

    private String waterSupply;

    private int numberOfCups;

    public CoffeeBrewer(String code, String description, double price,
                        String model, String waterSupply, int numberOfCups) {
        super(code, description, price);
        this.model = model;
        this.waterSupply = waterSupply;
        this.numberOfCups = numberOfCups;
    }

    public String getModel() {
        return this.model;
    }

    public String getWaterSupply() {
        return this.waterSupply;
    }

    public int getNumberOfCups() {
        return this.numberOfCups;
    }

    public String toString() {
        return super.toString() + "_" + this.getModel() + "_" + this.getWaterSupply() +
                "_" + this.getNumberOfCups();
    }

}
