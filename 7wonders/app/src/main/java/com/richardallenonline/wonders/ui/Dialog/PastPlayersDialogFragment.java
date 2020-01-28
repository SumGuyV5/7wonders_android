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

public class PastPlayersDialogFragment extends DialogFragment {
    private WondersApp app = null;

    private Activity activity = null;

    private List<PlayersDataset> pastPlayers = new ArrayList<>();

    /* The activity that creates an instance of this dialog fragment must
     * implement this interface in order to receive event callbacks.
     * Each method passes the DialogFragment in case the host needs to query it. */
    public interface PastPlayersDialogListener {
        void onDialogClose(int whitch);

        //public void onDialogNext(int age, int player);
    }

    // Use this instance of the interface to deliver action events
    private PastPlayersDialogListener mListener = null;

    @SuppressLint("InflateParams")
    @Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        activity = getActivity();
        AlertDialog.Builder builder = new AlertDialog.Builder(activity);

        LayoutInflater inflater = activity.getLayoutInflater();

        app = (WondersApp)activity.getApplication();

        builder.setTitle(R.string.past_players_dailog)
                .setPositiveButton(R.string.ok, addPlayersButtonClickListener())
                .setNeutralButton(R.string.more, addPlayersButtonClickListener())
                .setNegativeButton(R.string.cancel, addPlayersButtonClickListener());

        List<String> descriptions = new ArrayList<>();

        pastPlayers.addAll(app.db().getPlayers());
        for (PlayersDataset name : pastPlayers)
            descriptions.add(name.getName());

        CharSequence[] array = {};
        return builder
                .setMultiChoiceItems(descriptions.toArray(array), null,
                        new DialogInterface.OnMultiChoiceClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
                        if (isChecked) {
                            app.PlayersDataAdd(pastPlayers.get(which));
                        } else {
                            app.PlayersDataRemove(pastPlayers.get(which));
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
            mListener = (PastPlayersDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement PastPlayersDialogListener");
        }
    }

    private DialogInterface.OnClickListener addPlayersButtonClickListener() {
        return new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch(which) {
                    case DialogInterface.BUTTON_POSITIVE:
                        mListener.onDialogClose(which);
                        break;
                    case DialogInterface.BUTTON_NEUTRAL:
                        AlertDialog.Builder alert = new AlertDialog.Builder(activity);
                        LinearLayout layout       = new LinearLayout(activity);
                        TextView tvMessage        = new TextView(activity);
                        final EditText etInput    = new EditText(activity);

                        etInput.setId(R.id.player_name_edit);
                        tvMessage.setText(R.string.enter_name);
                        etInput.setSingleLine();
                        layout.setOrientation(LinearLayout.VERTICAL);
                        layout.addView(tvMessage);
                        layout.addView(etInput);
                        alert.setTitle(R.string.new_player);
                        alert.setView(layout);

                        alert.setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                            }
                        });

                        alert.setPositiveButton(R.string.done, new DialogInterface.OnClickListener() {

                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                if (!etInput.getText().toString().isEmpty()) {
                                    try {
                                        app.db().Add(new PlayersDataset(-1, etInput.getText()
                                                .toString(), 0));
                                        pastPlayers.clear();
                                        pastPlayers.addAll(app.db().getPlayers());
                                    } catch (Exception e) {
                                        // TODO Auto-generated catch block
                                        e.printStackTrace();
                                    }
                                }
                            }
                        });

                        alert.show();

                        mListener.onDialogClose(which);
                        break;
                    case DialogInterface.BUTTON_NEGATIVE:
                        app.PlayersDataClear();
                        mListener.onDialogClose(which);
                        break;
                }
            }
        };
    }

    public PlayersDataset getPastPlayer(int value) {
        return pastPlayers.get(value);
    }

    public int getPastPlayerSize() {
        return pastPlayers.size();
    }
}