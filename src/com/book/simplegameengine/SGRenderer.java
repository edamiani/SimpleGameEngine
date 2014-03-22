package com.book.simplegameengine;

import java.util.Iterator;
import java.util.LinkedList;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.graphics.Point;
import android.graphics.PointF;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.Region.Op;
import android.util.Log;
import android.util.SparseArray;

public class SGRenderer
{
	private Canvas				mCanvas;
	private Paint				mTempPaint = new Paint();
	private PointF				mTempPosition = new PointF();
	private RectF				mTempScreenCoordDestination = new RectF();
	private Rect				mTempRectSource = new Rect();
	private SGView				mView;
	private SGViewport			mViewport;
	
	public SGRenderer(SGView view)
	{
		mView = view;
	}
    
    public void beginDrawing(Canvas canvas, int backgroundColor)
	{
    	mCanvas = canvas;
    	
		mCanvas.drawColor(backgroundColor);
	}
    
    public void beginDrawing(Canvas canvas, int screenColor, int viewportColor)
	{
    	mCanvas = canvas;
		mCanvas.drawColor(screenColor);
		
		if(mViewport != null)
		{
			mCanvas.clipRect(mViewport.getDrawingArea(), Op.REPLACE);
			mCanvas.drawColor(viewportColor);
		}
	}
    
    public void drawImage(SGImage image, Rect objSource, RectF worldDestination)
	{
    	if(mViewport != null)
    	{
			Bitmap bitmap = image.getBitmap();
			
			if(bitmap != null)
			{
				Point offsetFromOrigin = mViewport.getOffsetFromOrigin();
				PointF scalingFactor = mViewport.getScalingFactor();
				
				mTempScreenCoordDestination.left = (worldDestination.left * scalingFactor.x) + offsetFromOrigin.x;
				mTempScreenCoordDestination.top = (worldDestination.top * scalingFactor.y) + offsetFromOrigin.y;
				mTempScreenCoordDestination.right = (worldDestination.right * scalingFactor.x) + offsetFromOrigin.x;
				mTempScreenCoordDestination.bottom = (worldDestination.bottom * scalingFactor.y) + offsetFromOrigin.y;
				
				mCanvas.drawBitmap(bitmap, objSource, mTempScreenCoordDestination, mTempPaint);
			}
			else
			{
				drawRect(worldDestination, Color.RED);
			}
    	}
    	else
    	{
    		Log.d("SimpleGameEngine", "SGRenderer.drawImage(): viewport is not defined!");
    	}
	}
    
    public void drawImage(SGImage image, Rect objSource, PointF position, PointF drawingDimensions)
	{
    	if(mViewport != null)
    	{
			Point imageDimensions = image.getDimensions();
			Point offsetFromOrigin = mViewport.getOffsetFromOrigin();
			PointF scalingFactor = mViewport.getScalingFactor();
			
			if(objSource == null)
			{
				mTempRectSource.set(0, 0, imageDimensions.x, imageDimensions.y);
				mTempScreenCoordDestination.set((position.x * scalingFactor.x) + offsetFromOrigin.x, 
												(position.y * scalingFactor.y) + offsetFromOrigin.y, 
												((position.x + drawingDimensions.x) * scalingFactor.x) + offsetFromOrigin.x, 
												((position.y + drawingDimensions.y) * scalingFactor.y) + offsetFromOrigin.y);
			}
			else
			{
				mTempRectSource.set(objSource.left, objSource.top, objSource.right, objSource.bottom);
				mTempScreenCoordDestination.set((position.x * scalingFactor.x) + offsetFromOrigin.x, 
												(position.y * scalingFactor.y) + offsetFromOrigin.y, 
												((position.x + drawingDimensions.x) * scalingFactor.x) + offsetFromOrigin.x, 
												((position.y + drawingDimensions.y) * scalingFactor.y) + offsetFromOrigin.y);
			}
			
			Bitmap bitmap = ((SGImage)image).getBitmap();
			mCanvas.drawBitmap(bitmap, mTempRectSource, mTempScreenCoordDestination, mTempPaint);
    	}
    	else
    	{
    		Log.d("SimpleGameEngine", "SGRenderer.drawImage(): viewport is not defined!");
    	}
	}
    
