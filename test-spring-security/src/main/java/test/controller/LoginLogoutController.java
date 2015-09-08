package test.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
@RequestMapping("auth")
public class LoginLogoutController {

	/** 指向登录页面 */
	@RequestMapping(value = "/login", method = RequestMethod.GET)
	public String getLoginPage(@RequestParam(required = false) boolean error, ModelMap model) {
		model.put("error", error == true ? "您输入了无效的用户名或密码!" : "");
		return "loginpage";
	}

	/** 指定无访问额权限页面 */
	@RequestMapping(value = "/denied", method = RequestMethod.GET)
	public String getDeniedPage() {
		return "deniedpage";
	}
	
}