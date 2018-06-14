package com.richardallenonline.wonders.leaders;

import com.richardallenonline.wonders.base.Wonder;

public class Roma extends Wonder {
	public Roma() {
		setA();
	}

	public Roma(String value) {
		setType(value);
	}
	
	public void setType(String value) {
		if (value.equalsIgnoreCase("A"))
			setA();
		if (value.equalsIgnoreCase("B"))
			setB();
	}
	
	private void setA() {
		setType(Type.RomaA);
		setStages(4, 6, 0, 0);
	}
	
	private void setB() {
		setType(Type.RomaB);
		setStages(0, 3, 3, 0);
	}
}
