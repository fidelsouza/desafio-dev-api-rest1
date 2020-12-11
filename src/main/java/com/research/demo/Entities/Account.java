package com.research.demo.Entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Data
@NoArgsConstructor
@Entity
public class Account implements Serializable{
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long id;
	@OneToOne
	@JoinColumn(name = "person_id", nullable = false)
	private Person person;
	@Column(nullable = false)
	private BigDecimal balance;
	@Column(nullable = false)
	private BigDecimal dailyLimit;
	@Column(nullable = false)
	@JsonProperty
	private boolean isActive;
	@Enumerated(EnumType.ORDINAL)
	private AccountType type;
	@Temporal(TemporalType.TIMESTAMP)
	@JsonFormat(pattern="dd-MM-yyyy HH:mm")
	private Date createdAt;
	
}
