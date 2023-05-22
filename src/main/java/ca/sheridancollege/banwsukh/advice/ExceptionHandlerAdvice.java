package ca.sheridancollege.banwsukh.advice;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import ca.sheridancollege.banwsukh.exceptions.ResourceNotFoundException;


/* 
 *  A global exception handler
 */
	@ControllerAdvice
	public class ExceptionHandlerAdvice {
		
		
	    private static final Logger logger = LoggerFactory.getLogger(ExceptionHandlerAdvice.class);


	    @ExceptionHandler({ DataAccessException.class })
	    public ResponseEntity<String> handleDataAccessException(DataAccessException ex) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Database connection failure.");
	    }
	    
	    @ExceptionHandler({ ResourceNotFoundException.class })
	    public ResponseEntity<String> handleResourceNotFoundException(DataAccessException ex) {
	        return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Resource not found");
	    }
	    

	    @ExceptionHandler(Exception.class)
	    public ResponseEntity<String> handleException(Exception ex) {
	    	// TODO: need to be more specific
	        logger.error("An error occurred", ex);
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("An error occurred");
	    }
	    
	}

