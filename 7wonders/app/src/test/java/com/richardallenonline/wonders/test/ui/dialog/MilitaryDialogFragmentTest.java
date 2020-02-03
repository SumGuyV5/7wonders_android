package com.richardallenonline.wonders.test.ui.dialog;

import android.app.AlertDialog;

import com.richardallenonline.wonders.R;
import com.richardallenonline.wonders.WondersApp;
import com.richardallenonline.wonders.test.testdata.testData;
import com.richardallenonline.wonders.ui.activity.MainScoreCardActivity;
import com.richardallenonline.wonders.ui.dialog.MilitaryDialogFragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowAlertDialog;

import static org.assertj.core.api.Assertions.assertThat;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by Laptop on 10/12/2015.
 */
@RunWith(RobolectricTestRunner.class)
public class MilitaryDialogFragmentTest {

    private MilitaryDialogFragment dialogFragment = null;

    private AlertDialog alert = null;
    private ShadowAlertDialog sAlert = null;

    private WondersApp app = null;

    @Before
    public void setup() {
        testData tstData = new testData();
        tstData.setupRealPeople();

        MainScoreCardActivity activity = Robolectric.setupActivity(MainScoreCardActivity.class);

        app = (WondersApp) activity.getApplication();

        dialogFragment = new MilitaryDialogFragment();
        dialogFragment.show(activity.getFragmentManager(), "");

        alert = ShadowAlertDialog.getLatestAlertDialog();
        sAlert = shadowOf(alert);
    }

    @Test
    public void militaryDialogFragmantNotNull() throws Exception {
        assertThat(dialogFragment).isNotNull();
    }

    @Test
    public void InfoSetup() throws Exception {
        assertThat(app.getPlayerScoreData().size()).isEqualTo(3);
    }

    @Test
    public void clickingPlayer1Age1Button_() throws Exception {
        dialogFragment.setAge(1);
        dialogFragment.getDialog().findViewById(R.id.buttonPlayer1).performClick();

        assertThat(app.getPlayerScoreData().get(0).getVitoryTokenAge1()).isEqualTo(1);
        assertThat(app.getPlayerScoreData().get(0).getDefeatToken()).isEqualTo(0);


        assertThat(app.getPlayerScoreData().get(1).getVitoryTokenAge1()).isEqualTo(0);
        assertThat(app.getPlayerScoreData().get(1).getDefeatToken()).isEqualTo(1);
    }

    @Test
    public void clickingPlayer2Age1Button_() throws Exception {
        dialogFragment.setAge(1);
        dialogFragment.getDialog().findViewById(R.id.buttonPlayer2).performClick();

        assertThat(app.getPlayerScoreData().get(0).getVitoryTokenAge1()).isEqualTo(0);
        assertThat(app.getPlayerScoreData().get(0).getDefeatToken()).isEqualTo(1);


        assertThat(app.getPlayerScoreData().get(1).getVitoryTokenAge1()).isEqualTo(1);
        assertThat(app.getPlayerScoreData().get(1).getDefeatToken()).isEqualTo(0);
    }

    @Test
    public void clickingTieAge1Button_() throws Exception {
        dialogFragment.setAge(1);
        dialogFragment.getDialog().findViewById(R.id.buttonTie).performClick();

        assertThat(app.getPlayerScoreData().get(0).getVitoryTokenAge1()).isEqualTo(0);
        assertThat(app.getPlayerScoreData().get(0).getDefeatToken()).isEqualTo(0);


        assertThat(app.getPlayerScoreData().get(1).getVitoryTokenAge1()).isEqualTo(0);
        assertThat(app.getPlayerScoreData().get(1).getDefeatToken()).isEqualTo(0);
    }

