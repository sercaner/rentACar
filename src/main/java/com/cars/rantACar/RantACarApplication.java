package com.cars.rantACar;

import com.cars.rantACar.core.utilities.exceptions.BusinessException;
import com.cars.rantACar.core.utilities.exceptions.ProblemDetails;
import com.cars.rantACar.core.utilities.exceptions.ValidationProblemDetails;
import org.modelmapper.ModelMapper;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;

@SpringBootApplication
@RestControllerAdvice
public class RantACarApplication {

	public static void main(String[] args) {
		SpringApplication.run(RantACarApplication.class, args);
	}

	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ProblemDetails handleValidationException(BusinessException businessException){
		ProblemDetails problemDetails = new ProblemDetails();
		problemDetails.setMessage(businessException.getMessage());
		return problemDetails;
	}
	@ExceptionHandler
	@ResponseStatus(code = HttpStatus.BAD_REQUEST)
	public ProblemDetails handleBusinessException(MethodArgumentNotValidException methodArgumentNotValidException){
		ValidationProblemDetails validationProblemDetails = new ValidationProblemDetails();
		validationProblemDetails.setMessage("Validation Exception");
		validationProblemDetails.setValitationErrors(new HashMap<String, String>());

		for(FieldError fieldError : methodArgumentNotValidException.getBindingResult().getFieldErrors() ){
			validationProblemDetails.getValitationErrors().put(fieldError.getField(), fieldError.getDefaultMessage());
		}
		return validationProblemDetails;
	}

	@Bean //IoC'ye ekle
public ModelMapper getModelMapper(){
		return new ModelMapper();
	}
}
