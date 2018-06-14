package com.richardallenonline.wonders.ui.Activity;

import com.richardallenonline.wonders.R;
import com.richardallenonline.wonders.WondersApp;
import com.richardallenonline.wonders.ui.Dialog.PastPlayersDialogFragment;
import com.richardallenonline.wonders.ui.Dialog.DeletePlayersDialogFragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RadioGroup;
import android.widget.RadioButton;

public class PlayerCountActivity extends Activity
		implements PastPlayersDialogFragment.PastPlayersDialogListener, DeletePlayersDialogFragment.DeletePlayersDialogListener {

	private WondersApp app = null;

	private final int[] radioidArray = {R.id.radio0, R.id.radio1, R.id.radio2, R.id.radio3,
			R.id.radio4, R.id.radio5, R.id.radio6 };
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_player_count);
				
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
		app = (WondersApp)getApplication();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	@Override
	protected void onResume() {
		//datasource.open();
		app.PlayersDataClear();
		app.getPlayerScoreData().clear();
		
	    super.onResume();
	}

	@Override
	protected void onPause() {
	    //datasource.close();
	    super.onPause();
	}

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			View rootView = inflater.inflate(R.layout.fragment_player_count, container,
					false);
			Setup();
			return rootView;
		}
		
		private void Setup() {
			
		}
	}
	
	public void goButton(View view) {
		Intent intent = new Intent(this, MainScoreCardActivity.class);
		app.setPlayerCount(PlayerCount());
        startActivity(intent);        
    }
	
	public void pastPlayersButton(View view) {
		PastPlayersDialogFragment newFragment = new PastPlayersDialogFragment();
		newFragment.show(getFragmentManager(), "");

	}
	
	public void deletePlayersButton(View view) {
		DeletePlayersDialogFragment newFragment = new DeletePlayersDialogFragment();
		newFragment.show(getFragmentManager(), "");
	}

	public void onDialogClose(int which) {
		switch(which) {
			case DialogInterface.BUTTON_POSITIVE:
				int count = app.getPlayersDataSize();
				RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radioGroupPlayerCount);
				if (count >= 2)
					count -= 2;
				else
					count = 0;
				radioGroup.check(radioidArray[count]);
				break;
			case DialogInterface.BUTTON_NEUTRAL:
				break;
			case DialogInterface.BUTTON_NEGATIVE:

				break;
		}
	}

	private int PlayerCount() {

		RadioGroup radioGroup = (RadioGroup)findViewById(R.id.radioGroupPlayerCount);

        int radioButtonID = radioGroup.getCheckedRadioButtonId();

		RadioButton radioButton = (RadioButton)radioGroup.findViewById(radioButtonID);

        return Integer.parseInt(radioButton.getText().toString());
	}
}
