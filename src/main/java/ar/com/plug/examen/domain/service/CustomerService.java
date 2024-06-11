package ar.com.plug.examen.domain.service;

import ar.com.plug.examen.domain.model.Customer;

public interface CustomerService {
	
	Customer save(Customer customer);
	
	void delete(Long id);
	
	Customer update(Long id, Customer custome);
	
	Customer findById(Long id);

}
