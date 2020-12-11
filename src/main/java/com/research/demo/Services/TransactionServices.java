package com.research.demo.Services;

import java.util.Date;
import java.util.List;

import com.research.demo.Entities.Account;
import com.research.demo.Entities.Transaction;

public interface TransactionServices {

	Transaction createTransaction(Transaction trans);

	List<Transaction> findTransactions(Account acc);

	List<Transaction> findTransactionsByPeriod(Long idAccount, Date start, Date end);

}
