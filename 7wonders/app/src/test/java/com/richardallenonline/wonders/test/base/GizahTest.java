package com.richardallenonline.wonders.test.base;

import com.richardallenonline.wonders.base.Gizah;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;

/**
 * Created by Laptop on 18/12/2015.
 */
@RunWith(RobolectricTestRunner.class)
public class GizahTest extends WonderHelper {
    @Test
    public void checkConstructorDefault() throws Exception {
        tst = new Gizah();

        checkWonder(0, 0, "Constructor Default");

        tst.setStagesBuilt(1);
        checkWonder(3, 1, "Constructor Default");

        tst.setStagesBuilt(2);
        checkWonder(8, 2, "Constructor Default");

        tst.setStagesBuilt(3);
        checkWonder(15, 3, "Constructor Default");

    }

    @Test
    public void checkConstructorA() throws Exception {
        tst = new Gizah("a");
        checkWonder(0, 0, "Constructor A");

        tst.setStagesBuilt(1);
        checkWonder(3, 1, "Constructor A");

        tst.setStagesBuilt(2);
        checkWonder(8, 2, "Constructor A");

        tst.setStagesBuilt(3);
        checkWonder(15, 3, "Constructor A");
    }

    @Test
    public void checkConstructorB() throws Exception {
        tst = new Gizah("b");

        checkWonder(0, 0, "Constructor B");

        tst.setStagesBuilt(1);
        checkWonder(3, 1, "Constructor B");

        tst.setStagesBuilt(2);
        checkWonder(8, 2, "Constructor B");

        tst.setStagesBuilt(3);
        checkWonder(13, 3, "Constructor B");

        tst.setStagesBuilt(4);
        checkWonder(20, 4, "Constructor B");
    }
}
