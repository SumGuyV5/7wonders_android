package com.richardallenonline.wonders.base;

public class Halikarnassos extends Wonder {
	public Halikarnassos() {
		setA();
	}

	public Halikarnassos(String value) {
		setType(value);
	}
	
	public void setType(String value) {
		if (value.equalsIgnoreCase("A"))
			setA();
		if (value.equalsIgnoreCase("B"))
			setB();
	}
	
	private void setA() {
		setType(Type.HalikarnassosA);
		setStages(3, 0, 7, 0);
	}
	
	private void setB() {
		setType(Type.HalikarnassosB);
		setStages(2, 1, 0, 0);
	}
}
