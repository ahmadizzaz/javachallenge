package com.izzaz.wcc.javachallenge.service;

import com.izzaz.wcc.javachallenge.model.dao.PostalCodeEntity;
import com.izzaz.wcc.javachallenge.model.request.PostDistanceRequest;
import com.izzaz.wcc.javachallenge.model.request.RequestHeaders;
import com.izzaz.wcc.javachallenge.repository.PostalCodeRepository;
import com.izzaz.wcc.javachallenge.util.CalculationHelper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;

import java.math.BigDecimal;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
@RequiredArgsConstructor
class PostDistanceServiceTest {

    @InjectMocks
    private PostDistanceService service;

    @Mock
    private PostalCodeRepository repository;

    @Test
    void success(){
        when(repository.findByPostcode("ABC 123"))
                .thenReturn(PostalCodeEntity.builder()
                        .id(1L)
                        .latitude(BigDecimal.valueOf(12.12345))
                        .longitude(BigDecimal.valueOf(40.1234))
                        .postcode("ABC 123")
                        .build());

        when(repository.findByPostcode("DEF 456"))
                .thenReturn(PostalCodeEntity.builder()
                        .id(2L)
                        .latitude(BigDecimal.valueOf(14.234))
                        .longitude(BigDecimal.valueOf(23.546))
                        .postcode("DEF 456")
                        .build());

        var distance = CalculationHelper.calculateDistance(BigDecimal.valueOf(12.12345),BigDecimal.valueOf(40.1234),BigDecimal.valueOf(14.234),BigDecimal.valueOf(23.546));
        var response = service.execute(
                new RequestHeaders(),
                PostDistanceRequest.builder()
                        .postalCodeFrom("ABC 123")
                        .postalCodeTo("DEF 456")
                        .build());
        var actual = response.getData();

        assertAll(
                () -> assertNotNull(actual) ,
                () -> assertEquals(HttpStatus.OK.value(),response.getStatus().getCode()) ,
                () -> assertEquals(actual.getDistance(),distance)
        );
    }

    @Test
    void fail_NotFoundError(){
        when(repository.findByPostcode("ABC 123"))
                .thenReturn(null);


        assertThrows(HttpClientErrorException.class,()-> service.execute(
                new RequestHeaders(),
                PostDistanceRequest.builder()
                        .postalCodeFrom("ABC 123")
                        .postalCodeTo("DEF 456")
                        .build()));


    }

}
