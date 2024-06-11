package ar.com.plug.examen.domain.exception;




public class TransactionCreateException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -7279927413836622210L;
	/**
	 * 
	 */
	private Long value;

	public TransactionCreateException(String message,Long value) {
        super(message);
        this.value = value;
    }
	
	public TransactionCreateException(String message) {
        super(message);
    }

}
