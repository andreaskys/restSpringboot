package br.com.andreas.exception;

import java.util.Date;

public record ExceptionResponse(Date timestamp, String message, String details) {

}
