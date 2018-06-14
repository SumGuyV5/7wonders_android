package com.richardallenonline.wonders.test.ui.Dialog;

import android.app.AlertDialog;
import android.widget.EditText;

import com.richardallenonline.wonders.BuildConfig;
import com.richardallenonline.wonders.R;
import com.richardallenonline.wonders.WondersApp;
import com.richardallenonline.wonders.test.testdata.testData;
import com.richardallenonline.wonders.ui.Activity.MainScoreCardActivity;
import com.richardallenonline.wonders.ui.Activity.PlayerCountActivity;
import com.richardallenonline.wonders.ui.Dialog.PastPlayersDialogFragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricGradleTestRunner;
import org.robolectric.annotation.Config;
import org.robolectric.shadows.ShadowAlertDialog;

import static org.assertj.core.api.Assertions.assertThat;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by Laptop on 10/12/2015.
 */
@RunWith(RobolectricGradleTestRunner.class)
@Config(constants = BuildConfig.class)
public class PastPlayersDialogFragmentTest {
    private PlayerCountActivity activity = null;

    private PastPlayersDialogFragment dialogFragment = null;

    private AlertDialog alert = null;
    private ShadowAlertDialog sAlert = null;

    private WondersApp app = null;

    @Before
    public void setup() {
        testData tstData = new testData();
        tstData.setupRealPeople();

        activity = Robolectric.setupActivity(PlayerCountActivity.class);

        app = (WondersApp)activity.getApplication();

        dialogFragment = new PastPlayersDialogFragment();
        dialogFragment.show(activity.getFragmentManager(), "");

        alert = ShadowAlertDialog.getLatestAlertDialog();
        sAlert = shadowOf(alert);
    }

    @Test
    public void DialogFragmantNotNull() throws Exception {
        assertThat(dialogFragment).isNotNull();
    }

    @Test
    public void clickingOKButton() throws Exception {
        String name = alert.getListView().getSelectedItem().toString();

        sAlert.clickOnItem(0);

        alert.getButton(AlertDialog.BUTTON_POSITIVE).performClick();

        assertThat(app.getPlayersData().get(0).getName()).isEqualTo(name);
    }

    @Test
    public void clickingOKButtonNoSelection() throws Exception {
        String name = alert.getListView().getSelectedItem().toString();

        sAlert.clickOnItem(0);

        assertThat(app.getPlayersData().size()).isEqualTo(1);

        sAlert.clickOnItem(0);

        alert.getButton(AlertDialog.BUTTON_POSITIVE).performClick();

        assertThat(app.getPlayersData().size()).isEqualTo(0);

    }

    @Test
    public void clickingMoreButton() throws Exception {
        alert.getButton(AlertDialog.BUTTON_NEUTRAL).performClick();

        AlertDialog dialog = ShadowAlertDialog.getLatestAlertDialog();
        ShadowAlertDialog sDialog = shadowOf(dialog);

        assertThat(sDialog.getTitle()).isEqualTo(activity.getString(R.string.new_player));
    }

    @Test
    public void clickingMoreButton_MoreDoneButton() throws Exception {
        alert.getButton(AlertDialog.BUTTON_NEUTRAL).performClick();

        AlertDialog dialog = ShadowAlertDialog.getLatestAlertDialog();

        ((EditText)dialog.findViewById(R.id.player_name_edit)).setText("Jeff");

        int count = app.db().getPlayers().size();

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).performClick();

        assertThat(app.db().getPlayers().size()).isEqualTo(count + 1);

        assertThat(dialogFragment.getPastPlayer(dialogFragment.getPastPlayerSize() - 1).getName()).isEqualTo("Jeff");
    }

    @Test
    public void clickingMoreButton_MoreOKButton() throws Exception {
        alert.getButton(AlertDialog.BUTTON_NEUTRAL).performClick();

        AlertDialog dialog = ShadowAlertDialog.getLatestAlertDialog();

        ((EditText)dialog.findViewById(R.id.player_name_edit)).setText("");

        int count = app.db().getPlayers().size();

        dialog.getButton(AlertDialog.BUTTON_POSITIVE).performClick();

        assertThat(app.db().getPlayers().size()).isEqualTo(count);

        assertThat(dialogFragment.getPastPlayer(dialogFragment.getPastPlayerSize() - 1).getName()).isEqualTo("Damon");
    }

    @Test
    public void clickingMoreButton_MoreCancelButton() throws Exception {
        alert.getButton(AlertDialog.BUTTON_NEUTRAL).performClick();

        AlertDialog dialog = ShadowAlertDialog.getLatestAlertDialog();

        ((EditText)dialog.findViewById(R.id.player_name_edit)).setText("Jeff");

        int count = app.db().getPlayers().size();

        dialog.getButton(AlertDialog.BUTTON_NEGATIVE).performClick();

        assertThat(app.db().getPlayers().size()).isEqualTo(count);

        assertThat(dialogFragment.getPastPlayer(dialogFragment.getPastPlayerSize() - 1).getName()).isEqualTo("Damon");
    }

    @Test
    public void clickingCanaclButton_() throws Exception {
        alert.getButton(AlertDialog.BUTTON_POSITIVE).performClick();

        assertThat(sAlert.hasBeenDismissed()).isTrue();
    }
}
