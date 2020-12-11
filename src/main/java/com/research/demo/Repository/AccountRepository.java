package com.research.demo.Repository;

import org.springframework.data.repository.CrudRepository;

import com.research.demo.Entities.Account;

public interface AccountRepository extends CrudRepository<Account,Long>{

}
