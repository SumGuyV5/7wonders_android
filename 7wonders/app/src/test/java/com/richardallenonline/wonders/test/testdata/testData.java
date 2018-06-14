package com.richardallenonline.wonders.test.testdata;

import com.richardallenonline.wonders.WondersApp;
import com.richardallenonline.wonders.dataset.PlayerScoreDataset;
import com.richardallenonline.wonders.dataset.PlayersDataset;
import com.richardallenonline.wonders.ui.Activity.PlayerCountActivity;

import org.robolectric.Robolectric;

/**
 * Created by Laptop on 26/11/2015.
 */
public class testData {
    private WondersApp app = null;

    public testData() {
        app = (WondersApp)Robolectric.setupActivity(PlayerCountActivity.class).getApplication();
    }

    public void setupGenPlayers() {
        for (int count = 1; count <= 8; count++) {
            app.setPlayerScoreDataName("Player " + ((Integer)count).toString(), new PlayerScoreDataset());
        }

        app.setPlayerCount(8);
    }

    public void setupRealPeople() {
        setupPlayer("Richard", 0);
        setupPlayer("Terry", 0);
        setupPlayer("Tanya", 0);
        setupPlayer("Damon", 1);

        for (PlayersDataset player : app.db().getPlayers() ) {
            if (player.getHide() == 0)
                app.PlayersDataAdd(player);
        }

        app.setPlayerCount(3);
    }

    private void setupPlayer(String name, int hide) {
        app.db().Add(new PlayersDataset(-1, name, hide));
    }
}
