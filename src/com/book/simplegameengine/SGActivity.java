package com.book.simplegameengine;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

public class SGActivity extends Activity
{
	public static final String TAG = "SimpleGameEngine";
	
	private SGInputPublisher 	mInputPublisher = null;
	private SGPreferences 		mPreferences;
	
	public enum SGOrientation
	{
		LANDSCAPE,
		PORTRAIT
	}
	
	public void openDialog(int OkId, int CancelId)
	{
		
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		mPreferences = new SGPreferences(this);
	}
	
	@Override protected void onDestroy() 
	{ 
		super.onDestroy();
		Log.v(TAG, "onDestroy() called.");
	}
	
	@Override protected void onPause() 
	{ 
		super.onPause();
		Log.v(TAG, "onPause() called.");
	}
	
	@Override protected void onRestart() 
	{ 
		super.onRestart();
		Log.v(TAG, "onRestart() called.");
	}
	
	@Override protected void onResume() 
	{ 
		super.onResume();
		Log.v(TAG, "onResume() called.");
	}
	
	@Override protected void onStart() 
	{ 
		super.onStart();
		Log.v(TAG, "onStart() called.");
	}
	
	@Override protected void onStop() 
	{ 
		super.onStop();
		Log.v(TAG, "onStop() called.");
	}
	
	public void enableKeepScreenOn()
	{
		this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	}
	
	
	
	@SuppressLint({ "NewApi", "InlinedApi" })
	public void enableFullScreen()
	{
		this.getWindow().addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		this.getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		
		if (android.os.Build.VERSION.SDK_INT >= 19)
		{
			getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION |
															 View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event)
	{
		if(mInputPublisher != null)
			return mInputPublisher.onTouchEvent(event);
		else
			return false;
	}
	
	public SGPreferences 	getPreferences() { return mPreferences; }
	public SGInputPublisher getInputPublisher() { return mInputPublisher; }
	
	public void setOrientation(SGOrientation orientation)
	{
		switch(orientation)
		{
		case LANDSCAPE:
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE);
			break;
		case PORTRAIT:
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_PORTRAIT);
			break;
		}
	}
	
	public void setInputPublisher(SGInputPublisher inputPublisher)
	{
		mInputPublisher = inputPublisher;
	}
}