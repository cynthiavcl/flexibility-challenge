package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.dto.CustomerDTO;
import ar.com.plug.examen.domain.exception.CustomerCreateException;
import ar.com.plug.examen.domain.model.Customer;
import ar.com.plug.examen.domain.service.CustomerService;
import ar.com.plug.examen.domain.utils.Utils;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
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
@RequestMapping(path = "/customers")
@Tag(name = "Customer Management")
public class CustomerController {

	final Logger logger = LoggerFactory.getLogger(CustomerController.class);

	@Autowired
	private CustomerService customerService;

	@Operation(summary = "Create customer")
	@PostMapping(path = "", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> create(@RequestBody CustomerDTO customerDTO) {
		logger.info("Create customer");
		Customer customer = customerService.save(Utils.convertToEntity(customerDTO));
		
        if (customer == null) {
            throw new CustomerCreateException("Customer not created");
        }
		logger.info("customer created:" + "id" + customer.getId());
		return new ResponseEntity<Customer>(customer, HttpStatus.CREATED);
	}

	@Operation(summary = "Update Customer")
	@PutMapping(path = "/{id}", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Customer> update(@PathVariable Long id, @RequestBody CustomerDTO customerDTO) {
		Customer customer = customerService.update(id, Utils.convertToEntity(customerDTO));
		logger.info("Customer updated:" + "id" + customer.getId());
		return new ResponseEntity<Customer>(customer, HttpStatus.OK);

	}

	@Operation(summary = "Delete Customer")
	@DeleteMapping(path = "/{id}", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		logger.info("delete customer");
		customerService.delete(id);
		return ResponseEntity.noContent().build();
	}


}
