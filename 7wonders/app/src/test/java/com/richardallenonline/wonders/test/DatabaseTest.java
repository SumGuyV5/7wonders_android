package com.richardallenonline.wonders.test;

import com.richardallenonline.wonders.BuildConfig;
import com.richardallenonline.wonders.Database;
import com.richardallenonline.wonders.ScienceCalculator;
import com.richardallenonline.wonders.WondersApp;
import com.richardallenonline.wonders.dataset.GamesDataset;
import com.richardallenonline.wonders.ui.Activity.PlayerCountActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class DatabaseTest {
    Database tst = null;
    @Before
    public void setup() {
        WondersApp app = (WondersApp) Robolectric.setupActivity(PlayerCountActivity.class)
                .getApplication();
        //app.setPlayerCount(8);
        tst = new Database(app);
    }

    @Test
    public void ConstructorDefault() {
        assertThat(tst).isNotNull();
    }

    @Test
    public void checkLoadDataBase() {
        tst.LoadDataBase();
    }

    @Test
    public void checkAddGame() {
        GamesDataset data = new GamesDataset(-11 , 1, 2, 3, 4, 5, 6, 7, 8, 20160101);

        long rtn = tst.Add(data);

        assertThat(rtn).isEqualTo(1);
    }
}
