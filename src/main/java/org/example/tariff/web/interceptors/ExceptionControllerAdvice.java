/**
 * 
 */
package org.example.tariff.web.interceptors;


import java.io.PrintWriter;
import java.io.StringWriter;


import org.example.tariff.exceptions.EntityNotFoundException;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;


import org.springframework.web.bind.ServletRequestBindingException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;



import org.springframework.http.ResponseEntity;


@ControllerAdvice
public class ExceptionControllerAdvice {

    private final Logger logger = LoggerFactory.getLogger(getClass());

   @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> entityNotFoundException(EntityNotFoundException e) {
        logger.error("EntityNotFoundException occured: "+e.getMessage());
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setErrorMessage(e.getMessage());
        errorDetails.setErrorCode(e.toString());
        errorDetails.setDevErrorMessage("");
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }


    @ExceptionHandler(ServletRequestBindingException.class)
    public ResponseEntity<?> servletRequestBindingException(ServletRequestBindingException e) {
        logger.error("ServletRequestBindingException occured: "+e.getMessage());
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setErrorMessage(e.getMessage());
        
        errorDetails.setDevErrorMessage("");
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }

    
    @ExceptionHandler(Exception.class)
    public ResponseEntity<?>  exception(Exception e) {
        logger.error("Exception occured: "+e.getMessage(), e);
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setErrorMessage(e.getMessage());
        errorDetails.setDevErrorMessage(getStackTraceAsString(e));
        return new ResponseEntity<>(errorDetails, HttpStatus.INTERNAL_SERVER_ERROR);
    }
     @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<?> exception(RuntimeException e) {
        logger.error("Exception occured: "+e.getMessage(), e);
        
        ErrorDetails errorDetails = new ErrorDetails();
        errorDetails.setErrorMessage(e.getMessage());
        errorDetails.setDevErrorMessage("");
        return new ResponseEntity<>(errorDetails, HttpStatus.BAD_REQUEST);
    }
    private String getStackTraceAsString(Exception e)
    {
        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);
        e.printStackTrace(pw);
        return sw.toString();
    }
}
