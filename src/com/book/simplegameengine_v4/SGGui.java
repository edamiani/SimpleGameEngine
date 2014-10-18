package com.book.simplegameengine_v4;

import android.graphics.Point;
import android.graphics.PointF;

import com.book.simplegameengine_v4.SGWidget.Alignment;

public class SGGui 
{
	private SGWidgetButton 		mCurrentButton = null;
	private SGRenderer			mRenderer;
	private SGWidgetContainer	mRoot;
	private PointF				mTempPosition = new PointF();
	
	public SGGui(SGRenderer renderer, Point sceneDimensions) 
	{
		mRenderer = renderer;
		PointF position = new PointF(0, 0);
		PointF dimensions = new PointF(sceneDimensions.x, sceneDimensions.y);
		mRoot = new SGWidgetContainer(Alignment.Left, position, dimensions);
		mRoot.setSceneDimensions(sceneDimensions);
	}
	
	public boolean injectDown(PointF position) 
	{
		return mRoot.injectDown(_screenToScene(position));
	}
	
	public boolean injectUp(PointF position) 
	{
		boolean result = mRoot.injectUp(_screenToScene(position));
		
		if(result == false && mCurrentButton != null) 
		{
			mCurrentButton.reset();
			mCurrentButton = null;
		}
		
		return result;
	}
	
	public void render() 
	{
		mRoot.render(mRenderer);
	}
	
	public void update() 
	{
		mRoot.update();
	}
	
	public SGWidgetButton getCurrentButton() { return mCurrentButton; }	
	public SGWidgetContainer getRoot() { return mRoot; }
	
	public void setCurrentButton(SGWidgetButton currentButton) { mCurrentButton = currentButton; }
	
	private PointF _screenToScene(PointF position) 
	{
		Point offsetFromOrigin = mRenderer.getViewport().getOffsetFromOrigin();
		PointF scalingFactor = mRenderer.getViewport().getScalingFactor();
		
		mTempPosition.x = (position.x - offsetFromOrigin.x) / scalingFactor.x;
		mTempPosition.y = (position.y - offsetFromOrigin.y) / scalingFactor.y;
		
		return mTempPosition;
	}
}
