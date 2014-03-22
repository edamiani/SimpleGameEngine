package com.book.simplegameengine;

import java.util.HashMap;

import android.graphics.Point;
import android.graphics.Rect;

public class SGSpriteDesc 
{
	private HashMap<String, SGAnimation> 
						mAnimations = new HashMap<String, SGAnimation>();
	private SGTileset	mTileset;
	
	public SGSpriteDesc(SGTileset tileset)
	{
		mTileset = tileset;
		
		_createDefaultAnimation();
	}
	
	public SGSpriteDesc(SGImage image, Rect drawableArea)
	{
		Point tilesNum = new Point(1, 1);
		mTileset = new SGTileset(image, tilesNum, drawableArea);
		
		_createDefaultAnimation();
	}
	
	public SGSpriteDesc(SGImage image)
	{
		this(image, new Rect(0, 0, image.getDimensions().x, image.getDimensions().y));
	}
	
	public SGSpriteDesc addAnimation(String name, SGAnimation animation) { mAnimations.put(name, animation); return this; }
		
	public HashMap<String, SGAnimation> 
						getAnimations() { return mAnimations; }
	public SGTileset 	getTileset() { return mTileset; }
	
	private void _createDefaultAnimation()
	{
		int tiles[] = new int[1];
		tiles[0] = 0;
		mAnimations.put("default", new SGAnimation(tiles, 0.0f));
	}
}
