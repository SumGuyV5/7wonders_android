package com.richardallenonline.wonders.base;

public class Gizah extends Wonder {
	public Gizah() {
		setA();
	}

	public Gizah(String value) {
		setType(value);
	}
	
	public void setType(String value) {
		if (value.equalsIgnoreCase("A"))
			setA();
		if (value.equalsIgnoreCase("B"))
			setB();
	}
	
	private void setA() {
		setType(Type.GizahA);
		setStages(3, 5, 7, 0);
	}
	
	private void setB() {
		setType(Type.GizahB);
		setStages(3, 5, 5, 7);
	}
}
