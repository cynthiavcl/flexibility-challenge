package ar.com.plug.examen.domain.exception;




public class CustomerCreateException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5114155324723110198L;
	/**
	 * 
	 */
	private Long value;

	public CustomerCreateException(String message,Long value) {
        super(message);
        this.value = value;
    }
	
	public CustomerCreateException(String message) {
        super(message);
    }

}
