package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.dto.TransactionDTO;
import ar.com.plug.examen.domain.exception.TransactionCreateException;
import ar.com.plug.examen.domain.exception.TransactionException;
import ar.com.plug.examen.domain.model.Transaction;
import ar.com.plug.examen.domain.service.TransactionService;
import ar.com.plug.examen.domain.utils.Utils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import java.time.LocalDate;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;


/**
 * 
 * @author vocal
 *
 */
//SWAGGER
//Documentation http://localhost:8080/payments/swagger-ui/index.html
///target/site/jacoco/index.html
@RestController
@RequestMapping(path = "/transactions")
@Tag(name = "Transaction Management")
public class TransactionController {
	
	final Logger logger = LoggerFactory.getLogger(TransactionController.class);

	@Autowired
    private TransactionService transactionService;

	@Operation(summary = "This method finds a transation by id")
	@GetMapping(path="findById/{id}" , produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Transaction>  findById(@PathVariable Long id) {
		logger.info("Get transaction with id " + id);
		Transaction transaction= transactionService.findById(id);
		if(transaction==null) {
	           throw new TransactionException("Transaction not found");			
			}
		logger.info("Transaction got with id " + id);
		return new ResponseEntity<Transaction>(transaction, HttpStatus.OK);				
	}
	
	@Operation(summary = "This method get all transactions available")
	@GetMapping(path="findAll" , produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Transaction>>  findAll() {
		logger.info("Get transactions");
		List<Transaction> transactions= transactionService.findAll();
		return new ResponseEntity<List<Transaction> >(transactions, HttpStatus.OK);				
	}
	
	@Operation(summary = "This method finds a transation between two dates")
	@GetMapping(path="findByDateBetween" , produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Transaction>>  findByDateBetween(@RequestParam String from,  @RequestParam String to) {
		logger.info("Get transactions by date");
		List<Transaction> transactions= transactionService.findByDateBetween(LocalDate.parse(from),LocalDate.parse(to));
		return new ResponseEntity<List<Transaction> >(transactions, HttpStatus.OK);				
	}
	
	@Operation(summary = "This method finds approved transactions")
	@GetMapping(path="findByApproved" , produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Transaction>>  findByApproved(@RequestParam Boolean approved) {
		logger.info("Get transactions by approved");
		List<Transaction> transactions= transactionService.findByApproved(approved);
		return new ResponseEntity<List<Transaction> >(transactions, HttpStatus.OK);				
	}
	
	@Operation(summary = "This method transactions between two amounts")
	@GetMapping(path="findByAmountBetween" , produces = {MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<List<Transaction>>  findByAmountBetween(@RequestParam Double min, @RequestParam Double max) {
		logger.info("Get transactions by amount");
		List<Transaction> transactions= transactionService.findByAmountBetween(min, max);
		return new ResponseEntity<List<Transaction> >(transactions, HttpStatus.OK);				
	}
	
	@Operation(summary = "This method change transactions to approved")
	@PutMapping(path="approveTransaction/{id}" , produces = {MediaType.APPLICATION_JSON_VALUE },consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Transaction>  approveTransaction(@PathVariable Long id) {
		logger.info("Approve Transaction");
		Transaction transaction=transactionService.findById(id);
		
		if(transaction==null) {
           throw new TransactionException("Transaction not found");			
		}
		transaction.setApproved(true);
		Transaction transactionApproved= transactionService.update(id,transaction);
		return new ResponseEntity<Transaction>(transactionApproved, HttpStatus.OK);				
	}
	
	@Operation(summary = "Create transaction")
	@PostMapping(path = "", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Transaction> create(@RequestBody TransactionDTO transactionDTO) {
		logger.info("Create transaction");
		Transaction transaction = transactionService.save(Utils.convertToEntity(transactionDTO));
		
        if (transaction == null) {
            throw new TransactionCreateException("Transaction not created");
        }
		logger.info("Transaction created:" + "id" + transaction.getId());
		return new ResponseEntity<Transaction>(transaction, HttpStatus.CREATED);
	}
	
	


}
