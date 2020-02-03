package com.richardallenonline.wonders.test.cities;

import com.richardallenonline.wonders.cities.Petra;
import com.richardallenonline.wonders.test.base.WonderHelper;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

/**
 * Created by Laptop on 18/12/2015.
 */
@RunWith(RobolectricTestRunner.class)
public class PetraTest extends WonderHelper {
    @Test
    public void checkConstructorDefault() throws Exception {
        tst = new Petra();

        checkWonder(0, 0, "Constructor Default");

        tst.setStagesBuilt(1);
        checkWonder(3, 1, "Constructor Default");

        tst.setStagesBuilt(2);
        checkWonder(10, 2, "Constructor Default");

        tst.setStagesBuilt(3);
        checkWonder(17, 3, "Constructor Default");

    }

    @Test
    public void checkConstructorA() throws Exception {
        tst = new Petra("a");
        checkWonder(0, 0, "Constructor A");

        tst.setStagesBuilt(1);
        checkWonder(3, 1, "Constructor A");

        tst.setStagesBuilt(2);
        checkWonder(10, 2, "Constructor A");

        tst.setStagesBuilt(3);
        checkWonder(17, 3, "Constructor A");
    }

    @Test
    public void checkConstructorB() throws Exception {
        tst = new Petra("b");

        checkWonder(0, 0, "Constructor B");

        tst.setStagesBuilt(1);
        checkWonder(3, 1, "Constructor B");

        tst.setStagesBuilt(2);
        checkWonder(17, 2, "Constructor B");

        tst.setStagesBuilt(3);
        checkWonder(17, 3, "Constructor B");
    }
}

