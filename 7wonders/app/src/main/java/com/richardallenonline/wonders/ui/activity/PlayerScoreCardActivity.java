package com.richardallenonline.wonders.ui.activity;

import com.richardallenonline.wonders.R;
import com.richardallenonline.wonders.WondersApp;
import com.richardallenonline.wonders.dataset.PlayerScoreDataset;
import com.richardallenonline.wonders.ui.dialog.ScienceCalculatorDialogFragment;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class PlayerScoreCardActivity extends Activity
	implements ScienceCalculatorDialogFragment.ScienceCalculatorDialogListener {

    @Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_player_score_card);
		
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.player_score_card, menu);
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

	/**
	 * A placeholder fragment containing a simple view.
	 */
	public static class PlaceholderFragment extends Fragment {
		private final int[] textidArray = { R.id.textViewPlayerName, R.id.editTextMilitary,
				R.id.editTextMoney, R.id.editTextWonder, R.id.editTextCivilian,
				R.id.editTextCommercial, R.id.editTextGuild, R.id.editTextScience,
				R.id.editTextLeaders, R.id.editTextCities, R.id.editTextDebt, R.id.textViewTotal };
		private int playerNum = 0;
		private View mainView = null;
		private WondersApp app = null;

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
				Bundle savedInstanceState) {
			mainView = inflater.inflate(R.layout.fragment_player_score_card, container, false);
			
			PlayerScoreCardActivity activity = (PlayerScoreCardActivity)getActivity();
			app = (WondersApp)activity.getApplication();
			Bundle extras = activity.getIntent().getExtras();
			if (extras != null)
				setPlayerNum(extras.getInt("PlayerNumber"));
			
			Setup();
			return mainView;
		}
		
		@Override
		public void onResume() {
			super.onResume();
			Setup();
		}
		
		private void Setup() {
			PlayerScoreDataset data = app.getPlayerScoreData().get(playerNum);
			TextView text;
			EditText edit = null;
			for (int id: textidArray) {
				switch(id) {
				case R.id.textViewPlayerName:
					text = mainView.findViewById(id);
					text.setText(app.getName(data));
					break;
				case R.id.editTextMilitary:
					edit = mainView.findViewById(id);
					edit.setText(String.format(app.getLocale(), "%d", (Integer)data.getMilitaryPoints()));
					break;
				case R.id.editTextMoney:
					edit = mainView.findViewById(id);
					edit.setText(String.format(app.getLocale(), "%d", (Integer)data.getMoneyPoints()));
					break;
				case R.id.editTextWonder:
					edit = mainView.findViewById(id);
					edit.setText(String.format(app.getLocale(), "%d", (Integer)data.getWonderPoints()));
					break;
				case R.id.editTextCivilian:
					edit = mainView.findViewById(id);
					edit.setText(String.format(app.getLocale(), "%d", (Integer)data.getCivilianPoints()));
					break;
				case R.id.editTextCommercial:
					edit = mainView.findViewById(id);
					edit.setText(String.format(app.getLocale(), "%d", (Integer)data.getCommercialPoints()));
					break;
				case R.id.editTextGuild:
					edit = mainView.findViewById(id);
					edit.setText(String.format(app.getLocale(), "%d", (Integer)data.getGuildPoints()));
					break;
				case R.id.editTextScience:
					edit = mainView.findViewById(id);
					edit.setText(String.format(app.getLocale(), "%d", (Integer)data.getSciencePoints()));
					break;
				case R.id.editTextLeaders:
					edit = mainView.findViewById(id);
					edit.setText(String.format(app.getLocale(), "%d", (Integer)data.getLeaderPoints()));
					break;
				case R.id.editTextCities:
					edit = mainView.findViewById(id);
					edit.setText(String.format(app.getLocale(), "%d", (Integer)data.getCityPoints()));
					break;
				case R.id.editTextDebt:
					edit = mainView.findViewById(id);
					edit.setText(String.format(app.getLocale(), "%d", (Integer)data.getDebtPoints()));
					break;
				case R.id.textViewTotal:
					text = mainView.findViewById(id);
					text.setText(String.format(app.getLocale(), "%d", (Integer)data.getFinalScore()));
					break;
				}
				if (edit != null) {
					edit.setOnFocusChangeListener(new View.OnFocusChangeListener() {
						@Override
						public void onFocusChange(View v, boolean hasFocus) {
							if(!hasFocus) {
								UpdateData();
							}
						}
					});
					edit = null;
				}
			}			
		}
		
		private void UpdateData() {
			PlayerScoreDataset data = app.getPlayerScoreData().get(playerNum);
			TextView text;
			EditText edit;
			for (int id: textidArray) {
				switch(id) {
				case R.id.textViewPlayerName:
					break;
				case R.id.editTextMilitary:
					edit = mainView.findViewById(id);
					data.setMilitaryPoints(edit.getText().toString());
					break;
				case R.id.editTextMoney:
					edit = mainView.findViewById(id);
					data.setMoneyPoints(edit.getText().toString());
					break;
				case R.id.editTextWonder:
					edit = mainView.findViewById(id);
					data.setWonderPoints(edit.getText().toString());
					break;
				case R.id.editTextCivilian:
					edit = mainView.findViewById(id);
					data.setCivilianPoints(edit.getText().toString());
					break;
				case R.id.editTextCommercial:
					edit = mainView.findViewById(id);
					data.setCommercialPoints(edit.getText().toString());
					break;
				case R.id.editTextGuild:
					edit = mainView.findViewById(id);
					data.setGuildPoints(edit.getText().toString());
					break;
				case R.id.editTextScience:
					edit = mainView.findViewById(id);
					data.setSciencePoints(edit.getText().toString());
					break;
				case R.id.editTextLeaders:
					edit = mainView.findViewById(id);
					data.setLeaderPoints(edit.getText().toString());
					break;
				case R.id.editTextCities:
					edit = mainView.findViewById(id);
					data.setCityPoints(edit.getText().toString());
					break;
				case R.id.editTextDebt:
					edit = mainView.findViewById(id);
					data.setDebtPoints(edit.getText().toString());
					break;
				case R.id.textViewTotal:
					text = mainView.findViewById(id);
					text.setText(String.format(app.getLocale(), "%d", (Integer)data.getFinalScore()));
					break;
				}
			}				
		}
		
		public int getPlayerNum() {
			return playerNum;
		}
		
		private void setPlayerNum( int value ) {
			playerNum = value;
		}
	}
	
	public void scoreButton( View view ) {
		PlaceholderFragment fragment = (PlaceholderFragment)getFragmentManager()
				.findFragmentById(R.id.container);
		fragment.UpdateData();
	}
	
	public void scienceHelpButton( View view ) {
		PlaceholderFragment fragment = (PlaceholderFragment)getFragmentManager()
				.findFragmentById(R.id.container);

		ScienceCalculatorDialogFragment newFragment = new ScienceCalculatorDialogFragment();
		//newFragment.setPlayerNum(fragment.getPlayerNum());
	    newFragment.show(getFragmentManager(), "");
	}
		
	public void backButton( View view  ){
		finish();
	}
	
	@Override
	public void onDialogClose() {
		PlaceholderFragment fragment = (PlaceholderFragment)getFragmentManager()
				.findFragmentById(R.id.container);
		fragment.Setup();
	}
}
