package example02;

import java.util.ArrayList;
import java.util.List;

public class DataFactory {

	public static List<Medicine> getData() {
		List<Medicine> list = new ArrayList<Medicine>();
		list.add(new Medicine(1, "银花 感冒颗粒", "功能主治：银花感冒颗粒 ，头痛,清热，解表，利咽。"));
		list.add(new Medicine(2, "感冒 止咳糖浆", "功能主治：感冒止咳糖浆,解表清热，止咳化痰。"));
		list.add(new Medicine(3, "感冒灵颗粒", "功能主治：解热镇痛。头痛 ,清热。"));
		list.add(new Medicine(4, "感冒灵胶囊", "功能主治：银花感冒颗粒 ，头痛,清热，解表，利咽。"));
		list.add(new Medicine(5, "仁和 感冒颗粒", "功能主治：疏风清热，宣肺止咳,解表清热，止咳化痰。"));
		return list;
	}

}