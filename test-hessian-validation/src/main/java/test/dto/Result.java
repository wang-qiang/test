package test.dto;

import java.io.Serializable;

public class Result implements Serializable {
	private static final long serialVersionUID = 1L;
	
	private int state;
	private Object data;
	
	public Result(int state, Object data) {
		this.state = state;
		this.data = data;
	}
	
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public Object getData() {
		return data;
	}
	public void setData(Object data) {
		this.data = data;
	}
}