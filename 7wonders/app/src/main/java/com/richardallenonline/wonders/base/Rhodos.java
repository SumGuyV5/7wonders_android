package com.richardallenonline.wonders.base;

public class Rhodos extends Wonder {
	public Rhodos() {
		setA();
	}

	public Rhodos(String value) {
		setType(value);
	}

	private void setType(String value) {
		if (value.equalsIgnoreCase("A"))
			setA();
		if (value.equalsIgnoreCase("B"))
			setB();
	}
	
	private void setA() {
		setType(Type.RhodosA);
		setStages(3, 0, 7, 0);
	}
	
	private void setB() {
		setType(Type.RhodosB);
		setStages(3, 4, 0, 0);
	}
}
