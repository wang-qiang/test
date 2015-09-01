package test;

import java.io.File;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.Map;

import freemarker.template.Configuration;
import freemarker.template.DefaultObjectWrapper;
import freemarker.template.Template;

public class Test {
	public static void main(String[] args) throws Exception {
		//创建和调整配置
		Configuration cfg = new Configuration(Configuration.VERSION_2_3_22);
		cfg.setDirectoryForTemplateLoading(new File(Test.class.getResource("/template").getPath()));
		cfg.setObjectWrapper(new DefaultObjectWrapper(Configuration.VERSION_2_3_22));
		
		//获取或创建模板
		Template temp = cfg.getTemplate("test.ftl");
		
		//创建数据模型
		Map<String, Object> root = new HashMap<>();
		root.put("user", "Big Joe");
		Map<String, String> latest = new HashMap<>();
		root.put("latestProduct", latest);
		latest.put("url", "products/greenmouse.html");
		latest.put("name", "green mouse");
		
		//将模板和数据模型合并
		Writer out = new OutputStreamWriter(System.out);
		temp.process(root, out);
		out.flush();
	}
}