    public void drawOutlineRect(Rect worldSpaceDestination, int color)
    {
    	if(mViewport != null)
    	{
    		Point offsetFromOrigin = mViewport.getOffsetFromOrigin();
			PointF scalingFactor = mViewport.getScalingFactor();
			
			mTempScreenCoordDestination.left = (worldSpaceDestination.left * scalingFactor.x) + offsetFromOrigin.x;
			mTempScreenCoordDestination.top = (worldSpaceDestination.top * scalingFactor.y) + offsetFromOrigin.y;
			mTempScreenCoordDestination.right = (worldSpaceDestination.right * scalingFactor.x) + offsetFromOrigin.x;
			mTempScreenCoordDestination.bottom = (worldSpaceDestination.bottom * scalingFactor.y) + offsetFromOrigin.y;
			
	    	mTempPaint.setColor(color);
	    	mTempPaint.setStyle(Style.STROKE);
	    	mTempPaint.setStrokeWidth(0);
	    	
	    	mTempScreenCoordDestination.right -= 1;
	    	mTempScreenCoordDestination.bottom -= 1;
	    	mCanvas.drawRect(mTempScreenCoordDestination, mTempPaint);
    	}
    	else
    	{
    		Log.d("SimpleGameEngine", "SGRenderer.drawRect(): viewport is not defined!");
    	}
    }
    
    public void drawOutlineRect(RectF worldSpaceDestination, int color)
    {
    	if(mViewport != null)
    	{
    		Point offsetFromOrigin = mViewport.getOffsetFromOrigin();
			PointF scalingFactor = mViewport.getScalingFactor();
			
			mTempScreenCoordDestination.left = (worldSpaceDestination.left * scalingFactor.x) + offsetFromOrigin.x;
			mTempScreenCoordDestination.top = (worldSpaceDestination.top * scalingFactor.y) + offsetFromOrigin.y;
			mTempScreenCoordDestination.right = (worldSpaceDestination.right * scalingFactor.x) + offsetFromOrigin.x;
			mTempScreenCoordDestination.bottom = (worldSpaceDestination.bottom * scalingFactor.y) + offsetFromOrigin.y;
			
	    	mTempPaint.setColor(color);
	    	mTempPaint.setStyle(Style.STROKE);
	    	mTempPaint.setStrokeWidth(0);
	    	
	    	mTempScreenCoordDestination.right -= 1;
	    	mTempScreenCoordDestination.bottom -= 1;
	    	mCanvas.drawRect(mTempScreenCoordDestination, mTempPaint);
    	}
    	else
    	{
    		Log.d("SimpleGameEngine", "SGRenderer.drawRect(): viewport is not defined!");
    	}
    }
    
    public void drawRect(Rect worldSpaceDestination, int color)
    {
    	if(mViewport != null)
    	{
	    	Point offsetFromOrigin = mViewport.getOffsetFromOrigin();
			PointF scalingFactor = mViewport.getScalingFactor();
			
			mTempScreenCoordDestination.left = (worldSpaceDestination.left * scalingFactor.x) + offsetFromOrigin.x;
			mTempScreenCoordDestination.top = (worldSpaceDestination.top * scalingFactor.y) + offsetFromOrigin.y;
			mTempScreenCoordDestination.right = (worldSpaceDestination.right * scalingFactor.x) + offsetFromOrigin.x;
			mTempScreenCoordDestination.bottom = (worldSpaceDestination.bottom * scalingFactor.y) + offsetFromOrigin.y;
			
	    	mTempPaint.setColor(color);
	    	mTempPaint.setStyle(Style.FILL);
	    	mCanvas.drawRect(mTempScreenCoordDestination, mTempPaint);
    	}
    	else
    	{
    		Log.d("SimpleGameEngine", "SGRenderer.drawRect(): viewport is not defined!");
    	}
    }
    
