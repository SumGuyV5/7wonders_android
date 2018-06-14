package com.richardallenonline.wonders.base;

public class Alexandria extends Wonder {
	public Alexandria() {
		setA();
	}

	public Alexandria(String value) {
		setType(value);
	}
	
	public void setType(String value) {
		if (value.equalsIgnoreCase("A"))
			setA();
		if (value.equalsIgnoreCase("B"))
			setB();
	}
	
	private void setA() {
		setType(Type.AlexandriaA);
		setStages(3, 0, 7, 0);
	}
	
	private void setB() {
		setType(Type.AlexandriaB);
		setStages(0, 0, 7, 0);
	}
}
