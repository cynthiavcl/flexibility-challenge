package ar.com.plug.examen.domain.service.impl;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import ar.com.plug.examen.domain.dao.TransactionRepository;
import ar.com.plug.examen.domain.exception.DataIntegrationException;
import ar.com.plug.examen.domain.exception.ObjectNotFoundException;
import ar.com.plug.examen.domain.exception.TransactionCreateException;
import ar.com.plug.examen.domain.exception.TransactionException;
import ar.com.plug.examen.domain.exception.TransactionUpdateException;
import ar.com.plug.examen.domain.model.Transaction;
import ar.com.plug.examen.domain.service.TransactionService;

@Service
public class TransactionServiceImpl implements TransactionService {

	final Logger logger = LoggerFactory.getLogger(TransactionServiceImpl.class);

	@Autowired
	private TransactionRepository transactionRepository;

	@Override
	public Transaction save(Transaction transaction) {
		Transaction newTransaction = new Transaction();
		try {
			newTransaction = transactionRepository.save(transaction);
			logger.info("product with id " + newTransaction.getId() + "created");
		} catch (EmptyResultDataAccessException e) {
			throw new ObjectNotFoundException("Transaction not found with id ", transaction.getId());
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrationException("Data integrity violation " , transaction.getId());
		} catch (DataAccessException e) {
			throw new TransactionCreateException("Transaction was not create with id ", transaction.getId());
		}
		return newTransaction;
	}

	@Override
	public Transaction update(Long id, Transaction transaction) {

		logger.info("Get transation");
		Optional<Transaction> transactionFromDB = transactionRepository.findById(id);
		Transaction newTransaction = new Transaction();
		if (transactionFromDB != null) {
			logger.info("Update transation");
			try {
				newTransaction = transactionRepository.save(transaction);
				logger.info("transaction with id " + newTransaction.getId() + "updated");
			} catch (EmptyResultDataAccessException e) {
				throw new ObjectNotFoundException("Transaction not found with id ", transaction.getId());
			} catch (DataIntegrityViolationException e) {
				throw new DataIntegrationException("Data integrity violation", transaction.getId());
			} catch (DataAccessException e) {
				throw new TransactionUpdateException("Transaction was not updated with id ", transaction.getId());
			}
		}
		return newTransaction;
	}

	@Override
	public Transaction findById(Long id) {
		Transaction transaction = new Transaction();

		try {
			transaction = transactionRepository.findById(id).orElse(null);
			logger.info("transaction with id " + transaction.getId());
		} catch (EmptyResultDataAccessException e) {
			throw new ObjectNotFoundException("transaction not found with id ", transaction.getId());
		} catch (DataIntegrityViolationException e) {
			throw new DataIntegrationException("Data integrity violation ", transaction.getId());
		} catch (DataAccessException e) {
			throw new TransactionException("Data Access error " + e.getMessage());
		}
		return transaction;
	}

	@Override
	public List<Transaction> findAll() {
		List<Transaction> list = new ArrayList<Transaction>();
		try {
			list = transactionRepository.findAll();
			logger.info("transactions");
		} catch (DataAccessException e) {
			throw new TransactionException("Data Access error " + e.getMessage());
		}
		return list;
	}

	@Override
	public List<Transaction> findByDateBetween(LocalDate start, LocalDate end) {
		List<Transaction> list = new ArrayList<Transaction>();
		try {
			list = transactionRepository.findByDateBetween(start, end);
			logger.info("transactions by date");
		} catch (DataAccessException e) {
			throw new TransactionException("Data Access error " + e.getMessage());
		}
		return list;
	}

	@Override
	public List<Transaction> findByApproved(Boolean approved) {
		List<Transaction> list = new ArrayList<Transaction>();
		try {
			list = transactionRepository.findByApproved(approved);
			logger.info("transactions by approved");
		} catch (DataAccessException e) {
			throw new TransactionException("Data Access error " + e.getMessage());
		}
		return list;
	}

	@Override
	public List<Transaction> findByAmountBetween(Double min, Double max) {
		List<Transaction> list = new ArrayList<Transaction>();
		try {
			list = transactionRepository.findByAmountBetween(min, max);
			logger.info("transactions by amount");
		} catch (DataAccessException e) {
			throw new TransactionException("Data Access error " + e.getMessage());
		}
		return list;
	}
}
