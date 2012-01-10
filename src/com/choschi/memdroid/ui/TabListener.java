package com.choschi.memdroid.ui;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentTransaction;

import com.choschi.memdroid.R;


public class TabListener<T extends Fragment> implements ActionBar.TabListener {



	private Fragment mFragment;
	private final Activity mActivity;
	private final String mTag;
	private final Class<T> mClass;


	public TabListener(Activity activity, String tag, Class<T> clz) {
		mActivity = activity;
		mTag = tag;
		mClass = clz;
	}

	// The following are each of the ActionBar.TabListener callbacks 

	public void onTabSelected(Tab tab, FragmentTransaction ft) {
		// Check if the fragment is already initialized
		//if (mFragment == null) {
			// If not, instantiate and add it to the activity
			mFragment = Fragment.instantiate(mActivity, mClass.getName());
			ft.add(R.id.fragmentContainer, mFragment, mTag);
			//} else {
			// If it exists, simply attach it in order to show it
			//    ft.add(mFragment);
		//}
	}

	public void onTabUnselected(Tab tab, FragmentTransaction ft) {
		if (mFragment != null) {
			// Detach the fragment, because another one is being attached
			ft.remove(mFragment);
		}
	}

	public void onTabReselected(Tab tab, FragmentTransaction ft) {
		// User selected the already selected tab. Usually do nothing.
	}
}