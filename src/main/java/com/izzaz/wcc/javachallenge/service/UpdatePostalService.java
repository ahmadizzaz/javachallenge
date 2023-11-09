package com.izzaz.wcc.javachallenge.service;

import com.izzaz.wcc.javachallenge.model.dao.PostalCodeEntity;
import com.izzaz.wcc.javachallenge.model.request.PostalCodeRequest;
import com.izzaz.wcc.javachallenge.model.request.RequestHeaders;
import com.izzaz.wcc.javachallenge.model.response.apiresponse.ResponseModel;
import com.izzaz.wcc.javachallenge.model.response.apiresponse.Status;
import com.izzaz.wcc.javachallenge.repository.PostalCodeRepository;
import com.izzaz.wcc.javachallenge.util.exceptionhandling.ResponseUtil;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
@Slf4j
@RequiredArgsConstructor
public class UpdatePostalService {

    private final PostalCodeRepository postalCodeRepository;

    //Another method in the event to throw error if postal code does not exist.
    public ResponseModel<Status,Void> execute(RequestHeaders headers, final PostalCodeRequest request){
        var postCodeEntity = postalCodeRepository.findByPostcode(request.getPostcode());
        if (Objects.isNull(postCodeEntity)){
            throw  ResponseUtil.generateNotFoundError("Invalid postcode value");
        }

        postalCodeRepository.save(PostalCodeEntity.builder()
                        .id(postCodeEntity.getId())
                        .postcode(request.getPostcode())
                        .longitude(request.getLongitude())
                        .latitude(request.getLatitude())
                        .build());
        return ResponseUtil.generateSuccessStatus(null);
    }
}
