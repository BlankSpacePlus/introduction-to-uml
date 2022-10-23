import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;

/**
 * 咖啡系统入口
 *
 * @see Product
 * @see Coffee
 * @see CoffeeBrewer
 * @see Catalog
 * @see OrderItem
 * @see Order
 * @see SalesFormatter
 * @see PlainTextSalesFormatter
 * @see HTMLSalesFormatter
 * @see XMLSalesFormatter
 * @see CatalogLoader
 * @see FileCatalogLoader
 */
public class GourmetCoffee {

    private static final BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));

    private static final PrintWriter stdOut = new PrintWriter(System.out, true);

    private static final PrintWriter stdErr = new PrintWriter(System.err, true);

    private Catalog catalog;

    private Sales sales;

    private SalesFormatter salesFormatter;

    /**
     * 从文件加载目录数据并启动应用程序
     * <p>
     * 文件的名称在命令参数中指定
     * </p>
     *
     * @param args String arguments.
     * @throws IOException if there are errors in the input.
     */
    public static void main(String[] args) throws IOException {
        Catalog catalog = null;
        if (args.length != 1) {
            stdErr.println("Usage: java GourmetCoffee filename");
        } else {
            try {
                catalog = (new FileCatalogLoader()).loadCatalog(args[0]);
            } catch (FileNotFoundException fnfe) {
                stdErr.println("The file does not exist");
                System.exit(1);
            } catch (DataFormatException dfe) {
                stdErr.println("The file contains malformed data: "
                        + dfe.getMessage());
                System.exit(1);
            }
            GourmetCoffee application = new GourmetCoffee(catalog);
            application.run();
        }
    }

    /**
     * 构造一个 <code>GourmetCoffee</code> 对象，使用参数中指定的值初始化Catalog数据。
     *
     * @param initialCatalog a product catalog
     */
    private GourmetCoffee(Catalog initialCatalog) {
        this.catalog = initialCatalog;
        this.sales = new Sales();
        this.salesFormatter = PlainTextSalesFormatter.getSingletonInstance();
        loadSales();
    }

    /**
     * 初始化Sales对象
     */
    private void loadSales() {
        Order orderOne = new Order();
        Product productOne = this.catalog.getProduct("C001");
        if (productOne != null) {
            orderOne.addItem(new OrderItem(productOne, 5));
            this.sales.addOrder(orderOne);
        }
        Order orderTwo = new Order();
        Product productTwo = this.catalog.getProduct("C002");
        Product productThree = this.catalog.getProduct("A001");
        if ((productTwo != null) && (productThree != null)) {
            orderTwo.addItem(new OrderItem(productTwo, 2));
            orderTwo.addItem(new OrderItem(productThree, 2));
            this.sales.addOrder(orderTwo);
        }
        Order orderThree = new Order();
        Product productFour = this.catalog.getProduct("B002");
        if (productFour != null) {
            orderThree.addItem(new OrderItem(productFour, 1));
            this.sales.addOrder(orderThree);
        }
    }

    /**
     * 向用户提供选项菜单并执行选定的任务
     */
    private void run() throws IOException {
        int choice = getChoice();
        while (choice != 0) {
            switch (choice) {
                case 1:
                    displayCatalog();
                    break;
                case 2:
                    this.salesFormatter = PlainTextSalesFormatter.getSingletonInstance();
                    writeFile(readFilename(), this.salesFormatter.formatSales(this.sales));
                    break;
                case 3:
                    this.salesFormatter = HTMLSalesFormatter.getSingletonInstance();
                    writeFile(readFilename(), this.salesFormatter.formatSales(this.sales));
                    break;
                case 4:
                    this.salesFormatter = XMLSalesFormatter.getSingletonInstance();
                    writeFile(readFilename(), this.salesFormatter.formatSales(this.sales));
                    break;
            }
            choice = getChoice();
        }
    }

    /**
     * 显示选项菜单并验证用户的选择.
     *
     * @return [0,4]范围内的整数
     */
    private int getChoice() throws IOException {
        int input;
        do {
            try {
                stdErr.println();
                stdErr.print("[0]  Quit\n"
                        + "[1]  Display Catalog\n"
                        + "[2]  Save sales (Plain Text)\n"
                        + "[3]  Save sales (HTML)\n"
                        + "[4]  Save sales (XML)\n"
                        + "choice> ");
                stdErr.flush();
                input = Integer.parseInt(stdIn.readLine());
                stdErr.println();
                if (0 <= input && 4 >= input) {
                    break;
                } else {
                    stdErr.println("Invalid choice:  " + input);
                }
            } catch (NumberFormatException nfe) {
                stdErr.println(nfe);
            }
        } while (true);
        return input;
    }

    /**
     * 输出Catalog
     */
    private void displayCatalog() {
        int size = this.catalog.getNumberOfProducts();
        if (size == 0) {
            stdErr.println("The catalog is empty");
        } else {
            for (Product product : this.catalog) {
                stdOut.println(product.getCode() + " " + product.getDescription());
            }
        }
    }

    /**
     * 创建一个新文件（具有指定名称）并将指定文本字符串写入新文件
     *
     * @param filename 文件名
     * @param content  文本内容
     */
    private void writeFile(String filename, String content) throws IOException {
        BufferedWriter buffer = new BufferedWriter(new FileWriter(filename));
        buffer.write(content);
        buffer.close();
    }

    /**
     * 提示用户输入文件名（将存储Sales信息的文件的名称）并返回用户的响应
     *
     * @return 文件名
     */
    private String readFilename() throws IOException {
        stdErr.print("Filename> ");
        stdErr.flush();
        return stdIn.readLine();
    }

}
