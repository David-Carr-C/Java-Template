package com.example.domain;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;
import lombok.experimental.SuperBuilder;
import org.springframework.http.HttpStatus;

import java.time.LocalDateTime;
import java.util.Map;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;

@Data
@SuperBuilder
@JsonInclude(NON_NULL)
public class HttpResponse {
    protected String timeStamp;
    protected Integer httpStatusCode;
    protected HttpStatus httpStatus;
    protected String reason;
    protected String message;
    protected String developerMessage;
    protected StackTraceElement[] trace;
    protected Map<?, ?> data;

    public static HttpResponse getHttpResponse(Map<?, ?> data, String message, HttpStatus httpStatus) {
        return HttpResponse
                .builder()
                .timeStamp(String.valueOf(LocalDateTime.now()))
                .data(data)
                .httpStatusCode(httpStatus.value())
                .httpStatus(httpStatus)
                .message(message)
                .build();
    }

    public static HttpResponse getErrorHttpResponse(String message, String reason, String developerMessage, StackTraceElement[] trace, HttpStatus httpStatus) {
        return HttpResponse
                .builder()
                .timeStamp(String.valueOf(LocalDateTime.now()))
                .httpStatusCode(httpStatus.value())
                .httpStatus(httpStatus)
                .message(message)
                .reason(reason)
                .developerMessage(developerMessage)
                .trace(trace)
                .build();
    }
}
