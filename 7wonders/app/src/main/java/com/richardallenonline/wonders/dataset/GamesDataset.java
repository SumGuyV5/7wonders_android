package com.richardallenonline.wonders.dataset;

import android.content.ContentValues;
import android.database.Cursor;
import android.support.annotation.NonNull;

import com.richardallenonline.wonders.EqualsBy;
import com.richardallenonline.wonders.database.SQLDatabase.GAMES;

public class GamesDataset extends Dataset {
	private long id;
	
	private long playerKey1;
	private long playerKey2;
	private long playerKey3;
	private long playerKey4;
	private long playerKey5;
	private long playerKey6;
	private long playerKey7;
	private long playerKey8;
	
	private long datetime;
	
	public GamesDataset() {
		Clean();
	}	
	
	public GamesDataset(long id, long playerKey1, long playerKey2,
			long playerKey3, long playerKey4, long playerKey5, long playerKey6,
			long playerKey7, long playerKey8, long datetime) {
		this.id = id;
		this.playerKey1 = playerKey1;
		this.playerKey2 = playerKey2;
		this.playerKey3 = playerKey3;
		this.playerKey4 = playerKey4;
		this.playerKey5 = playerKey5;
		this.playerKey6 = playerKey6;
		this.playerKey7 = playerKey7;
		this.playerKey8 = playerKey8;
		this.datetime = datetime;
	}


	public GamesDataset(GamesDataset copy) {
		this(copy.getId(), copy.getPlayerKey1(), copy.getPlayerKey2(), copy.getPlayerKey3(),
				copy.getPlayerKey4(), copy.getPlayerKey5(), copy.getPlayerKey6(),
				copy.getPlayerKey7(), copy.getPlayerKey8(), copy.getDatetime());
	}

	public void Clean() {
		id = -1;
		playerKey1 = -1;
		playerKey2 = -1;
		playerKey3 = -1;
		playerKey4 = -1;
		playerKey5 = -1;
		playerKey6 = -1;
		playerKey7 = -1;
		playerKey8 = -1;
		datetime = -1;
	}

	@Override
	public int compareTo(@NonNull Object another) {
		return (int)(this.id - ((GamesDataset)another).getId());
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		final GamesDataset other = (GamesDataset)obj;

		boolean rtn;
		switch (EqualsBy.getId(super.getEqualsBy().getValue() | other.getEqualsBy().getValue())) {
			default:
			case ID:
				rtn = (id == other.getId());
				break;
			case EveryThing:
				rtn = ((playerKey1 == other.getPlayerKey1()) &&
						(playerKey2 == other.getPlayerKey2()) &&
						(playerKey3 == other.getPlayerKey3()) &&
						(playerKey4 == other.getPlayerKey4()) &&
						(playerKey5 == other.getPlayerKey5()) &&
						(playerKey6 == other.getPlayerKey6()) &&
						(playerKey7 == other.getPlayerKey7()) &&
						(playerKey8 == other.getPlayerKey8()) &&
						(datetime == other.getDatetime()));
				break;
		}
		return rtn;
	}

	@Override
	public void SetData(Cursor cursor) {
		setId(cursor.getLong(0));
		setPlayerKey1(cursor.getLong(1));
		setPlayerKey2(cursor.getLong(2));
		setPlayerKey3(cursor.getLong(3));
		setPlayerKey4(cursor.getLong(4));
		setPlayerKey5(cursor.getLong(5));
		setPlayerKey6(cursor.getLong(6));
		setPlayerKey7(cursor.getLong(7));
		setPlayerKey8(cursor.getLong(8));
		setDatetime(cursor.getLong(9));
	}

	@Override
	public ContentValues GetData() {
		ContentValues rtn = new ContentValues();
		//rtn.put(GAMES.COLUMN_ID, getId());
		rtn.put(GAMES.COLUMN_PLAYER1, getPlayerKey1());
		rtn.put(GAMES.COLUMN_PLAYER2, getPlayerKey2());
		rtn.put(GAMES.COLUMN_PLAYER3, getPlayerKey3());
		rtn.put(GAMES.COLUMN_PLAYER4, getPlayerKey4());
		rtn.put(GAMES.COLUMN_PLAYER5, getPlayerKey5());
		rtn.put(GAMES.COLUMN_PLAYER6, getPlayerKey6());
		rtn.put(GAMES.COLUMN_PLAYER7, getPlayerKey7());
		rtn.put(GAMES.COLUMN_PLAYER8, getPlayerKey8());
		rtn.put(GAMES.COLUMN_DATETIME, getDatetime());
		return rtn;
	}	

	@Override
	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public long getPlayerKey1() {
		return playerKey1;
	}

	public void setPlayerKey1(long playerKey1) {
		this.playerKey1 = playerKey1;
	}

	public long getPlayerKey2() {
		return playerKey2;
	}

	public void setPlayerKey2(long playerKey2) {
		this.playerKey2 = playerKey2;
	}

	public long getPlayerKey3() {
		return playerKey3;
	}

	public void setPlayerKey3(long playerKey3) {
		this.playerKey3 = playerKey3;
	}

	public long getPlayerKey4() {
		return playerKey4;
	}

	public void setPlayerKey4(long playerKey4) {
		this.playerKey4 = playerKey4;
	}

	public long getPlayerKey5() {
		return playerKey5;
	}

	public void setPlayerKey5(long playerKey5) {
		this.playerKey5 = playerKey5;
	}

	public long getPlayerKey6() {
		return playerKey6;
	}

	public void setPlayerKey6(long playerKey6) {
		this.playerKey6 = playerKey6;
	}

	public long getPlayerKey7() {
		return playerKey7;
	}

	public void setPlayerKey7(long playerKey7) {
		this.playerKey7 = playerKey7;
	}

	public long getPlayerKey8() {
		return playerKey8;
	}

	public void setPlayerKey8(long playerKey8) {
		this.playerKey8 = playerKey8;
	}

	public long getDatetime() {
		return datetime;
	}

	public void setDatetime(long datetime) {
		this.datetime = datetime;
	}
}
