package com.richardallenonline.wonders.test.ui.Activity;

import android.app.AlertDialog;
import android.content.Intent;
import android.widget.Button;
import android.widget.ImageView;

import com.richardallenonline.wonders.BuildConfig;
import com.richardallenonline.wonders.R;
import com.richardallenonline.wonders.WondersApp;
import com.richardallenonline.wonders.ui.Activity.PlayerCountActivity;
import com.richardallenonline.wonders.ui.Activity.MainScoreCardActivity;
import com.richardallenonline.wonders.ui.Activity.PlayerScoreCardActivity;

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
 * Created by Richard on 31/08/2015.
 */
@RunWith(RobolectricTestRunner.class)
public class MainScoreCardActivityTest {
    private final int[] buttonIdArray = { R.id.buttonPlayer1, R.id.buttonPlayer2,
            R.id.buttonPlayer3, R.id.buttonPlayer4, R.id.buttonPlayer5, R.id.buttonPlayer6,
            R.id.buttonPlayer7, R.id.buttonPlayer8 };

    private final int[] imageViewIdArray = { R.id.imageViewAge1,  R.id.imageViewAge2,
            R.id.imageViewAge3 };

    private MainScoreCardActivity activity = null;

    private WondersApp app = null;

    @Before
    public void setup() {
        app = (WondersApp)Robolectric.setupActivity(PlayerCountActivity.class).getApplication();
        app.setPlayerCount(8);
        activity = Robolectric.setupActivity(MainScoreCardActivity.class);
    }

    //Is everything there?
    @Test
    public void checkActivityNotNull() throws Exception {
        assertThat(activity).isNotNull();
    }

    @Test
    public void checkButtonsNotNull() throws Exception {
        for (int buttonId : buttonIdArray) {
            Button btn = activity.findViewById(buttonId);
            try{
                assertThat(btn).isNotNull();
                System.out.println(btn.getText() + " - passed");
            }catch(AssertionError e){
                System.out.println(btn.getText() + " - failed");
                throw e;
            }
        }
    }

    @Test
    public void checkImagesNotNull()  throws Exception {
        for (int imageViewId : imageViewIdArray) {
            ImageView imgView = activity.findViewById(imageViewId);
            try{
                assertThat(imgView).isNotNull();
                System.out.println("ImageView - passed");
            }catch(AssertionError e){
                System.out.println("ImageView - failed");
                throw e;
            }
        }
    }

    @Test
    public void clickingScoreButtons_shouldStartScoreCardActivity() throws Exception {
        for (int playerNum = 0; playerNum < app.getPlayerCount(); playerNum++) {
            Intent expectedIntent = new Intent(activity, PlayerScoreCardActivity.class);
            expectedIntent.putExtra("PlayerNumber", playerNum);
            Button btn = activity.findViewById(buttonIdArray[playerNum]);
            try{
                btn.performClick();
                assertThat(shadowOf(activity).getNextStartedActivity()).isEqualTo(expectedIntent);
                System.out.println(btn.getText() + " - passed");
            }catch(AssertionError e){
                System.out.println(btn.getText() + " - failed");
                throw e;
            }
        }
    }

    @Test
    public void clickingAgeImage_() throws Exception {
        activity.findViewById(R.id.imageViewAge1).performClick();

        AlertDialog alert = ShadowAlertDialog.getLatestAlertDialog();
        ShadowAlertDialog sAlert = shadowOf(alert);

        Button btn = alert.getButton(AlertDialog.BUTTON_POSITIVE);


        assertThat(sAlert.getTitle()).isEqualTo(activity.getString(R.string.military_dailog));
    }

}
