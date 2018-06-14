package com.richardallenonline.wonders.cities;

import com.richardallenonline.wonders.base.Wonder;

public class Byzantium extends Wonder {
	public Byzantium() {
		setA();
	}

	public Byzantium(String value) {
		setType(value);
	}
	
	public void setType(String value) {
		if (value.equalsIgnoreCase("A"))
			setA();
		if (value.equalsIgnoreCase("B"))
			setB();
	}
	
	private void setA() {
		setType(Type.ByzantiumA);
		setStages(3, 2, 7, 0);
		setStages2(0, StageInfo.Diplomacy, 0, 0);
		
	}
	
	private void setB() {
		setType(Type.ByzantiumB);
		setStages(3, 4, 0, 0);
		setStages2(StageInfo.Diplomacy, StageInfo.Diplomacy, 0, 0);
	}
}
