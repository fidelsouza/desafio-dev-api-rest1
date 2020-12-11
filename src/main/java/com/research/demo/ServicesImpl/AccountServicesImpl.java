package com.research.demo.ServicesImpl;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.research.demo.Entities.Account;
import com.research.demo.Entities.Transaction;
import com.research.demo.Repository.AccountRepository;
import com.research.demo.Services.AccountServices;

@Service
public class AccountServicesImpl implements AccountServices {
	@Autowired
	AccountRepository repository;

	@Autowired
	TransactionServicesImpl transService;

	@Override
	public Account createAccount(Account account) {
		return this.repository.save(account);
	}

	@Override
	@Transactional
	public Account deposit(Long id, BigDecimal value) {

		if (value.signum() < 0 || value.signum() == 0) {
			throw new IllegalArgumentException("O valor a ser depositado deve ser positivo.");
		}

		Optional<Account> accountOpt = this.repository.findById(id);
		if (accountOpt.isPresent()) {
			Account account = accountOpt.get();
			
			if(account.isActive() == false) {
				throw new RuntimeException("A conta está inativa.");
			}
			account.setBalance(account.getBalance().add(value));

			account = this.repository.save(account);
			transService.createTransaction(this.createTrans(account, value));
			return account;
		} else {
			throw new RuntimeException("Conta não encontrada!");
		}

	}

	@Override
	@Transactional
	public Account withdraw(Long id, BigDecimal value) {

		if (value.signum() < 0 || value.signum() == 0) {
			throw new IllegalArgumentException("O valor a ser sacado deve ser positivo.");
		}

		Optional<Account> accountOpt = this.repository.findById(id);
		if (accountOpt.isPresent()) {
			Account account = accountOpt.get();
			
			if(account.isActive() == false) {
				throw new RuntimeException("A conta está inativa.");
			}

			if (account.getBalance().compareTo(value) >= 0) {

				if (account.getDailyLimit().compareTo(value) >= 0) {
					account.setBalance(account.getBalance().subtract(value));

					account = this.repository.save(account);
					//value.negate() change to negative in the transaction
					transService.createTransaction(this.createTrans(account, value.negate()));
					return account;
				} else {
					throw new RuntimeException("O limite disponível para saque é menor que o valor requisitado.");
				}
			} else {
				throw new RuntimeException("Não há saldo disponível na conta para o saque.");
			}
		} else {
			throw new RuntimeException("Conta não encontrada!");
		}
	}

	@Override
	public BigDecimal checkBalance(Long id) {

		Optional<Account> accountOpt = this.repository.findById(id);
		if (accountOpt.isPresent()) {
			Account account = accountOpt.get();
			return account.getBalance();
		} else {
			throw new RuntimeException("Conta não encontrada!");
		}
	}

	@Override
	public Account changeActiveStatus(Long id, Boolean isActive) {

		Optional<Account> accountOpt = this.repository.findById(id);
		if (accountOpt.isPresent()) {
			Account account = accountOpt.get();
			account.setActive(isActive);

			return this.repository.save(account);
		} else {
			throw new RuntimeException("Conta não encontrada!");
		}
	}
	
	@Override
	public List<Transaction> findTransactions(Long id){
		
		Optional<Account> accountOpt = this.repository.findById(id);
		if (accountOpt.isPresent()) {
			Account account = accountOpt.get();
			
			return this.transService.findTransactions(account);
			
		} else {
			throw new RuntimeException("Conta não encontrada!");
		}
	}
	
	@Override
	public List<Transaction> findTransactionsByPeriod(Long id, Date start, Date end){
		
		Optional<Account> accountOpt = this.repository.findById(id);
		if (accountOpt.isPresent()) {
			Account account = accountOpt.get();
			
			return this.transService.findTransactionsByPeriod(id, start, end);
			
		} else {
			throw new RuntimeException("Conta não encontrada!");
		}
	}

	private Transaction createTrans(Account account, BigDecimal value) {
		Transaction trans = new Transaction();
		trans.setAccount(account);
		trans.setValue(value);
		trans.setDate(new Date());
		return trans;
	}

}
