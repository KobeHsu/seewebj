package org.iii.see.form;

import java.io.Serializable;

public class BaseFormBean implements Serializable {

	/** Serial Version UID */
	private static final long serialVersionUID = 9185073886024331412L;
	
	private String functionName;
	
	// For datatabless
	private int draw;
	private int start;
	private int length;

	public String getFunctionName() {
		return functionName;
	}

	public void setFunctionName(String functionName) {
		this.functionName = functionName;
	}

	public int getDraw() {
		return draw;
	}

	public void setDraw(int draw) {
		this.draw = draw;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getLength() {
		return length;
	}

	public void setLength(int length) {
		this.length = length;
	}
}
