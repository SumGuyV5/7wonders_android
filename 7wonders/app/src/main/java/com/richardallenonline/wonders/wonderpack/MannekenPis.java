package com.richardallenonline.wonders.wonderpack;

import com.richardallenonline.wonders.base.Wonder;

public class MannekenPis extends Wonder {
	public MannekenPis() {
		setA();
	}

	public MannekenPis(String value) {
		setType(value);
	}
	
	public void setType(String value) {
		if (value.equalsIgnoreCase("A"))
			setA();
		if (value.equalsIgnoreCase("B"))
			setB();
	}
	
	private void setA() {
		setType(Type.MannekenPisA);
		setStages(0, 0, 0, 0);
		setStages2(StageInfo.StageCopyLeft, StageInfo.StageCopyRight, StageInfo.StageCopyLeft, 0);
	}
	
	private void setB() {
		setType(Type.MannekenPisB);
		setStages(7, 0, 0, 0);
	}
}
