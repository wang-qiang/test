<@compress single_line=true>			<#-- 移除空白，也可使用<#compress> -->

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>${title!"默认标题"}</title>
	</head>
	<body>
		<!-- 1.注释 -->
		<#-- FreeMarker注释 -->
		
		<!-- 2.插值 -->
		插值：
			${content!"默认内容"}		<#-- 空值设默认 -->
			${(a.b)!"默认内容"}			<#-- 多级空值设默认 -->
			${1.1234567}				<#-- 格式化数字：通过配置freemarkerSettings下的number_format设置，默认#.###(只输出3位小数) -->
			<br>
		使用内建函数：
			${123?string+456}			<#-- 转字符串 -->
			${false?string("yes","no")}	<#-- 转字符串 -->
			${1.1?int}					<#-- 转int -->
			${"<b>bbb</b>"?html}		<#-- 转html -->
			${"china"?cap_first}		<#-- 首字母转大写 -->
			${"abc"?upper_case}			<#-- 转大写 -->
			${"AbC"?lower_case}			<#-- 转小写 -->
			${" xyz "?trim}				<#-- 去首尾空格 -->
			${is?size}					<#-- 序列个数 -->
			${123.1?c}					<#-- 转换为计算机能识别的数字 -->
			${d?time}					<#-- 格式化时间：通过配置freemarkerSettings下的time_format设置，默认yyyy-MM-dd-->
			${d?date}					<#-- 格式化日期：通过配置freemarkerSettings下的date_format设置，默认HH:mm:ss-->
			${d?datetime}				<#-- 格式化日期时间：通过配置freemarkerSettings下的datetime_format设置，默认yyyy-MM-dd HH:mm:ss-->
			<br>
		转义：
			${r"${foo}"}
			${"\axxxx"}
			<br>
			<hr>
	
		<!-- 3.指令 -->
		if指令：
			<#if !content??>			<#-- 空值处理：判断 -->
				content为null
			<#elseif content == "1">
				content为1
			<#else>
				content非null非1
			</#if>
			<br>
		
		list指令：
			<#list 1..5 as i>
				${i}
			</#list>
			<#list is[5..8] as i>		<#-- 序列切分 -->
				${i}
			</#list>
			<br>
		
		include指令：
			<#include "/test/test_page.txt">
			<br>
			
		escape与noescape指令：
			<#escape x as x?html>		<#-- 对包含的所有插值转义，相当于都加了一个?html，建议将完整的模板放入到escape指令中，可阻止跨站脚本攻击和非格式良好的HTML页 -->
				${"<b>escape</b>"}
				<#noescape>${"<b>escape</b>"}</#noescape>
			</#escape>
			<br>
		
		macro指令：
			<#macro greet person color="black">				<#-- 自定义指令，创建一个宏变量greet，参数person、color，color默认black -->
				<font size="+2" color="${color}">Hello ${person}!</font>
			</#macro>
			<@greet person="Fred" color="red"></@greet>		<#-- 使用自定义指令 -->
			<@greet person="Fred"/>							<#-- 使用自定义指令 -->
			<br>
			
		nested指令：
			<#macro border>
				<table border=1 cellspacing=0 cellpadding=4>
					<tr>
						<td>
							<#nested>						<#-- 输出<@border>和</@border>之间的内容 -->
						</td>
					</tr>
				</table>
			</#macro>
			<@border>zcdscfdsf</@border>
			<br>
			
		assign指令：
			<#assign content = 1>							<#-- 定义变量，比数据模型上同名变量有更高优先级 -->
			${content}
			<#assign content = content + 3>					<#-- 替换变量 -->
			${content}
			${.globals.content}								<#-- 强制访问数据模型中的变量 -->
			<br>
			
		import指令：
			<#import "/test/test_library.ftl" as my>		<#-- 导入库，并指定命名空间为my -->
			${my.mail}										<#-- 访问命名空间中的变量 -->
			<@my.copyright date="1998 - 2015"/>				<#-- 使用命名空间中的自定义指令 -->
			<#assign mail="555@qq.com" in my>				<#-- 替换命名空间中的变量 -->
			${my.mail}
			<br>
			
		自定义函数：
			${substring(my.mail, 5, "...")}
			${random(100)}
			<br>
			
		自定义指令：
			<@checkbox name="abc" value="true" />
			<@pagination pager=pager baseUrl="/xxx/yyy/zzz.do">
         		<#include "pager.ftl">
         	</@pagination>
         	<table>
				<tr>
					<td>标题</td>
					<td>内容</td>
				</tr>
				<@test num=10; articleList>
					<#if (articleList?? && articleList?size > 0)>
						<#list articleList as article>
							<tr>
								<td>${article.title}</td>
								<td>${article.content}</td>
							</tr>
						</#list>
					</#if>
				</@test>
			</table>
			<br>
	</body>
</html>

</@compress>