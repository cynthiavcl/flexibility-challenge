package ar.com.plug.examen.domain.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import ar.com.plug.examen.domain.dao.ProductRepository;
import ar.com.plug.examen.domain.exception.DataIntegrationException;
import ar.com.plug.examen.domain.exception.ObjectNotFoundException;
import ar.com.plug.examen.domain.exception.ProductCreateException;
import ar.com.plug.examen.domain.exception.ProductDeleteException;
import ar.com.plug.examen.domain.exception.ProductUpdateException;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	final Logger logger = LoggerFactory.getLogger(ProductServiceImpl.class);
	
	@Autowired
	private ProductRepository productRepository;

	@Override
	public Product save(Product product) {
		Product newProduct = new Product();
        try {
        	newProduct = productRepository.save(product);
//        	logger.info("product with id " + newProduct.getId() + "created");
        } catch (EmptyResultDataAccessException  e) {
            throw new ObjectNotFoundException("Product not found with id ",product.getId());
        } catch (DataIntegrityViolationException e) {
        	throw new DataIntegrationException("Data integrity violation with id ",product.getId());
        } catch (DataAccessException e) {
        	throw new ProductCreateException("The product was not create with id ", product.getId());
        }
        return newProduct;
	}

	@Override
	public void delete(Long id)  {			
        try {
            productRepository.deleteById(id);
            logger.info("product with id " + id + "deleted");
        } catch (EmptyResultDataAccessException  e) {
            throw new ObjectNotFoundException("Product not found with id  ",id);
        } catch (DataIntegrityViolationException e) {
        	throw new DataIntegrationException("Data integrity violation with id ",id);
        } catch (DataAccessException e) {
        	throw new ProductDeleteException("The product was not deleted with id ", id);
        }
	}

	@Override
	public Product update(Long id, Product product) {
		logger.info("Get product");
		Optional<Product> productFromDB= productRepository.findById(id);
		Product productModified = new Product();
		if (productFromDB != null) {
			Product productToDB= productFromDB.get();
			productToDB.setBrand(product.getBrand());
			productToDB.setName(product.getName());
			productToDB.setPrice(product.getPrice());
			productToDB.setStock(product.getStock());
			logger.info("Update product");
	        try {
	        	productModified = productRepository.save(productToDB);
	        	logger.info("product with id " + productModified.getId() + "updated");
	        } catch (EmptyResultDataAccessException  e) {
	            throw new ObjectNotFoundException("Product not found with id ",product.getId());
	        } catch (DataIntegrityViolationException e) {
	        	throw new DataIntegrationException("Data integrity violation",product.getId());
	        } catch (DataAccessException e) {
	        	throw new ProductUpdateException("The product was not updated with id ", product.getId());
	        }
		 }
        return productModified;
	}

	@Override
	public Product findById(Long id) {
		Product newProduct = new Product();
        try {
        	newProduct = productRepository.findById(id).orElse(null);
        	logger.info("product with id " + newProduct.getId() );
        } catch (EmptyResultDataAccessException  e) {
            throw new ObjectNotFoundException("Product not found with id ",id);
        } catch (DataAccessException e) {
        	throw new ProductUpdateException("The product was not updated with id ", id);
        }
        return newProduct;
	}
	


}
