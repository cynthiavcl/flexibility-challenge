package ar.com.plug.examen.domain.dao;

import java.time.LocalDate;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import ar.com.plug.examen.domain.model.Transaction;

public interface TransactionRepository  extends JpaRepository<Transaction, Long>{
	
	List<Transaction> findByDateBetween(LocalDate start, LocalDate end);
	
	List<Transaction> findByApproved(Boolean approved);
	
	List<Transaction> findByAmountBetween(Double min, Double max);

}
