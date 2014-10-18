package com.book.simplegameengine_v4;

import android.util.Log;

public class SGTimer 
{
	protected float		mAccumulator = 0;
	protected boolean	mHasStarted = false;
	protected float		mInterval = 0;
	
	public SGTimer(float interval) 
	{
		if(interval > 0) 
		{
			mInterval = interval;
		}
		else 
		{
			Log.d("SimpleGameEngine", "SGTimer: intervalo inválido.");
		}
	}

	public void onInterval() { }
	
	public void start() { mHasStarted = true; }
	
	public void stop() { mHasStarted = false; }

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

	public void reset() { mAccumulator = 0; }
	
	public void stopAndReset() 
	{ 
		mHasStarted = false; 
		mAccumulator = 0; 
	}
	
	public float getInterval() { return mInterval; }
	
	public boolean hasStarted() { return mHasStarted; }

}
