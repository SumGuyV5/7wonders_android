package com.richardallenonline.wonders.database;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class SQLDatabase extends SQLiteOpenHelper {
	private static final String DATABASE_NAME = "sevenwonders.db";
	private static final int DATABASE_VERSION = 1;
	private static final String[] allTablesCreate = { PLAYER.TABLE_CREATE,
		PLAYS.TABLE_CREATE, GAMES.TABLE_CREATE
	};
	
	public static final class GAMES {
		public static final String TABLE_NAME = "games";
		public static final String COLUMN_ID = "_id";
		public static final String COLUMN_PLAYER1 = "player1";
		public static final String COLUMN_PLAYER2 = "player2";
		public static final String COLUMN_PLAYER3 = "player3";
		public static final String COLUMN_PLAYER4 = "player4";
		public static final String COLUMN_PLAYER5 = "player5";
		public static final String COLUMN_PLAYER6 = "player6";
		public static final String COLUMN_PLAYER7 = "player7";
		public static final String COLUMN_PLAYER8 = "player8";
		public static final String COLUMN_DATETIME = "datetime";
		
		public static final String[] allColumns = { COLUMN_ID, 
			COLUMN_PLAYER1,	COLUMN_PLAYER2, COLUMN_PLAYER3, COLUMN_PLAYER4,
			COLUMN_PLAYER5,	COLUMN_PLAYER6, COLUMN_PLAYER7, COLUMN_PLAYER8,
			COLUMN_DATETIME
		};
		
		// Database creation sql statement
		private static final String TABLE_CREATE = "create table " +
				TABLE_NAME + "(" + COLUMN_ID + " integer primary key autoincrement, " +
				COLUMN_PLAYER1 + " integer not null, " +
				COLUMN_PLAYER2 + " integer not null, " +
				COLUMN_PLAYER3 + " integer not null, " +
				COLUMN_PLAYER4 + " integer not null, " +
				COLUMN_PLAYER5 + " integer not null, " +
				COLUMN_PLAYER6 + " integer not null, " +
				COLUMN_PLAYER7 + " integer not null, " +
				COLUMN_PLAYER8 + " integer not null, " +
				COLUMN_DATETIME + " DATETIME DEFAULT CURRENT_TIMESTAMP " + ");";
	}
	
	public static final class PLAYER {
		public static final String TABLE_NAME = "player";
		public static final String COLUMN_ID = "_id";
		public static final String COLUMN_NAME = "name";
		public static final String COLUMN_HIDE = "hide";
		
		public static final String[] allColumns = { COLUMN_ID,
			COLUMN_NAME, COLUMN_HIDE
		};
		
		// Database creation sql statement
		private static final String TABLE_CREATE = "create table " + TABLE_NAME + "(" 
			+ COLUMN_ID + " integer primary key autoincrement, "
			+ COLUMN_NAME + " text not null, "
			+ COLUMN_HIDE + " integer not null " + ");";
	}
	
	public static final class PLAYS {
		 public static final String TABLE_NAME = "plays";
		 public static final String COLUMN_ID = "_id";
		 public static final String COLUMN_PLAYER_KEY = "playerKey";
		 public static final String COLUMN_GAME_KEY = "gameKey";
		 public static final String COLUMN_MILITARY = "military";
		 public static final String COLUMN_MONEY = "money";
		 public static final String COLUMN_WONDER = "wonder";
		 public static final String COLUMN_CIVILIAN = "civilian";
		 public static final String COLUMN_COMMERCIAL = "commercial";
		 public static final String COLUMN_SCIENCE = "science";
		 public static final String COLUMN_GUILD = "guild";
		 public static final String COLUMN_LEADER = "leader";
		 public static final String COLUMN_CITY = "city";
		 public static final String COLUMN_DEBT = "debt";
		 public static final String COLUMN_FINAL_SCORE = "finalscore";
		 public static final String COLUMN_TABLET = "tablet";
		 public static final String COLUMN_GEAR = "gear";
		 public static final String COLUMN_COMPASS = "compass";
		 public static final String COLUMN_WILD = "wild";
		 public static final String COLUMN_DEFEAT = "defeat";
		 public static final String COLUMN_VITORY_1 = "vitory1";
		 public static final String COLUMN_VITORY_3 = "vitory3";
		 public static final String COLUMN_VITORY_5 = "vitory5";
		 public static final String COLUMN_WONDER_TYPE = "wondertype";
		 
		 public static final String[] allColumns = { COLUMN_ID, COLUMN_PLAYER_KEY, 
			 COLUMN_GAME_KEY, COLUMN_MILITARY, COLUMN_MONEY, COLUMN_WONDER, 
			 COLUMN_CIVILIAN, COLUMN_COMMERCIAL, COLUMN_SCIENCE, COLUMN_GUILD,
			 COLUMN_LEADER, COLUMN_CITY, COLUMN_DEBT, COLUMN_FINAL_SCORE,
			 COLUMN_TABLET, COLUMN_GEAR, COLUMN_COMPASS, COLUMN_WILD,
			 COLUMN_DEFEAT, COLUMN_VITORY_1, COLUMN_VITORY_3, COLUMN_VITORY_5, COLUMN_WONDER_TYPE 
		 };
		 
		 // Database creation sql statement
		 private static final String TABLE_CREATE = "create table " +
			TABLE_NAME + "(" + 
			COLUMN_ID + " integer primary key autoincrement, " + 
			COLUMN_PLAYER_KEY + " integer not null, " +
			COLUMN_GAME_KEY + " integer not null, " +
			COLUMN_MILITARY + " integer not null, " +
			COLUMN_MONEY + " integer not null, " +
			COLUMN_WONDER + " integer not null, " +
			COLUMN_CIVILIAN + " integer not null, " +
			COLUMN_COMMERCIAL + " integer not null, " +
			COLUMN_SCIENCE + " integer not null, " + 
			COLUMN_GUILD + " integer not null, " +
			COLUMN_LEADER + " integer not null, " +
			COLUMN_CITY + " integer not null, " +
			COLUMN_DEBT + " integer not null, " +
			COLUMN_FINAL_SCORE + " integer not null, " +
			COLUMN_TABLET + " integer not null, " +
			COLUMN_GEAR + " integer not null, " + 
			COLUMN_COMPASS + " integer not null, " + 
			COLUMN_WILD + " integer not null, " + 
			COLUMN_DEFEAT + " integer not null, " + 
			COLUMN_VITORY_1 + " integer not null, " + 
			COLUMN_VITORY_3 + " integer not null, " + 
			COLUMN_VITORY_5 + " integer not null, " +
			COLUMN_WONDER_TYPE +  "integer not null, " +
			" FOREIGN KEY (" + COLUMN_PLAYER_KEY + ") REFERENCES " + PLAYER.TABLE_NAME + " (" + PLAYER.COLUMN_ID + "), " +
			" FOREIGN KEY (" + COLUMN_GAME_KEY + ") REFERENCES " + GAMES.TABLE_NAME + " (" + GAMES.COLUMN_ID + ") " + ");";
	}
	
	public SQLDatabase(Context context) {
		super(context, DATABASE_NAME, null, DATABASE_VERSION);
	}
	
	@Override
	public void onCreate(SQLiteDatabase database) {

		try {
			for (String table: allTablesCreate)
                database.execSQL(table);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
	@Override
	public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
		Log.w(SQLDatabase.class.getName(), "Upgrading database from version " + oldVersion + " to " 
				+ newVersion + ", which will destroy all old data");
		for (String table: allTablesCreate)
			db.execSQL("DROP TABLE IF EXISTS " + table);
	    onCreate(db);
	}
} 