package com.richardallenonline.wonders.ui.Dialog;

import com.richardallenonline.wonders.R;
import com.richardallenonline.wonders.WondersApp;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MilitaryDialogFragment extends DialogFragment  {
	private View mainView = null;
	
	private static final int[] imageidArray = {R.drawable.age1, R.drawable.age2, R.drawable.age3};
	private int age = 1;
	private int player = 0;
	private int playerNext = 1;

	private WondersApp app = null;
	
	/* The activity that creates an instance of this dialog fragment must
     * implement this interface in order to receive event callbacks.
     * Each method passes the DialogFragment in case the host needs to query it. */
    public interface MilitaryDialogListener {
    	public void onDialogClose();
    	public void onDialogNext(int age, int player);
    }
    
    // Use this instance of the interface to deliver action events
    private MilitaryDialogListener mListener = null;

	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        
        LayoutInflater inflater = getActivity().getLayoutInflater();
        
        mainView = inflater.inflate(R.layout.fragment_military_dailog, null);

		app = (WondersApp)getActivity().getApplication();
        
        builder.setView(mainView);
        builder.setTitle(R.string.military_dailog)
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

        
        initButtons();
        initImages();
        
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
            mListener = (MilitaryDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement MilitaryDialogListener");
        }
    }
        
    private void initButtons() {
    	if (mainView != null) {
			Button button = (Button)mainView.findViewById(R.id.buttonPlayer1);
			Button button2 = (Button)mainView.findViewById(R.id.buttonPlayer2);
			Button tieBtn = (Button)mainView.findViewById(R.id.buttonTie);
			
			button.setText(app.getPlayerScoreDataName(player));
			button2.setText(app.getPlayerScoreDataName(playerNext));
			
			Button[] btnArray = {button, button2, tieBtn};
			for(Button btn : btnArray ) {
				btn.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View view) {
						switch (view.getId()){
						case R.id.buttonPlayer1:
							app.getPlayerScoreData().get(player).addVitoryToken(age);
							app.getPlayerScoreData().get(playerNext).addDefeatToken();
							break;
						case R.id.buttonPlayer2:
							app.getPlayerScoreData().get(player).addDefeatToken();
							app.getPlayerScoreData().get(playerNext).addVitoryToken(age);
							break;						
						}
						mListener.onDialogNext(age, playerNext);
						dismiss();
					}
				});
			}
    	}
	}
	
	private void initImages() {
		if (mainView != null) {
			ImageView image = (ImageView)mainView.findViewById(R.id.imageViewAge);
			
			image.setImageResource(imageidArray[age - 1]);
		}
	}
	

	public int getAge() {
		return age;
	}
	
	public void setAge(int value) {
		age = value;
		initImages();
	}
	
	public int getPlayer() {
		return player;
	}

	public void setPlayer(int value, int size) {
		player = value;
		if ((player + 1) == size)
			playerNext = 0;
		else
			playerNext = player + 1;
	}	
}
