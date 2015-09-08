package test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
@RequestMapping("/main")
public class MainController {
	
	/** 跳转到commonpage页面 */
	@RequestMapping(value = "/common", method = RequestMethod.GET)
	public String getCommonPage() {
		return "commonpage";
	}

	/** 跳转到adminpage页面 */
	@RequestMapping(value = "/admin", method = RequestMethod.GET)
	public String getAadminPage() {
		return "adminpage";
	}

}