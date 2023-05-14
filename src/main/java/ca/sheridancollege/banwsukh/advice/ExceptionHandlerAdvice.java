package ca.sheridancollege.banwsukh.advice;

import org.springframework.dao.DataAccessException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;


/* 
 *  A global exception handler
 */
	@ControllerAdvice
	public class ExceptionHandlerAdvice {

	    @ExceptionHandler({ DataAccessException.class })
	    public ResponseEntity<String> handleDataAccessException(DataAccessException ex) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Database connection failure.");
	    }
	}

