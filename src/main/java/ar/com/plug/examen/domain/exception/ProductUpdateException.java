package ar.com.plug.examen.domain.exception;

import lombok.Data;

@Data
public class ProductUpdateException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = -7371456921823614521L;
	/**
	 * 
	 */
	private Long value;

	public ProductUpdateException(String message,Long value) {
        super(message);
        this.value = value;
    }

}
