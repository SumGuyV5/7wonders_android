package com.richardallenonline.wonders.dataset;

import com.richardallenonline.wonders.EqualsBy;
import com.richardallenonline.wonders.database.SQLDatabase.PLAYER;

import android.content.ContentValues;
import android.database.Cursor;
import androidx.annotation.NonNull;

public class PlayersDataset extends Dataset {
	  private long id;
	  private String name;
	  private long hide;
	  
	  public PlayersDataset() {
		  Clean();
	  }
	  
	  public PlayersDataset(long id, String name, long hide) {
		  this.id = id;
		  this.name = name;
		  this.hide = hide;
	  }
	  
	  public PlayersDataset(PlayersDataset copy) {
		  this(copy.getId(), copy.getName(), copy.getHide());
	  }

	public void Clean() {
		id = -1;
		name = "";
		hide = 0;
	}

	  @Override
	  public int compareTo(@NonNull Object another) {
		  return (int)(this.id - ((PlayersDataset)another).getId());
	  }

	  @Override
	  public boolean equals(Object obj) {
		  if (obj == null)
			  return false;
		  if (getClass() != obj.getClass())
			  return false;
		  final PlayersDataset other = (PlayersDataset)obj;

		  boolean rtn;
		  switch (EqualsBy.getId(super.getEqualsBy().getValue() | other.getEqualsBy().getValue())) {
			  default:
			  case ID:
				  rtn = (id == other.getId());
				  break;
			  case EveryThing:
				  rtn = (name.equals(other.getName()));
				  break;
		  }
		  return rtn;
	  }
	  
	  @Override
	  public void SetData(Cursor cursor) {
		  setId(cursor.getLong(0));
		  setName(cursor.getString(1));
		  setHide(cursor.getLong(2));
	  }

	  @Override
	  public ContentValues GetData() {
		  ContentValues rtn = new ContentValues();
		  rtn.put(PLAYER.COLUMN_NAME, getName());
		  rtn.put(PLAYER.COLUMN_HIDE, getHide());
		  return rtn;
	  }	

	  @Override	  
	  public long getId() {
		  return id;
	  }

	  public void setId(long id) {
		  this.id = id;
	  }

	  public String getName() {
		  return name;
	  }

	  public void setName(String name) {
		  this.name = name;
	  }
	  
	  public long getHide() {
		  return hide;
	  }
	  
	  public void setHide(long hide) {
		  this.hide = hide;
	  }

	  // Will be used by the ArrayAdapter in the ListView
	  @NonNull
	  @Override
	  public String toString() {
		  return name;
	  }
}