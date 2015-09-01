package test.service;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.validation.annotation.Validated;

import test.dto.Result;

@Validated
public interface TestService {

	@NotNull(message="return null") 
	Result test(
			@NotNull(message="parameter id is null") @Min(value=10000, message="parameter id value < 10000") 
			Integer id, 
			@NotNull(message="parameter name is null") 
			String name);
	
}