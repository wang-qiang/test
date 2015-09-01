package test.action;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import test.common.Pager;

@Controller
public class TestAction {

	@RequestMapping("/test1")
	public String test(Map<String, Object> map) {
		map.put("test", "fuchao");
		return "test/test";
	}
	
	@RequestMapping("/test2")
	public ModelAndView test(String title, String content) {
		ModelAndView mv = new ModelAndView("test/test_page");
		mv.addObject("title", title);
		mv.addObject("content", content);
		mv.addObject("d", new Date());
		
		int[] is = {1,2,3,4,5,6,7,8,9};
		mv.addObject("is", is);
		
		Map<String,String> map = new HashMap<>();
		map.put("b", "yyy");
		mv.addObject("a", map);
		
		Pager pager = new Pager();
		pager.setPageNumber(10);
		pager.setTotalCount(9999);
		pager.setOrderBy("createDate");
		pager.setOrder(Pager.Order.asc);
		mv.addObject("pager", pager);
		return mv;
	}

}