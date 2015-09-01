package test.service;

import org.springframework.stereotype.Service;

import test.dto.Result;


@Service
public class TestServiceImpl implements TestService {
	
	public Result test(Integer id, String name) {
		return new Result(0, "success");
	}

}