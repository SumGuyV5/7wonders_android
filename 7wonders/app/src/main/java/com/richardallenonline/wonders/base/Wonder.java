package com.richardallenonline.wonders.base;

public class Wonder {
	private int stage1 = 0;
	private int stage2 = 0;
	private int stage3 = 0;
	private int stage4 = 0;
		
	private int stagePart1 = 0;
	private int stagePart2 = 0;
	private int stagePart3 = 0;
	private int stagePart4 = 0;
	
	private int stagesBuilt = 0;
	
	private Type type = Type.RhodosA;

	public int getPoints() {
		return getPoints(stagesBuilt);
	}

	public int getStagesBuilt() {
		return stagesBuilt;
	}

	public void setStagesBuilt(int stagesBuilt) {
		this.stagesBuilt = stagesBuilt;
	}

	public Type getType() {
		return type;
	}

	public void setType(Type value) {
		type = value;
	}
	
	protected void setStages( int one, int two, int three, int four ) {
		stage1 = one;
		stage2 = two;
		stage3 = three;
		stage4 = four;
	}
	
	protected void setStages2( int one, int two, int three, int four ) {
		stagePart1 = one;
		stagePart2 = two;
		stagePart3 = three;
		stagePart4 = four;
	}

	protected int getPoints(int stages) {
		int rtn = 0;
		switch(stages) {
		case 4:
			rtn += stage4;
		case 3:
			rtn += stage3;
		case 2:
			rtn += stage2;
		case 1:
			rtn += stage1;				
		}
		return rtn;
	}
	
	protected void getStages2( int one, int two, int three, int four ) {
		one = stagePart1;
		two = stagePart2;
		three = stagePart3;
		four = stagePart4;
	}

	public static final class Side {
		public static final int A = 0;
		public static final int B = 1;
	}
	
	public static final class StageInfo {
		public static final int ScienceWild = -1;
		public static final int Mask = -2;
		public static final int StageCopyLeft = -3;
		public static final int StageCopyRight = -4;
		public static final int Diplomacy = -5;
	}
	
	public enum Type {
		//7 Wonders
		RhodosA(0),	RhodosB(1),
		
		AlexandriaA(2), AlexandriaB(3),
		
		EphesosA(4), EphesosB(5),
		
		BabylonA(6), BabylonB(7),
		
		OlympiaA(8), OlympiaB(9),
		
		HalikarnassosA(10),	HalikarnassosB(11),
		
		GizahA(12),	GizahB(13),
		
		//7 Wonders Leaders
		RomaA(14), RomaB(15),
		
		//7 Wonders Cities
		ByzantiumA(16),	ByzantiumB(17),
		
		PetraA(18),	PetraB(19),
		
		//7 Wonders Wonder Pack
		TheGreatWallA(20), TheGreatWallB(21),
		
		AbuSimbelA(22),	AbuSimbelB(23),
		
		StonehengeA(24), StonehengeB(25),
		
		MannekenPisA(26), MannekenPisB(27);
		
		private final int value;
	    private Type(int value) {
	        this.value = value;
	    }

	    public int getValue() {
	        return value;
	    }
	}
}
