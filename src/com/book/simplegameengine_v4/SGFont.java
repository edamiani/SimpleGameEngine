package com.book.simplegameengine_v4;

import android.graphics.PointF;

public class SGFont 
{
	private PointF		mFontDimensions = new PointF();
	private SGTileset 	mFontTileSet;
	private PointF		mTempDimensions = new PointF();
	
	public SGFont(SGTileset tileset, PointF fontDimensions) 
	{
		mFontTileSet = tileset;
		mFontDimensions.set(fontDimensions);
	}
	
	public PointF measureText(SGText text) 
	{
		mTempDimensions.x = text.getLength() * mFontDimensions.x;
		mTempDimensions.y = mFontDimensions.y;
		
		return mTempDimensions;
	}
	
	public PointF getFontDimensions() { return mFontDimensions; }
	public SGTileset getTileSet() { return mFontTileSet; }
}
