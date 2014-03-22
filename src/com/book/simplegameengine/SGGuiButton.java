package com.book.simplegameengine;

import android.graphics.PointF;

public class SGGuiButton extends SGWidget
{
	//protected Rect		mClippingArea = new Rect();
	protected int		mCurrentTile = 0;
	protected SGGui		mGui;
	protected SGTileset	mTileset = null;
	
	public SGGuiButton(Alignment alignment, PointF position, PointF dimensions, SGTileset tileset, SGGui gui)
	{
		super("button", alignment, position, dimensions);
		
		mTileset = tileset;
		mGui = gui;
	}
	
	@Override
	public void render(SGRenderer renderer)
	{
		renderer.drawImage(mTileset.getImage(), mTileset.getTile(mCurrentTile), mArea);
	}
	
	public void reset()
	{
		mCurrentTile = 0;
	}
	
	@Override
	public boolean injectDown(PointF position)
	{
		if(mIsEnabled)
		{
			mGui.setCurrentButton(this);			
			mCurrentTile = 1;
			
			return onDown(position);
		}
		else
		{
			return false;
		}
	}
	
	@Override
	public boolean injectUp(PointF position)
	{
		if(mIsEnabled)
		{
			if(mGui.getCurrentButton() == this)
			{
				mCurrentTile = 0;
				return onUp(position);
			}
			else if(mGui.getCurrentButton() != null)
			{
				mGui.getCurrentButton().reset();
			}
			
			mGui.setCurrentButton(null);
			
			return onUp(position);
		}
		else
		{
			return false;
		}
	}
	
	@Override
	public boolean onDown(PointF position)
	{
		return true;
	}
	
	@Override
	public boolean onUp(PointF position)
	{
		return true;
	}
}
