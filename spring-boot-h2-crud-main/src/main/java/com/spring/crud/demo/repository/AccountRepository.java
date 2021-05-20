package com.spring.crud.demo.repository;

import com.spring.crud.demo.model.AccountInfo;

import java.util.List;

import javax.transaction.Transactional;

import org.hibernate.sql.Update;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface AccountRepository extends JpaRepository<AccountInfo, Integer> {
	List<AccountInfo> findByAccountNumber(int accountNumber);
	
	@Transactional
	@Modifying(clearAutomatically = true)
	@Query(value = "UPDATE ACCOUNT_INFO  set BALANCE =?1 where ACCOUNT_NUMBER =?2",
            nativeQuery = true)
	void update(float amount, int accountNumber);
	
}
