package com.book.simplegameengine_v4;

import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;

public class SGViewport 
{
	public enum ScalingMode 
	{
		FULL_SCREEN,
		FULL_SCREEN_KEEP_ORIGINAL_ASPECT,
		INTEGER_RATIO,
		ORIGINAL
	}
	
	private Rect 	mDrawingArea = new Rect();
	private Point 	mOffsetFromOrigin = new Point();
	private PointF 	mScalingFactor = new PointF();

	public SGViewport(Point sceneDimensions, Point screenDimensions, ScalingMode scalingMode) 
	{			
		if(scalingMode == ScalingMode.FULL_SCREEN) 
		{
			mScalingFactor.set((float)screenDimensions.x / (float)sceneDimensions.x, 
							   (float)screenDimensions.y / (float)sceneDimensions.y);
		}
		else if(scalingMode == ScalingMode.FULL_SCREEN_KEEP_ORIGINAL_ASPECT) 
		{
			mScalingFactor.set((float)screenDimensions.x / (float)sceneDimensions.x, 
							   (float)screenDimensions.y / (float)sceneDimensions.y);
			
			if(mScalingFactor.x > mScalingFactor.y) 
			{
				mScalingFactor.x = mScalingFactor.y;
			}
			else 
			{
				mScalingFactor.y = mScalingFactor.x;
			}
		}
		else if(scalingMode == ScalingMode.INTEGER_RATIO) 
		{
			mScalingFactor.set((float)screenDimensions.x / (float)sceneDimensions.x, 
							   (float)screenDimensions.y / (float)sceneDimensions.y);
			
			if(mScalingFactor.x >= 1 && mScalingFactor.y >= 1) 
			{
				if(mScalingFactor.x > mScalingFactor.y) 
				{
					mScalingFactor.y = (int)(mScalingFactor.y);
					mScalingFactor.x = mScalingFactor.y;
				}
				else 
				{
					mScalingFactor.x = (int)(mScalingFactor.x);
					mScalingFactor.y = mScalingFactor.x;
				}
			}
		}
		else // ScalingMode.ORIGINAL
		{
			mScalingFactor.x = 1;
			mScalingFactor.y = 1;
		}

		mOffsetFromOrigin.x = (int) ((screenDimensions.x  - (sceneDimensions.x * mScalingFactor.x)) / 2);
		mOffsetFromOrigin.y = (int) ((screenDimensions.y - (sceneDimensions.y * mScalingFactor.y)) / 2);

		mDrawingArea.left = mOffsetFromOrigin.x;
		mDrawingArea.top = mOffsetFromOrigin.y;
		mDrawingArea.right = (int) ((sceneDimensions.x * mScalingFactor.x) + mOffsetFromOrigin.x);
		mDrawingArea.bottom = (int) ((sceneDimensions.y * mScalingFactor.y) + mOffsetFromOrigin.y);
	}
	
	public Rect		getDrawingArea() { return mDrawingArea; }
	public Point 	getOffsetFromOrigin() { return mOffsetFromOrigin; }
	public PointF 	getScalingFactor() { return mScalingFactor; }

}
