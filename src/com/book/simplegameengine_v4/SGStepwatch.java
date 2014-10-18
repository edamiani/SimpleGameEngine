package com.book.simplegameengine_v4;

import android.os.SystemClock;

public class SGStepwatch 
{
	protected long		mCurrentTime;
	protected long		mLastTime;
	protected float		mElapsedTime;
	
	protected float tick() 
	{
		if(mCurrentTime == 0) 
		{
			mLastTime = mCurrentTime = SystemClock.uptimeMillis();
		}
		else 
		{
	    	mCurrentTime = SystemClock.uptimeMillis();
		}
		
		mElapsedTime = (mCurrentTime - mLastTime) / 1000.0f;
		mLastTime = mCurrentTime;
		
		return mElapsedTime;
	}
}
