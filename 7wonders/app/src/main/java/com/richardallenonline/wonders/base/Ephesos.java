package com.richardallenonline.wonders.base;

public class Ephesos extends Wonder {
	public Ephesos() {
		setA();
	}

	public Ephesos(String value) {
		setType(value);
	}
	
	public void setType(String value) {
		if (value.equalsIgnoreCase("A"))
			setA();
		if (value.equalsIgnoreCase("B"))
			setB();
	}
	
	private void setA() {
		setType(Type.EphesosA);
		setStages(3, 0, 7, 0);
	}
	
	private void setB() {
		setType(Type.EphesosB);
		setStages(2, 3, 5, 0);
	}
}
