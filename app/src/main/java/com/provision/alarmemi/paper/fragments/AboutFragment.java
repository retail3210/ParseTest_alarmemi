package com.provision.alarmemi.paper.fragments;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.provision.alarmemi.paper.R;

public class AboutFragment extends BaseFragment {

	ViewGroup root;

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle bundle) {

        root = (ViewGroup) inflater.inflate(R.layout.about, null);

        mActivity = (FragmentChangeActivity)getActivity();
        menu = mActivity.getSlidingMenu();

        mActivity.setOnLifeCycleChangeListener(this);

		ViewTreeObserver vto = root.getViewTreeObserver();
		vto.addOnGlobalLayoutListener(this);

		final ImageView moreAlarm = (ImageView) root.findViewById(R.id.more_alarm);
		FragmentChangeActivity.moreAlarm = moreAlarm;
		moreAlarm.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				if (menu.isMenuShowing()) {
					menu.showContent();
				} else {
					menu.showMenu(true);
				}
			}
		});
		// Make the entire view selected when focused.
		moreAlarm.setOnFocusChangeListener(new View.OnFocusChangeListener() {
			public void onFocusChange(View v, boolean hasFocus) {
				v.setSelected(hasFocus);
			}
		});

		View.OnClickListener back_click = new View.OnClickListener() {
			public void onClick(View v) {
				mActivity.switchContent(new MainFragment());
			}
		};
		ImageView b = (ImageView) root.findViewById(R.id.back);
		b.setOnClickListener(back_click);

		b = (ImageView) root.findViewById(R.id.logo);
		b.setOnClickListener(back_click);

		root.findViewById(R.id.visit_web).setOnClickListener(
				new View.OnClickListener() {
					@Override
					public void onClick(View v) {
						Uri uri = Uri.parse("http://www.provisionmod.com/");
						Intent intent = new Intent(Intent.ACTION_VIEW, uri);
						startActivity(intent);
					}
				});

		FragmentChangeActivity.OnNotifyArrived.sendEmptyMessage(0);
		return root;
	}

	@Override
	public void onWindowFocusChanged(boolean hasFocus) {
		// Set minimum height of listbox
		LinearLayout listbox = (LinearLayout) root.findViewById(R.id.list_box);
		listbox.setMinimumHeight(root.findViewById(R.id.layout_root)
				.getHeight()
				- root.findViewById(R.id.title_bar).getHeight()
				- root.findViewById(R.id.bkg).getHeight());
	}

	@Override
	public void onGlobalLayout() {
		onWindowFocusChanged(true);
		ViewTreeObserver obs = root.getViewTreeObserver();
		obs.removeGlobalOnLayoutListener(this);
	}
}
