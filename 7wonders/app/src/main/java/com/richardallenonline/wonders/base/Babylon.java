package com.richardallenonline.wonders.base;

public class Babylon extends Wonder {
	public Babylon() { setA(); }

	public Babylon(String value) {
		setType(value);
	}
	
	public void setType(String value) {
		if (value.equalsIgnoreCase("A"))
			setA();
		if (value.equalsIgnoreCase("B"))
			setB();
	}
	
	private void setA() {
		setType(Type.BabylonA);
		setStages(3, 0, 7, 0);
		setStages2(0, StageInfo.ScienceWild, 0, 0);
	}
	
	private void setB() {
		setType(Type.BabylonB);
		setStages(3, 0, 0, 0);
		setStages2(0, 0, StageInfo.ScienceWild, 0);
	}
}
