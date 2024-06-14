package ar.com.plug.examen.domain.exception;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class GlobalExceptionHandler {
	
    @ExceptionHandler(DataAccessException.class)
    public ResponseEntity<String> handleDataAccessException(DataAccessException e) {
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Data access error: " + e.getMessage());
    } 
    
    @ExceptionHandler(TransactionException.class)
    public ResponseEntity<String> handleTransactionException(TransactionException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("TransactionException " + e.getMessage());
    } 
    
    @ExceptionHandler(TransactionCreateException.class)
    public ResponseEntity<String> handleTransactionCreateException(TransactionCreateException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("TransactionCreateException " + e.getMessage());
    } 
    
    @ExceptionHandler(TransactionUpdateException.class)
    public ResponseEntity<String> handleTransactionUpdateException(TransactionUpdateException e) {
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("TransactionUpdateException " + e.getMessage());
    } 

	@ExceptionHandler(DataIntegrationException.class)
	public ResponseEntity<String> handleDataIntegrationException(DataIntegrationException e) {
		return ResponseEntity.status(HttpStatus.CONFLICT).body("data integration violation: " + e.getMessage());
	}

	@ExceptionHandler(ObjectNotFoundException.class)
	public ResponseEntity<String> handleObjectNotFoundException(ObjectNotFoundException e) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Object not found: " + e.getMessage());
	}

	@ExceptionHandler(CustomerUpdateException.class)
	public ResponseEntity<String> handleCustomerUpdateException(CustomerCreateException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer not updated: " + e.getMessage());
	}

	@ExceptionHandler(CustomerCreateException.class)
	public ResponseEntity<String> handleCustomerCreateException(CustomerCreateException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer not created: " + e.getMessage());
	}

	@ExceptionHandler(CustomerDeleteException.class)
	public ResponseEntity<String> handleCustomerDeleteException(CustomerDeleteException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Customer not deletec: " + e.getMessage());
	}
	
	@ExceptionHandler(ProductUpdateException.class)
	public ResponseEntity<String> handleProductUpdateException(ProductUpdateException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product not updated: " + e.getMessage());
	}

	@ExceptionHandler(ProductCreateException.class)
	public ResponseEntity<String> handleProductCreateException(ProductCreateException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product not created: " + e.getMessage());
	}

	@ExceptionHandler(ProductDeleteException.class)
	public ResponseEntity<String> handleProductDeleteException(ProductDeleteException e) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Product not deleted: " + e.getMessage());
	}

}
