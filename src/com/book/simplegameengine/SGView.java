package com.book.simplegameengine;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Point;
import android.view.View;

public class SGView extends View
{
	private SGImageFactory mImageFactory;
	private SGRenderer 	mRenderer;
	private SGStepwatch	mStepwatch = new SGStepwatch();
	private Point 		mViewDimensions;
	
	public SGView(Context context)
	{
		super(context);
		
		mImageFactory = new SGImageFactory(context);
		mRenderer = new SGRenderer(this);
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
	
	public void onEntityCreated(SGEntity entity) { }
	
	@Override
	protected void onSizeChanged(int width, int height, int oldWidth, int oldHeight) 
	{
		mViewDimensions = new Point(width, height);
		setup();
	}
	
	public void setup() { }

	public SGImageFactory	getImageFactory() { return mImageFactory; }
	public SGRenderer 		getRenderer() { return mRenderer; }
	public Point 			getViewDimensions() { return mViewDimensions; }
}
