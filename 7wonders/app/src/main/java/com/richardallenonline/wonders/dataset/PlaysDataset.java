package com.richardallenonline.wonders.dataset;

import android.content.ContentValues;
import android.database.Cursor;
import androidx.annotation.NonNull;

import com.richardallenonline.wonders.EqualsBy;
import com.richardallenonline.wonders.base.Wonder;
import com.richardallenonline.wonders.database.SQLDatabase.PLAYS;

public class PlaysDataset extends Dataset {
	private long id;
	private long playerKey;
	private long gameKey;
	
	private int militaryPoints = 0;
	private int moneyPoints = 0;
	private int wonderPoints = 0;
	private int civilianPoints = 0;
	private int commercialPoints = 0;
	private int sciencePoints = 0;
	private int guildPoints = 0;
	private int leaderPoints = 0;
	private int cityPoints = 0;
	private int debtPoints = 0;
	private int finalScore = 0;
	
	private int tabletCount = 0;
	private int gearCount = 0;
	private int compassCount = 0;
	private int wildCount = 0;
	
	private int defeatToken = 0;
	private int vitoryTokenAge1 = 0;
	private int vitoryTokenAge2 = 0;
	private int vitoryTokenAge3 = 0;
	
	private Wonder wonder = null;
	
	public PlaysDataset() {
		Clean();
	}
		
	public PlaysDataset(long id, long playerKey, long gameKey,
			int militaryPoints, int moneyPoints, int wonderPoints,
			int civilianPoints, int commercialPoints, int sciencePoints,
			int guildPoints, int leaderPoints, int cityPoints, int debtPoints,
			int finalScore, int tabletCount, int gearCount, int compassCount,
			int wildCount, int defeatToken, int vitoryTokenAge1,
			int vitoryTokenAge2, int vitoryTokenAge3, Wonder wonder) {
		this.id = id;
		this.playerKey = playerKey;
		this.gameKey = gameKey;
		this.militaryPoints = militaryPoints;
		this.moneyPoints = moneyPoints;
		this.wonderPoints = wonderPoints;
		this.civilianPoints = civilianPoints;
		this.commercialPoints = commercialPoints;
		this.sciencePoints = sciencePoints;
		this.guildPoints = guildPoints;
		this.leaderPoints = leaderPoints;
		this.cityPoints = cityPoints;
		this.debtPoints = debtPoints;
		this.finalScore = finalScore;
		this.tabletCount = tabletCount;
		this.gearCount = gearCount;
		this.compassCount = compassCount;
		this.wildCount = wildCount;
		this.defeatToken = defeatToken;
		this.vitoryTokenAge1 = vitoryTokenAge1;
		this.vitoryTokenAge2 = vitoryTokenAge2;
		this.vitoryTokenAge3 = vitoryTokenAge3;
		this.wonder = wonder;
	}

	public PlaysDataset(PlaysDataset copy) {
		this(copy.getId(), copy.getPlayerKey(), copy.getGameKey(), copy.getMilitaryPoints(),
				copy.getMoneyPoints(), copy.getWonderPoints(), copy.getCivilianPoints(),
				copy.getCommercialPoints(), copy.getSciencePoints(), copy.getGuildPoints(),
				copy.getLeaderPoints(), copy.getCityPoints(), copy.getDebtPoints(),
				copy.getFinalScore(), copy.getTabletCount(), copy.getGearCount(),
				copy.getCompassCount(), copy.getWildCount(), copy.getDefeatToken(),
				copy.getVitoryTokenAge1(), copy.getVitoryTokenAge2(), copy.getVitoryTokenAge3(),
				copy.getWonder());
	}
	
	public PlaysDataset(PlayersDataset value) {
		this((long)-1, value.getId(), (long)-1, 0, 0, 0,
				0, 0, 0, 0, 0,
				0, 0, 0, 0, 0, 0,
				0, 0, 0, 0, 0,
				null);
	}

