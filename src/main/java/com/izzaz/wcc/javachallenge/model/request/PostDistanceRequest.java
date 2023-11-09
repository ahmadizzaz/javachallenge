package com.izzaz.wcc.javachallenge.model.request;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PostDistanceRequest {
    @NotBlank
    private String postalCodeFrom;
    @NotBlank
    private String postalCodeTo;
}
