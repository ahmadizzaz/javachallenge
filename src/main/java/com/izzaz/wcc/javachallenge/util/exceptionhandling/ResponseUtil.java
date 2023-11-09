package com.izzaz.wcc.javachallenge.util.exceptionhandling;

import com.izzaz.wcc.javachallenge.model.response.apiresponse.ResponseModel;
import com.izzaz.wcc.javachallenge.model.response.apiresponse.Status;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public class ResponseUtil {

    public static <T>ResponseModel<Status,T> generateSuccessStatus(T data){
        var status=  Status.builder()
                .code(HttpStatus.OK.value())
                .message("Success")
                .build();
        ResponseModel<Status,T> response = new ResponseModel<>();
        response.setStatus(status);
        response.setData(data);
        return response;
    }

    public static HttpClientErrorException generateNotFoundError(final String message){
        return HttpClientErrorException.create(message,HttpStatus.NOT_FOUND,"",null,null,null);
    }
    public static HttpServerErrorException generateInternalServerError(final String message){
        return  HttpServerErrorException.create(message,HttpStatus.INTERNAL_SERVER_ERROR,"",null,null,null);
    }

    public static <T> ResponseModel<Status,T> generateInternalServerErrorResponse(T data){
        var status=  Status.builder()
                .code(HttpStatus.INTERNAL_SERVER_ERROR.value())
                .message("Internal Server Error")
                .build();
        ResponseModel<Status,T> response = new ResponseModel<>();
        response.setStatus(status);
        response.setData(data);
        return response;
    }

    public static <T> ResponseModel<Status,T> generateNotFoundErrorResponse(T data){

        var status=  Status.builder()
                .code(HttpStatus.NOT_FOUND.value())
                .message("Not Found")
                .build();
        ResponseModel<Status,T> response = new ResponseModel<>();
        response.setStatus(status);
        response.setData(data);
        return response;
    }

    public static <T> ResponseModel<Status,T> generateBadRequestErrorResponse(T data){
        var status=  Status.builder()
                .code(HttpStatus.BAD_REQUEST.value())
                .message("Bad Request")
                .build();
        ResponseModel<Status,T> response = new ResponseModel<>();
        response.setStatus(status);
        response.setData(data);
        return response;
    }

    public static <T> ResponseModel<Status,T> generateUnauthorizedErrorResponse(T data){
        var status=  Status.builder()
                .code(HttpStatus.UNAUTHORIZED.value())
                .message("Unauthorized")
                .build();
        ResponseModel<Status,T> response = new ResponseModel<>();
        response.setStatus(status);
        response.setData(data);
        return response;
    }
}
