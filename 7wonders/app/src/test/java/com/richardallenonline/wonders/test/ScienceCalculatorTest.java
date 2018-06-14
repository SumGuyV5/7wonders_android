package com.richardallenonline.wonders.test;

import com.richardallenonline.wonders.BuildConfig;
import com.richardallenonline.wonders.ScienceCalculator;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Richard on 02/09/2015.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class ScienceCalculatorTest {
    ScienceCalculator tst = null;

    @Before
    public void setup() {
        tst = new ScienceCalculator();
    }

    @Test
    public void ConstructorDefault() {
        tst = new ScienceCalculator();

        CheckAllOutputs(0, 0, 0, 0, 0, "ConstructorDefault Zeros");
    }

    @Test
    public void ConstructorValues() {
        tst = new ScienceCalculator(0, 0, 0, 0);

        CheckAllOutputs(0, 0, 0, 0, 0, "ConstructorValues Zeros");


        ConstructorValuesOne();

        ConstructorValuesNegativeOne();

        ConstructorValuesNine();
    }

    private void ConstructorValuesOne() {
        // Test Ones
        // Test One Tablet
        tst = new ScienceCalculator(1, 0, 0, 0);

        CheckAllOutputs(1, 0, 0, 0, 1, "ConstructorValues One Tablet");

        // Test One Gear
        tst = new ScienceCalculator(0, 1, 0, 0);

        CheckAllOutputs(0, 1, 0, 0, 1, "ConstructorValues One Gear");

        // Test One Compass
        tst = new ScienceCalculator(0, 0, 1, 0);

        CheckAllOutputs(0, 0, 1, 0, 1, "ConstructorValues One Compass");

        // Test One Wild
        tst = new ScienceCalculator(0, 0, 0, 1);

        CheckAllOutputs(0, 0, 1, 0, 1, "ConstructorValues One Wild");

        // Test One of Each no Wild
        tst = new ScienceCalculator(1, 1, 1, 0);

        CheckAllOutputs(1, 1, 1, 0, 10, "ConstructorValues One of Each no Wild");

        // Test One of Each plus Wild
        tst = new ScienceCalculator(1, 1, 1, 1);

        CheckAllOutputs(1, 1, 2, 0, 13, "ConstructorValues One of Each plus Wild");
    }

    private void ConstructorValuesNegativeOne() {
        // Test Negative Ones
        // Test Negative One Tablet
        tst = new ScienceCalculator(-1, 0, 0, 0);

        CheckAllOutputs(0, 0, 0, 0, 0, "ConstructorValues Negative One Tablet");

        // Test Negative One Gear
        tst = new ScienceCalculator(0, -1, 0, 0);

        CheckAllOutputs(0, 0, 0, 0, 0, "ConstructorValues Negative One Gear");

        // Test Negative One Compass
        tst = new ScienceCalculator(0, 0, -1, 0);

        CheckAllOutputs(0, 0, 0, 0, 0, "ConstructorValues Negative One Compass");

        // Test Negative One Wild
        tst = new ScienceCalculator(0, 0, 0, -1);

        CheckAllOutputs(0, 0, 0, 0, 0, "ConstructorValues Negative One Wild");

        // Test Negative One of Each no Wild
        tst = new ScienceCalculator(-1, -1, -1, 0);

        CheckAllOutputs(0, 0, 0, 0, 0, "ConstructorValues Negative One of Each no Wild");

        // Test Negative One of Each plus Wild
        tst = new ScienceCalculator(0, 0, 0, 0);

        CheckAllOutputs(0, 0, 0, 0, 0, "ConstructorValues Negative One of Each plus Wild");
    }

    private void ConstructorValuesNine() {
        // Test Nine
        // Test Nine Tablet
        tst = new ScienceCalculator(9, 0, 0, 0);

        CheckAllOutputs(9, 0, 0, 0, 81, "ConstructorValues Nine Tablet");

        // Test Nine Gear
        tst = new ScienceCalculator(0, 9, 0, 0);

        CheckAllOutputs(0, 9, 0, 0, 81, "ConstructorValues Nine Gear");

        // Test Nine Compass
        tst = new ScienceCalculator(0, 0, 9, 0);

        CheckAllOutputs(0, 0, 9, 0, 81, "ConstructorValues Nine Compass");

        // Test Nine Wild
        tst = new ScienceCalculator(0, 0, 0, 9);

        CheckAllOutputs(0, 0, 9, 0, 81, "ConstructorValues Nine Wild");

        // Test Nine of Each no Wild
        tst = new ScienceCalculator(9, 9, 9, 0);

        CheckAllOutputs(9, 9, 9, 0, 306, "ConstructorValues Nine of Each no Wild");

        // Test Nine of Each plus Wild
        tst = new ScienceCalculator(9, 9, 9, 9);

        CheckAllOutputs(9, 9, 18, 0, 549, "ConstructorValues Nine of Each plus Wild");
    }

    private void CheckAllOutputs(int tablet, int gear, int compass, int wild, int score, String msg) {
        String what = "";
        try{
            what = msg + " Tablet ";
            CheckOutput(what, tst.getTablet(), tablet);

            what = msg + " Gear ";
            CheckOutput(what, tst.getGear(), gear);

            what = msg + " Compass ";
            CheckOutput( what, tst.getCompass(), compass);

            what = msg + " Wild ";
            CheckOutput(what, tst.getWild(), wild);

            what = msg + " Score ";
            CheckOutput(what, tst.getScore(), score);

        }catch(AssertionError e){
            System.out.println(what + " - failed");

            throw e;
        }
    }

    private void CheckOutput(String msg, int test, int outcome) throws AssertionError {
        assertThat(test).isEqualTo(outcome);
        System.out.println(msg + " - passed");
    }
}
