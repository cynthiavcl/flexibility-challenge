package ar.com.plug.examen.domain.service;

import java.time.LocalDate;
import java.util.List;

import ar.com.plug.examen.domain.model.Transaction;

public interface TransactionService {
	
	Transaction save(Transaction transaction);
	
	Transaction update(Long id,Transaction transaction);
	
	Transaction findById(Long id);
	
	List<Transaction> findAll();
	
	List<Transaction> findByDateBetween(LocalDate start, LocalDate end);
	
	List<Transaction> findByApproved(Boolean approved);
	
	List<Transaction> findByAmountBetween(Double min, Double max);
	

}
