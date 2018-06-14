package com.richardallenonline.wonders.ui.Dialog;

import kankan.wheel.widget.WheelView;
import kankan.wheel.widget.adapters.NumericWheelAdapter;

import com.richardallenonline.wonders.R;
import com.richardallenonline.wonders.ScienceCalculator;
import com.richardallenonline.wonders.WondersApp;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.app.DialogFragment;
import android.os.Bundle;
import android.app.Fragment;
import android.content.DialogInterface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.animation.AnticipateOvershootInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

/**
 * A simple {@link Fragment} subclass. Activities that contain this fragment
 * must implement the
 * {@link ScienceCalculatorDialogFragment.OnFragmentInteractionListener}
 * interface to handle interaction events. Use the
 * {@link ScienceCalculatorDialogFragment#newInstance} factory method to create
 * an instance of this fragment.
 *
 */

public class ScienceCalculatorDialogFragment extends DialogFragment  {
	private static final int[] textidArray = {R.id.textViewTablet, R.id.textViewGear, R.id.textViewCompass, R.id.textViewWild,
		R.id.textViewScore};
	
	private static final int[] wheelidArray = {R.id.wheelTablet, R.id.wheelGear, R.id.wheelCompass, R.id.wheelWild};
	
	private static final int[] clickidArray = {R.id.buttonGO, R.id.buttonRESET};
	private static final int[] clickImageidArray = {R.id.imageViewTablet, R.id.imageViewGear, R.id.imageViewCompass, 
		R.id.imageViewTabletWild, R.id.imageViewSlash1, R.id.imageViewGearWild, R.id.imageViewSlash2, R.id.imageViewCompassWild };
	
	private ScienceCalculator score = new ScienceCalculator(0, 0, 0, 0);
		
	private int playerNumber = 0;

	private WondersApp app = null;
	
	private View mainView = null;
	
	/* The activity that creates an instance of this dialog fragment must
     * implement this interface in order to receive event callbacks.
     * Each method passes the DialogFragment in case the host needs to query it. */
    public interface ScienceCalculatorDialogListener {
    	public void onDialogClose();
    }
    
    // Use this instance of the interface to deliver action events
    private ScienceCalculatorDialogListener mListener = null;
		
	@Override
    public Dialog onCreateDialog(Bundle savedInstanceState) {
        // Use the Builder class for convenient dialog construction
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());

		LayoutInflater inflater = getActivity().getLayoutInflater();

		mainView = inflater.inflate(R.layout.fragment_science_calculator_dailog, null);

