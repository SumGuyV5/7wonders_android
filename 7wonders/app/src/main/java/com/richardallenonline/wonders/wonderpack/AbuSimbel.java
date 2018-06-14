package com.richardallenonline.wonders.wonderpack;

import com.richardallenonline.wonders.base.Wonder;

public class AbuSimbel extends Wonder {
	public AbuSimbel() {
		setA();
	}

	public AbuSimbel(String value) {
		setType(value);
	}
	
	public void setType(String value) {
		if (value.equalsIgnoreCase("A"))
			setA();
		if (value.equalsIgnoreCase("B"))
			setB();
	}
	
	private void setA() {
		setType(Type.AbuSimbelA);
		setStages(3, 5, 0, 0);
	}
	
	private void setB() {
		setType(Type.AbuSimbelB);
		setStages(0, 0, 0, 0);
	}
}
