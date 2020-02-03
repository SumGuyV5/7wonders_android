package com.richardallenonline.wonders.test.wonderpack;

import com.richardallenonline.wonders.test.base.WonderHelper;
import com.richardallenonline.wonders.wonderpack.MannekenPis;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

/**
 * Created by Laptop on 18/12/2015.
 */
@RunWith(RobolectricTestRunner.class)
public class MannekenPisTest extends WonderHelper {
    @Test
    public void checkConstructorDefault() throws Exception {
        tst = new MannekenPis();

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
        tst = new MannekenPis("a");
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
        tst = new MannekenPis("b");

        checkWonder(0, 0, "Constructor B");

        tst.setStagesBuilt(1);
        checkWonder(7, 1, "Constructor B");

        tst.setStagesBuilt(2);
        checkWonder(7, 2, "Constructor B");

        tst.setStagesBuilt(3);
        checkWonder(7, 3, "Constructor B");
    }
}
