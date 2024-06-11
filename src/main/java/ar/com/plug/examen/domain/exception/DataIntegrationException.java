package ar.com.plug.examen.domain.exception;

import lombok.Data;

@Data
public class DataIntegrationException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 9140053432835253487L;
	private Long value;

	public DataIntegrationException(String message,Long value) {
        super(message);
        this.value = value;
    }
	
	public DataIntegrationException(String message) {
        super(message);
    }

}
