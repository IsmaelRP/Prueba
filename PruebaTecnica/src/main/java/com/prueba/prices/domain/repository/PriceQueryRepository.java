package com.prueba.prices.domain.repository;

import java.time.LocalDateTime;
import java.util.Optional;

import com.prueba.prices.domain.model.PriceQuery;

public interface PriceQueryRepository {
	
	Optional<PriceQuery> findByApplicationDate(
			LocalDateTime application, 
			int product_id, 
			short subsidiary_id);

}
