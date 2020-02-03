package com.richardallenonline.wonders.test.ui.activity;

import android.app.AlertDialog;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.richardallenonline.wonders.R;
import com.richardallenonline.wonders.WondersApp;
import com.richardallenonline.wonders.test.testdata.testData;
import com.richardallenonline.wonders.ui.activity.PlayerScoreCardActivity;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowActivity;
import org.robolectric.shadows.ShadowAlertDialog;

import static org.assertj.core.api.Assertions.assertThat;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by Laptop on 26/11/2015.
 */
@RunWith(RobolectricTestRunner.class)
public class PlayerScoreCardActivityTest {

    private final int[] buttonIdArray = { R.id.buttonScore, R.id.buttonScienceHelp,
            R.id.buttonBack };

    private final int[] editTextIdArray = { R.id.editTextMilitary, R.id.editTextMoney,
            R.id.editTextWonder, R.id.editTextCivilian, R.id.editTextCommercial, R.id.editTextGuild,
            R.id.editTextScience, R.id.editTextLeaders, R.id.editTextCities, R.id.editTextDebt };

    private final int[] textIdArray = { R.id.textViewPlayerName, R.id.textViewTotal };

    private PlayerScoreCardActivity activity = null;

    @Before
    public void setup() {
        testData tstData = new testData();
        tstData.setupGenPlayers();

        activity = Robolectric.setupActivity(PlayerScoreCardActivity.class);

        WondersApp app = (WondersApp) activity.getApplication();
    }

    @Test
    public void checkActivityNotNull() throws Exception {
        assertThat(activity).isNotNull();
    }

    @Test
    public void checkTextViewNotNull() throws Exception {
        TextView txt = null;
        for (int textId : textIdArray) {
            try{
                txt = activity.findViewById(textId);
                assertThat(txt).isNotNull();
                System.out.println(txt.getId() + " - passed");
            }catch(AssertionError e){
                System.out.println(txt.getId() + " - failed");
                throw e;
            }
        }
    }

    @Test
    public void checkEditTextNotNull() throws Exception {
        EditText txt = null;
        for (int textId : editTextIdArray) {
            try{
                txt = activity.findViewById(textId);
                assertThat(txt).isNotNull();
                System.out.println(txt.getId() + " - passed");
            }catch(AssertionError e){
                System.out.println(txt.getId() + " - failed");
                throw e;
            }
        }
    }

    @Test
    public void checkButtonNotNull() throws Exception {
        Button btn = null;
        for (int buttonId : buttonIdArray) {
            try{
                btn = activity.findViewById(buttonId);
                assertThat(btn).isNotNull();
                System.out.println(btn.getText() + " - passed");
            }catch (AssertionError e){
                System.out.println(btn.getText() + " - failed");
                throw e;
            }
        }
    }

    @Test
    public void checkEditTextIsZero() throws Exception {
        EditText txt = null;
        for (int textId : editTextIdArray) {
            try{
                txt = activity.findViewById(textId);
                assertThat(txt.getText().toString()).isEqualTo("0");
                System.out.println(txt.getId() + " - passed");
            }catch(AssertionError e){
                System.out.println(txt.getId() + " - failed");
                throw e;
            }
        }
    }

    @Test
    public void checkTextViewPlayerName() throws Exception {
        TextView txt = activity.findViewById(R.id.textViewPlayerName);
        try{
            assertThat(txt.getText()).isEqualTo("Player 1");
            System.out.println("PlayerName - passed");
        }catch(AssertionError e){
            System.out.println("PlayerName - failed");
            throw e;
        }
    }

    @Test
    public void checkTextViewTotal() throws Exception {
        TextView txt = activity.findViewById(R.id.textViewTotal);
        try{
            assertThat(txt.getText()).isEqualTo("0");
            System.out.println("Total - passed");
        }catch(AssertionError e){
            System.out.println("Total - failed");
            throw e;
        }
    }

    @Test
    public void clickingScoreButton() throws  Exception {
        activity.findViewById(R.id.buttonScore).performClick();
        TextView txt = activity.findViewById(R.id.textViewTotal);
        assertThat(txt.getText()).isEqualTo("0");
    }

    @Test
    public void clickingScienceHelpButton_shouldScienceCalculatorAlertDialog() throws Exception {
        activity.findViewById(R.id.buttonScienceHelp).performClick();

        AlertDialog alert = ShadowAlertDialog.getLatestAlertDialog();
        ShadowAlertDialog sAlert = shadowOf(alert);

        assertThat(sAlert.getTitle()).isEqualTo(activity.getString(R.string.science_calculator_dailog));
    }

    @Test
    public void clickingBackButton_shouldGOBack() throws Exception {
        activity.findViewById(R.id.buttonBack).performClick();

        ShadowActivity activityShadow = shadowOf(activity);

        assertThat(activityShadow.isFinishing()).isTrue();
    }

    @Test
    public void clickingScienceImage_shouldScienceCalculatorAlertDialog() throws Exception {
        activity.findViewById(R.id.imageViewScienceScore).performClick();

        AlertDialog alert = ShadowAlertDialog.getLatestAlertDialog();
        ShadowAlertDialog sAlert = shadowOf(alert);

        assertThat(sAlert.getTitle()).isEqualTo(activity.getString(R.string.science_calculator_dailog));
    }
}