	public void Clean() {
		id = -1;
		playerKey = -1;
		gameKey = -1;
		militaryPoints = 0;
		moneyPoints = 0;
		wonderPoints = 0;
		civilianPoints = 0;
		commercialPoints = 0;
		sciencePoints = 0;
		guildPoints = 0;
		leaderPoints = 0;
		cityPoints = 0;
		debtPoints = 0;
		finalScore = 0;
		tabletCount = 0;
		gearCount = 0;
		compassCount = 0;
		wildCount = 0;
		defeatToken = 0;
		vitoryTokenAge1 = 0;
		vitoryTokenAge2 = 0;
		vitoryTokenAge3 = 0;
		wonder = null;
	}

	@Override
	public int compareTo(@NonNull Object another) {
		return (int)(this.id - ((PlaysDataset)another).getId());
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final PlaysDataset other = (PlaysDataset)obj;

		boolean rtn = false;
		switch (EqualsBy.getId(super.getEqualsBy().getValue() | other.getEqualsBy().getValue())) {
			case ID:
			case EveryThing:
				rtn = (playerKey == other.getPlayerKey());
				break;
		}
		return rtn;
	}
	
	@Override
	public void SetData(Cursor cursor) {
		setId(cursor.getLong(0));
		setPlayerKey(cursor.getLong(1));
		setGameKey(cursor.getLong(2));
		setMilitaryPoints(cursor.getInt(3));
		setMoneyPoints(cursor.getInt(4));
		setWonderPoints(cursor.getInt(5));
		setCivilianPoints(cursor.getInt(6));
		setCommercialPoints(cursor.getInt(7));
		setSciencePoints(cursor.getInt(8));
		setGuildPoints(cursor.getInt(9));
		setLeaderPoints(cursor.getInt(10));
		setCityPoints(cursor.getInt(11));
		setDebtPoints(cursor.getInt(12));
		setFinalScore(cursor.getInt(13));
		setTabletCount(cursor.getInt(14));
		setGearCount(cursor.getInt(15));
		setCompassCount(cursor.getInt(16));
		setWildCount(cursor.getInt(17));
		setDefeatToken(cursor.getInt(18));
		setVitoryTokenAge1(cursor.getInt(19));
		setVitoryTokenAge2(cursor.getInt(20));
		setVitoryTokenAge3(cursor.getInt(21));		
	}

	@Override
	public ContentValues GetData() {
		ContentValues rtn = new ContentValues();
		rtn.put(PLAYS.COLUMN_MILITARY, getMilitaryPoints());
		rtn.put(PLAYS.COLUMN_MONEY, getMoneyPoints());
		rtn.put(PLAYS.COLUMN_WONDER, getWonderPoints());
		rtn.put(PLAYS.COLUMN_CIVILIAN, getCivilianPoints());
		rtn.put(PLAYS.COLUMN_COMMERCIAL, getCommercialPoints());
		rtn.put(PLAYS.COLUMN_SCIENCE, getSciencePoints());
		rtn.put(PLAYS.COLUMN_GUILD, getGuildPoints());
		rtn.put(PLAYS.COLUMN_LEADER, getLeaderPoints());
		rtn.put(PLAYS.COLUMN_CITY, getCityPoints());
		rtn.put(PLAYS.COLUMN_DEBT, getDebtPoints());
		rtn.put(PLAYS.COLUMN_FINAL_SCORE, getFinalScore());
		rtn.put(PLAYS.COLUMN_TABLET, getTabletCount());
		rtn.put(PLAYS.COLUMN_GEAR, getGearCount());
		rtn.put(PLAYS.COLUMN_COMPASS, getCompassCount());
		rtn.put(PLAYS.COLUMN_WILD, getWildCount());
		rtn.put(PLAYS.COLUMN_DEFEAT, getDefeatToken());
		rtn.put(PLAYS.COLUMN_VITORY_1, getVitoryTokenAge1());
		rtn.put(PLAYS.COLUMN_VITORY_3, getVitoryTokenAge2());
		rtn.put(PLAYS.COLUMN_VITORY_5, getVitoryTokenAge3());
		rtn.put(PLAYS.COLUMN_WONDER_TYPE, getWonder().getType().getValue());
		rtn.put(PLAYS.COLUMN_PLAYER_KEY, getPlayerKey());
		rtn.put(PLAYS.COLUMN_GAME_KEY, getGameKey());		
		return rtn;
	}	

