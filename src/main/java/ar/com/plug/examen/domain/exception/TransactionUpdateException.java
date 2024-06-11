package ar.com.plug.examen.domain.exception;

import lombok.Data;

@Data
public class TransactionUpdateException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6971715334938234750L;
	private Long value;

	public TransactionUpdateException(String message,Long value) {
        super(message);
        this.value = value;
    }

}
