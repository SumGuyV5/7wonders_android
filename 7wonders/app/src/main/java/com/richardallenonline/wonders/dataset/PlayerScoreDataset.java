package com.richardallenonline.wonders.dataset;

public class PlayerScoreDataset extends PlaysDataset {
	
	public PlayerScoreDataset() {
		super();
	}
	
	public PlayerScoreDataset(PlaysDataset value) {
		super(value);
	}
	
	public PlayerScoreDataset(PlayersDataset value) {
		super(value);
	}
	
	private void Calculate() {
		int finalScore = getMilitaryPoints();
		finalScore += getMoneyPoints();
		finalScore += getWonderPoints();
		finalScore += getCivilianPoints();
		finalScore += getCommercialPoints();
		finalScore += getSciencePoints();
		finalScore += getGuildPoints();
		finalScore += getLeaderPoints();
		finalScore += getCityPoints();
		finalScore -= getDebtPoints();
		this.setFinalScore(finalScore);
	}
	
	private void CalculateMil() {
		int militaryPoints = (getVitoryTokenAge1());
		militaryPoints += (getVitoryTokenAge2() * 3);
		militaryPoints += (getVitoryTokenAge3() * 5);
		militaryPoints += (getDefeatToken() * -1);
		setMilitaryPoints(militaryPoints);
	}
	
	@Override
	public int getFinalScore() {
		CalculateMil();
		Calculate();
		return super.getFinalScore();
	}

	public void setMilitaryPoints(String value) {
		super.setMilitaryPoints(Integer.parseInt(value));
	}
	
	public void setMoneyPoints(String value) {
		super.setMoneyPoints(Integer.parseInt(value));
	}
	
	public void setWonderPoints(String value) {
		super.setWonderPoints(Integer.parseInt(value));
	}
	
	public void setCivilianPoints(String value) {
		super.setCivilianPoints(Integer.parseInt(value));
	}
	
	public void setCommercialPoints(String value) {
		super.setCommercialPoints(Integer.parseInt(value));
	}
	
	public void setGuildPoints(String value) {
		super.setGuildPoints(Integer.parseInt(value));
	}
	
	public void setSciencePoints(String value) {
		super.setSciencePoints(Integer.parseInt(value));
	}
	
	public void setLeaderPoints(String value) {
		super.setLeaderPoints(Integer.parseInt(value));
	}
	
	public void setCityPoints(String value) {
		super.setCityPoints(Integer.parseInt(value));
	}
	
	public void setDebtPoints(String value) {
		super.setDebtPoints(Integer.parseInt(value));
	}
	
	public void addDefeatToken() {
		setDefeatToken(getDefeatToken() + 1);
	}
	
	public void addVitoryToken(int age){
		switch(age){
		case 1:
			addVitoryTokenAge1();
			break;
		case 2:
			addVitoryTokenAge2();
			break;
		case 3:
			addVitoryTokenAge3();
			break;
		}
	}
	
	public void addVitoryTokenAge1() {
		setVitoryTokenAge1(getVitoryTokenAge1() + 1);
	}
	
	public void addVitoryTokenAge2() {
		setVitoryTokenAge2(getVitoryTokenAge2() + 1);
	}

	public void addVitoryTokenAge3() {
		setVitoryTokenAge3(getVitoryTokenAge3() + 1);
	}
}
