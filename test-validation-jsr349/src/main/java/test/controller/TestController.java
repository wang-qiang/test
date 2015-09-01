package test.controller;

import java.util.HashSet;
import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import fuc.common.util.JsonUtil;
import test.service.TestService;

@Controller
public class TestController {

	@Autowired
	private TestService testService;
	
	@RequestMapping("/test")
	public @ResponseBody String test(Integer id, String name) {
		try {
			return testService.test(id, name);
		} catch (ConstraintViolationException e) {
			Set<String> result = new HashSet<>();
			Set<ConstraintViolation<?>> set = e.getConstraintViolations();
			for (ConstraintViolation<?> m : set) {
				result.add(m.getMessage());
			}
			return JsonUtil.toJson(result);
		}
	}
	
}