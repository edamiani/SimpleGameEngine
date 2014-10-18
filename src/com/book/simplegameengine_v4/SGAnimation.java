package com.book.simplegameengine_v4;

import android.util.Log;

public class SGAnimation 
{
	protected float 	mAccumulator;
	protected int		mCurrentIndex;
	protected float		mFrameDuration;
	protected boolean	mHasStarted;
	protected boolean 	mIsRunning;
	protected int		mNumberOfRepetitions;
	protected boolean	mResetAfterRunning;
	protected int[]		mTiles;
	
	public SGAnimation(int[] tiles, float frameDurationInSeconds) 
	{
		if(frameDurationInSeconds < 0.0f) 
		{
			Log.e("SimpleGameEngine", Float.toString(frameDurationInSeconds) + 
									  " não é um valor válido para" +
									  " frameDurationInSeconds.");
			
			throw new Error();
		}
		
		mTiles = tiles;
		mCurrentIndex = 0;
		mFrameDuration = frameDurationInSeconds;
		mResetAfterRunning = true;
	}
	
	public void start(int numberOfRepetitions) 
	{
		mIsRunning = true;
		mHasStarted = true;
		mNumberOfRepetitions = numberOfRepetitions; 
	}
	
	public void play() 
	{ 
		if(mHasStarted)
		{
			mIsRunning = true;
		}
	}
	
	public void pause() 
	{ 
		if(mHasStarted) 
		{
			mIsRunning = false;
		}
	}
	
	public void reset() { mCurrentIndex = 0; }
	
	public void stop() 
	{
		mHasStarted = false;
		mIsRunning = false;
		
		if(mResetAfterRunning == true)
		{
			mCurrentIndex = 0;
		}
		else
		{
			mCurrentIndex = mTiles.length - 1;
		}
	}

	public int step(float elapsedTimeInSeconds) 
	{
		mAccumulator += elapsedTimeInSeconds;
		
		while(mAccumulator > mFrameDuration) 
		{
			if(mIsRunning == true && mFrameDuration > 0.0f) 
			{
				mCurrentIndex++;
				if(mCurrentIndex == mTiles.length) 
				{
					if(mNumberOfRepetitions > 0) 
					{
						if(--mNumberOfRepetitions == 0) 
						{
							stop();
						}
						else 
						{
							reset();
						}
					}
					else
					{
						reset();
					}
				}
			}
			
			mAccumulator -= mFrameDuration;
		}
		
		return mTiles[mCurrentIndex];
	}

	public int getCurrentTile() { return mTiles[mCurrentIndex]; }
	
	public void setResetAfterRunning(boolean reset) { mResetAfterRunning = reset; }

}
