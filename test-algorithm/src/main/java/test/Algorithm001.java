package test;

import java.util.ArrayList;
import java.util.List;

import fuc.common.util.JsonUtil;

/** 查找数组中和值的某数的所有两数组合 */
public class Algorithm001 {
	
	public static void main(String[] args) {
		int sum = 30;
		int[] ascArrs = {1, 3, 6, 8, 9, 10, 12, 14, 15, 17, 18, 20, 21, 23, 25, 26, 27, 29};	//数据越多，algorithm1优势越大
		int c = 10000000;	//循环次数
		
		//正确性测试
		System.out.println(JsonUtil.toJson(algorithm1(ascArrs, sum)));
		System.out.println(JsonUtil.toJson(algorithm2(ascArrs, sum)));
		
		//algorithm1性能
		long start = System.currentTimeMillis();
		for (int i = 0; i < c; i++) {
			algorithm1(ascArrs, sum);
		}
		long end = System.currentTimeMillis();
		long time1 = end - start;
		System.out.println(time1);
		
		//algorithm2性能
		start = System.currentTimeMillis();
		for (int i = 0; i < c; i++) {
			algorithm2(ascArrs, sum);
		}
		end = System.currentTimeMillis();
		long time2 = end - start;
		System.out.println(time2);
		
		//性能比
		System.out.println((double)time1 / (double)time2);
	}
	

	/**
	 * 找出ascArrs中和值为sum的两数组合
	 * @param ascArrs 升序数组，无重复值
	 * @param sum 两数相加的和值
	 * @return 和值为sum的两数组合
	 */
	public static List<int[]> algorithm1(int[] ascArrs, int sum) {
		int x = 0;
		int y = ascArrs.length - 1;
		List<int[]> result = new ArrayList<int[]>();
		while (x < y) {
			int xy = ascArrs[x] + ascArrs[y];
			if (xy > sum) {
				y--;
			} else if (xy < sum) {
				x++;
			} else {
				result.add(new int[]{ascArrs[x++], ascArrs[y--]});
			}
		}
		return result;
	}
	
	
	public static List<int[]> algorithm2(int[] ascArrs, int sum) {
		List<int[]> result = new ArrayList<int[]>();
		for (int i = 0; i < ascArrs.length; i++) {
			for (int j = i + 1; j < ascArrs.length; j++) {
				if (ascArrs[i] + ascArrs[j] == sum) {
					result.add(new int[]{ascArrs[i], ascArrs[j]});
				}
			}
		}
		return result;
	}
	
}
