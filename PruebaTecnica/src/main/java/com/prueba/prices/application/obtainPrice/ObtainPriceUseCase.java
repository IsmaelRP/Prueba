package com.prueba.prices.application.obtainPrice;

import java.time.LocalDateTime;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.prueba.prices.domain.model.PriceQuery;
import com.prueba.prices.domain.repository.PriceQueryRepository;

@Service
public class ObtainPriceUseCase {

	@Autowired
	private PriceQueryRepository priceQueryRepository;

    public Optional<PriceQuery> findPriceByDate(LocalDateTime application, int product_id, short subsidiary_id){
        return priceQueryRepository.findByApplicationDate(application, product_id, subsidiary_id);
    }

}
