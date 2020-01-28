package com.richardallenonline.wonders.ui.Dialog;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.database.SQLException;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.richardallenonline.wonders.R;
import com.richardallenonline.wonders.WondersApp;
import com.richardallenonline.wonders.dataset.PlayersDataset;

import java.util.ArrayList;
import java.util.List;

public class DeletePlayersDialogFragment extends DialogFragment {
    private WondersApp app = null;

    private List<PlayersDataset> pastPlayers = new ArrayList<PlayersDataset>();
    private List<PlayersDataset> deletePlayers = new ArrayList<PlayersDataset>();

    /* The activity that creates an instance of this dialog fragment must
     * implement this interface in order to receive event callbacks.
     * Each method passes the DialogFragment in case the host needs to query it. */
    public interface DeletePlayersDialogListener {
        void onDialogClose(int which);
    }

    // Use this instance of the interface to deliver action events
    private DeletePlayersDialogListener mListener = null;

    @SuppressLint("InflateParams")
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        Activity activity = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();

        app = (WondersApp)activity.getApplication();

        builder.setTitle(R.string.delete_players)
                .setPositiveButton(R.string.ok, deletePlayersButtonClickListener())
                .setNegativeButton(R.string.cancel, deletePlayersButtonClickListener());


        final List<String> descriptions = new ArrayList<String>();

        pastPlayers.addAll(app.db().getPlayers());
        for (PlayersDataset name :pastPlayers)
            descriptions.add(name.getName());

        CharSequence[] array = {};
        return builder
                .setMultiChoiceItems(descriptions.toArray(array), null,
                        new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked) {
                            deletePlayers.add(pastPlayers.get(which));
                        } else {
                            deletePlayers.remove(pastPlayers.get(which));
                        }
                    }
                }).create();
    }

    // Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (DeletePlayersDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement DeletePlayersDialogListener");
        }
    }

    private DialogInterface.OnClickListener deletePlayersButtonClickListener() {
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch(which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        for (PlayersDataset player : deletePlayers) {
                            player.setHide(1);
                            app.db().Update(player);
                        }
                        break;
                    case DialogInterface.BUTTON_NEUTRAL:
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        deletePlayers.clear();
                        break;
                }
            }
        };
    }

    public PlayersDataset getDeletePlayer(int value) {
        return deletePlayers.get(value);
    }

    public int getDeletePlayerSize() {
        return deletePlayers.size();
    }
}