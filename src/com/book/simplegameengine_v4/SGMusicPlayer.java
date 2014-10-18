package com.book.simplegameengine_v4;

import java.io.IOException;

import android.content.Context;
import android.content.res.AssetFileDescriptor;
import android.media.MediaPlayer;

public class SGMusicPlayer 
{
	private Context 	mContext = null;
	private MediaPlayer mMediaPlayer = null;
	
	private boolean mHasInitialized = false;
	private boolean mIsPaused = false;
	private boolean mIsPlaying = false;
	
	public SGMusicPlayer(Context context) 
	{
		mContext = context;
		
		mMediaPlayer = new MediaPlayer();
	}
	
	public void loadMusic(String filename) 
	{
		if(mHasInitialized == true) 
		{
			mMediaPlayer.reset();
		}
		try 
		{
			AssetFileDescriptor descriptor;
			descriptor = mContext.getAssets().openFd(filename);
			mMediaPlayer.setDataSource(descriptor.getFileDescriptor(), 
									   descriptor.getStartOffset(), 
									   descriptor.getLength());
		} 
		catch(IOException e) {
			e.printStackTrace();
		}
		mHasInitialized = true;
	}

	public void play(boolean enableLooping, float leftVolume, float rightVolume)
	{
		if(mHasInitialized == true) 
		{
			try 
			{
				mMediaPlayer.prepare();
			}
			catch(IOException e) 
			{
				e.printStackTrace();
			}
			
			mMediaPlayer.setVolume(leftVolume, rightVolume);
			
			if(enableLooping == true)
			{
				mMediaPlayer.setLooping(true);
			}
			
			mMediaPlayer.start();
			mIsPaused = false;
			mIsPlaying = true;
		}
	}
	
	public void pause() 
	{
		mMediaPlayer.pause();
		mIsPaused = true;
		mIsPlaying = false;
	}

	public void stop() 
	{
		if(mHasInitialized == true)
		{
			mMediaPlayer.seekTo(0);
			mMediaPlayer.stop();
			mIsPaused = false;
			mIsPlaying = false;
		}
	}
	
	public void resume()
	{
		if(mIsPaused == true)
		{
			mMediaPlayer.start();
			mIsPaused = false;
			mIsPlaying = true;
		}
	}

	public void reset() 
	{
		mMediaPlayer.reset();
		mHasInitialized = false;
		mIsPaused = false;
		mIsPlaying = false;
	}
	
	public void release() 
	{
		mMediaPlayer.release();
		mMediaPlayer = null;
		mHasInitialized = false;
		mIsPaused = false;
		mIsPlaying = false;
	}
	
	public boolean hasInitialized() { return mHasInitialized; }
	public boolean isPaused() { return mIsPaused; }
	public boolean isPlaying() { return mIsPlaying; }
	
	public void setVolume(float leftVolume, float rightVolume) 
	{
		mMediaPlayer.setVolume(leftVolume, rightVolume);
	}

}
