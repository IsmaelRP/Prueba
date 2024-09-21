package com.prueba.prices.domain.model;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@NoArgsConstructor
@AllArgsConstructor
@Getter
public class PriceQuery {

	private int productId;
	private short brandId;
	private float price;
	private LocalDateTime startDate;
	private LocalDateTime endDate;
	
}
