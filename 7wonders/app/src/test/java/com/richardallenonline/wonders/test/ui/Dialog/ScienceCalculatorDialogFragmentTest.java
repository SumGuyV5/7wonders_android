package com.richardallenonline.wonders.test.ui.Dialog;

import android.app.AlertDialog;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.richardallenonline.wonders.BuildConfig;
import com.richardallenonline.wonders.R;
import com.richardallenonline.wonders.WondersApp;
import com.richardallenonline.wonders.test.testdata.testData;
import com.richardallenonline.wonders.test.testdata.testDataScienceCalculatorDialog;
import com.richardallenonline.wonders.ui.Activity.PlayerScoreCardActivity;
import com.richardallenonline.wonders.ui.Dialog.ScienceCalculatorDialogFragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowAlertDialog;

import kankan.wheel.widget.WheelView;

import static org.assertj.core.api.Assertions.assertThat;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by Laptop on 11/12/2015.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class ScienceCalculatorDialogFragmentTest extends testDataScienceCalculatorDialog {

    private PlayerScoreCardActivity activity = null;

    private ScienceCalculatorDialogFragment dialogFragment = null;

    private AlertDialog alert = null;

    private WondersApp app = null;

    @Before
    public void setup() {
        testData tstData = new testData();
        tstData.setupRealPeople();

        activity = Robolectric.setupActivity(PlayerScoreCardActivity.class);

        app = (WondersApp)activity.getApplication();

        dialogFragment = new ScienceCalculatorDialogFragment();
        dialogFragment.show(activity.getFragmentManager(), "");

        alert = ShadowAlertDialog.getLatestAlertDialog();
        sAlert = shadowOf(alert);
    }

    @Test
    public void ScienceCalculatorNotNull() throws Exception {
        assertThat(dialogFragment).isNotNull();
    }

    @Test
    public void clickingGoButton_() throws Exception {
        WheelView wheel = null;
        TextView text = null;

        setupRandoData();

        sAlert.getView().findViewById(R.id.buttonGO).performClick();

        LinearLayout ll = sAlert.getView().findViewById(R.id.linearLayout1);

        try {
            assertThat(ll.getVisibility()).isEqualTo(View.VISIBLE);
            System.out.println("Hide View - passed");
        } catch (AssertionError e) {
            System.out.println("Hide View - faild " + e.getMessage());
            throw e;
        }

        try {
            for (int id: textidArray) {
                text = sAlert.getView().findViewById(id);
                assertThat(text.getText().toString()).isNotEmpty();
            }
            System.out.println("Reset Text View - passed");
        } catch (AssertionError e) {
            System.out.println("Reset Text View - faild " + e.getMessage());
            throw e;
        }

    }

    @Test
    public void clickingResetButton_() throws Exception {
        WheelView wheel = null;
        TextView text = null;

        setupRandoData();

        sAlert.getView().findViewById(R.id.buttonRESET).performClick();

        LinearLayout ll = sAlert.getView().findViewById(R.id.linearLayout1);

        try {
            for (int id: wheelidArray) {
                wheel = sAlert.getView().findViewById(id);
                assertThat(wheel.getCurrentItem()).isEqualTo(0);
            }
            System.out.println("Reset Wheels - passed");
        } catch (AssertionError e) {
            System.out.println("Reset Wheels - faild " + e.getMessage());
            throw e;
        }

        try {
            for (int id: textidArray) {
                text = sAlert.getView().findViewById(id);
                assertThat(text.getText().toString()).isEmpty();
            }
            System.out.println("Reset Text View - passed");
        } catch (AssertionError e) {
            System.out.println("Reset Text View - faild " + e.getMessage());
            throw e;
        }

        try {
            assertThat(ll.getVisibility()).isEqualTo(View.GONE);
            System.out.println("Hide View - passed");
        } catch (AssertionError e) {
            System.out.println("Hide View - faild " + e.getMessage());
            throw e;
        }
    }

    @Test
    public void One() throws Exception {
        //Test Tablet
        Setup(1, 0, 0, 0);
        CheckAllOutput(1, 0, 0, 0, 1, "One Tablet");
        //Test Gear
        Setup(0, 1, 0, 0);
        CheckAllOutput(0, 1, 0, 0, 1, "One Gear");
        //Test Compass
        Setup(0, 0, 1, 0);
        CheckAllOutput(0, 0, 1, 0, 1, "One Compass");
        //Test Wild
        Setup(0, 0, 0, 1);
        CheckAllOutput(0, 0, 1, 0, 1, "One Wild");

        //Test All Ones no wild
        Setup(1, 1, 1, 0);
        CheckAllOutput(1, 1, 1, 0, 10, "All Ones no wilds");

        //Test All Ones
        Setup(1, 1, 1, 1);
        CheckAllOutput(1, 1, 2, 0, 13, "All Ones");
    }

    @Test
    public void Nine() throws Exception {
        //Test Tablet
        Setup(9, 0, 0, 0);
        CheckAllOutput(9, 0, 0, 0, 81, "Nine Tablet");
        //Test Gear
        Setup(0, 9, 0, 0);
        CheckAllOutput(0, 9, 0, 0, 81, "Nine Gear");
        //Test Compass
        Setup(0, 0, 9, 0);
        CheckAllOutput(0, 0, 9, 0, 81, "Nine Compass");
        //Test Wild
        Setup(0, 0, 0, 9);
        CheckAllOutput(0, 0, 9, 0, 81, "Nine Wild");

        //Test All Ones no wild
        Setup(9, 9, 9, 0);
        CheckAllOutput(9, 9, 9, 0, 306, "All Nines no wilds");

        //Test All Ones
        Setup(9, 9, 9, 9);
        CheckAllOutput(9, 9, 18, 0, 549, "All Nines");
    }

    private void Setup(int tablet, int gear, int compass, int wild) throws Exception {
        setupReset();

        setupTabletTo(tablet);
        setupGearTo(gear);
        setupCompassTo(compass);
        setupWildTo(wild);

        sAlert.getView().findViewById(R.id.buttonGO).performClick();
    }

    private void CheckAllOutput(int tablet, int gear, int compass, int wild, int total, String msg)
            throws Exception {

        String what = "";
        try {
            what = msg + " Tablet ";
            CheckThis(tablet, R.id.textViewTablet, what);

            what = msg + " Gear ";
            CheckThis(gear, R.id.textViewGear, what);

            what = msg + " Compass ";
            CheckThis(compass, R.id.textViewCompass, what);

            what = msg + " Wild ";
            CheckThis(wild, R.id.textViewWild, what);

            what = msg + " Score ";
            CheckThis(total, R.id.textViewScore, what);
        } catch(AssertionError e){
            System.out.println(what + " - failed");

            throw e;
        }
    }

    private void CheckThis(int value, int id, String msg) throws AssertionError {
        TextView txt = sAlert.getView().findViewById(id);
        int what = Integer.parseInt(txt.getText().toString());
        assertThat(what).isEqualTo(value);
        System.out.println(msg + " - passed");
    }

    @Test
    public void clickingOKButton_() throws Exception {
        setupReset();

        setupTabletTo(1);
        setupGearTo(1);
        setupCompassTo(1);
        setupWildTo(0);

        sAlert.getView().findViewById(R.id.buttonGO).performClick();

        try {
            alert.getButton(AlertDialog.BUTTON_POSITIVE).performClick();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

        assertThat(app.getPlayerScoreData().get(0).getSciencePoints()).isEqualTo(10);
    }

    @Test
    public void clickingCanaclButton_() throws Exception {
        alert.getButton(AlertDialog.BUTTON_NEGATIVE).performClick();

        assertThat(sAlert.hasBeenDismissed()).isTrue();
    }
}
