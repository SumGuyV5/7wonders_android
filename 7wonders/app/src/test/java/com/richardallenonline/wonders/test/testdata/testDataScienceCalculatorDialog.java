package com.richardallenonline.wonders.test.testdata;

import android.widget.TextView;

import com.richardallenonline.wonders.R;

import org.robolectric.shadows.ShadowAlertDialog;

import java.util.Random;

import kankan.wheel.widget.WheelView;

/**
 * Created by Laptop on 11/12/2015.
 */
public class testDataScienceCalculatorDialog {
    protected static final int[] textidArray = { R.id.textViewTablet, R.id.textViewGear,
            R.id.textViewCompass, R.id.textViewWild, R.id.textViewScore };

    protected static final int[] wheelidArray = { R.id.wheelTablet, R.id.wheelGear,
            R.id.wheelCompass, R.id.wheelWild };

    protected ShadowAlertDialog sAlert = null;

    protected void setupRandoData() {
        Random r = new Random();

        for (int id: wheelidArray) {
            WheelView wheel = sAlert.getView().findViewById(id);
            wheel.setCurrentItem(r.nextInt(9));
        }

        for (int id : textidArray) {
            TextView text = sAlert.getView().findViewById(id);
            text.setText("99");
        }
    }

    private void setWheel( int to, int id) {
        WheelView wheel = sAlert.getView().findViewById(id);
        wheel.setCurrentItem(to);
    }

    protected void setupTabletTo(int value) {
        setWheel(value, R.id.wheelTablet);
    }

    protected void setupGearTo(int value) {
        setWheel(value, R.id.wheelGear);
    }

    protected void setupCompassTo(int value) {
        setWheel(value, R.id.wheelCompass);
    }

    protected void setupWildTo(int value) {
        setWheel(value, R.id.wheelWild);
    }

    protected void setupReset() {
        setupCompassTo(0);
        setupGearTo(0);
        setupTabletTo(0);
        setupWildTo(0);
    }
}
