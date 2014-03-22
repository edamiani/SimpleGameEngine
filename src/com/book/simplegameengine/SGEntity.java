package com.book.simplegameengine;

import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.RectF;

public class SGEntity 
{
	public enum DebugDrawingStyle
	{
		FILLED,
		OUTLINE
	};
	
	private RectF 	mBoundingBox = new RectF();
	private String	mCategory;
	private int 	mDebugColor = Color.RED;
	private DebugDrawingStyle mDebugDrawingStyle = DebugDrawingStyle.FILLED;
	private PointF 	mDimensions = new PointF();
	private int		mFlags;
	private int		mId;
	private boolean	mIsActive = true;
	private PointF 	mPosition = new PointF();
	private SGWorld mWorld;
	
	public SGEntity(SGWorld world, String category, PointF position, PointF dimensions)
	{
		mWorld = world;
		mCategory = category;
		mPosition.set(position);
		mDimensions.set(dimensions);
		
		_updateBoundingBox();
	}
	
	public SGEntity(SGWorld world, int id, String category, PointF position, PointF dimensions)
	{
		mWorld = world;
		mId = id;
		mCategory = category;
		mPosition.set(position);
		mDimensions.set(dimensions);
		
		_updateBoundingBox();
	}
	
	public boolean hitTest(SGEntity entity)
	{
		RectF boundingBox2 = entity.getBoundingBox();
		
		if(mBoundingBox.left < boundingBox2.right && 
		   boundingBox2.left < mBoundingBox.right && 
		   mBoundingBox.top < boundingBox2.bottom && 
		   boundingBox2.top < mBoundingBox.bottom)
		{
			//mHasHit = true;
			return true;
		}
		else
		{
			//mHasHit = false;
			return false;
		}
	}
	
	public void move(float offsetX, float offsetY)
	{
		mPosition.x += offsetX;
		mPosition.y += offsetY;
		
		_updateBoundingBox();
	}
	
	public void step(float timeElapsedInSeconds) {}
	
	//public boolean	hasHit() { return mHasHit; }
	public boolean	isActive() { return mIsActive; }
	
	public void addFlags(int flags) 
	{ 
		mFlags |= flags;
	}
	
	public boolean hasFlag(int flag)
	{
		return (mFlags & flag) != 0;
	}
	
	public void removeFlags(int flags) 
	{
		flags = ~flags;
		mFlags &= flags;
	}
	
	public RectF 	getBoundingBox() {	return mBoundingBox; }
	public String	getCategory() { return mCategory; }
	public int 		getDebugColor() { return mDebugColor; }
	public DebugDrawingStyle	
					getDebugDrawingStyle() { return mDebugDrawingStyle; }
	public int		getFlags() { return mFlags; }
	public PointF 	getDimensions() { return mDimensions; }
	public int		getId() { return mId; }
	public PointF 	getPosition() { return mPosition; }
	public SGWorld	getWorld() { return mWorld; }
	
	public void 	setDebugColor(int color) { mDebugColor = color; }
	public void 	setDebugDrawingStyle(DebugDrawingStyle drawingStyle) { mDebugDrawingStyle = drawingStyle; }
	public void 	setIsActive(boolean isActive) { mIsActive = isActive; }
	
	public void setPosition(float x, float y) 
	{
		mPosition.set(x, y);
		
		_updateBoundingBox();
	}
	
	public void setPosition(PointF position) 
	{
		mPosition.set(position);
		
		_updateBoundingBox();
	}
	
	public void setDimensions(float x, float y) 
	{
		mDimensions.set(x, y);
		
		_updateBoundingBox();
	}
	
	public void setDimensions(PointF dimensions) 
	{
		mDimensions.set(dimensions);
		
		_updateBoundingBox();
	}
	
	private void _updateBoundingBox()
	{
		mBoundingBox.set(mPosition.x, mPosition.y, mPosition.x + mDimensions.x, mPosition.y + mDimensions.y);
	}
}
