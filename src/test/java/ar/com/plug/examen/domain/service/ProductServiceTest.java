package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.dao.ProductRepository;
import ar.com.plug.examen.domain.exception.DataIntegrationException;
import ar.com.plug.examen.domain.exception.ObjectNotFoundException;
import ar.com.plug.examen.domain.exception.ProductCreateException;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.service.impl.ProductServiceImpl;

import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

//Se agrego jacoco para ver la cobertura de los tests
@ExtendWith(MockitoExtension.class)
public class ProductServiceTest {

	@Mock
	private ProductRepository productRepository;

	@InjectMocks
	private ProductServiceImpl productService;

	Product product;
    Optional<Product> productOptional;

	@BeforeEach
	public void setUp() {
		System.out.println("Invoked before each test method");
		product = new Product();
		product.setBrand("Apple");
		product.setName("Mac Book Pro");
		product.setPrice(2.5);
		product.setStock(50);
		product.setId(2L);
        productOptional=Optional.of(product);

	}

	@Test
	public void testSave() {
		when(productRepository.save(any(Product.class))).thenReturn(product);
		Product productSaved = productService.save(new Product());
		assertNotNull(productSaved);
		verify(productRepository, times(1)).save(new Product());
	}

	@Test
	public void testUpdate() {
		when(productRepository.save(any(Product.class))).thenReturn(product);
    	when(productRepository.findById(any(Long.class))).thenReturn(productOptional);

		Product productSaved = productService.save(new Product());
		productSaved.setStock(100);
		Product productUpdated = productService.update(2L, productSaved);
		assertNotNull(productUpdated);
		assertNotEquals(productUpdated.getStock(), 50);
	}

	@Test
	public void testDelete() {
		doNothing().when(productRepository).deleteById(2L);
		productService.delete(2L);
		verify(productRepository, times(1)).deleteById(2L);
	}

	@Test
	void test_ThrowsProductCreateException() {
		when(productRepository.save(any(Product.class))).thenThrow(ProductCreateException.class);
		assertThrows(ProductCreateException.class, () -> productService.save(new Product()));
	}

	@Test
	void test_ThrowsDataIntegrationException() {
		when(productRepository.save(any(Product.class))).thenThrow(DataIntegrationException.class);
		assertThrows(DataIntegrationException.class, () -> productService.save(new Product()));
	}

	@Test
	void test_ThrowsObjectNotFoundException() {
		when(productRepository.save(any(Product.class))).thenThrow(ObjectNotFoundException.class);
		assertThrows(ObjectNotFoundException.class, () -> productService.save(new Product()));
	}
}
