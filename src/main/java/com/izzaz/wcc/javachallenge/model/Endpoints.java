package com.izzaz.wcc.javachallenge.model;

import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class Endpoints {

    public static final String APPLICATION_BASE_V1= "/v1/jchallenge";
    public static final String DISTANCE = APPLICATION_BASE_V1 +  "/distance";
    public static final String POSTAL_V1 = APPLICATION_BASE_V1 +  "/postal";

}
