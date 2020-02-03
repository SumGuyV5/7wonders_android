package com.richardallenonline.wonders.test.base;

import com.richardallenonline.wonders.base.Olympia;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

/**
 * Created by Laptop on 18/12/2015.
 */
@RunWith(RobolectricTestRunner.class)
public class OlympiaTest extends WonderHelper {
    @Test
    public void checkConstructorDefault() throws Exception {
        tst = new Olympia();

        checkWonder(0, 0, "Constructor Default");

        tst.setStagesBuilt(1);
        checkWonder(3, 1, "Constructor Default");

        tst.setStagesBuilt(2);
        checkWonder(3, 2, "Constructor Default");

        tst.setStagesBuilt(3);
        checkWonder(10, 3, "Constructor Default");

    }

    @Test
    public void checkConstructorA() throws Exception {
        tst = new Olympia("a");
        checkWonder(0, 0, "Constructor A");

        tst.setStagesBuilt(1);
        checkWonder(3, 1, "Constructor A");

        tst.setStagesBuilt(2);
        checkWonder(3, 2, "Constructor A");

        tst.setStagesBuilt(3);
        checkWonder(10, 3, "Constructor A");
    }

    @Test
    public void checkConstructorB() throws Exception {
        tst = new Olympia("b");

        checkWonder(0, 0, "Constructor B");

        tst.setStagesBuilt(1);
        checkWonder(0, 1, "Constructor B");

        tst.setStagesBuilt(2);
        checkWonder(5, 2, "Constructor B");

        tst.setStagesBuilt(3);
        checkWonder(5, 3, "Constructor B");
    }
}
