package com.book.simplegameengine;

import android.graphics.PointF;

public class SGFont 
{
	private PointF		mDimensions = new PointF();
	private SGTileset 	mFontTileSet;
	private PointF		mTempDimensions = new PointF();
	
	public SGFont(SGTileset tileset, PointF fontDimensions)
	{
		mFontTileSet = tileset;
		mDimensions.set(fontDimensions);
	}
	
	public PointF measureText(SGText text)
	{
		mTempDimensions.x = text.getLength() * mDimensions.x;
		mTempDimensions.y = mDimensions.y;
		
		return mTempDimensions;
	}
	
	public PointF getDimensions() { return mDimensions; }
	public SGTileset getTileSet() { return mFontTileSet; }
}
