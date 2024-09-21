package com.prueba.prices.infraestructure.inbound.controllers;

import java.time.LocalDateTime;

import org.openapitools.api.PricesApi;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.prueba.prices.application.obtainPrice.ObtainPriceUseCase;
import com.prueba.shared.ResponseObj;

import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping("/prices")
@RequiredArgsConstructor
public class PricesController implements PricesApi {
	
	@Autowired
	private ObtainPriceUseCase pricesUseCase;

	Logger logger = LoggerFactory.getLogger(PricesController.class);
	
	@SuppressWarnings("rawtypes")
	@GetMapping("findPrice")
    public ResponseEntity<ResponseObj> findPrice(@RequestParam short subsidiary_id,
    											@RequestParam int product_id, 
    											@RequestParam LocalDateTime application){

    	return pricesUseCase.findPriceByDate(application, product_id, subsidiary_id)
    		    .<ResponseEntity<ResponseObj>>map(priceQuery -> {
    		        logger.info("Price controller - findPrice successful");
    		        return ResponseEntity.ok(new ResponseObj<>(priceQuery, "Price found"));
    		    }).orElseGet(() -> {
    		        logger.warn("Price controller - findPrice unsuccessful");
    		        return ResponseEntity.status(HttpStatus.NOT_FOUND)
    		            .body(new ResponseObj<>("Error", "Price not found"));
    		    });
    	
        
    }
    
}
