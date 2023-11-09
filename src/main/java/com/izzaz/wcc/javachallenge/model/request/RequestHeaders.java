package com.izzaz.wcc.javachallenge.model.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;


@Data
@Builder
@AllArgsConstructor
@RequiredArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class RequestHeaders {
    @JsonProperty("x-consumer-username")
    String userId;
    @JsonProperty("x-correlation-id")
    String correlationId;
    @JsonProperty("x-api-key")
    String apiKey;
    @JsonProperty("x-scope")
    String scope;
}
