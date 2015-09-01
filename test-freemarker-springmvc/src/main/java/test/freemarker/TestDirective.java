package test.freemarker;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Component;

import freemarker.core.Environment;
import freemarker.template.TemplateDirectiveBody;
import freemarker.template.TemplateDirectiveModel;
import freemarker.template.TemplateException;
import freemarker.template.TemplateModel;
import fuc.common.freemarker.FreemarkerUtil;

@Component
public class TestDirective implements TemplateDirectiveModel {
	
	@SuppressWarnings({"rawtypes", "unchecked" })
	public void execute(Environment env, Map params, TemplateModel[] loopVars, TemplateDirectiveBody body) throws TemplateException, IOException {
		if (body == null) {
			return;
		}
		
		//获取参数
		int num = FreemarkerUtil.getIntegerParameter("num", params);
		
		//获取数据
		List<Map<String, String>> l = new ArrayList<>();
		for (int i = 1; i <= num; i++) {
			Map<String, String> m = new HashMap<>();
			m.put("title", "title" + i);
			m.put("content", "content" + i);
			l.add(m);
		}
		
		//设置变量
		env.setVariable("articleList", env.getObjectWrapper().wrap(l));
		body.render(env.getOut());
	}
}