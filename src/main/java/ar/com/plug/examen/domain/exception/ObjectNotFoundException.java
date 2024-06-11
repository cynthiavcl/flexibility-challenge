package ar.com.plug.examen.domain.exception;

import lombok.Data;

@Data
public class ObjectNotFoundException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1535175866769059943L;
	/**
	 * 
	 */

	private Long value;

	public ObjectNotFoundException(String message,Long value) {
        super(message);
        this.value = value;
    }

}
