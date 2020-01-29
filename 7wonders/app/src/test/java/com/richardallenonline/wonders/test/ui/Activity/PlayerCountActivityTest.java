package com.richardallenonline.wonders.test.ui.Activity;

import com.richardallenonline.wonders.BuildConfig;
import com.richardallenonline.wonders.WondersApp;
import com.richardallenonline.wonders.ui.Activity.PlayerCountActivity;
import com.richardallenonline.wonders.ui.Activity.MainScoreCardActivity;
import com.richardallenonline.wonders.R;

import android.content.Intent;
import android.widget.Button;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.app.AlertDialog;

import org.junit.Test;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowAlertDialog;

import static org.robolectric.Shadows.shadowOf;
import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by Richard on 29/08/2015.
 */
@RunWith(RobolectricTestRunner.class)
@Config()
public class PlayerCountActivityTest {
    private PlayerCountActivity activity = null;

    @Before
    public void setup() {
        WondersApp app = (WondersApp) Robolectric.setupActivity(PlayerCountActivity.class).getApplication();
        app.setPlayerCount(8);
        activity = Robolectric.setupActivity(PlayerCountActivity.class);
    }

    //Is everything there?
    @Test
    public void checkActivityNotNull() throws Exception {
        assertThat(activity).isNotNull();
    }

    @Test
    public void checkRadioButtonNotNull() throws Exception {
        RadioGroup radioGroup = activity.findViewById(R.id.radioGroupPlayerCount);
        assertThat(radioGroup).isNotNull();
    }

    @Test
    public void checkButtonsNotNull() throws Exception {
        final int[] buttonArray = { R.id.buttonGO, R.id.buttonPastPlayers,
                R.id.buttonDeletePlayers };

        for (int button : buttonArray) {
            Button btn = activity.findViewById(button);
            try{
                assertThat(btn).isNotNull();
                System.out.println(btn.getText() + " - passed");
            }catch(AssertionError e){
                System.out.println(btn.getText() + " - failed");
                throw e;
            }
        }
    }

    //Ok check ui elements
    @Test
    public void checkRadioButtonDefault() throws Exception {
        final int[] radioidArray = { R.id.radio0, R.id.radio1, R.id.radio2, R.id.radio3,
                R.id.radio4, R.id.radio5, R.id.radio6 };

        RadioGroup radioGroup = activity.findViewById(R.id.radioGroupPlayerCount);

        for (int radioId: radioidArray) {
            RadioButton radioButton = radioGroup.findViewById(radioId);
            try{
                if (radioId == radioidArray[0])
                    assertThat(radioButton.isChecked()).isTrue();
                else
                    assertThat(!radioButton.isChecked()).isTrue();
                System.out.println(radioButton.getText() + " - passed");
            }catch(AssertionError e){
                System.out.println(radioButton.getText() + " - failed");
                throw e;
            }
        }
    }

    @Test
    public void clickingGOButton_shouldStartScoreCardActivity() throws Exception {
        activity.findViewById(R.id.buttonGO).performClick();

        Intent expectedIntent = new Intent(activity, MainScoreCardActivity.class);
        assertThat(shadowOf(activity).getNextStartedActivity()).isEqualTo(expectedIntent);
    }

    @Test
    public void clickingPastPlayersButton_shouldStartAlertDialog() throws Exception {
        activity.findViewById(R.id.buttonPastPlayers).performClick();

        AlertDialog alert = ShadowAlertDialog.getLatestAlertDialog();
        ShadowAlertDialog sAlert = shadowOf(alert);

        assertThat(sAlert.getTitle()).isEqualTo(activity.getString(R.string.past_players_dailog));
    }

    @Test
    public void clickingDeletePlayersButton_shouldStartAlertDialog() throws Exception {
        activity.findViewById(R.id.buttonDeletePlayers).performClick();

        AlertDialog alert = ShadowAlertDialog.getLatestAlertDialog();
        ShadowAlertDialog sAlert = shadowOf(alert);

        assertThat(sAlert.getTitle()).isEqualTo(activity.getString(R.string.delete_players));
    }
}
