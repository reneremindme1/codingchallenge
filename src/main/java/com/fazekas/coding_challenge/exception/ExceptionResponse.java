package com.fazekas.coding_challenge.exception;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

import java.util.Date;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
public class ExceptionResponse {

    private Date timestamp;
    private String message;
    private String details;
}
