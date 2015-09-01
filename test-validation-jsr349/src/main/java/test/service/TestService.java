package test.service;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Service;
import org.springframework.validation.annotation.Validated;

@Validated
@Service
public class TestService {

	public @NotNull(message="return null") String test(
			@NotNull(message="parameter id is null") @Min(value=10000, message="parameter id value < 10000") Integer id,
			@NotNull(message="parameter name is null") String name) {
		return "success";
	}
	
}