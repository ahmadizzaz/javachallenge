package com.izzaz.wcc.javachallenge.model.response;

import lombok.Builder;
import lombok.Data;

import java.math.BigDecimal;
import java.util.List;

@Data
@Builder
public class PostDistanceResponse {
    private List<PostalData> postalInfo;
    private BigDecimal distance;
    @Builder.Default
    private String unit = "km";

    @Data
    @Builder
    public static class PostalData{
        private String postalCode;
        private BigDecimal latitude;
        private BigDecimal longitude;
    }
}
