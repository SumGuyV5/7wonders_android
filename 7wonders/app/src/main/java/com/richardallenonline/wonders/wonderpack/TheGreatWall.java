package com.richardallenonline.wonders.wonderpack;

import com.richardallenonline.wonders.base.Wonder;

public class TheGreatWall extends Wonder {
	public TheGreatWall() {
		setA();
	}

	public TheGreatWall(String value) {
		setType(value);
	}
	
	public void setType(String value) {
		if (value.equalsIgnoreCase("A"))
			setA();
		if (value.equalsIgnoreCase("B"))
			setB();
	}
	
	private void setA() {
		setType(Type.TheGreatWallA);
		setStages(0, 0, 0, 0);
		setStages2(0, StageInfo.ScienceWild, 0, 0);
	}
	
	private void setB() {
		setType(Type.TheGreatWallB);
		setStages(0, 0, 0, 0);
		setStages2(0, StageInfo.Mask, StageInfo.Diplomacy, 0);
	}
}