        builder.setView(mainView);
        builder.setTitle(R.string.science_calculator_dailog)
               .setPositiveButton(R.string.ok, new DialogInterface.OnClickListener() {
                   @Override
				public void onClick(DialogInterface dialog, int id) {
                       // FIRE ZE MISSILES!
                	   okButton();
					   mListener.onDialogClose();
                   }
               })
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
        initImages();
        initWheel();
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
            mListener = (ScienceCalculatorDialogListener) activity;
        } catch (ClassCastException e) {
            // The activity doesn't implement the interface, throw exception
            throw new ClassCastException(activity.toString()
                    + " must implement ScienceCalculatorDialogListener");
        }
    }
	
	private void initButtons() {
		Button button = null;
		for (int id : clickidArray) {
			button = (Button)mainView.findViewById(id);
			button.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View view) {
					switch (view.getId()){
					case R.id.buttonGO:
						goButton(view);
						break;
					case R.id.buttonRESET:
						resetButton(view);
						break;
					}
				}
			});
		}
	}

	private void initImages() {
		ImageView image = null;
		for (int id : clickImageidArray) {
			image = (ImageView)mainView.findViewById(id);
			image.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View view) {
					imagePress(view);
				}
			});
		}
	}

	private void initWheel() {

        WheelView wheel = null;

        for (int id : wheelidArray) {
	        wheel = (WheelView)mainView.findViewById(id);
	        wheel.setViewAdapter(new NumericWheelAdapter(mainView.getContext(), 0, 9));

	        wheel.setCyclic(true);
	        wheel.setInterpolator(new AnticipateOvershootInterpolator());
        }
    }

	public void setPlayerNum(int value) {
    	playerNumber = value;
    }

	public void goButton(View view) {
    	WheelView wheel = null;
    	TextView text = null;
    	int tablet = 0, gear = 0, compass = 0, wild = 0;


    	for (int id : wheelidArray) {
    		wheel = (WheelView)mainView.findViewById(id);
    		int tmp = wheel.getCurrentItem();

    		switch (id) {
				case R.id.wheelTablet:
					tablet = tmp;
					break;
				case R.id.wheelGear:
					gear = tmp;
					break;
				case R.id.wheelCompass:
					compass = tmp;
					break;
				case R.id.wheelWild:
					wild = tmp;
					break;
			}
    	}

    	score.Set(tablet, gear, compass, wild);

    	LinearLayout ll = (LinearLayout)mainView.findViewById(R.id.linearLayout1);
    	ll.setVisibility(View.VISIBLE);

    	for (int id : textidArray) {
    		text = (TextView)mainView.findViewById(id);
    		switch (id) {
	    		case R.id.textViewTablet:
	    			text.setText(((Integer)score.getTablet()).toString());
	    			break;
	    		case R.id.textViewGear:
	    			text.setText(((Integer)score.getGear()).toString());
	    			break;
	    		case R.id.textViewCompass:
	    			text.setText(((Integer)score.getCompass()).toString());
	    			break;
	    		case R.id.textViewWild:
	    			text.setText(((Integer)score.getWild()).toString());
	    			break;
	    		case R.id.textViewScore:
	    			text.setText(((Integer)score.getScore()).toString());
	    			break;
    		}
    	}
    }

	public void resetButton(View view) {
    	WheelView wheel = null;
    	TextView text = null;

    	for (int id : wheelidArray) {
    		wheel = (WheelView)mainView.findViewById(id);
    		wheel.setCurrentItem(0);
    	}

    	for (int id : textidArray) {
    		text = (TextView)mainView.findViewById(id);
    		text.setText("");
    	}

    	LinearLayout ll = (LinearLayout)mainView.findViewById(R.id.linearLayout1);
    	ll.setVisibility(View.GONE);
    }

    public void imagePress(View view) {
    	WheelView wheel = null;
    	switch (view.getId()) {
	    	case R.id.imageViewTablet:
	    		wheel = (WheelView)mainView.findViewById(R.id.wheelTablet);
	    		break;
	    	case R.id.imageViewGear:
	    		wheel = (WheelView)mainView.findViewById(R.id.wheelGear);
	    		break;
	    	case R.id.imageViewCompass:
	    		wheel = (WheelView)mainView.findViewById(R.id.wheelCompass);
	    		break;
	    	case R.id.imageViewTabletWild:
	    	case R.id.imageViewSlash1:
	    	case R.id.imageViewGearWild:
	    	case R.id.imageViewSlash2:
	    	case R.id.imageViewCompassWild:
	    		wheel = (WheelView)mainView.findViewById(R.id.wheelWild);
	    		break;
    	}
    	wheel.setCurrentItem(wheel.getCurrentItem() + 1);
    }
    
    public void okButton() {
		//goButton(null);

		app.getPlayerScoreData().get(playerNumber).setSciencePoints(score.getScore());
		app.getPlayerScoreData().get(playerNumber).setTabletCount(score.getTablet());
		app.getPlayerScoreData().get(playerNumber).setGearCount(score.getGear());
		app.getPlayerScoreData().get(playerNumber).setCompassCount(score.getCompass());
		app.getPlayerScoreData().get(playerNumber).setWildCount(score.getWild());
    }
}
