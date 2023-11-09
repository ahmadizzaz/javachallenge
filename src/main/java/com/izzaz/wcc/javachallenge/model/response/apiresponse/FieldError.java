package com.izzaz.wcc.javachallenge.model.response.apiresponse;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class FieldError {
    private String fieldError;
}
