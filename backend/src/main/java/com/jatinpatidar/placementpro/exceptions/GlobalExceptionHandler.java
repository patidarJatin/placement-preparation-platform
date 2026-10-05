package com.jatinpatidar.placementpro.exceptions;

import com.jatinpatidar.placementpro.dto.common.ErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.parameters.P;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {

    private ResponseEntity<ErrorResponse> buildErrorResponse(
            String message,
            HttpStatus status,
            HttpServletRequest request
    ) {
        ErrorResponse errorResponse = new ErrorResponse(
                LocalDateTime.now(),
                status.value(),
                status.getReasonPhrase(),
                message,
                request.getRequestURI()
        );

        return ResponseEntity
                .status(status)
                .body(errorResponse);
    }

    @ExceptionHandler(EmailAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleEmailAlreadyExistsException(
            EmailAlreadyExistsException exception, HttpServletRequest request
    ) {
        return buildErrorResponse(exception.getMessage(),HttpStatus.CONFLICT, request);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ErrorResponse> handleValidationException(
            MethodArgumentNotValidException exception, HttpServletRequest request
    ) {
        String message = exception.getBindingResult()
                .getFieldErrors()
                .get(0)
                .getDefaultMessage();

        return buildErrorResponse(message,HttpStatus.BAD_REQUEST,request);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleGlobalException(
            Exception exception, HttpServletRequest request
    ) {
      return buildErrorResponse(exception.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR,request);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public ResponseEntity<ErrorResponse> handleInvalidCredentialsException(
            InvalidCredentialsException exception ,HttpServletRequest request
    ){
       return buildErrorResponse(exception.getMessage(), HttpStatus.UNAUTHORIZED, request);
    }

    @ExceptionHandler(InvalidTokenException.class)
    public ResponseEntity<ErrorResponse> handleInvalidToken(
            InvalidTokenException exception,
            HttpServletRequest request
    ) {
        return buildErrorResponse(exception.getMessage(), HttpStatus.UNAUTHORIZED,request);
    }

    @ExceptionHandler(PasswordMismatchException.class)
    public ResponseEntity<ErrorResponse> handlePasswordMismatchException(
            PasswordMismatchException exception,
            HttpServletRequest request
    ) {

        return buildErrorResponse(exception.getMessage(), HttpStatus.BAD_REQUEST,request);
    }

    @ExceptionHandler(ProfileAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleProfileAlreadyExistsException(
            ProfileAlreadyExistsException exception, HttpServletRequest request
    ) {

        return buildErrorResponse(exception.getMessage(), HttpStatus.CONFLICT,request);
    }

    @ExceptionHandler(ProfileNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleProfileNotFoundxception(
            ProfileNotFoundException exception, HttpServletRequest request
    ) {

        return buildErrorResponse(exception.getMessage(), HttpStatus.NOT_FOUND,request);
    }

    @ExceptionHandler(CompanyAlreadyExistsException.class)
    public ResponseEntity<ErrorResponse> handleCompanyAlreadyExistsException(
            CompanyAlreadyExistsException exception,HttpServletRequest request
    ){
        return buildErrorResponse(exception.getMessage(), HttpStatus.CONFLICT,request);
    }
}