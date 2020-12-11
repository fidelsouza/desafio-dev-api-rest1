package com.research.demo.Repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import com.research.demo.Entities.Account;
import com.research.demo.Entities.Transaction;

public interface TransactionRepository extends CrudRepository<Transaction,Long>{
	List<Transaction> findByAccount(Account account);
	
	@Query(value = "from Transaction t where t.date BETWEEN :startDate AND :endDate and t.account.id = :id") 
	public List<Transaction> getAllBetweenDates(@Param("startDate")Date startDate,@Param("endDate")Date endDate, @Param("id") Long id);
	
//	 @Query("select a from Transaction a where a.account.id = :id and a.date >= dateStart and a.date <= dateEnd ")
//	    List<Transaction> findTransactionsByPeriod(
//	      @Param("dateStart") Date dateStart, @Param("dateEnd") Date dateEnd, @Param("id") Long id  );
}
