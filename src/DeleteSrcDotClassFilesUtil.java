import java.io.File;

/**
 * 早期为方便删除src目录下*.class文件而完成的工具类，不属于系统
 */
public class DeleteSrcDotClassFilesUtil {

    public static void main(String[] args) {
        File file = new File("D:/IDEA/java-gourmet-coffee/src");
        deleteFile(file);
    }

    private static void deleteFile(File file) {
        if (file == null || !file.exists()) {
            System.out.println("文件删除失败,请检查文件路径是否正确");
            return;
        }
        // 取得这个目录下的所有子文件对象
        File[] files = file.listFiles();
        // 遍历该目录下的文件对象
        if (files != null) {
            for (File f : files) {
                if (f != null) {
                    // 打印文件名
                    String name = file.getName();
                    System.out.println(name);
                    // 判断子目录是否存在子目录，如果是文件则删除
                    if (f.isDirectory()) {
                        deleteFile(f);
                    } else if (f.getName().endsWith(".class")) {
                        f.delete();
                    }
                }
            }
        }
        // 删除空文件夹，for循环已经把上一层节点的目录清空
        file.delete();
    }

}