	@Override
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPlayerKey() {
		return playerKey;
	}

	public void setPlayerKey(long playerkey) {
		this.playerKey = playerkey;
	}

	public long getGameKey() {
		return gameKey;
	}

	public void setGameKey(long gameNum) {
		this.gameKey = gameNum;
	}

	public int getMilitaryPoints() {
		return militaryPoints;
	}

	public void setMilitaryPoints(int militaryPoints) {
		this.militaryPoints = militaryPoints;
	}

	public int getMoneyPoints() {
		return moneyPoints;
	}

	public void setMoneyPoints(int moneyPoints) {
		this.moneyPoints = moneyPoints;
	}

	public int getWonderPoints() {
		return wonderPoints;
	}

	public void setWonderPoints(int wonderPoints) {
		this.wonderPoints = wonderPoints;
	}

	public int getCivilianPoints() {
		return civilianPoints;
	}

	public void setCivilianPoints(int civilianPoints) {
		this.civilianPoints = civilianPoints;
	}

	public int getCommercialPoints() {
		return commercialPoints;
	}

	public void setCommercialPoints(int commercialPoints) {
		this.commercialPoints = commercialPoints;
	}

	public int getSciencePoints() {
		return sciencePoints;
	}

	public void setSciencePoints(int sciencePoints) {
		this.sciencePoints = sciencePoints;
	}

	public int getGuildPoints() {
		return guildPoints;
	}

	public void setGuildPoints(int guildPoints) {
		this.guildPoints = guildPoints;
	}

	public int getLeaderPoints() {
		return leaderPoints;
	}

	public void setLeaderPoints(int leaderPoints) {
		this.leaderPoints = leaderPoints;
	}

	public int getCityPoints() {
		return cityPoints;
	}

	public void setCityPoints(int cityPoints) {
		this.cityPoints = cityPoints;
	}

	public int getDebtPoints() {
		return debtPoints;
	}

	public void setDebtPoints(int debtPoints) {
		this.debtPoints = debtPoints;
	}

	public int getFinalScore() {
		return finalScore;
	}

	public void setFinalScore(int finalScore) {
		this.finalScore = finalScore;
	}

	public int getTabletCount() {
		return tabletCount;
	}

	public void setTabletCount(int tabletCount) {
		this.tabletCount = tabletCount;
	}

	public int getGearCount() {
		return gearCount;
	}

	public void setGearCount(int gearCount) {
		this.gearCount = gearCount;
	}

	public int getCompassCount() {
		return compassCount;
	}

	public void setCompassCount(int compassCount) {
		this.compassCount = compassCount;
	}

	public int getWildCount() {
		return wildCount;
	}

	public void setWildCount(int wildCount) {
		this.wildCount = wildCount;
	}

	public int getDefeatToken() {
		return defeatToken;
	}

	public void setDefeatToken(int defeatToken) {
		this.defeatToken = defeatToken;
	}

	public int getVitoryTokenAge1() {
		return vitoryTokenAge1;
	}

	public void setVitoryTokenAge1(int vitoryTokenAge1) {
		this.vitoryTokenAge1 = vitoryTokenAge1;
	}

	public int getVitoryTokenAge2() {
		return vitoryTokenAge2;
	}

	public void setVitoryTokenAge2(int vitoryTokenAge2) {
		this.vitoryTokenAge2 = vitoryTokenAge2;
	}

	public int getVitoryTokenAge3() {
		return vitoryTokenAge3;
	}

	public void setVitoryTokenAge3(int vitoryTokenAge3) {
		this.vitoryTokenAge3 = vitoryTokenAge3;
	}

	public Wonder getWonder() {
		return wonder;
	}

	public void setWonder(Wonder wonder) {
		this.wonder = wonder;
	}
}
