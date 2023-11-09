package com.izzaz.wcc.javachallenge.controller;

import com.izzaz.wcc.javachallenge.model.request.PostDistanceRequest;
import com.izzaz.wcc.javachallenge.model.request.PostalCodeRequest;
import com.izzaz.wcc.javachallenge.model.response.PostDistanceResponse;
import com.izzaz.wcc.javachallenge.model.response.apiresponse.ResponseModel;
import com.izzaz.wcc.javachallenge.model.response.apiresponse.Status;
import com.izzaz.wcc.javachallenge.service.PostDistanceService;
import com.izzaz.wcc.javachallenge.service.UpdatePostalService;
import com.izzaz.wcc.javachallenge.util.HeaderUtil;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import static com.izzaz.wcc.javachallenge.model.Endpoints.*;

@RestController
@RequiredArgsConstructor
@Slf4j
public class ChallengeController {

    private final PostDistanceService postDistanceService;
    private final UpdatePostalService updatePostalService;

    @PostMapping(DISTANCE)
    public ResponseModel<Status,PostDistanceResponse> getDistance(HttpServletRequest request,HttpServletResponse response, @RequestBody @Valid PostDistanceRequest body){
        log.info("Headers : {} \n Request : {}",body);
        return postDistanceService.execute(HeaderUtil.getHeaders(request),body);
    }

    @PatchMapping(POSTAL_V1)
    @PreAuthorize("hasAuthority('ROLE_ADMIN')")
    public ResponseModel<Status,Void> updatePostal(HttpServletRequest request, HttpServletResponse response, @RequestBody @Valid PostalCodeRequest body){
        log.info("Headers : {} \n Request : {}",body);
        return updatePostalService.execute(HeaderUtil.getHeaders(request),body);
    }

}
