package com.richardallenonline.wonders;

import java.lang.Math;

public class ScienceCalculator {
	private int tablets = 0;
	private int gears = 0;
	private int compass = 0;
	private int wilds = 0;
	
	private int score = 0;
	
	private int colScore = 0;
	private int rowScore = 0;

	public ScienceCalculator() {
	}
	
	public ScienceCalculator(int tablet, int gear, int compass, int wild) {
		Set(tablet, gear, compass, wild);
	}

	public ScienceCalculator(ScienceCalculator copy) {
		Copy(copy);
		//Calculate();
	}

	public void Copy(ScienceCalculator copy){
		setTablet(copy.getTablet());
		setGear(copy.getGear());
		setCompass(copy.getCompass());
		setWild(copy.getWild());
		Calculate();
	}

	public void Set(int tablet, int gear, int compass, int wild) {
		setTablet(tablet);
		setGear(gear);
		setCompass(compass);
		setWild(wild);
		Calculate();
	}
		
	public int getTablet() {
		return tablets;
	}
	
	public void setTablet(int value) {
		if (value < 0)
			value = 0;
		tablets = value;
		//Calculate();
	}
	
	public int getGear() {
		return gears;
	}
	
	public void setGear(int value) {
		if (value < 0)
			value = 0;
		gears = value;
		//Calculate();
	}
	
	public int getCompass() {
		return compass;
	}
	
	public void setCompass(int value) {
		if (value < 0)
			value = 0;
		compass = value;
		//Calculate();
	}
	
	public int getWild() {
		return wilds;
	}
	
	public void setWild(int value) {
		if (value < 0)
			value = 0;
		wilds = value;
		//Calculate();
	}
	
	public int getScore() {
		return score;
	}
	
	private void Calculate() {
		if (wilds > 0)
			WildCalculate();
		
		Columns();
		Rows();
		score = rowScore + colScore;
	}
	
	private void WildCalculate() {
		wilds--;
		ScienceCalculator wildTablet = new ScienceCalculator(tablets + 1, gears, compass, wilds);
		ScienceCalculator wildGear = new ScienceCalculator(tablets, gears + 1, compass, wilds);
		ScienceCalculator wildCompass = new ScienceCalculator(tablets, gears, compass + 1, wilds);
		
		int tabletScore = wildTablet.getScore();
		int gearScore = wildGear.getScore();
		int compassScore = wildCompass.getScore();
		
		if (( tabletScore >= compassScore )  && ( tabletScore >= gearScore ))
			Copy(wildTablet);
		if ((gearScore >= tabletScore) && ( gearScore >= compassScore ))
			Copy(wildGear);
		if (( compassScore >= gearScore ) && ( compassScore >= tabletScore ))
			Copy(wildCompass);
	}

	private void Columns() {
		int score = (int) Math.pow(tablets, 2);
		score += Math.pow(gears, 2);
		score += Math.pow(compass, 2);
		colScore = score;
	}
	
	private void Rows() {
		int score = 0;
		if (( tablets <= compass )  && ( tablets <= gears ))
			score = tablets * 7;
		if ((gears <= tablets) && ( gears <= compass ))
			score = gears * 7;
		if (( compass <= gears ) && ( compass <= tablets ))
			score = compass * 7;
		
		rowScore = score;
	}
}
