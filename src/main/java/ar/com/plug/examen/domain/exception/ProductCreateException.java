package ar.com.plug.examen.domain.exception;


public class ProductCreateException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6870760118475742956L;
	private Long value;

	public ProductCreateException(String message,Long value) {
        super(message);
        this.value = value;
    }
	
	public ProductCreateException(String message) {
        super(message);
    }

}
