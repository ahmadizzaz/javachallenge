package com.izzaz.wcc.javachallenge.model.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@RequiredArgsConstructor
public class PostDistanceRequest {
    @NotBlank
    private String postalCodeFrom;
    @NotBlank
    private String postalCodeTo;
}
