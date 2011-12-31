package com.choschi.memdroid.data;

import java.util.List;

import com.choschi.memdroid.R;
import com.choschi.memdroid.data.form.Form;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

public class ListOfFormsAdapter extends ArrayAdapter<Form> {

	private List<Form> forms;
	
	public ListOfFormsAdapter (Context context, int textViewResourceId, List<Form> items) {
        super(context, textViewResourceId, items);
        this.forms = items;
	}
	
	
	@Override
    public View getView(int position, View convertView, ViewGroup parent) {
            View v = convertView;
            
            if (v == null) {
                LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                v = vi.inflate(R.layout.study_row, null);
            }
            
            Form o = forms.get(position);
            if (o != null) {
	            TextView tt = (TextView) v.findViewById(R.id.studyName);
	            tt.setText(o.getName());
            }
            return v;
    }

}
