package com.izzaz.wcc.javachallenge.service;

import com.izzaz.wcc.javachallenge.model.dao.PostalCodeEntity;
import com.izzaz.wcc.javachallenge.model.request.PostDistanceRequest;
import com.izzaz.wcc.javachallenge.model.request.RequestHeaders;
import com.izzaz.wcc.javachallenge.model.response.PostDistanceResponse;
import com.izzaz.wcc.javachallenge.model.response.PostDistanceResponse.PostalData;
import com.izzaz.wcc.javachallenge.model.response.apiresponse.ResponseModel;
import com.izzaz.wcc.javachallenge.model.response.apiresponse.Status;
import com.izzaz.wcc.javachallenge.repository.PostalCodeRepository;
import com.izzaz.wcc.javachallenge.util.CalculationHelper;
import com.izzaz.wcc.javachallenge.util.exceptionhandling.ResponseUtil;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException.*;

import java.util.ArrayList;
import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class PostDistanceService {

    private final PostalCodeRepository postalCodeRepository;

    public ResponseModel<Status,PostDistanceResponse> execute(RequestHeaders headers, final PostDistanceRequest request, HttpServletResponse response){
        var postalCodeFromData = postalCodeRepository.findByPostcode(request.getPostalCodeFrom());
        if (BooleanUtils.isFalse(isEntityValidated(postalCodeFromData))){
            throw  ResponseUtil.generateNotFoundError("Invalid postalCodeFrom value");
        }
        var postalCodeToData = postalCodeRepository.findByPostcode(request.getPostalCodeTo());
        if (BooleanUtils.isFalse(isEntityValidated(postalCodeToData))){
            throw ResponseUtil.generateNotFoundError("Invalid postalCodeTo value");
        }
        var responseList = new ArrayList<PostalData>();
        responseList.add(PostalData.builder()
                        .latitude(postalCodeFromData.getLatitude())
                        .longitude(postalCodeFromData.getLongitude())
                        .postalCode(postalCodeFromData.getPostcode())
                .build());
        responseList.add(PostalData.builder()
                .latitude(postalCodeToData.getLatitude())
                .longitude(postalCodeToData.getLongitude())
                .postalCode(postalCodeToData.getPostcode())
                .build());

        return ResponseUtil.generateSuccessStatus(PostDistanceResponse.builder()
                .postalInfo(responseList)
                .distance(CalculationHelper.calculateDistance(postalCodeFromData.getLatitude(),postalCodeFromData.getLongitude(),
                        postalCodeToData.getLatitude(),postalCodeToData.getLongitude()))
                .build());

    }

    private Boolean isEntityValidated(PostalCodeEntity entity){
        return !Objects.isNull(entity) && !Objects.isNull(entity.getLatitude()) && !Objects.isNull(entity.getLongitude());
    }
}
