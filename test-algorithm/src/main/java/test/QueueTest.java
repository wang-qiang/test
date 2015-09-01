package test;

import java.util.Deque;
import java.util.LinkedList;
import java.util.Queue;

public class QueueTest {

	public static void main(String[] args) {
		Queue<String> queue = new LinkedList<String>();
		
		//添加元素
		queue.offer("a");
		queue.offer("b");
		queue.offer("c");
		queue.offer("d");
		queue.offer("e");
		for (String q : queue) {
			System.out.print(q);
		}
		System.out.println("\n");
		
		//返回第一个元素，并在队列中删除，不存在返回null
		System.out.println("poll=" + queue.poll());
		for (String q : queue) {
			System.out.print(q);
		}
		System.out.println("\n");
		
		//返回第一个元素，不存在抛异常
		System.out.println("element=" + queue.element());
		for (String q : queue) {
			System.out.print(q);
		}
		System.out.println("\n");
		
		//返回第一个元素，不存在返回null
		System.out.println("peek=" + queue.peek());
		for (String q : queue) {
			System.out.print(q);
		}
		System.out.println("\n");
		
		//返回第一个元素，并在队列中删除，不存在抛异常
		Deque<String> deque = (Deque<String>) queue;
		System.out.println("pop=" + deque.pop());
		for (String q : deque) {
			System.out.print(q);
		}
		System.out.println("\n");
		
		//从前面插入
		deque.push("f");
		for (String q : deque) {
			System.out.print(q);
		}
	}
}