    public void drawRect(RectF worldSpaceDestination, int color)
    {
    	if(mViewport != null)
    	{
	    	Point offsetFromOrigin = mViewport.getOffsetFromOrigin();
			PointF scalingFactor = mViewport.getScalingFactor();
			
			mTempScreenCoordDestination.left = (worldSpaceDestination.left * scalingFactor.x) + offsetFromOrigin.x;
			mTempScreenCoordDestination.top = (worldSpaceDestination.top * scalingFactor.y) + offsetFromOrigin.y;
			mTempScreenCoordDestination.right = (worldSpaceDestination.right * scalingFactor.x) + offsetFromOrigin.x;
			mTempScreenCoordDestination.bottom = (worldSpaceDestination.bottom * scalingFactor.y) + offsetFromOrigin.y;
			
	    	mTempPaint.setColor(color);
	    	mTempPaint.setStyle(Style.FILL);
	    	mCanvas.drawRect(mTempScreenCoordDestination, mTempPaint);
    	}
    	else
    	{
    		Log.d("SimpleGameEngine", "SGRenderer.drawRect(): viewport is not defined!");
    	}
    }
    
    public void drawSprite(SGSprite sprite, Boolean isDebug)
    {
    	if(sprite.isVisible())
		{
	    	if(sprite.getTileSet() != null && !isDebug)
	    	{
		    	int currentTile = sprite.getCurrentAnimation().getCurrentTile();
		    	Rect tileRect = sprite.getTileSet().getTile(currentTile);
		    	drawImage(sprite.getImage(), tileRect, sprite.getPosition(), sprite.getDimensions());
	    	}
		}
    }
    
    public void drawSpriteList(float timeElapsedInSeconds, LinkedList<SGSprite> sprites, Boolean isDebug) 
    {
		for(SGSprite sprite : sprites)
			drawSprite(sprite, isDebug);
    }
    
    public void drawSprites(float timeElapsedInSeconds, Iterator<SGSprite> sprites, Boolean isDebug) 
    {
		while(sprites.hasNext())
			drawSprite(sprites.next(), isDebug);
    }
    
    public void drawSprites(float timeElapsedInSeconds, SparseArray<SGSprite> sprites, Boolean isDebug) 
    {
    	for(int i = 0; i < sprites.size(); i++)
		{
			SGSprite sprite = sprites.valueAt(i);
			drawSprite(sprite, isDebug);
		}
    }
    
    public void drawText(SGText text, SGFont font, PointF position, PointF dimensions)
    {
    	SGTileset fontTileSet = font.getTileSet();
    	char[] textChars = text.getCharacters();
    	
    	mTempPosition.set(position);
    	for(int i = 0; i < textChars.length; i++)
    	{
    		Rect character = fontTileSet.getTile(textChars[i]);
    		drawImage(fontTileSet.getImage(), character, mTempPosition, font.getDimensions());
    		
    		mTempPosition.x += font.getDimensions().x;
    	}
    }
    
    /*public void drawText(SGText text, SGFont font, RectF worldDestination)
    {
    	SGTileset fontTileSet = font.getTileSet();
    	char[] textChars = text.getCharacters();
    	
    	mTempPosition.set(worldDestination.left, worldDestination.top);
    	for(int i = 0; i < textChars.length; i++)
    	{
    		Rect character = fontTileSet.getTile(textChars[i]);
    		drawImage(fontTileSet.getImage(), character, mTempPosition, font.getDimensions());
    		
    		mTempPosition.x += font.getDimensions().x;
    	}
    }*/
    
    public void drawText(SGText text, SGFont font, PointF position)
    {
    	PointF fontDimensions = font.getDimensions();
    	SGTileset fontTileSet = font.getTileSet();
    	char[] textChars = text.getCharacters();
    	
    	/*if(font.usesGuiCoordinates())
    	{
    		PointF viewportDimensions = mViewport.getDimensions();
    		
    		mTempDimensions.set(viewportDimensions.x * fontDimensions.x, viewportDimensions.x * fontDimensions.y);
    	}
    	else
    	{
    		mTempDimensions.set(font.getDimensions());
    	}*/
    	
    	mTempPosition.set(position);
    	for(int i = 0; i < textChars.length; i++)
    	{
    		Rect character = fontTileSet.getTile(textChars[i]);
    		drawImage(fontTileSet.getImage(), character, mTempPosition, fontDimensions);
    		
    		mTempPosition.x += fontDimensions.x;
    	}
    }
	
	public void endDrawing()
	{
		
	}
	
	public SGView getView() { return mView; }
	public SGViewport getViewport() { return mViewport; }
	
	public void setViewport(SGViewport viewport) { mViewport = viewport; }
}