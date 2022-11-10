package com.dailycodebuffer.user.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    // Handel Specific Exception
    @ExceptionHandler(APIException.class)
    public ResponseEntity<?> handleAPIException(APIException exception, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    // Handel Specific Exception Similar Implimentation
    @ExceptionHandler(CustomerNotFoundException.class)
    public ResponseEntity<?> handleCustomerNotFoundExceptin(CustomerNotFoundException exception, HttpServletRequest httpServletRequest){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), httpServletRequest.getServletPath());
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    // This handle MethodArgumentNotValidException during post request.
    // We override ResposneEntityExcetpinHandler MethodArgumentNotValidException class method.
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<?> handleMethodArgumentNotValidException(MethodArgumentNotValidException exception, HttpServletRequest httpServletRequest){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), "Bad request", httpServletRequest.getServletPath());
        //It gets all the binding result with multiple errors
        BindingResult bindingResult = exception.getBindingResult();
        Map<String, String> validationErrors = new HashMap<>();
        // loop on multiple Field Errors
        for(FieldError fieldError : bindingResult.getFieldErrors()){
          validationErrors.put(fieldError.getField(), fieldError.getDefaultMessage());
        }
        errorDetails.setValidationError(validationErrors);
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    // Handel Global Exception
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> handleException(Exception exception, WebRequest request){
        ErrorDetails errorDetails = new ErrorDetails(new Date(), exception.getMessage(), request.getDescription(false));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
