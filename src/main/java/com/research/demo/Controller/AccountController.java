package com.research.demo.Controller;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.research.demo.Entities.Account;
import com.research.demo.Entities.Transaction;
import com.research.demo.Services.AccountServices;
import com.research.demo.ServicesImpl.AccountServicesImpl;

@RequestMapping(value = "/account")
@RestController()
public class AccountController {

	@Autowired
	AccountServices service;

	@PostMapping("/insert")
	ResponseEntity<Account> create(@RequestBody Account account) {

		Account ret = service.createAccount(account);

		return ResponseEntity.status(HttpStatus.OK).body(ret);
	}

	@PutMapping("/deposit")
	ResponseEntity<Account> deposit(@RequestParam Long id, @RequestParam BigDecimal value) {

		Account ret = service.deposit(id, value);

		return ResponseEntity.status(HttpStatus.OK).body(ret);
	}

	@PutMapping("/withdraw")
	ResponseEntity<Account> withdraw(@RequestParam Long id, @RequestParam BigDecimal value) {

		Account ret = service.withdraw(id, value);

		return ResponseEntity.status(HttpStatus.OK).body(ret);
	}

	@GetMapping("/checkBalance")
	ResponseEntity<BigDecimal> checkBalance(@RequestParam Long id) {

		BigDecimal res = service.checkBalance(id);

		return ResponseEntity.status(HttpStatus.OK).body(res);
	}

	@PutMapping("/block")
	ResponseEntity<Account> block(@RequestParam Long id) {

		Account ret = service.changeActiveStatus(id, false);

		return ResponseEntity.status(HttpStatus.OK).body(ret);
	}

	@PutMapping("/activate")
	ResponseEntity<Account> activate(@RequestParam Long id) {

		Account ret = service.changeActiveStatus(id, true);

		return ResponseEntity.status(HttpStatus.OK).body(ret);
	}

	@GetMapping("/transactions")
	ResponseEntity<List<Transaction>> searchTransactions(@RequestParam Long id) {

		List<Transaction> list = service.findTransactions(id);

		return ResponseEntity.status(HttpStatus.OK).body(list);
	}

	@GetMapping("/transactionsByPeriod")
	ResponseEntity<List<Transaction>> searchTransactionsByPeriod(@RequestParam Long id,
			@RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") Date start,
			@RequestParam @DateTimeFormat(pattern = "dd-MM-yyyy") Date end) {

		List<Transaction> list = service.findTransactionsByPeriod(id, start, end);

		return ResponseEntity.status(HttpStatus.OK).body(list);
	}
}
