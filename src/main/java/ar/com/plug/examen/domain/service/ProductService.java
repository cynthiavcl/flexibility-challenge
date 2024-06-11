package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.model.Product;

public interface ProductService {
	
	Product save(Product product);
	
	void delete(Long id);
	
	Product update(Long id, Product product);
	
	Product findById(Long id);

}
