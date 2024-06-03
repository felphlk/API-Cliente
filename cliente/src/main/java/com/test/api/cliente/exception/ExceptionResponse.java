package com.test.api.cliente.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class ExceptionResponse {

    private Date timestamp;
    private String message;
    private String details;

}
