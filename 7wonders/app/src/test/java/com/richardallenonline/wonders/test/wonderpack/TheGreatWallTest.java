package com.richardallenonline.wonders.test.wonderpack;

import com.richardallenonline.wonders.BuildConfig;
import com.richardallenonline.wonders.test.base.WonderHelper;
import com.richardallenonline.wonders.wonderpack.Stonehenge;
import com.richardallenonline.wonders.wonderpack.TheGreatWall;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

/**
 * Created by Laptop on 18/12/2015.
 */
@RunWith(RobolectricTestRunner.class)
public class TheGreatWallTest extends WonderHelper {
    @Test
    public void checkConstructorDefault() throws Exception {
        tst = new TheGreatWall();

        checkWonder(0, 0, "Constructor Default");

        tst.setStagesBuilt(1);
        checkWonder(0, 1, "Constructor Default");

        tst.setStagesBuilt(2);
        checkWonder(0, 2, "Constructor Default");

        tst.setStagesBuilt(3);
        checkWonder(0, 3, "Constructor Default");

    }

    @Test
    public void checkConstructorA() throws Exception {
        tst = new TheGreatWall("a");
        checkWonder(0, 0, "Constructor A");

        tst.setStagesBuilt(1);
        checkWonder(0, 1, "Constructor A");

        tst.setStagesBuilt(2);
        checkWonder(0, 2, "Constructor A");

        tst.setStagesBuilt(3);
        checkWonder(0, 3, "Constructor A");
    }

    @Test
    public void checkConstructorB() throws Exception {
        tst = new TheGreatWall("b");

        checkWonder(0, 0, "Constructor B");

        tst.setStagesBuilt(1);
        checkWonder(0, 1, "Constructor B");

        tst.setStagesBuilt(2);
        checkWonder(0, 2, "Constructor B");

        tst.setStagesBuilt(3);
        checkWonder(0, 3, "Constructor B");
    }
}

