package com.book.simplegameengine_v4;

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
	
	protected SGInputPublisher mInputPublisher = null;
	private SGPreferences mPreferences;
	
	public enum SGOrientation 
	{
		LANDSCAPE,
		PORTRAIT
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		
		mPreferences = new SGPreferences(this);
	}
	
	@Override 
	protected void onDestroy() 
	{ 
		super.onDestroy();
		Log.v(TAG, "onDestroy() chamado.");
	}
	@Override 
	protected void onPause() 
	{ 
		super.onPause();
		Log.v(TAG, "onPause() chamado.");
	}
	
	@Override 
	protected void onRestart() 
	{ 
		super.onRestart();
		Log.v(TAG, "onRestart() chamado.");
	}
	
	@Override 
	protected void onResume() 
	{ 
		super.onResume();
		Log.v(TAG, "onResume() chamado.");
	}
	
	@Override 
	protected void onStart() 
	{ 
		super.onStart();
		Log.v(TAG, "onStart() chamado.");
	}
	
	@Override 
	protected void onStop() 
	{ 
		super.onStop();
		Log.v(TAG, "onStop() chamado.");
	}
	
	@SuppressLint("NewApi")
	public void enableFullScreen() 
	{
		Window window = getWindow();
		window.addFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN);
		window.requestFeature(Window.FEATURE_NO_TITLE);
		
		if(android.os.Build.VERSION.SDK_INT >= 19) 
		{
			window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE_STICKY);
		}
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) 
	{
		if(mInputPublisher != null) 
		{
			return mInputPublisher.onTouchEvent(event);
		}
		else 
		{
			return false;
		}
	}
	
	public void enableKeepScreenOn() 
	{
		Window window = getWindow();
		window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
	}
	
	public void setInputPublisher(SGInputPublisher inputPublisher) 
	{
		mInputPublisher = inputPublisher;
	}
	
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

	public SGPreferences getPreferences() { return mPreferences; }
	public SGInputPublisher getInputPublisher() { return mInputPublisher; }
}

