package com.richardallenonline.wonders;

import android.app.Application;
import android.content.Context;
import android.os.Bundle;

import com.richardallenonline.wonders.dataset.PlayerScoreDataset;
import com.richardallenonline.wonders.dataset.PlayersDataset;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Richard on 01/09/2015.
 */
public class WondersApp extends Application {
    private Database dataBase = null;

    private List<PlayerScoreDataset> playerScoreData = new ArrayList<>();
    private List<PlayersDataset> playersData = new ArrayList<>();

    private int playerCount = 2;

    @Override
    public void onCreate() {
        super.onCreate();
        Setup(this);
    }

    public synchronized Database db() {
        return dataBase;
    }

    private synchronized void Setup(Context con) {
        dataBase = new Database(con);
    }

    public List<String> getNames() {
        List<String> rtn = new ArrayList<>();
        for (PlayerScoreDataset data : playerScoreData)
            rtn.add(getName(data));
        return rtn;
    }

    public void Reorder(List<String> names) {
        List<PlayerScoreDataset> tmp = new ArrayList<>();
        for (String name: names) {
            for (PlayerScoreDataset data: playerScoreData){
                if (getName(data).equals(name)) {
                    tmp.add(data);
                }
            }
        }

        playerScoreData.clear();
        playerScoreData = tmp;
    }

    public List<PlayersDataset> getPlayersData() {
        return playersData;
    }

    public List<PlayerScoreDataset> getPlayerScoreData() {
        return playerScoreData;
    }

    public void setPlayerScoreDataName(String name, int value) {
        setPlayerScoreDataName(name, playerScoreData.get(value));
    }

    public void setPlayerScoreDataName(String name, PlayerScoreDataset data) {
        boolean found = false;
        for (PlayersDataset player : playersData) {
            if (player.getId() == data.getPlayerKey()) {
                player.setName(name);
                found = true;
                break;
            }
        }
        if (!found) {
            PlayersDataset tmp = new PlayersDataset(-1, name, 0);
            tmp.setId(dataBase.Add(tmp));
            int idx = dataBase.getPlayers().indexOf(tmp);
            if (idx >= 0) {
                tmp = dataBase.getPlayers().get(idx);

                data.setPlayerKey(tmp.getId());
                playersData.add(tmp);
            }
        }
    }

    public String getPlayerScoreDataName(int value) {
        return getName(playerScoreData.get(value));
    }

    public String getName(PlayerScoreDataset value) {
        String rtn = "";
        for (PlayersDataset player : playersData) {
            if (player.getId() == value.getPlayerKey()) {
                rtn = player.getName();
                break;
            }
        }
        return rtn;
    }

    public int getPlayersDataSize() {
        return playersData.size();
    }

    public void PlayersDataAdd(PlayersDataset value) {
        playersData.add(value);
    }

    public void PlayersDataRemove(PlayersDataset value) {
        playersData.remove(value);
    }

    public void PlayersDataClear() {
        playersData.clear();
    }

    public int getPlayerCount() {
        return playerCount;
    }

    public void setPlayerCount(int value) {
        if (value < 2)
            value = 2;
        if (value > 8)
            value = 8;

        playerCount = value;

        playerScoreData.clear();
        for ( PlayersDataset player : playersData)
            playerScoreData.add(new PlayerScoreDataset(player));

        for (int count = playerScoreData.size() + 1; playerScoreData.size() < playerCount; count++) {
            int idx = playersData.indexOf(new PlayersDataset(-1, "Player " +
                    ((Integer)count).toString(), 0));
            if (idx != -1)
                playerScoreData.add( new PlayerScoreDataset(playersData.get(idx)));
            else {
                PlayerScoreDataset tmp = new PlayerScoreDataset();
                setPlayerScoreDataName("Player " + ((Integer) count).toString(), tmp);
                playerScoreData.add(tmp);
            }
        }
    }
}
