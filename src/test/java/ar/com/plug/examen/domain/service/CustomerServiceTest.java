package ar.com.plug.examen.domain.service;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ar.com.plug.examen.domain.dao.CustomerRepository;
import ar.com.plug.examen.domain.exception.CustomerCreateException;
import ar.com.plug.examen.domain.exception.CustomerUpdateException;
import ar.com.plug.examen.domain.exception.DataIntegrationException;
import ar.com.plug.examen.domain.exception.ObjectNotFoundException;
import ar.com.plug.examen.domain.model.Customer;
import ar.com.plug.examen.domain.model.Province;
import ar.com.plug.examen.domain.service.impl.CustomerServiceImpl;


//Se agrego jacoco para ver la cobertura de los tests

@ExtendWith(MockitoExtension.class)
public class CustomerServiceTest {

    @Mock
    private CustomerRepository customerRepository;
    
    @InjectMocks
    private CustomerServiceImpl customerService;
    
    @Mock
    private Province province;
    
    Customer customer;
    Optional<Customer> customerOptional;
    
    @BeforeEach
    public void setUp() {
        System.out.println("Invoked before each test method");
        customer = new Customer();
        customer.setAddress("santa fe 777");
        customer.setCity("Moron");
        customer.setBirth(LocalDate.parse("2024-12-31"));
        customer.setEmail("vocal.cynthia@gmail.com");
        customer.setId(1L);
        customer.setName("Pedro");
        province= new Province(1L, "buenos aires");
        customer.setProvince(province);
        customer.setSurname("Carra");
        customerOptional=Optional.of(customer);
    }
    
    @Test
    public void testSave()
    {
    	when(customerRepository.save(any(Customer.class))).thenReturn(customer);
    	Customer customerSaved = customerService.save(new Customer());
        assertNotNull(customerSaved);
        verify(customerRepository, times(1)).save(new Customer());
    }
    
    @Test
    public void testUpdate()
    
    {
    	when(customerRepository.save(any(Customer.class))).thenReturn(customer);
    	when(customerRepository.findById(any(Long.class))).thenReturn(customerOptional);
    	Customer customerSaved = customerService.save(new Customer());
    	customerSaved.setCity("Castelar");
    	Customer customerUpdated  = customerService.update(1L, customerSaved);
    	assertNotNull(customerUpdated);
    	assertNotEquals(customerUpdated.getCity(),"Moron");
    }
    
    @Test
    public void testDelete()
    {
    	doNothing().when(customerRepository).deleteById(2L);
    	customerService.delete(2L);
    	verify(customerRepository, times(1)).deleteById(2L);
    }
    
    @Test
    void testFindById() {

        when(customerRepository.findById(1L)).thenReturn(Optional.of(customer));

        Customer customerFound = customerService.findById(1L);

        assertNotNull(customerFound); 
        assertEquals(customerFound.getName(),"Pedro");
    }
    
	@Test
	void test_ThrowsCustomerCreateException() {
		when(customerRepository.save(any(Customer.class))).thenThrow(CustomerCreateException.class);
		assertThrows(CustomerCreateException.class, () -> customerService.save(new Customer()));
	}
	
	@Test
	void test_ThrowsCustomerUpdateException() {
		when(customerRepository.save(any(Customer.class))).thenThrow(CustomerUpdateException.class);
    	when(customerRepository.findById(any(Long.class))).thenReturn(customerOptional);
		assertThrows(CustomerUpdateException.class, () -> customerService.update(1L,customer));
	}
	
	@Test
	void test_ThrowsDataIntegrationException() {
		when(customerRepository.save(any(Customer.class))).thenThrow(DataIntegrationException.class);
		assertThrows(DataIntegrationException.class, () -> customerService.save(new Customer()));
	}

	@Test
	void test_ThrowsObjectNotFoundException() {
		when(customerRepository.save(any(Customer.class))).thenThrow(ObjectNotFoundException.class);
		assertThrows(ObjectNotFoundException.class, () -> customerService.save(new Customer()));
	}
}
