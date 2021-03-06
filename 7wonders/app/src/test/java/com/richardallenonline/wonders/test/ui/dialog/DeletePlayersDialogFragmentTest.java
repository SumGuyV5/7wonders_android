package com.richardallenonline.wonders.test.ui.dialog;

import android.app.AlertDialog;

import com.richardallenonline.wonders.WondersApp;
import com.richardallenonline.wonders.test.testdata.testData;
import com.richardallenonline.wonders.ui.activity.PlayerCountActivity;
import com.richardallenonline.wonders.ui.dialog.DeletePlayersDialogFragment;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.robolectric.Robolectric;
import org.robolectric.RobolectricTestRunner;
import org.robolectric.shadows.ShadowAlertDialog;

import static org.assertj.core.api.Assertions.assertThat;
import static org.robolectric.Shadows.shadowOf;

/**
 * Created by Laptop on 07/12/2015.
 */
@RunWith(RobolectricTestRunner.class)
public class DeletePlayersDialogFragmentTest {

    private DeletePlayersDialogFragment dialogFragment = null;

    private AlertDialog alert = null;
    private ShadowAlertDialog sAlert = null;

    @Before
    public void setup() {
        testData tstData = new testData();
        tstData.setupRealPeople();

        PlayerCountActivity activity = Robolectric.setupActivity(PlayerCountActivity.class);

        WondersApp app = (WondersApp) activity.getApplication();

        dialogFragment = new DeletePlayersDialogFragment();
        dialogFragment.show(activity.getFragmentManager(), "");

        alert = ShadowAlertDialog.getLatestAlertDialog();
        sAlert = shadowOf(alert);
    }

    @Test
    public void deletePlayerDialogFragmantNotNull() throws Exception {
        assertThat(dialogFragment).isNotNull();
    }

    @Test
    public void InfoSetup() throws Exception {
        assertThat(alert.getListView().getCount()).isEqualTo(4);
    }

    @Test
    public void clickOKButton() throws Exception {
        String name = alert.getListView().getSelectedItem().toString();

        sAlert.clickOnItem(0);

        alert.getButton(AlertDialog.BUTTON_POSITIVE).performClick();

        assertThat(dialogFragment.getDeletePlayer(0).getName()).isEqualTo(name);
    }

    @Test
    public void clickCanacelButton() throws Exception {
        sAlert.clickOnItem(0);

        alert.getButton(AlertDialog.BUTTON_NEGATIVE).performClick();

        assertThat(dialogFragment.getDeletePlayerSize()).isEqualTo(0);
        assertThat(sAlert.hasBeenDismissed()).isTrue();
    }
}
