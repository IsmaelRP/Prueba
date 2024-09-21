package com.prueba.prices.application.obtainPrice;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.prueba.prices.domain.model.PriceQuery;
import com.prueba.prices.domain.repository.PriceQueryRepository;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
class ObtainPriceUseCaseTest {

    @Mock
    private PriceQueryRepository priceQueryRepository;

    @InjectMocks
    private ObtainPriceUseCase obtainPriceUseCase;

    @Test
    public void testFindPrice() {
    	//	We could extract this to a POJO Generator class
        LocalDateTime applicationDate = LocalDateTime.now();
        int productId = 123;
        short subsidiaryId = 456;
        PriceQuery priceQuery = new PriceQuery();
        
        when(priceQueryRepository.findByApplicationDate(applicationDate, productId, subsidiaryId))
                .thenReturn(Optional.of(priceQuery));

        Optional<PriceQuery> result = obtainPriceUseCase.findPriceByDate(applicationDate, productId, subsidiaryId);

        assertThat(result.isPresent()).isTrue();
        assertThat(result.get()).isEqualTo(priceQuery);
    }
}