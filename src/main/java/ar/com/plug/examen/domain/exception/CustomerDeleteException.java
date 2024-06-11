package ar.com.plug.examen.domain.exception;




public class CustomerDeleteException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 9095356456048868128L;
	/**
	 * 
	 */
	private Long value;

	public CustomerDeleteException(String message,Long value) {
        super(message);
        this.value = value;
    }

}
