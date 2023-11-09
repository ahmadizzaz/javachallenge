package com.izzaz.wcc.javachallenge.model.response.apiresponse;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

@Data
@Accessors(chain = true)
@NoArgsConstructor
public class ResponseModel<T,B> {
    T status;
    B data;
}
