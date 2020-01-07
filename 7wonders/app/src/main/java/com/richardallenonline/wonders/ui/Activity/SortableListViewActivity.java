package com.richardallenonline.wonders.ui.Activity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.richardallenonline.wonders.WondersApp;
import com.richardallenonline.wonders.R;
import com.richardallenonline.wonders.ui.TouchInterceptor;

import android.app.ListActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class SortableListViewActivity extends ListActivity {
	
	private TouchInterceptor mList;
	
	//private SortableListViewListener mListener = null;
	
	public interface  SortableListViewListener {
    	void onDialogClose();
    }
	
	private TouchInterceptor.DropListener mDropListener = new TouchInterceptor.DropListener() {
		@Override
		public void drop(int from, int to) {
			System.out.println("Droplisten from:"+from+" to:"+to);
		
			//Assuming that item is moved up the list
		
			int direction = -1;
			int loop_start = from;
			int loop_end = to;
			//For instance where the item is dragged down the list
		
			if(from < to) {
				direction = 1;
			}		
		
			Object target = sArray[from];		
		
			for(int i=loop_start;i!=loop_end;i=i+direction){
				sArray[i] = sArray[i+direction];
			}		
		
			sArray[to] = target;		
		
			System.out.println("Changed array is:" + Arrays.toString(sArray));
			((BaseAdapter) mList.getAdapter()).notifyDataSetChanged();
		}
	};
	private Object[] sArray  = {"Item 0", "Item 1", "Item 2", 42, false, "Item 5", "Item 6"};


	/** Called when the activity is first created. */


	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		setContentView(R.layout.activity_sortable_list_view);
		
		List<String> playerNames = ((WondersApp)getApplication()).getNames();
		
		sArray = playerNames.toArray();
		
		//mListener = (SortableListViewListener)this.startActivityForResult(intent, requestCode);
		
		ArrayAdapter<Object> adp = new ArrayAdapter<Object>(this, R.layout.listrow, sArray);
		setListAdapter(adp);
		
		mList = (TouchInterceptor) getListView();
		mList.setDropListener(mDropListener);

		registerForContextMenu(mList);
	}


	@Override
	protected void onListItemClick(ListView l, View v, int position, long id) {
		String selection = sArray[position].toString();
		Toast.makeText(this, selection, Toast.LENGTH_SHORT).show();
	}
	
	public void closeClick(View view) {
		List<String> data = new ArrayList<String>();
		for (Object ojt: sArray)
			data.add((String)ojt);
		((WondersApp)getApplication()).Reorder(data);
		finish();
		//mListener.onDialogClose();
	}
}
