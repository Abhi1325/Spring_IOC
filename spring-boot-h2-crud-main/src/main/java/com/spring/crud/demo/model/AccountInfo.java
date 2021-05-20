package com.spring.crud.demo.model;


import javax.persistence.*;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.DynamicUpdate;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Set;


@Entity
@Table
public class AccountInfo implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private int id;
	private int accountNumber;
	private float balance;
	@CreationTimestamp
	private Timestamp lastUpdateTimestamp;
	@OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(referencedColumnName = "id")
	@JsonIgnore
    private Set<Transactions> transactions;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public int getAccountNumber() {
		return accountNumber;
	}
	public void setAccountNumber(int accountNumber) {
		this.accountNumber = accountNumber;
	}
	public float getBalance() {
		return balance;
	}
	public void setBalance(float balance) {
		this.balance = balance;
	}
	public Timestamp getLastUpdateTimestamp() {
		return lastUpdateTimestamp;
	}
	public void setLastUpdateTimestamp(Timestamp lastUpdateTimestamp) {
		this.lastUpdateTimestamp = lastUpdateTimestamp;
	}
	  @JsonIgnore
	public Set<Transactions> getTransactions() {
		return transactions;
	}
	public void setTransactions(Set<Transactions> transactions) {
		this.transactions = transactions;
	}
	
	
	
	
}
