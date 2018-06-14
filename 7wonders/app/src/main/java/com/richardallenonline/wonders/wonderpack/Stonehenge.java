package com.richardallenonline.wonders.wonderpack;

import com.richardallenonline.wonders.base.Wonder;

public class Stonehenge extends Wonder {
	public Stonehenge() {
		setA();
	}

	public Stonehenge(String value) {
		setType(value);
	}
	
	public void setType(String value) {
		if (value.equalsIgnoreCase("A"))
			setA();
		if (value.equalsIgnoreCase("B"))
			setB();
	}
	
	private void setA() {
		setType(Type.StonehengeA);
		setStages(3, 5, 0, 0);
	}
	
	private void setB() {
		setType(Type.StonehengeB);
		setStages(0, 0, 0, 0);
	}
}
