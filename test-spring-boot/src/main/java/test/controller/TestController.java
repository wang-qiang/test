package test.controller;

import org.springframework.boot.SpringApplication;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
//@EnableAutoConfiguration
public class TestController {

	@RequestMapping("test")
    public String test(){
        return "hello world";
    }
	
	public static void main(String[] args) {
        SpringApplication.run(TestController.class, args);	//访问：http://localhost:8080/hello
    }
	
}
