package com.richardallenonline.wonders.ui.dialog;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;

import com.richardallenonline.wonders.R;
import com.richardallenonline.wonders.WondersApp;

public class WondersDialogFragment extends DialogFragment  {
	private View mainView = null;

	private int player = 0;

	private WondersApp app = null;
	
	/* The activity that creates an instance of this dialog fragment must
     * implement this interface in order to receive event callbacks.
     * Each method passes the DialogFragment in case the host needs to query it. */
    public interface WondersDialogListener {
    	void onDialogClose();
    	void onDialogNext(int age, int player);
    }
    
    // Use this instance of the interface to deliver action events
    private WondersDialogListener mListener = null;
		
	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        
        LayoutInflater inflater = getActivity().getLayoutInflater();
        
        mainView = inflater.inflate(R.layout.fragment_wonders_dailog, null);
        
        builder.setView(mainView);
        builder.setTitle(R.string.wonder)
               /*.setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                   public void onClick(DialogInterface dialog, int id) {
                       // FIRE ZE MISSILES!
                	   okButton();
                	   //mListener.onDialogClose();
                   }
               })*/
               .setNegativeButton(R.string.cancel, new DialogInterface.OnClickListener() {
				   @Override
				   public void onClick(DialogInterface dialog, int id) {
					   // User cancelled the dialog
					   dismiss();
					   mListener.onDialogClose();
				   }
			   });
		app = (WondersApp)getActivity().getApplication();

        initButtons();
        
        // Create the AlertDialog object and return it
        return builder.create();
    }
	
	// Override the Fragment.onAttach() method to instantiate the NoticeDialogListener
    @Override
    public void onAttach(Activity activity) {
        super.onAttach(activity);
        // Verify that the host activity implements the callback interface
        try {
            // Instantiate the NoticeDialogListener so we can send events to the host
            mListener = (WondersDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement MilitaryDialogListener");
        }
    }
        
    private void initButtons() {
    	if (mainView != null) {
    	}
	}
	
	public int getPlayer() {
		return player;
	}

	public void setPlayer(int value) {
		player = value;
		if (player == app.getPlayerScoreData().size())
			player = 0;
		initButtons();
	}	
}