    @Test
    public void clickingPlayer1Age2Button_() throws Exception {
        dialogFragment.setAge(2);
        dialogFragment.getDialog().findViewById(R.id.buttonPlayer1).performClick();

        assertThat(app.getPlayerScoreData().get(0).getVitoryTokenAge2()).isEqualTo(1);
        assertThat(app.getPlayerScoreData().get(0).getDefeatToken()).isEqualTo(0);


        assertThat(app.getPlayerScoreData().get(1).getVitoryTokenAge2()).isEqualTo(0);
        assertThat(app.getPlayerScoreData().get(1).getDefeatToken()).isEqualTo(1);
    }

    @Test
    public void clickingPlayer2Age2Button_() throws Exception {
        dialogFragment.setAge(2);
        dialogFragment.getDialog().findViewById(R.id.buttonPlayer2).performClick();

        assertThat(app.getPlayerScoreData().get(0).getVitoryTokenAge2()).isEqualTo(0);
        assertThat(app.getPlayerScoreData().get(0).getDefeatToken()).isEqualTo(1);


        assertThat(app.getPlayerScoreData().get(1).getVitoryTokenAge2()).isEqualTo(1);
        assertThat(app.getPlayerScoreData().get(1).getDefeatToken()).isEqualTo(0);
    }

    @Test
    public void clickingTieAge2Button_() throws Exception {
        dialogFragment.setAge(2);
        dialogFragment.getDialog().findViewById(R.id.buttonTie).performClick();

        assertThat(app.getPlayerScoreData().get(0).getVitoryTokenAge2()).isEqualTo(0);
        assertThat(app.getPlayerScoreData().get(0).getDefeatToken()).isEqualTo(0);


        assertThat(app.getPlayerScoreData().get(1).getVitoryTokenAge2()).isEqualTo(0);
        assertThat(app.getPlayerScoreData().get(1).getDefeatToken()).isEqualTo(0);
    }

    @Test
    public void clickingPlayer1Age3Button_() throws Exception {
        dialogFragment.setAge(3);
        dialogFragment.getDialog().findViewById(R.id.buttonPlayer1).performClick();

        assertThat(app.getPlayerScoreData().get(0).getVitoryTokenAge3()).isEqualTo(1);
        assertThat(app.getPlayerScoreData().get(0).getDefeatToken()).isEqualTo(0);


        assertThat(app.getPlayerScoreData().get(1).getVitoryTokenAge3()).isEqualTo(0);
        assertThat(app.getPlayerScoreData().get(1).getDefeatToken()).isEqualTo(1);
    }

    @Test
    public void clickingPlayer2Age3Button_() throws Exception {
        dialogFragment.setAge(3);
        dialogFragment.getDialog().findViewById(R.id.buttonPlayer2).performClick();

        assertThat(app.getPlayerScoreData().get(0).getVitoryTokenAge3()).isEqualTo(0);
        assertThat(app.getPlayerScoreData().get(0).getDefeatToken()).isEqualTo(1);


        assertThat(app.getPlayerScoreData().get(1).getVitoryTokenAge3()).isEqualTo(1);
        assertThat(app.getPlayerScoreData().get(1).getDefeatToken()).isEqualTo(0);
    }

    @Test
    public void clickingTieAge3Button_() throws Exception {
        dialogFragment.setAge(3);
        dialogFragment.getDialog().findViewById(R.id.buttonTie).performClick();

        assertThat(app.getPlayerScoreData().get(0).getVitoryTokenAge3()).isEqualTo(0);
        assertThat(app.getPlayerScoreData().get(0).getDefeatToken()).isEqualTo(0);


        assertThat(app.getPlayerScoreData().get(1).getVitoryTokenAge3()).isEqualTo(0);
        assertThat(app.getPlayerScoreData().get(1).getDefeatToken()).isEqualTo(0);
    }

    @Test
    public void clickingCanaclButton_() throws Exception {
        alert.getButton(AlertDialog.BUTTON_NEGATIVE).performClick();

        assertThat(sAlert.hasBeenDismissed()).isTrue();
    }
}
