package com.izzaz.wcc.javachallenge.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.RequiredArgsConstructor;

import java.math.BigDecimal;


@Data
@RequiredArgsConstructor
public class PostalCodeRequest {
    @NotBlank
    private String postcode;
    @NotNull
    private BigDecimal latitude;
    @NotNull
    private BigDecimal longitude;
}
