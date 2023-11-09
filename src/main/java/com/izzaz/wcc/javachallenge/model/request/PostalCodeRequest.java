package com.izzaz.wcc.javachallenge.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;


@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class PostalCodeRequest {
    @NotBlank
    private String postcode;
    @NotNull
    private BigDecimal latitude;
    @NotNull
    private BigDecimal longitude;
}
