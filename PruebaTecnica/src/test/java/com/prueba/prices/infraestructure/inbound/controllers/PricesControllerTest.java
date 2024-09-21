package com.prueba.prices.infraestructure.inbound.controllers;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.slf4j.Logger;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.prueba.prices.application.obtainPrice.ObtainPriceUseCase;
import com.prueba.prices.domain.model.PriceQuery;
import com.prueba.shared.ResponseObj;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class PricesControllerTest {

    @Mock
    private ObtainPriceUseCase pricesUseCase;

    @Mock
    private Logger logger;

    @InjectMocks
    private PricesController pricesController;


    @Test
    void findPrice_PriceFound_ReturnsOkResponse() {
        short subsidiaryId = 1;
        int productId = 123;
        LocalDateTime application = LocalDateTime.now();
        PriceQuery priceQuery = new PriceQuery();
        when(pricesUseCase.findPriceByDate(application, productId, subsidiaryId))
                .thenReturn(Optional.of(priceQuery));

        @SuppressWarnings("rawtypes")
		ResponseEntity<ResponseObj> responseEntity = pricesController.findPrice(subsidiaryId, productId, application);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals("Price found", responseEntity.getBody().getMsg());
        assertEquals(priceQuery, responseEntity.getBody().getBody());
        verify(logger).info("Price controller - findPrice successful");
    }

    @Test
    void findPrice_PriceNotFound_ReturnsNotFoundResponse() {
        short subsidiaryId = 1;
        int productId = 123;
        LocalDateTime application = LocalDateTime.now();
        when(pricesUseCase.findPriceByDate(application, productId, subsidiaryId))
                .thenReturn(Optional.empty());

        @SuppressWarnings("rawtypes")
		ResponseEntity<ResponseObj> responseEntity = pricesController.findPrice(subsidiaryId, productId, application);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
        assertEquals("Price not found", responseEntity.getBody().getMsg());
        assertEquals("Error", responseEntity.getBody().getBody());
        verify(logger).warn("Price controller - findPrice unsuccessful");
    }
}