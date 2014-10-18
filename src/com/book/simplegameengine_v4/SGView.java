package com.book.simplegameengine_v4;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.View;

public class SGView extends View 
{
	private Point mDimensions = new Point();
	private boolean mHasStarted;
	private SGImageFactory mImageFactory;
	private SGRenderer mRenderer;
	private SGStepwatch mStepwatch = new SGStepwatch();
	
	public SGView(Context context) 
	{
		super(context);
		
		mImageFactory = new SGImageFactory(context);
		
		mRenderer = new SGRenderer();
	}

	@Override
	public void onDraw(Canvas canvas) 
	{		
		step(canvas, mStepwatch.tick());
		
		invalidate();
	}
	
	public void step(Canvas canvas, float elapsedTimeInSeconds) 
	{
	}

	@Override
	protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) 
	{
		mDimensions.set(width, height);
		
		if(!mHasStarted) 
		{
			setup();
			mHasStarted = true;
		}

	}
	
	protected void setup() 
	{
	}

	public Point getDimensions() { return mDimensions; }
	public SGImageFactory getImageFactory() { return mImageFactory; }
	public SGRenderer getRenderer() { return mRenderer; }
} 

