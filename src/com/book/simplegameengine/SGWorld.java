package com.book.simplegameengine;

import android.graphics.Point;
import android.graphics.RectF;

public class SGWorld 
{
	private SGView mView;
	private Point mWorldDimensions;
	
	public SGWorld(Point worldDimensions) 
	{ 
		mWorldDimensions = new Point(worldDimensions);
	}
	
	public boolean collisionTest(RectF R1, RectF R2)
	{
		return R1.left < R2.right &&
	   	   	   R1.right > R2.left &&
	  	   	   R1.top < R2.bottom &&
	  	   	   R1.bottom > R2.top;
	}
	
	public void createScene() { }
	
	public void registerEntity(SGEntity entity) { }
	
	public void step(float elapsedTimeInSeconds) { }
	
	public SGView getView() { return mView; }
	public Point getWorldDimensions() { return mWorldDimensions; }
	
	public void setView(SGView view) { mView = view; }
}
