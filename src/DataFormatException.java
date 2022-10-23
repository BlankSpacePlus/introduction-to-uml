/**
 * 自定义DataFormatException
 */
public class DataFormatException extends Exception  {

    /**
     * 序列化UID
     */
    private static final long serialVersionUID = 1L;

	public DataFormatException() {
	}

	public DataFormatException(String message) {
		super(message);
	}

}
