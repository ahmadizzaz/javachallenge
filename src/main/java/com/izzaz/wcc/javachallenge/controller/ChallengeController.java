package com.izzaz.wcc.javachallenge.controller;

import com.izzaz.wcc.javachallenge.model.request.PostDistanceRequest;
import com.izzaz.wcc.javachallenge.model.request.PostalCodeRequest;
import com.izzaz.wcc.javachallenge.model.response.PostDistanceResponse;
import com.izzaz.wcc.javachallenge.model.response.apiresponse.ResponseModel;
import com.izzaz.wcc.javachallenge.model.response.apiresponse.Status;
import com.izzaz.wcc.javachallenge.service.GetPostalService;
import com.izzaz.wcc.javachallenge.service.PostDistanceService;
import com.izzaz.wcc.javachallenge.service.UpdatePostalService;
import com.izzaz.wcc.javachallenge.util.HeaderUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.izzaz.wcc.javachallenge.model.Endpoints.DISTANCE;
import static com.izzaz.wcc.javachallenge.model.Endpoints.POSTAL_V1;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ChallengeController {

    private final PostDistanceService postDistanceService;
    private final UpdatePostalService updatePostalService;
    private final GetPostalService getPostalService;

    @PostMapping(DISTANCE)
    public ResponseModel<Status,PostDistanceResponse> getDistance(HttpServletRequest request, @RequestBody @Valid PostDistanceRequest body){
        log.info("Request : {}",body);
        return postDistanceService.execute(HeaderUtil.getHeaders(request),body);
    }

    @PatchMapping(POSTAL_V1)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseModel<Status,Void> updatePostal(HttpServletRequest request, @RequestBody @Valid PostalCodeRequest body){
        log.info("Request : {}",body);
        return updatePostalService.execute(HeaderUtil.getHeaders(request),body);
    }

    @GetMapping(POSTAL_V1)
    public ResponseModel<Status,PostalCodeRequest> getPostal(HttpServletRequest request, @RequestParam String postcode){
        log.info("Request : {}",postcode);
        return getPostalService.execute(HeaderUtil.getHeaders(request),postcode);
    }

}
