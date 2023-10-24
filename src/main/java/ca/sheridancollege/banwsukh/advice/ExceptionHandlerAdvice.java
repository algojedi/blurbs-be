package ca.sheridancollege.banwsukh.advice;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.validation.UnexpectedTypeException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import ca.sheridancollege.banwsukh.exceptions.ResourceNotFoundException;


/* 
 *  A global exception handler
 */
	@ControllerAdvice
	public class ExceptionHandlerAdvice {
		
		
	    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);

	    // TODO: add consistency to response messages

	    @ExceptionHandler({ DataAccessException.class })
	    public ResponseEntity<String> handleDataAccessException(DataAccessException ex) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Database connection failure.");
	    }
	    
	    @ExceptionHandler({ ResourceNotFoundException.class })
	    public ResponseEntity<String> handleResourceNotFoundException(DataAccessException ex) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource not found");
	    }

	    @ExceptionHandler({ UnexpectedTypeException.class })
	    public ResponseEntity<String> handleUnexpectedTypeException(UnexpectedTypeException ex) {
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body("Wrong Type!");
	    }

	    @ExceptionHandler(MethodArgumentNotValidException.class)
	    public ResponseEntity<Map<String, String>> handleValidationException(MethodArgumentNotValidException ex) {
	        BindingResult result = ex.getBindingResult();
	        Map<String, String> validationErrors = new HashMap<>();
	        result.getFieldErrors().forEach(error -> {
	            validationErrors.put(error.getField(), error.getDefaultMessage());
	        });
	        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(validationErrors);
	    }
	    
	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<String> handleException(Exception ex) {
	    	// TODO: need to be more specific
	        logger.error("An error occurred", ex);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
	    }
	    
	}

