package test;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class DynamicProxy {

	interface X {
		void print();
	}
	
	static class Y implements X {
		public void print() {
			System.out.println("Y");
		}
	}
	
	static class Z implements InvocationHandler {
		Object y;
		
		Object bind (Object y) {
			this.y = y;
			return Proxy.newProxyInstance(y.getClass().getClassLoader(), y.getClass().getInterfaces(), this);
		}

		public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
			System.out.println("Z");
			return method.invoke(y, args);
		}
	}
	
	public static void main(String[] args) {
		X x = (X) new Z().bind(new Y());
		x.print();
	}
	
}
