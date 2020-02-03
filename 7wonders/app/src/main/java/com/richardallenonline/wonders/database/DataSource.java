package com.richardallenonline.wonders.database;

import java.util.ArrayList;
import java.util.List;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import com.richardallenonline.wonders.database.SQLDatabase.GAMES;
import com.richardallenonline.wonders.database.SQLDatabase.PLAYER;
import com.richardallenonline.wonders.database.SQLDatabase.PLAYS;
import com.richardallenonline.wonders.dataset.Dataset;
import com.richardallenonline.wonders.dataset.GamesDataset;
import com.richardallenonline.wonders.dataset.PlayersDataset;
import com.richardallenonline.wonders.dataset.PlaysDataset;

public class DataSource<T extends Dataset> {
	// Database fields
	private SQLiteDatabase database;
	private SQLDatabase dbHelper;
	
	private String TABLE_NAME;
	private String COLUMN_ID;
	private String[] allColumns;
	
	private final Class<T> type;
	
	public DataSource(Class<T> cls, Context context) {
		this.type = cls;
		dbHelper = new SQLDatabase(context);
		if (this.type.equals(GamesDataset.class)) {
			TABLE_NAME = GAMES.TABLE_NAME;
			COLUMN_ID = GAMES.COLUMN_ID;
			allColumns = GAMES.allColumns;
		} else if (this.type.equals(PlayersDataset.class)) {
			TABLE_NAME = PLAYER.TABLE_NAME;
			COLUMN_ID = PLAYER.COLUMN_ID;
			allColumns = PLAYER.allColumns;	
		} else if (this.type.equals(PlaysDataset.class)) {
			TABLE_NAME = PLAYS.TABLE_NAME;
			COLUMN_ID = PLAYS.COLUMN_ID;
			allColumns = PLAYS.allColumns;
		}
	}

	public void open() throws SQLException {
		database = dbHelper.getWritableDatabase();
	}

	public void close() {
		dbHelper.close();
	}

	public void update(T value) {
		database.update(TABLE_NAME, value.GetData(), COLUMN_ID + " = " + value.getId(), null);
	}

	public T create(T value) {
		long insertId = -1;
		try {
			insertId = database.insertOrThrow(TABLE_NAME, null, value.GetData());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Cursor cursor = database.query(TABLE_NAME, allColumns, COLUMN_ID + " = " + insertId, null,
				null, null, null);
		T rtn = null;
		if ((!isCursorEmptyOrNotPrepared(cursor)) && (cursor.moveToFirst())) {
			rtn = generateDatasetFromCursor(cursor);
			cursor.close();
		}
		return rtn;
	}

	public void delete(T vlaue) {
		long id = vlaue.getId();
		System.out.println("Game deleted with id: " + id);
		database.delete(TABLE_NAME, COLUMN_ID + " = " + id, null);
	}

	public List<T> getAll() {
		List<T> rtn = new ArrayList<>();

		Cursor cursor = database.query(TABLE_NAME, allColumns, null, null, null, null, null);

		if ((!isCursorEmptyOrNotPrepared(cursor)) && (cursor.moveToFirst())) {
			while (!cursor.isAfterLast()) {
				rtn.add(generateDatasetFromCursor(cursor));
				cursor.moveToNext();
			}
			// make sure to close the cursor
			cursor.close();
		}

		return rtn;
	}

	public T getCursor() {
		T rtn = null;
		Cursor cursor = database.query(TABLE_NAME, allColumns, null, null, null, null, null);

		if ((!isCursorEmptyOrNotPrepared(cursor)) && (cursor.moveToFirst())) {
			rtn = generateDatasetFromCursor(cursor);
			// make sure to close the cursor
			cursor.close();
		}
		return rtn;
	}

	private T generateDatasetFromCursor(Cursor cursor) {
		T rtn = null;
		try {
			rtn = type.newInstance();
			rtn.SetData(cursor);
		} catch (InstantiationException | IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return rtn;
	}

	private synchronized boolean isCursorEmptyOrNotPrepared(Cursor cursor) {
		if (cursor == null)
			return true;

		if (cursor.isClosed())
			return true;

		// HERE IT CRASHES
		return cursor.getCount() == 0;
	}
}
