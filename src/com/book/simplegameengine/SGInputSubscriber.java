package com.book.simplegameengine;

import android.view.MotionEvent;

public interface SGInputSubscriber 
{
	public void onDown(MotionEvent event);
	
	//public void onFling(MotionEvent downEvent, MotionEvent moveEvent, float velocityX, float velocityY);
	
	//public void onLongPress(MotionEvent event);
	
	public void onScroll(MotionEvent downEvent, MotionEvent moveEvent, float distanceX, float distanceY);
	
	//public void onShowPress(MotionEvent event);
	
	public void onUp(MotionEvent event);
	
	//public void onTap(MotionEvent event);
}