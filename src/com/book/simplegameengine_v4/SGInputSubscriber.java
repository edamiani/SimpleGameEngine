package com.book.simplegameengine_v4;

import android.view.MotionEvent;

public interface SGInputSubscriber 
{
	public void onDown(MotionEvent event);
	
	public void onScroll(MotionEvent downEvent, MotionEvent moveEvent, float distanceX, float distanceY);
	
	public void onUp(MotionEvent event);
}
