package ar.com.plug.examen.domain.service;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyBoolean;
import static org.mockito.ArgumentMatchers.anyDouble;
import static org.mockito.Mockito.when;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import ar.com.plug.examen.domain.dao.TransactionRepository;
import ar.com.plug.examen.domain.exception.DataIntegrationException;
import ar.com.plug.examen.domain.exception.ObjectNotFoundException;
import ar.com.plug.examen.domain.exception.TransactionCreateException;
import ar.com.plug.examen.domain.exception.TransactionUpdateException;
import ar.com.plug.examen.domain.model.Customer;
import ar.com.plug.examen.domain.model.Product;
import ar.com.plug.examen.domain.model.Province;
import ar.com.plug.examen.domain.model.Transaction;
import ar.com.plug.examen.domain.service.impl.TransactionServiceImpl;

//Se agrego jacoco para ver la cobertura de los tests

@ExtendWith(MockitoExtension.class)
public class TransactionServiceTest {

    @Mock
    private TransactionRepository transactionRepository;
    
    @InjectMocks
    private TransactionServiceImpl transactionService;
    
    @Mock
    private Product product;
    
    @Mock
    private Customer customer;
    
    @Mock
    private Province province;
    
    Transaction transaction1;
    Transaction transaction2;
    
    @BeforeEach
    public void setUp() {
        transaction1 = new Transaction();
        transaction1.setAmount(200.0);
        transaction1.setApproved(true);
        transaction1.setCustomer(customer);
        transaction1.setDate(LocalDate.of(2023, 2, 1));
        transaction1.setId(1L);
        transaction1.setProduct(product);
        transaction1.setQuantity(2);
        
        transaction2 = new Transaction();
        transaction2.setAmount(400.0);
        transaction2.setApproved(false);
        transaction2.setCustomer(customer);
        transaction2.setDate(LocalDate.of(2024, 2, 1));
        transaction2.setId(2L);
        transaction2.setProduct(product);
        transaction2.setQuantity(42);
    }
    
    @Test
    public void testSave()
    {
    	when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction1).thenReturn(transaction2);
    	Transaction transactionSaved1 = transactionService.save(new Transaction());
    	Transaction transactionSaved2 = transactionService.save(new Transaction());
    	
        assertNotNull(transactionSaved1);
        assertEquals(transaction1, transactionSaved1);
        assertNotNull(transactionSaved2);
        assertEquals(transaction2, transactionSaved2);
    }
    
    @Test
    public void testUpdate()
    {
    	when(transactionRepository.save(any(Transaction.class))).thenReturn(transaction1);
    	Transaction transactionSaved = transactionService.save(new Transaction());
    	transactionSaved.setQuantity(22);
    	Transaction transactionUpdated  = transactionService.update(1L, transactionSaved);
    	assertNotNull(transactionUpdated);
    	assertNotEquals(transactionUpdated.getQuantity(),2);
    }
    
    
    @Test
    void testFindById() {

        when(transactionRepository.findById(1L)).thenReturn(Optional.of(transaction1));

        Transaction transactionFound = transactionService.findById(1L);

        assertNotNull(transactionFound); 
        assertEquals(transactionFound.getId(),1);
    }
    
    @Test
    void testFindAll() {

        when(transactionRepository.findAll()).thenReturn(Arrays.asList(transaction1, transaction2));

        List<Transaction> transactions = transactionService.findAll();

        assertNotNull(transactions); 
        assertEquals(transaction1, transactions.get(0));
        assertEquals(transaction2, transactions.get(1));
        assertEquals(2, transactions.size());
    }
    
    @Test
    void testFindByDateBetween() {
    	
        LocalDate startDate = LocalDate.of(2024, 2, 1);
        LocalDate endDate = LocalDate.of(2024, 5, 1);

        when(transactionRepository.findByDateBetween(any(LocalDate.class), any(LocalDate.class))).thenReturn(Arrays.asList(transaction2));
    	
        List<Transaction> transactions = transactionService.findByDateBetween(startDate, endDate);

        assertNotNull(transactions); 
        assertEquals(1, transactions.size());
        assertEquals(transaction2, transactions.get(0));
    }
    
    @Test
    void testFindByApproved() {

    		when(transactionRepository.findByApproved(anyBoolean())).thenReturn(Arrays.asList(transaction1));
    	List<Transaction> transactions = transactionService.findByApproved(true);

        assertNotNull(transactions); 
        assertEquals(1, transactions.size());
        assertEquals(transaction1, transactions.get(0));
    }
    
    @Test
    void testFindByAmountBetween() {

    	when(transactionRepository.findByAmountBetween(anyDouble(), anyDouble())).thenReturn(Arrays.asList(transaction2));
    	List<Transaction> transactions = transactionService.findByAmountBetween(300.0, 500.0);

        assertNotNull(transactions); 
        assertEquals(1, transactions.size());
        assertEquals(transaction2, transactions.get(0));
    }
    
	@Test
	void test_ThrowsTransactionCreateException() {
		when(transactionRepository.save(any(Transaction.class))).thenThrow(TransactionCreateException.class);
		assertThrows(TransactionCreateException.class, () -> transactionService.save(new Transaction()));
	}
	
	@Test
	void test_ThrowsTransactionUpdateException() {
		when(transactionRepository.save(any(Transaction.class))).thenThrow(TransactionUpdateException.class);
		assertThrows(TransactionUpdateException.class, () -> transactionService.update(1L,new Transaction()));
	}
	
	@Test
	void test_ThrowsDataIntegrationException() {
		when(transactionRepository.save(any(Transaction.class))).thenThrow(DataIntegrationException.class);
		assertThrows(DataIntegrationException.class, () -> transactionService.save(new Transaction()));
	}

	@Test
	void test_ThrowsObjectNotFoundException() {
		when(transactionRepository.save(any(Transaction.class))).thenThrow(ObjectNotFoundException.class);
		assertThrows(ObjectNotFoundException.class, () -> transactionService.save(new Transaction()));
	}
}
