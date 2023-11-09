package com.izzaz.wcc.javachallenge.service;

import com.izzaz.wcc.javachallenge.model.dao.PostalCodeEntity;
import com.izzaz.wcc.javachallenge.model.request.PostDistanceRequest;
import com.izzaz.wcc.javachallenge.model.request.PostalCodeRequest;
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
class UpdatePostalServiceTest {

    @InjectMocks
    private UpdatePostalService service;

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

        var response = service.execute(
                new RequestHeaders(),
                PostalCodeRequest.builder()
                        .postcode("ABC 123")
                        .latitude(BigDecimal.valueOf(123.4356))
                        .longitude(BigDecimal.valueOf(125.234))
                        .build());

        assertAll(
                () -> assertEquals(HttpStatus.OK.value(),response.getStatus().getCode())
        );
    }

    @Test
    void fail_NotFoundError(){
        when(repository.findByPostcode("ABC 123"))
                .thenReturn(null);

        assertThrows(HttpClientErrorException.class,()-> service.execute(
                new RequestHeaders(),
                PostalCodeRequest.builder()
                        .postcode("ABC 123")
                        .latitude(BigDecimal.valueOf(123.4356))
                        .longitude(BigDecimal.valueOf(125.234))
                        .build()));
    }

}
