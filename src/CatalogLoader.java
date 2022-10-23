import java.io.IOException;

/**
 * 获取产品目录
 *
 * @see Catalog
 */
public interface CatalogLoader {

    /**
     * 使用指定文件中的数据加载产品目录.
     *
     * @param fileName 存储calalog信息的文件名
     * @return catalog对象
     * @throws IOException 读取指定文件中的信息时出错
     * @throws DataFormatException 文件包含格式错误的数据
     */
    Catalog loadCatalog(String fileName) throws IOException, DataFormatException;

}
