package ar.com.plug.examen.domain.exception;

import lombok.Data;

@Data
public class ProductDeleteException extends RuntimeException {
    /**
	 * 
	 */
	private static final long serialVersionUID = -4930150738653139671L;
	private Long value;

	public ProductDeleteException(String message,Long value) {
        super(message);
        this.value = value;
    }

}
