package rest.api.springboot.rest.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import rest.api.springboot.rest.response.msg.ApiResponse;

@RestControllerAdvice
public class GlobalExceptionHandler {
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ApiResponse> resourceNotFoundExceptionHandler(ResourceNotFoundException ex){
		
		String message = ex.getMessage();
		return new ResponseEntity<ApiResponse> (new ApiResponse(message,false),HttpStatus.NOT_FOUND);
		
	}

}
