package ar.com.plug.examen.domain.utils;

import ar.com.plug.examen.domain.dto.CustomerDTO;
import ar.com.plug.examen.domain.dto.ProductDTO;
import ar.com.plug.examen.domain.dto.TransactionDTO;
import ar.com.plug.examen.domain.model.Customer;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.model.Province;
import ar.com.plug.examen.domain.model.Transaction;

public class Utils {
	public static Customer convertToEntity(CustomerDTO customerDTO) {
		Customer customerConverted = new Customer();
		customerConverted.setAddress(customerDTO.getAddress());
		customerConverted.setCity(customerDTO.getCity());
		customerConverted.setBirth(customerDTO.getBirth());
		customerConverted.setEmail(customerDTO.getEmail());
		customerConverted.setName(customerDTO.getName());
		Province province = new Province();
		province.setId(customerDTO.getProvince());
		customerConverted.setProvince(province);
		customerConverted.setSurname(customerDTO.getSurname());
		return customerConverted;
	}

	public static Product convertToEntity(ProductDTO productDTO) {
		Product productConverted = new Product();
		productConverted.setBrand(productDTO.getBrand());
		productConverted.setName(productDTO.getName());
		productConverted.setPrice(productDTO.getPrice());
		productConverted.setStock(productDTO.getStock());
		return productConverted;
	}
	
	public static Transaction convertToEntity(TransactionDTO transactionDTO) {
		Transaction transactionConverted = new Transaction();
		transactionConverted.setAmount(transactionDTO.getAmount());
		transactionConverted.setApproved(transactionDTO.isApproved());
		Customer customer = new Customer();
		customer.setId(transactionDTO.getCustomer());
		transactionConverted.setCustomer(customer);
		transactionConverted.setDate(transactionDTO.getDate());
		Product product = new Product();
		product.setId(transactionDTO.getProduct());
		transactionConverted.setProduct(product);
		transactionConverted.setQuantity(transactionDTO.getQuantity());
		return transactionConverted;
	}
}
