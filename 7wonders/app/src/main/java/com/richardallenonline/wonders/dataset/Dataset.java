package com.richardallenonline.wonders.dataset;

import android.content.ContentValues;
import android.database.Cursor;

import com.richardallenonline.wonders.EqualsBy;

public abstract class Dataset implements Comparable<Object> {
	private EqualsBy equalsBy = EqualsBy.ID;

	public void setEqualsBy() { setEqualsBy(EqualsBy.ID); }

	public void setEqualsBy(EqualsBy value) {
		equalsBy = value;
	}

	public EqualsBy getEqualsBy() {
		return equalsBy;
	}

	public abstract void SetData(Cursor cursor);
	public abstract ContentValues GetData();

	public abstract long getId();
}