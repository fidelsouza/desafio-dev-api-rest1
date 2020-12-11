package com.research.demo.Services;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.research.demo.Entities.Account;
import com.research.demo.Entities.Transaction;

public interface AccountServices {

	Account createAccount(Account account);

	Account deposit(Long id, BigDecimal value);

	Account withdraw(Long id, BigDecimal value);

	BigDecimal checkBalance(Long id);

	Account changeActiveStatus(Long id, Boolean isActive);

	List<Transaction> findTransactions(Long id);

	List<Transaction> findTransactionsByPeriod(Long id, Date start, Date end);

}
