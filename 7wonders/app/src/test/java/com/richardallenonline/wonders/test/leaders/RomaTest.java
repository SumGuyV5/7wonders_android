package com.richardallenonline.wonders.test.leaders;

import com.richardallenonline.wonders.BuildConfig;
import com.richardallenonline.wonders.cities.Petra;
import com.richardallenonline.wonders.leaders.Roma;
import com.richardallenonline.wonders.test.base.WonderHelper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

/**
 * Created by Laptop on 18/12/2015.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class RomaTest extends WonderHelper {
    @Test
    public void checkConstructorDefault() throws Exception {
        tst = new Roma();

        checkWonder(0, 0, "Constructor Default");

        tst.setStagesBuilt(1);
        checkWonder(4, 1, "Constructor Default");

        tst.setStagesBuilt(2);
        checkWonder(10, 2, "Constructor Default");

        tst.setStagesBuilt(3);
        checkWonder(10, 3, "Constructor Default");

    }

    @Test
    public void checkConstructorA() throws Exception {
        tst = new Roma("a");
        checkWonder(0, 0, "Constructor A");

        tst.setStagesBuilt(1);
        checkWonder(4, 1, "Constructor A");

        tst.setStagesBuilt(2);
        checkWonder(10, 2, "Constructor A");

        tst.setStagesBuilt(3);
        checkWonder(10, 3, "Constructor A");
    }

    @Test
    public void checkConstructorB() throws Exception {
        tst = new Roma("b");

        checkWonder(0, 0, "Constructor B");

        tst.setStagesBuilt(1);
        checkWonder(0, 1, "Constructor B");

        tst.setStagesBuilt(2);
        checkWonder(3, 2, "Constructor B");

        tst.setStagesBuilt(3);
        checkWonder(6, 3, "Constructor B");
    }
}
