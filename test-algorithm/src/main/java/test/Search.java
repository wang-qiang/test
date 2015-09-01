package test;

import fuc.common.util.RandomUtil;

/** 搜索. */
public class Search {

	public static void main(String[] args) throws InterruptedException {
		int c = 100000;	//数组数据个数
		int f = c / 2;	//查找数据
		int t = 100000;	//循环执行次数
		int r = -2;
		long start, end;
		
		//测试数据
		int[] arr = new int[c];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = RandomUtil.nextInt(c);
		}
		
		//algorithm1
		int[] arr1 = arr.clone();
		start = System.currentTimeMillis();
		for (int i = 0; i < t; i++) {
			r = algorithm1(arr1, f);
		}
		end = System.currentTimeMillis();
		if (r > 0) System.out.println(arr1[r]);
		System.out.println(end - start);	//13000左右
		
		//algorithm2
		int[] arr2 = arr.clone();
		Sort.algorithm3(arr2, 0, arr2.length - 1);	//必须先排序
		start = System.currentTimeMillis();
		for (int i = 0; i < t; i++) {
			r = algorithm2(arr2, 0, arr2.length - 1, f);
		}
		end = System.currentTimeMillis();
		if (r > 0) System.out.println(arr2[r]);
		System.out.println(end - start);	//13000左右
	}
	
	/** 顺序 */
	public static int algorithm1(int[] arry, int des) {
		for (int i = 0; i < arry.length; i++) {
			if (des == arry[i]) {
				return i;
			}
		}
		return -1;
	}
	
	/** 二分 */
	public static int algorithm2(int[] sortedData, int start, int end, int findValue) {
        if (start <= end) {
            //中间位置
            int middle = (start + end) >> 1;		//相当于(start+end)/2
            int middleValue = sortedData[middle];	//中值
            if (findValue == middleValue) {
                return middle;	//等于中值直接返回
            } else if (findValue < middleValue) {
                return algorithm2(sortedData, start, middle - 1, findValue);	//小于中值时在中值前面找
            } else {
                return algorithm2(sortedData, middle + 1, end, findValue);		//大于中值在中值后面找
            }
        } else {
            return -1;
        }
    }
}
