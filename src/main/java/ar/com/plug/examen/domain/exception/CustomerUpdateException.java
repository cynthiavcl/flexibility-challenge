package ar.com.plug.examen.domain.exception;




public class CustomerUpdateException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8018411365718641518L;
	private Long value;

	public CustomerUpdateException(String message,Long value) {
        super(message);
        this.value = value;
    }

}
