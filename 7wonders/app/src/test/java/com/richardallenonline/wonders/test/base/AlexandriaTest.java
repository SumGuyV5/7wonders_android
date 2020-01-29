package com.richardallenonline.wonders.test.base;

import com.richardallenonline.wonders.base.Alexandria;
import com.richardallenonline.wonders.BuildConfig;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.robolectric.Shadows.shadowOf;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Richard on 05/09/2015.
 */
@RunWith(RobolectricTestRunner.class)
public class AlexandriaTest extends WonderHelper {
    @Test
    public void checkConstructorDefault() throws Exception {
        tst = new Alexandria();

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
        tst = new Alexandria("a");
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
        tst = new Alexandria("b");

        checkWonder(0, 0, "Constructor B");

        tst.setStagesBuilt(1);
        checkWonder(0, 1, "Constructor B");

        tst.setStagesBuilt(2);
        checkWonder(0, 2, "Constructor B");

        tst.setStagesBuilt(3);
        checkWonder(7, 3, "Constructor B");
    }
}
