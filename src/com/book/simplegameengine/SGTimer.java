package com.book.simplegameengine;

import android.util.Log;

public class SGTimer 
{
	protected float		mAccumulator = 0;
	protected boolean	mHasStarted = false;
	protected float 	mInterval = 0;
	
	public SGTimer(float intervalInSeconds)
	{
		if(intervalInSeconds > 0)
		{
			mInterval = intervalInSeconds;
		}
		else
		{
			Log.d("SimpleGameEngine", "SGTimer constructor: invalid interval.");
		}
	}
	
	public void onInterval() { }
	
	public void reset() { mAccumulator = 0; }
	
	public void start() { mHasStarted = true; }
	
	public boolean step(float elapsedTimeInSeconds)
	{
		if(mHasStarted)
		{
			mAccumulator += elapsedTimeInSeconds;
			
			if(mAccumulator >= mInterval)
			{
				mAccumulator -= mInterval;
				onInterval();
				
				return true;
			}
		}
		
		return false;
	}
	
	public void stop() { mHasStarted = false; }
	
	public void stopAndReset() 
	{ 
		mHasStarted = false; 
		mAccumulator = 0; 
	}
	
	// __SGStopwatch__Getters__
	public float getInterval() { return mInterval; }
	public boolean hasStarted() { return mHasStarted; }
}
