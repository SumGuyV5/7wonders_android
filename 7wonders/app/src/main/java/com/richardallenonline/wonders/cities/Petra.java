package com.richardallenonline.wonders.cities;

import com.richardallenonline.wonders.base.Wonder;

public class Petra extends Wonder {
	public Petra() {
		setA();
	}

	public Petra(String value) {
		setType(value);
	}
	
	public void setType(String value) {
		if (value.equalsIgnoreCase("A"))
			setA();
		if (value.equalsIgnoreCase("B"))
			setB();
	}
	
	private void setA() {
		setType(Type.PetraA);
		setStages(3, 7, 7, 0);
	}
	
	private void setB() {
		setType(Type.PetraB);
		super.setStages(3, 14, 0, 0);
	}
}
