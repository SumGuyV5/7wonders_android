package com.richardallenonline.wonders.ui.Activity;

import com.richardallenonline.wonders.R;
import com.richardallenonline.wonders.WondersApp;
import com.richardallenonline.wonders.dataset.PlayersDataset;
import com.richardallenonline.wonders.dataset.PlayerScoreDataset;
import com.richardallenonline.wonders.ui.Dialog.MilitaryDialogFragment;
import com.richardallenonline.wonders.ui.Dialog.WondersDialogFragment;

import android.app.Activity;
import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

public class MainScoreCardActivity extends Activity
	implements MilitaryDialogFragment.MilitaryDialogListener {
	PlaceholderFragment fragment = null;

	private final int[] buttonsidArray = {R.id.buttonPlayer1, R.id.buttonPlayer2,
			R.id.buttonPlayer3, R.id.buttonPlayer4, R.id.buttonPlayer5, R.id.buttonPlayer6,
			R.id.buttonPlayer7, R.id.buttonPlayer8 };

	private WondersApp app = null;
		
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_score_card);
				
		if (savedInstanceState == null) {
			getFragmentManager().beginTransaction()
					.add(R.id.container, new PlaceholderFragment()).commit();
		}

		app = (WondersApp)getApplication();
	}
	
	@Override
	public void onResume() {
		super.onResume();

		fragment = (PlaceholderFragment)getFragmentManager().findFragmentById(R.id.container);
		fragment.Refresh();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.score_card, menu);
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
		private final int[] layoutidArray = { R.id.layoutPlayer1, R.id.layoutPlayer2,
				R.id.layoutPlayer3, R.id.layoutPlayer4, R.id.layoutPlayer5, R.id.layoutPlayer6,
				R.id.layoutPlayer7, R.id.layoutPlayer8 };
		private final int[] editTextidArray = { R.id.editTextPlayer1, R.id.editTextPlayer2,
				R.id.editTextPlayer3, R.id.editTextPlayer4, R.id.editTextPlayer5,
				R.id.editTextPlayer6, R.id.editTextPlayer7, R.id.editTextPlayer8 };
		private final int[] textidArray = { R.id.textViewPlayer1Score, R.id.textViewPlayer2Score,
				R.id.textViewPlayer3Score, R.id.textViewPlayer4Score, R.id.textViewPlayer5Score,
				R.id.textViewPlayer6Score, R.id.textViewPlayer7Score, R.id.textViewPlayer8Score };

		private View mainView = null;

		private WondersApp app = null;

		public PlaceholderFragment() {
		}

		@Override
		public View onCreateView(LayoutInflater inflater, ViewGroup container,
								 Bundle savedInstanceState) {
			mainView = inflater.inflate(R.layout.fragment_score_card, container, false);

			app = (WondersApp)getActivity().getApplication();

			PlayerCountChange();

			return mainView;
		}
		
		@Override
		public void onResume() {
			super.onResume();
			Refresh();
		}

		public void Refresh() {
			for (int id = 0; id < app.getPlayerCount(); id++) {
				TextView txt = mainView.findViewById(textidArray[id]);
				txt.setText(((Integer)app.getPlayerScoreData().get(id).getFinalScore()).toString());
			}			
		}

		private void PlayerCountChange() {
			Hide();
			Show();
			UpdateNames();
		}
		
		private void Hide() {
			for ( int id: layoutidArray) {
				LinearLayout ll = mainView.findViewById(id);
				ll.setVisibility(View.INVISIBLE);
			}
		}
		
		private void Show() {
			for (int id = 0; id < app.getPlayerCount(); id++) {
                LinearLayout ll = mainView.findViewById(layoutidArray[id]);
				ll.setVisibility(View.VISIBLE);
				EditTextListener(editTextidArray[id]);
			}			
		}
		
		private void UpdateNames() {
			for (int id = 0; id < app.getPlayerCount(); id++) {
				EditText txt = mainView.findViewById(editTextidArray[id]);
				txt.setText(app.getPlayerScoreDataName(id));
			}
		}
				
		private void EditTextListener(int value) {			
			EditText myTextBox = mainView.findViewById(value);
			myTextBox.addTextChangedListener(new TextWatcher() {
				@Override
				public void afterTextChanged(Editable s) {
					EditText txt = (EditText)getActivity().getCurrentFocus();
				   
					if (txt != null) {
						for (int id = 0; id < editTextidArray.length; id++) {
							if (txt.getId() == editTextidArray[id])
								app.setPlayerScoreDataName(txt.getText().toString(), id);
							}
						}
					}
				
				@Override
				public void beforeTextChanged(CharSequence s, int start, int count, int after) {
					
				}
				
				@Override
				public void onTextChanged(CharSequence s, int start, int before, int count) {
					
				}
			});
		}
	}
	
	public void scoreClick(View view) {		
		int playerNum = 0;
		for (int count = 0; count < buttonsidArray.length; count++) {
			if (buttonsidArray[count] == view.getId()) {
				playerNum = count;
				break;
			}
		}
		
		PlayerScoreDataset tmp = app.getPlayerScoreData().get(playerNum);
		if (tmp.getPlayerKey() == -1) {
			PlayersDataset player = new PlayersDataset(-1, app.getName(tmp), 0);
			player.setId(app.db().Add(player));
			tmp.setPlayerKey(player.getId());
			app.getPlayersData().add(player);
		} else {
			for (PlayersDataset player: app.getPlayersData()) {
				if (player.getId() == tmp.getPlayerKey()) {
					app.db().Update(player);
					break;
				}
			}
		}
		
		Intent intent = new Intent(this, PlayerScoreCardActivity.class);
		intent.putExtra("PlayerNumber", playerNum);
		startActivity(intent);		
	}
	
	public void imagePress(View view) {
		int age = 1;
		switch(view.getId()) {
		case R.id.imageViewAge1:
			age = 1;
			break;
		case R.id.imageViewAge2:
			age = 2;
			break;
		case R.id.imageViewAge3:
			age = 3;
			break;
		}
		MilitaryDialogFragment newFragment = new MilitaryDialogFragment();
	    newFragment.show(getFragmentManager(), "");
	    newFragment.setAge(age);
	}
	
	public void reorderPress(View view) {
		Intent intent = new Intent(this, SortableListViewActivity.class);
		startActivity(intent);	
	}

	public void wondersPress(View view) {
		WondersDialogFragment newFragment = new WondersDialogFragment();
		newFragment.show(getFragmentManager(), "");
	}
	
	@Override
	public void onDialogClose() {
		
	}
	
	@Override
	public void onDialogNext(int age, int player) {
		if (player != 0) {
			MilitaryDialogFragment newFragment = new MilitaryDialogFragment();
		    newFragment.show(getFragmentManager(), "");
		    newFragment.setAge(age);
		    newFragment.setPlayer(player, app.getPlayerScoreData().size());
		} else {
			PlaceholderFragment fragment = (PlaceholderFragment)getFragmentManager()
					.findFragmentById(R.id.container);
			fragment.Refresh();
		}
	}
}
