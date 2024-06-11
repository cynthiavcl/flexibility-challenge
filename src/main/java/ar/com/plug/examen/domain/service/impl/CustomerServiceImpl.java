package ar.com.plug.examen.domain.service.impl;

import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import ar.com.plug.examen.domain.dao.CustomerRepository;
import ar.com.plug.examen.domain.exception.CustomerCreateException;
import ar.com.plug.examen.domain.exception.CustomerDeleteException;
import ar.com.plug.examen.domain.exception.CustomerUpdateException;
import ar.com.plug.examen.domain.exception.DataIntegrationException;
import ar.com.plug.examen.domain.exception.ObjectNotFoundException;
import ar.com.plug.examen.domain.exception.ProductUpdateException;
import ar.com.plug.examen.domain.model.Customer;
import ar.com.plug.examen.domain.model.Province;
import ar.com.plug.examen.domain.service.CustomerService;

@Service
public class CustomerServiceImpl implements CustomerService {

	final Logger logger = LoggerFactory.getLogger(CustomerServiceImpl.class);

	@Autowired
	private CustomerRepository customerRepository;

	@Override
	public Customer save(Customer customer) {
		Customer newCustomer = new Customer();
		try {
			newCustomer = customerRepository.save(customer);
			logger.info("customer with id " + newCustomer.getId() + "created");
		} catch (EmptyResultDataAccessException e) {
			throw new ObjectNotFoundException("Customer not found with id ", customer.getId());
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrationException("Data integrity violation for customer ", customer.getId());
		} catch (DataAccessException e) {
			throw new CustomerCreateException("Customer was not created with id ", customer.getId());
		}
		return newCustomer;
	}

	@Override
	public void delete(Long id) {
		try {
			customerRepository.deleteById(id);
			logger.info("customer with id " + id + "deleted");
		} catch (EmptyResultDataAccessException e) {
			throw new ObjectNotFoundException("Customer not found with id  ", id);
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrationException("Data integrity violation with id ", id);
		} catch (DataAccessException e) {
			throw new CustomerDeleteException("Customer was not deleted with id ", id);
		}
	}

	@Override
	public Customer update(Long id, Customer customer) {
		logger.info("Get customer");
		Optional<Customer> customerFromDB = customerRepository.findById(id);
		Customer customerModified = new Customer();
		if (customerFromDB != null) {
			Customer customerToDB= customerFromDB.get();
			customerToDB.setAddress(customer.getAddress());
			customerToDB.setBirth(customer.getBirth());
			customerToDB.setCity(customer.getCity());
			customerToDB.setEmail(customer.getEmail());
			customerToDB.setName(customer.getName());
			Province province= new Province();
			province.setId(customer.getProvince().getId());
			customerToDB.setProvince(province);
			customerToDB.setSurname(customer.getSurname());

			logger.info("Update customer");
			try {
				customerModified = customerRepository.save(customerToDB);
				logger.info("customer with id " + customerModified.getId() + "updated");
			} catch (EmptyResultDataAccessException e) {
				throw new ObjectNotFoundException("Customer not updated with id ", customer.getId());
			} catch (DataIntegrityViolationException e) {
				throw new DataIntegrationException("Data integrity violation for customer ", customer.getId());
			} catch (DataAccessException e) {
				throw new CustomerUpdateException("Customer was not updated with id ", customer.getId());
			}
		}
		return customerModified;
	}

	@Override
	public Customer findById(Long id) {
		Customer newCustomer = new Customer();
		try {
			newCustomer = customerRepository.findById(id).orElse(null);
			logger.info("customer with id " + newCustomer.getId());
		} catch (EmptyResultDataAccessException e) {
			throw new ObjectNotFoundException("Customer not found with id ", id);
		} catch (DataAccessException e) {
			throw new ProductUpdateException("Customer was not updated with id ", id);
		}
		return newCustomer;
	}
}
