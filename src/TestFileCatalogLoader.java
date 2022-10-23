import java.io.IOException;
import java.io.PrintWriter;

/**
 * FileCatalogLoader类的测试类
 *
 * @see FileCatalogLoader
 */
public class TestFileCatalogLoader {

    private static final PrintWriter stdOut = new PrintWriter(System.out, true);

    private static final PrintWriter stdErr = new PrintWriter(System.err, true);

    /**
     * 类 {@link FileCatalogLoader} 的测试入口
     *
     * @param args 命令行参数
     * @throws IOException I/O异常
     */
    public static void main(String[] args) throws IOException {
        stdOut.println("");
        stdOut.println("Testing class FileCatalogLoader...");
        TestFileCatalogLoader tester = new TestFileCatalogLoader();
        tester.testLoadCatalog();
        stdOut.println("All tests passed");
        stdOut.println("");
    }

    /**
     * 如果参数 <code>condition<code> 指定的值为 <code>false</code>，则在标准错误流中显示一条消息
     *
     * @param message   错误信息
     * @param condition 测试条件
     */
    public static void assertTrue(String message, boolean condition) {
        if (!condition) {
            stdErr.print("** Test failure ");
            stdErr.println(message);
            System.exit(1);
        }
    }

    /**
     * 在标准错误流中显示消息
     *
     * @param message 错误信息
     */
    public static void fail(String message) {
        stdErr.print("** Test failure ");
        stdErr.println(message);
        System.exit(1);
    }

    /**
     * loadCatalog()方法测试
     *
     * @throws IOException I/O异常
     */
    public void testLoadCatalog() throws IOException {
        CatalogLoader loader = new FileCatalogLoader();
        try {
            // Testing an empty file
            Catalog emptyCatalog =
                    loader.loadCatalog("empty.dat");
            assertTrue("1, testing method read with an empty file",
                    emptyCatalog instanceof Catalog);
            assertTrue("2, testing method read with an empty file"
                            + emptyCatalog.getNumberOfProducts() + " products loaded",
                    emptyCatalog.getNumberOfProducts() == 0);
            // Testing a not empty file
            Catalog catalog = loader.loadCatalog("catalog.dat");
            assertTrue("3, testing method loadCatalog", catalog instanceof Catalog);
            assertTrue("4, testing method loadCatalog: "
                            + catalog.getNumberOfProducts() + " products loaded",
                    catalog.getNumberOfProducts() == 26);
            // Testing product C001
            Product product = catalog.getProduct("C001");
            assertTrue("5, testing method loadCatalog" + product.toString(),
                    product instanceof Coffee);
            Coffee coffeeC001 = (Coffee) product;
            assertTrue("6, testing method loadCatalog: " +
                            coffeeC001.toString(),
                    coffeeC001.getCode().equals("C001") &&
                            coffeeC001.getDescription().equals("Colombia, Whole, 1 lb") &&
                            coffeeC001.getPrice() == 17.99 &&
                            coffeeC001.getOrigin().equals("Colombia") &&
                            coffeeC001.getRoast().equals("Medium") &&
                            coffeeC001.getFlavor().equals("Rich and Hearty") &&
                            coffeeC001.getAroma().equals("Rich") &&
                            coffeeC001.getAcidity().equals("Medium") &&
                            coffeeC001.getBody().equals("Full"));
            // Testing product C002
            product = catalog.getProduct("C002");
            assertTrue("7, testing method loadCatalog: " + product.toString(),
                    product instanceof Coffee);
            Coffee coffeeC002 = (Coffee) product;
            assertTrue("8, testing method loadCatalog: " +
                            coffeeC002.toString(),
                    coffeeC002.getCode().equals("C002") &&
                            coffeeC002.getDescription().equals("Colombia, Ground, 1 lb") &&
                            coffeeC002.getPrice() == 18.75 &&
                            coffeeC002.getOrigin().equals("Colombia") &&
                            coffeeC002.getRoast().equals("Medium") &&
                            coffeeC002.getFlavor().equals("Rich and Hearty") &&
                            coffeeC002.getAroma().equals("Rich") &&
                            coffeeC002.getAcidity().equals("Medium") &&
                            coffeeC002.getBody().equals("Full"));
            // Testing product A001
            product = catalog.getProduct("A001");
            assertTrue("9, testing method loadCatalog: " + product.toString(),
                    product instanceof Product);
            assertTrue("10, testing method loadCatalog: " +
                            product.toString(),
                    product.getCode().equals("A001") &&
                            product.getDescription().equals("Almond Flavored Syrup") &&
                            product.getPrice() == 9.0);
            // Testing product B002
            product = catalog.getProduct("B002");
            assertTrue("11, testing method loadCatalog: " + product.toString(),
                    product instanceof CoffeeBrewer);
            CoffeeBrewer brewerB002 = (CoffeeBrewer) product;
            assertTrue("12, testing method loadCatalog: " +
                            brewerB002.toString(),
                    brewerB002.getCode().equals("B002") &&
                            brewerB002.getDescription().equals("Coffee Brewer, 2 Warmers") &&
                            brewerB002.getPrice() == 200.0 &&
                            brewerB002.getModel().equals("Brewer 200") &&
                            brewerB002.getWaterSupply().equals("Pourover") &&
                            brewerB002.getNumberOfCups() == 12);
        } catch (Exception e) {
            fail("13, testing method loadCatalog: " + e.getMessage());
        }
    }

}
