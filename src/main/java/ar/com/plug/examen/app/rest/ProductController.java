package ar.com.plug.examen.app.rest;

import ar.com.plug.examen.domain.dto.ProductDTO;
import ar.com.plug.examen.domain.exception.ProductCreateException;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.service.ProductService;
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
@RequestMapping(path = "/products")
@Tag(name = "Product Management")
public class ProductController {

	final Logger logger = LoggerFactory.getLogger(ProductController.class);

	@Autowired
	private ProductService productService;

	@Operation(summary = "Create product")
	@PostMapping(path = "", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> create(@RequestBody ProductDTO productDTO) {
		logger.info("Create producto");
		Product product = productService.save(Utils.convertToEntity(productDTO));
        if (product == null) {
            throw new ProductCreateException("Product not created");
        }
		logger.info("product created:" + "id" + product.getId());
		return new ResponseEntity<Product>(product, HttpStatus.CREATED);
	}

	@Operation(summary = "Update product")
	@PutMapping(path = "/{id}", produces = {
			MediaType.APPLICATION_JSON_VALUE }, consumes = MediaType.APPLICATION_JSON_VALUE)
	public ResponseEntity<Product> update(@PathVariable Long id, @RequestBody ProductDTO productDTO) {
		Product product = productService.update(id, Utils.convertToEntity(productDTO));
		logger.info("product updated:" + "id" + product.getId());
		return new ResponseEntity<Product>(product, HttpStatus.OK);

	}

	@Operation(summary = "Delete product")
	@DeleteMapping(path = "/{id}", produces = {
			MediaType.APPLICATION_JSON_VALUE })
	public ResponseEntity<Void> delete(@PathVariable Long id) {
		logger.info("delete product");
		productService.delete(id);
		return ResponseEntity.noContent().build();
	}
}
