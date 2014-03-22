package com.book.simplegameengine;

import android.util.Log;

public class SGAnimation
{
	protected float 	mAccumulator;
	protected int		mCurrentIndex;
	protected float		mFrameDuration;
	protected boolean 	mIsRunning;
	protected int		mNumberOfRepetitions;
	protected boolean	mResetAfterRunning;
	protected int[]		mTiles;
	
	public SGAnimation(int[] tiles, float frameDurationInSeconds)
	{
		if(frameDurationInSeconds < 0.0f)
		{
			Log.e("SimpleGameEngine", Float.toString(frameDurationInSeconds) + " não é um valor válido para frameDurationInSeconds.");
			throw new Error();
		}
		
		mTiles = tiles;
		mCurrentIndex = 0;
		mFrameDuration = frameDurationInSeconds;
		mResetAfterRunning = true;
	}
	
	public int step(float elapsedTimeInSeconds)
	{		
		if(mIsRunning == true && mFrameDuration > 0.0f)
		{
			mAccumulator += elapsedTimeInSeconds;
			
			while(mAccumulator > mFrameDuration)
			{
				mCurrentIndex++;
				if(mCurrentIndex == mTiles.length)
				{					
					if(mNumberOfRepetitions > 0)
					{
						if(--mNumberOfRepetitions == 0)
						{
							if(mResetAfterRunning)
							{
								stopAndReset();
							}
							else
							{
								stop();
							}
						}
						else
						{
							mCurrentIndex = 0;
						}
					}
					else if(mNumberOfRepetitions <= 0)
					{
						mCurrentIndex = 0;
					}
					/*else
					{
						if(mResetAfterRunning)
						{
							stopAndReset();
						}
						else
						{
							stop();
						}
					}*/
				}
				
				mAccumulator -= mFrameDuration;
			}
		}
		
		return mTiles[mCurrentIndex];
	}
	
	public void reset() { mCurrentIndex = 0; }
	public void start(int numberOfRepetitions) { mIsRunning = true; mNumberOfRepetitions = numberOfRepetitions; }
	public void stop() { mIsRunning = false; }
	public void stopAndReset() { mIsRunning = false; mCurrentIndex = 0; }
	
	public int getCurrentTile() { return mTiles[mCurrentIndex]; }
	
	public void setResetAfterRunning(boolean reset) { mResetAfterRunning = reset; }
}
