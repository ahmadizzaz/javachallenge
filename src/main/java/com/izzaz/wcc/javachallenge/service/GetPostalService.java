package com.izzaz.wcc.javachallenge.service;

import com.izzaz.wcc.javachallenge.model.request.PostalCodeRequest;
import com.izzaz.wcc.javachallenge.model.request.RequestHeaders;
import com.izzaz.wcc.javachallenge.model.response.apiresponse.ResponseModel;
import com.izzaz.wcc.javachallenge.model.response.apiresponse.Status;
import com.izzaz.wcc.javachallenge.repository.PostalCodeRepository;
import com.izzaz.wcc.javachallenge.util.exceptionhandling.ResponseUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class GetPostalService {

    private final PostalCodeRepository postalCodeRepository;

    public ResponseModel<Status, PostalCodeRequest> execute(RequestHeaders headers, final String postcode) {
        if (StringUtils.contains(postcode,"%20")){
            StringUtils.remove(postcode,"%20");
        }
        var entity = postalCodeRepository.findByPostcode(postcode);
        if (Objects.isNull(entity)) {
            throw ResponseUtil.generateNotFoundError("Invalid postcode value");
        }

        return ResponseUtil.generateSuccessStatus(PostalCodeRequest.builder()
                .postcode(entity.getPostcode())
                .longitude(entity.getLongitude())
                .latitude(entity.getLatitude())
                .build());

    }
}
