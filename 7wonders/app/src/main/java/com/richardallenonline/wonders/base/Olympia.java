package com.richardallenonline.wonders.base;

public class Olympia extends Wonder {
	public Olympia() {
		setA();
	}

	public Olympia(String value) {
		setType(value);
	}

	public void setType(String value) {
		if (value.equalsIgnoreCase("A"))
			setA();
		if (value.equalsIgnoreCase("B"))
			setB();
	}
	
	private void setA() {
		setType(Type.OlympiaA);
		setStages(3, 0, 7, 0);
	}
	
	private void setB() {
		setType(Type.OlympiaB);
		setStages(0, 5, 0, 0);
	}
}
