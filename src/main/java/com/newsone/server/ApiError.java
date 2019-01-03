package com.newsone.server;

import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@NoArgsConstructor
class ApiError {

    private HttpStatus status;
    private String message;

    ApiError(HttpStatus status, String message) {
        this.status = status;
        this.message = message;
    }
}
