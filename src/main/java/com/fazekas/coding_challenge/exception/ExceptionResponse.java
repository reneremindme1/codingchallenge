package com.fazekas.coding_challenge.exception;

import lombok.*;

import java.util.Date;

@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor
@Data
@ToString
public class ExceptionResponse {

    private Date timestamp;
    private String message;
    private String details;
}
