package com.richardallenonline.wonders.test;

import com.richardallenonline.wonders.BuildConfig;
import com.richardallenonline.wonders.WondersApp;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Laptop on 24/11/2015.
 */
@RunWith(RobolectricTestRunner.class)
public class WondersAppTest {
    WondersApp app = null;

    @Before
    public void setup() {
        app = new WondersApp();
    }

    @Test
    public void checkAppNotNull() throws Exception {
        assertThat(app).isNotNull();
    }
}
