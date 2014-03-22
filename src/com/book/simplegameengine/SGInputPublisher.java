package com.book.simplegameengine;

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

	@Override
	public boolean onDown(MotionEvent event)
	{
		/*int action = event.getActionMasked(); 
		
		if(action == MotionEvent.ACTION_DOWN ||
		   action == MotionEvent.ACTION_POINTER_DOWN)
		{
			for (int i = 0; i < event.getPointerCount(); i++)
			{				
				for(int j = 0; j < mSubscribers.size(); j++)
					mSubscribers.get(j).onDown(event);
			}
		}
		else if(action == MotionEvent.ACTION_UP ||
				action == MotionEvent.ACTION_POINTER_UP)
		{
			for (int i = 0; i < event.getPointerCount(); i++)
			{				
				for(int j = 0; j < mSubscribers.size(); j++)
					mSubscribers.get(j).onUp(event);
			}
		}*/
					
		for(int i = 0; i < mSubscribers.size(); i++)
			mSubscribers.get(i).onDown(event);
		
		return true;
	}

	@Override
	public boolean onFling(MotionEvent downEvent, MotionEvent moveEvent, float velocityX, float velocityY)
	{
		/*for(int i = 0; i < mSubscribers.size(); i++)
			mSubscribers.get(i).onFling(downEvent, moveEvent, velocityX, velocityY);*/
		
		return true;
	}

	@Override
	public void onLongPress(MotionEvent event)
	{
		/*for(int i = 0; i < mSubscribers.size(); i++)
			mSubscribers.get(i).onLongPress(event);*/
	}

	@Override
	public boolean onScroll(MotionEvent downEvent, MotionEvent moveEvent, float distanceX, float distanceY)
	{	
		for(int i = 0; i < mSubscribers.size(); i++)
			mSubscribers.get(i).onScroll(downEvent, moveEvent, distanceX, distanceY);
		
		return true;
	}

	@Override
	public void onShowPress(MotionEvent event)
	{
		/*for(int i = 0; i < mSubscribers.size(); i++)
			mSubscribers.get(i).onShowPress(event);*/
	}

	@Override
	public boolean onSingleTapUp(MotionEvent event)
	{
		/*for(int i = 0; i < mSubscribers.size(); i++)
			mSubscribers.get(i).onTap(event);*/
		
		return true;
	}
	
	public boolean onTouchEvent(MotionEvent event)
	{
		int action = event.getAction();
		if(action == MotionEvent.ACTION_UP)
		{
			for(int i = 0; i < mSubscribers.size(); i++)
				mSubscribers.get(i).onUp(event);
		}
		
		return mGestureDetector.onTouchEvent(event);
	}
}
