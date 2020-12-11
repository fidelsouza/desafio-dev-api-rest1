package com.research.demo.ServicesImpl;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.research.demo.Entities.Account;
import com.research.demo.Entities.Transaction;
import com.research.demo.Repository.TransactionRepository;
import com.research.demo.Services.TransactionServices;

@Service
public class TransactionServicesImpl implements TransactionServices {
	@Autowired
	TransactionRepository repository;
	
	@Override
	public Transaction createTransaction(Transaction trans) {
		return this.repository.save(trans);
	}
	
	@Override
	public List<Transaction> findTransactions(Account acc){
		return this.repository.findByAccount(acc);
	}
	
	@Override
	public List<Transaction> findTransactionsByPeriod(Long idAccount, Date start, Date end){
		return this.repository.getAllBetweenDates(start, end, idAccount);
	}
}
