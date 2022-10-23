import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.StringTokenizer;

/**
 * 从文件中获取Catalog目录
 *
 * @see DataFormatException
 */
public class FileCatalogLoader implements CatalogLoader {
    private final String split = "_";

    /**
     * 读取一行咖啡配件数据
     *
     * @param line 读入的一行数据
     * @return 咖啡配件数据
     * @throws DataFormatException 数据格式不符合要求
     */
    private Product readProduct(String line) throws DataFormatException {
        String code = null;
        String description = null;
        double price = 0.0;
        StringTokenizer token = new StringTokenizer(line, split);
        while (token.hasMoreTokens()) {
            token.nextToken();
            code = token.nextToken();
            description = token.nextToken();
            price = Double.parseDouble(token.nextToken());
        }
        return new Product(code, description, price);
    }

    /**
     * 读取一行咖啡数据
     *
     * @param line 读入的一行数据
     * @return 咖啡对象
     * @throws DataFormatException 数据格式不符合要求
     */
    private Coffee readCoffee(String line) throws DataFormatException {
        String code = null;
        String description = null;
        double price = 0.0;
        String origin = null;
        String roast = null;
        String flavor = null;
        String aroma = null;
        String acidity = null;
        String body = null;
        StringTokenizer token = new StringTokenizer(line, split);
        while (token.hasMoreTokens()) {
            token.nextToken();
            code = token.nextToken();
            description = token.nextToken();
            price = Double.parseDouble(token.nextToken());
            origin = token.nextToken();
            roast = token.nextToken();
            flavor = token.nextToken();
            aroma = token.nextToken();
            acidity = token.nextToken();
            body = token.nextToken();

        }
        return new Coffee(code, description, price, origin, roast, flavor, aroma, acidity, body);
    }

    /**
     * 读取一行咖啡机数据
     *
     * @param line 输入的一行数据
     * @return 咖啡机对象
     * @throws DataFormatException 数据格式不符合要求
     */
    private CoffeeBrewer readCoffeeBrewer(String line) throws DataFormatException {
        String code = null;
        String description = null;
        double price = 0.0;
        String model = null;
        String waterSupply = null;
        int numberOfCups = 0;
        StringTokenizer token = new StringTokenizer(line, split);
        while (token.hasMoreTokens()) {
            token.nextToken();
            code = token.nextToken();
            description = token.nextToken();
            price = Double.parseDouble(token.nextToken());
            model = token.nextToken();
            waterSupply = token.nextToken();
            numberOfCups = Integer.parseInt(token.nextToken());
        }
        return new CoffeeBrewer(code, description, price, model, waterSupply, numberOfCups);
    }

    @Override
    public Catalog loadCatalog(String filename) throws FileNotFoundException, IOException, DataFormatException {
        Catalog catalog = new Catalog();
        BufferedReader buffer = new BufferedReader(new FileReader(filename));
        String line = buffer.readLine();
        while (line != null) {
            if (line.startsWith("Product")) {
                catalog.addProduct(readProduct(line));
            } else if (line.startsWith("Coffee")) {
                catalog.addProduct(readCoffee(line));
            } else if (line.startsWith("Brewer")) {
                catalog.addProduct(readCoffeeBrewer(line));
            }
            line = buffer.readLine();
        }
        buffer.close();
        return catalog;
    }

}
