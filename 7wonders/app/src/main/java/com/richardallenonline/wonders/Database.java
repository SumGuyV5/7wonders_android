package com.richardallenonline.wonders;

import java.util.ArrayList;
import java.util.List;

import com.richardallenonline.wonders.database.DataSource;
import com.richardallenonline.wonders.dataset.Dataset;
import com.richardallenonline.wonders.dataset.GamesDataset;
import com.richardallenonline.wonders.dataset.PlayersDataset;
import com.richardallenonline.wonders.dataset.PlaysDataset;
import com.richardallenonline.wonders.ui.Activity.PlayerScoreCardActivity;

import android.content.Context;
import android.database.SQLException;

public class Database {
	private List<GamesDataset> gamesDatasetList = new ArrayList<>();
	private List<PlayersDataset> playersDatasetList = new ArrayList<>();
	private List<PlaysDataset> playsDatasetList = new ArrayList<>();
	
	private DataSource<GamesDataset> gamesDataSource = null;
	private DataSource<PlayersDataset> playersDataSource = null;
	private DataSource<PlaysDataset> playsDataSource = null;

	private boolean databaseLoaded = false;
	
	public Database(Context con) {
		gamesDataSource = new DataSource<>(GamesDataset.class, con);
		playersDataSource = new DataSource<>(PlayersDataset.class, con);
		playsDataSource = new DataSource<>(PlaysDataset.class, con);
		LoadDataBase();
	}
	
	public synchronized void LoadDataBase() {
		databaseLoaded = false;
		try {
			gamesDataSource.open();
			gamesDatasetList = gamesDataSource.getAll();

			playersDataSource.open();
			playersDatasetList = playersDataSource.getAll();

			playsDataSource.open();
			playsDatasetList = playsDataSource.getAll();

			databaseLoaded = true;
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			playersDataSource.close();
			gamesDataSource.close();
			playsDataSource.close();
		}
	}

	public synchronized <T extends Dataset> long Add(T value) {
		Class type = value.getClass();
		List list = null;
		DataSource source = null;

		if (!databaseLoaded)
			LoadDataBase();

		if (type.equals(GamesDataset.class)) {
			list = gamesDatasetList;
			source = gamesDataSource;
		} else if (type.equals(PlayersDataset.class)) {
			list = playersDatasetList;
			source = playersDataSource;
		} else if (type.equals(PlaysDataset.class)) {
			list = playsDatasetList;
			source = playsDataSource;
		}

		T tmp = null;
		int idx = list.indexOf(value);
		if (idx == -1) {
			tmp = AddToDataBase(value, source);
			list.add(tmp);
		} else {
			tmp = (T)list.get(idx);
			tmp.setEqualsBy(EqualsBy.EveryThing);
			if (tmp.equals(value) == false) {
				UpdateDataBase(value, source);
				tmp = (T)list.set(idx, value);
			}
			tmp.setEqualsBy();
		}
		return tmp.getId();
	}

	private synchronized <T extends Dataset> T AddToDataBase(T value, DataSource source) {
		T tmp = null;
		try {
			source.open();
			tmp = (T)source.create(value);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			source.close();
		}

		return tmp;
	}

	private synchronized <T extends Dataset> boolean UpdateDataBase(T value, DataSource source) {
		boolean rtn = false;
		try {
			source.open();
			source.update(value);
			rtn = true;
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			source.close();
		}
		return rtn;
	}

	public synchronized List<GamesDataset> getGames() {
		return gamesDatasetList;
	}
	
	public synchronized List<PlayersDataset> getPlayers() {
		return playersDatasetList;
	}
		
	public synchronized List<PlaysDataset> getPlays() {
		return playsDatasetList;
	}

	public synchronized void Update(PlayersDataset value) {
		int idx = playersDatasetList.indexOf(value);
		if (idx != -1) {
			PlayersDataset tmp =  playersDatasetList.get(idx);
			tmp.setHide(value.getHide());
		}

		try {
			playersDataSource.open();
			playersDataSource.update(value);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			playersDataSource.close();
		}
	}
}
