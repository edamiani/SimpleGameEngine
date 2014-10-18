package com.book.simplegameengine_v4;

import java.util.ArrayList;

import android.content.Context;
import android.view.GestureDetector;
import android.view.MotionEvent;

public class SGInputPublisher implements GestureDetector.OnGestureListener 
{
	protected GestureDetector mGestureDetector = null;
	protected ArrayList<SGInputSubscriber> mSubscribers = new ArrayList<SGInputSubscriber>();
	
	public SGInputPublisher(Context context) 
	{
		mGestureDetector = new GestureDetector(context, this);
	}
	
	public void registerSubscriber(SGInputSubscriber listener) 
	{
		mSubscribers.add(listener);
	}
	
	public boolean unregisterSubscriber(SGInputSubscriber listener) 
	{
		return mSubscribers.remove(listener);
	}

	public boolean onTouchEvent(MotionEvent event) 
	{
		int action = event.getActionMasked();
		if(action == MotionEvent.ACTION_UP) 
		{
			for(int i = 0; i < mSubscribers.size(); i++)
			{
				mSubscribers.get(i).onUp(event);
			}
			
			return true;
		}
		
		return mGestureDetector.onTouchEvent(event);
	}

	@Override
	public boolean onDown(MotionEvent event) 
	{
		for(int i = 0; i < mSubscribers.size(); i++)
		{
			mSubscribers.get(i).onDown(event);
		}
		
		return true;
	}
	
	@Override
	public boolean onFling(MotionEvent downEvent, MotionEvent moveEvent, 
							 float velocityX, float velocityY) 
	{
		return true;
	}
	
	@Override
	public void onLongPress(MotionEvent event) 
	{
	}
	
	@Override
	public boolean onScroll(MotionEvent downEvent, MotionEvent moveEvent, 
							   float distanceX, float distanceY) 
	{
		for(int i = 0; i < mSubscribers.size(); i++)
		{
			mSubscribers.get(i).onScroll(downEvent, moveEvent, distanceX, distanceY);
		}
		
		return true;
	}
	
	@Override
	public void onShowPress(MotionEvent event) 
	{
	}
	
	@Override
	public boolean onSingleTapUp(MotionEvent event) 
	{
		return true;
	}

}
