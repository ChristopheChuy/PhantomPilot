package com.iut63.projet21.phamtom_pilot.views;

import android.app.ActionBar;
import android.app.ActionBar.Tab;
import android.app.Dialog;
import android.app.FragmentTransaction;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;

import com.iut63.projet21.phamtom_pilot.R;

import java.util.ArrayList;

import dji.sdk.api.DJIDrone;
import dji.sdk.api.DJIDroneTypeDef;
import dji.sdk.api.DJIError;
import dji.sdk.interfaces.DJIGeneralListener;
import dji.sdk.interfaces.DJIReceivedVideoDataCallBack;
import dji.sdk.widget.DjiGLSurfaceView;

public class MainActivity extends FragmentActivity {

	android.support.v4.view.ViewPager ViewPager;
	TabsAdapter TabsAdapter;

	private void onInitSDK(){

		DJIDrone.initWithType(this.getApplicationContext(), DJIDroneTypeDef.DJIDroneType.DJIDrone_Phantom3_Advanced);
		connect();
	}

	private void connect(){
		if(!DJIDrone.connectToDrone()){
			afficherDialog();
		}
	}


	@Override
	protected void onDestroy() {
		DJIDrone.disconnectToDrone();
		super.onDestroy();
	}

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		onInitSDK();
		new Thread(){
			public void run() {
				try {
					DJIDrone.checkPermission(getApplicationContext(), new DJIGeneralListener() {

						@Override
						public void onGetPermissionResult(int result) {
							// TODO Auto-generated method stub
							Log.e("Permission", "onGetPermissionResult = "+result);
							Log.e("Permission", "onGetPermissionResultDescription = "+DJIError.getCheckPermissionErrorDescription(result));
						}
					});
				} catch (Exception e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}.start();




		ViewPager = new ViewPager(this);
		ViewPager.setId(R.id.pager);

		//final ActionBar bar = getActionBar();
		//bar.setNavigationMode(ActionBar.NAVIGATION_MODE_TABS);
		TabsAdapter = new TabsAdapter(this, ViewPager);
		TabsAdapter.addTab(FirstPage.class, null);
		TabsAdapter.addTab(SecondPage.class, null);
		ViewPager.setCurrentItem(0);
		setContentView(ViewPager);

	}


	private void afficherDialog(){
		final Dialog dialog = new Dialog(this);
		dialog.setContentView(R.layout.activity_main);
		dialog.setTitle("Erreur de connection");
		LinearLayout l=new LinearLayout(this);
		l.setOrientation(LinearLayout.HORIZONTAL);
		Button button = new Button(this);
		button.setText("Réessayer");
		l.addView(button);
		dialog.addContentView(l, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT));
		button.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				connect();
				dialog.dismiss();
			}
		});
		dialog.show();
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);

	}

	// create TabsAdapter to create tabs and behavior
	public static class TabsAdapter extends FragmentPagerAdapter implements
			ActionBar.TabListener, android.support.v4.view.ViewPager.OnPageChangeListener {

		private final Context mContext;
		private final ActionBar mActionBar;
		private final android.support.v4.view.ViewPager mViewPager;
		private final ArrayList<TabInfo> mTabs = new ArrayList<TabInfo>();

		static final class TabInfo {
			private final Class<?> clss;
			private final Bundle args;

			TabInfo(Class<?> _class, Bundle _args) {
				clss = _class;
				args = _args;
			}
		}

		public TabsAdapter(FragmentActivity activity, android.support.v4.view.ViewPager pager) {
			super(activity.getSupportFragmentManager());
			mContext = activity;
			mActionBar = activity.getActionBar();
			mViewPager = pager;
			mViewPager.setAdapter(this);
			mViewPager.setOnPageChangeListener(this);
		}

		public void addTab(Class<?> clss, Bundle args) {
			TabInfo info = new TabInfo(clss, args);
			mTabs.add(info);
			notifyDataSetChanged();

		}

		@Override
		public void onPageScrollStateChanged(int state) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageScrolled(int position, float positionOffset,
				int positionOffsetPixels) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onPageSelected(int position) {
			// TODO Auto-generated method stub
			//mActionBar.setSelectedNavigationItem(position);
		}

		@Override
		public void onTabReselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub

		}

		@Override
		public void onTabSelected(Tab tab, FragmentTransaction ft) {
			Object tag = tab.getTag();
			for (int i = 0; i < mTabs.size(); i++) {
				if (mTabs.get(i) == tag) {
					mViewPager.setCurrentItem(i);
				}
			}
		}

		@Override
		public void onTabUnselected(Tab tab, FragmentTransaction ft) {
			// TODO Auto-generated method stub

		}

		@Override
		public Fragment getItem(int position) {
			TabInfo info = mTabs.get(position);
			return Fragment.instantiate(mContext, info.clss.getName(),
					info.args);
		}

		@Override
		public int getCount() {
			return mTabs.size();
		}

	}


}