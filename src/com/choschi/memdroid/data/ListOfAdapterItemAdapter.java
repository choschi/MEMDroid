package com.choschi.memdroid.data;

import java.util.List;

import com.choschi.memdroid.R;
import com.choschi.memdroid.interfaces.AdapterItem;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ListOfAdapterItemAdapter extends ArrayAdapter<AdapterItem> {

	private List<AdapterItem> items;
	
	public ListOfAdapterItemAdapter (Context context, int textViewResourceId, List<AdapterItem> items) {
        super(context, textViewResourceId, items);
        this.items = items;
	}
	
	
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            
            if (v == null) {
                LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.study_row, null);
            }
            
            AdapterItem o = items.get(position);
            if (o != null) {
	            TextView tt = (TextView) v.findViewById(R.id.studyName);
	            tt.setText(o.toString());
            }
            return v;
    }

}
