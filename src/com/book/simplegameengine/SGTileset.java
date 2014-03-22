package com.book.simplegameengine;

import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;

public class SGTileset
{
	protected Point		m2dNumberOfTiles = new Point();
	protected PointF	mDimensions = new PointF();
	protected Rect		mDrawableTileArea = new Rect();
	protected SGImage	mImage;
	protected Rect		mTempTileArea = new Rect();
	protected Point 	mTileDimensions = new Point(0, 0);
	
	public SGTileset(SGImage image, Point tilesNum, Rect drawableTileArea)
	{
		mDimensions.set(image.getDimensions().x, image.getDimensions().y);
		mImage = image;
		m2dNumberOfTiles.set(tilesNum.x, tilesNum.y);
		
		mTileDimensions.x = (int) mDimensions.x / m2dNumberOfTiles.x;
		mTileDimensions.y = (int) mDimensions.y / m2dNumberOfTiles.y;
		
		if(drawableTileArea != null)
		{
			mDrawableTileArea.set(drawableTileArea);
		}
		else
		{
			mDrawableTileArea.set(0, 0, mTileDimensions.x, mTileDimensions.y);
		}
	}
	
	public PointF	getDimensions() { return mDimensions; }
	public Rect		getDrawableTileArea() { return mDrawableTileArea; }
	public SGImage	getImage() { return mImage; }
	public int		get1dNumberOfTiles() { return m2dNumberOfTiles.x * m2dNumberOfTiles.y; }
	public Point 	get2dNumberOfTiles() { return m2dNumberOfTiles; }
	public Point 	getTileDimensions() { return mTileDimensions; }	
	
	public Rect getTile(int x, int y)
	{
		int left = mTileDimensions.x * x;
		int top = mTileDimensions.y * y;
		
		mTempTileArea.set(left + mDrawableTileArea.left, 
					  	  top + mDrawableTileArea.top, 
					  	  left + mDrawableTileArea.right, 
					  	  top + mDrawableTileArea.bottom);
		
		return mTempTileArea;
	}
	
	public Rect getTile(int index)
	{
		int x = index % m2dNumberOfTiles.x;
		int y = index / m2dNumberOfTiles.x;
		
		int left = mTileDimensions.x * x;
		int top = mTileDimensions.y * y;
		
		mTempTileArea.set(left + mDrawableTileArea.left, 
				  	  	  top + mDrawableTileArea.top, 
				  	  	  left + mDrawableTileArea.right, 
				  	  	  top + mDrawableTileArea.bottom);
		
		return mTempTileArea;
	}
}
