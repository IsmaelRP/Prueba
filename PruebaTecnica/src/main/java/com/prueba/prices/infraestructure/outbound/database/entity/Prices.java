package com.prueba.prices.infraestructure.outbound.database.entity;

import java.time.LocalDateTime;

import com.prueba.shared.CurrEnum;

import jakarta.persistence.Column;
import jakarta.persistence.EmbeddedId;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Getter;

@Getter
@Entity
@Table(name = "prices")
public class Prices {

	@EmbeddedId
	private PricesId id;	
	
	@Column(name = "priority")
	private short priority;
	
	@Column(name = "price")
	private float price;
	
	@Column(name = "curr")
	private CurrEnum curr;
	
	@Column(name = "start_date")
	private LocalDateTime startDate;
	
	@Column(name = "end_date")
	private LocalDateTime endDate;

	
}
