package test;

//import fuc.common.util.JsonUtil;
import fuc.common.util.RandomUtil;

/** 排序. */
public class Sort {

	public static void main(String[] args) throws InterruptedException {
		int c = 100000;	//排序数据个数
		long start, end;
		
		//测试数据
		int[] arr = new int[c];
		for (int i = 0; i < arr.length; i++) {
			arr[i] = RandomUtil.nextInt(c);
		}
		
//		//algorithm1
//		int[] arr1 = arr.clone();
//		start = System.currentTimeMillis();
//		algorithm1(arr1);
//		end = System.currentTimeMillis();
////		System.out.println(JsonUtil.toJson(arr1));
//		System.out.println(end - start);	//13000左右
//		
//		//algorithm2
//		int[] arr2 = arr.clone();
//		start = System.currentTimeMillis();
//		algorithm2(arr2);
//		end = System.currentTimeMillis();
////		System.out.println(JsonUtil.toJson(arr2));
//		System.out.println(end - start);	//3000左右
		
		//algorithm3
		int[] arr3 = arr.clone();
		start = System.currentTimeMillis();
		algorithm3(arr3, 0, arr3.length - 1);
		end = System.currentTimeMillis();
//		System.out.println(JsonUtil.toJson(arr3));
		System.out.println(end - start);	//30左右
		
		//algorithm4
		int[] arr4 = arr.clone();
		start = System.currentTimeMillis();
		algorithm4(arr4, 0, arr4.length - 1);
		end = System.currentTimeMillis();
//		System.out.println(JsonUtil.toJson(arr4));
		System.out.println(end - start);	//30左右
		
		//algorithm5
		int[] arr5 = arr.clone();
		start = System.currentTimeMillis();
		algorithm5(arr5);
		end = System.currentTimeMillis();
//		System.out.println(JsonUtil.toJson(arr5));
		System.out.println(end - start);	//30左右
		
		//algorithm6
		int[] arr6 = arr.clone();
		start = System.currentTimeMillis();
		algorithm6(arr6, 0, arr6.length - 1);
		end = System.currentTimeMillis();
//		System.out.println(JsonUtil.toJson(arr6));
		System.out.println(end - start);	//30左右
		
	}
	
	/** 冒泡（将最大、次大、次次大……依次、逐步移动到正确位置） */
	public static void algorithm1(int[] arr) {
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = 0; j < arr.length - i - 1; j++) {
				if (arr[j] > arr[j + 1]) {
					int temp = arr[j];
					arr[j] = arr[j + 1];
					arr[j + 1] = temp;
				}
			}
		}
	}
	
	/** 选择（依次找最小、次小、次次小……，并一次性移到合理位置） */
	public static void algorithm2(int[] arr) {
		for (int i = 0; i < arr.length; i++) {
			int minIndex = i;
			for (int j = i + 1; j < arr.length; j++) {
				if (arr[minIndex] > arr[j]) {
					minIndex = j;
				}
			}
			if (minIndex != i) {	//作用不大，加与不加循环次数非常接近
				int temp = arr[i];
				arr[i] = arr[minIndex];
				arr[minIndex] = temp;
			}
		}
	}

	/** 快速 */
	public static void algorithm3(int[] arr, int left, int right) {
		if (left > right) {
			return;
		}
		
		int l = left;
		int r = right;
		int base = arr[left];					//基数
		
		while (l < r) {
			while (arr[r] >= base && l < r) {	//从右往左，找小于基数的索引（顺序很重要，可能会找不到）
				r--;
			}
			if (l < r) {						//将小于基数的值交换到左边
				int temp = arr[r];
				arr[r] = arr[l];
				arr[l] = temp;
				l++;
			}
			while (arr[l] <= base && l < r) {	//从左往右，找大于基数的索引（顺序很重要，一定能找到）
				l++;
			}
			if (l < r) {						//将大于基数的值交换到右边
				int temp = arr[r];
				arr[r] = arr[l];
				arr[l] = temp;
				r--;
			}
		}
		algorithm3(arr, left, l - 1);
		algorithm3(arr, l + 1, right);
	}
	
	/** 快速 */
	public static void algorithm4(int[] arr, int left, int right) {
		if (left > right) {
			return;
		}
		
		int l = left;
		int r = right;
		int base = arr[left];					//基数

		while (l < r) {
			while (arr[r] >= base && l < r) {	//从右往左，找小于基数的索引（顺序很重要，可能会找不到）
				r--;
			}
			while (arr[l] <= base && l < r) {	//从左往右，找大于基数的索引（顺序很重要，一定能找到）
				l++;
			}
			if (l < r) {						//l、r未相遇，交换两个数的位置
				int temp = arr[l];
				arr[l] = arr[r];
				arr[r] = temp;
			} else {							//l、r相遇，交换l和基数的值
				arr[left] = arr[l];
				arr[l] = base;
			}
		}
		algorithm4(arr, left, l - 1);			//继续处理左边的，这里是一个递归的过程
		algorithm4(arr, l + 1, right);			//继续处理右边的，这里是一个递归的过程
	}
	
	/** 堆 */
	public static void algorithm5(int[] array) {
		if (array == null || array.length <= 1) {
			return;
		}

		buildMaxHeap(array);

		for (int i = array.length - 1; i >= 1; i--) {
			exchangeElements(array, 0, i);
			maxHeap(array, i, 0);
        }
    }
	private static void buildMaxHeap(int[] array) {
		if (array == null || array.length <= 1) {
			return;
		}

		int half = array.length / 2;
		for (int i = half; i >= 0; i--) {
			maxHeap(array, array.length, i);
		}
	}
	private static void maxHeap(int[] array, int heapSize, int index) {
		int left = index * 2 + 1;
		int right = index * 2 + 2;

		int largest = index;
		if (left < heapSize && array[left] > array[index]) {
			largest = left;
		}

		if (right < heapSize && array[right] > array[largest]) {
			largest = right;
		}

		if (index != largest) {
			exchangeElements(array, index, largest);
			maxHeap(array, heapSize, largest);
		}
	}  
	private static void exchangeElements(int[] array, int index1, int index2) {
		int temp = array[index1];
		array[index1] = array[index2];
		array[index2] = temp;
	}
	
	/** 归并 */
	public static int[] algorithm6(int[] nums, int low, int high) {
		int mid = (low + high) / 2;
		if (low < high) {
			algorithm6(nums, low, mid);		//左边
			algorithm6(nums, mid + 1, high);		//右边
			merge(nums, low, mid, high);	//左右归并
		}
		return nums;
	}
	private static void merge(int[] nums, int low, int mid, int high) {
		int[] temp = new int[high - low + 1];
		int i = low;		//左指针
		int j = mid + 1;	//右指针
		int k = 0;

		//把较小的数先移到新数组中
		while (i <= mid && j <= high) {
			if (nums[i] < nums[j]) {
				temp[k++] = nums[i++];
			} else {
				temp[k++] = nums[j++];
			}
		}

		//把左边剩余的数移入数组  
		while (i <= mid) {
			temp[k++] = nums[i++];
		}

		//把右边边剩余的数移入数组
		while (j <= high) {
			temp[k++] = nums[j++];
		}

		//把新数组中的数覆盖nums数组
		for (int k2 = 0; k2 < temp.length; k2++) {
			nums[k2 + low] = temp[k2];
		}
	}
}