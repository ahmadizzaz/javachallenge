package com.izzaz.wcc.javachallenge.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.izzaz.wcc.javachallenge.config.SecurityConfig;
import com.izzaz.wcc.javachallenge.model.dao.PostalCodeEntity;
import com.izzaz.wcc.javachallenge.model.request.PostDistanceRequest;
import com.izzaz.wcc.javachallenge.model.request.PostalCodeRequest;
import com.izzaz.wcc.javachallenge.model.response.apiresponse.ResponseModel;
import com.izzaz.wcc.javachallenge.model.response.apiresponse.Status;
import com.izzaz.wcc.javachallenge.repository.PostalCodeRepository;
import com.izzaz.wcc.javachallenge.service.PostDistanceService;
import com.izzaz.wcc.javachallenge.service.UpdatePostalService;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Import;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.RequestPostProcessor;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

import java.math.BigDecimal;
import java.util.List;

import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.httpBasic;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.patch;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringBootTest
@ExtendWith(SpringExtension.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class ChallengeControllerTest {
    @Autowired
    private WebApplicationContext applicationContext;
    ObjectMapper objectMapper = new ObjectMapper();

    private  MockMvc mockMvc;

    @Autowired
    private PostalCodeRepository repository;

    private void initData(){
        PostalCodeEntity entity = PostalCodeEntity.builder()
                .id(1L)
                .postcode("ABC 123")
                .latitude(BigDecimal.ONE)
                .longitude(BigDecimal.ONE)
                .build();

        PostalCodeEntity entity2 = PostalCodeEntity.builder()
                .id(2L)
                .postcode("DEF 456")
                .latitude(BigDecimal.valueOf(2))
                .longitude(BigDecimal.valueOf(2))
                .build();

        repository.saveAll(List.of(entity,entity2));
    }

    @BeforeAll
    public void init(){
        this.mockMvc = MockMvcBuilders
                .webAppContextSetup(applicationContext)
                .build();
        initData();
    }
    @Test
    @WithMockUser(roles = "ADMIN")
    void success_secure() throws Exception {
        this.mockMvc.perform(patch("/v1/jchallenge/postal")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString( PostalCodeRequest.builder()
                        .latitude(BigDecimal.valueOf(123.1))
                        .longitude(BigDecimal.valueOf(432.1))
                        .postcode("ABC 123")
                        .build())))
                .andExpect(status().isOk());

    }

    @Test
    void fail_unauthorized() throws Exception {
        this.mockMvc.perform(patch("/v1/jchallenge/postal")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString( PostalCodeRequest.builder()
                                .latitude(BigDecimal.valueOf(123.1))
                                .longitude(BigDecimal.valueOf(432.1))
                                .postcode("ABC 123")
                                .build()))
                )
                .andExpect(status().isUnauthorized());

    }

    @Test
    void success_non_secure() throws Exception {
        this.mockMvc.perform(post("/v1/jchallenge/distance")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString( PostDistanceRequest.builder()
                                .postalCodeFrom("ABC 123")
                                .postalCodeTo("DEF 456")
                                .build())))
                .andExpect(status().isOk());

    }
}
