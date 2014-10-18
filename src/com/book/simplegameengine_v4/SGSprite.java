package com.book.simplegameengine_v4;

import java.util.HashMap;

import android.graphics.PointF;
import android.util.Log;

public class SGSprite {
	private static StringBuilder	stringBuilder = new StringBuilder();
	private HashMap<String, SGAnimation> 
									mAnimations = new HashMap<String, SGAnimation>();
	private SGAnimation				mCurrentAnimation;
	private PointF					mDimensions = new PointF();
	private SGEntity				mEntity;
	private boolean					mIsVisible = true;
	private PointF					mPosition = new PointF();
	private SGTileset				mTileset = null;
	
	public SGSprite(SGSpriteDesc spriteDesc) 
	{		
		mTileset = spriteDesc.getTileset();
		mAnimations = spriteDesc.getAnimations();
	}
	
	public SGSprite(SGSpriteDesc spriteDesc, SGEntity entity) 
	{
		mEntity = entity;
		mPosition.set(mEntity.getPosition());
		mDimensions.set(mEntity.getDimensions());
		mTileset = spriteDesc.getTileset();
		mAnimations = spriteDesc.getAnimations();
	}

	public void changeDesc(SGSpriteDesc spriteDesc) 
	{
		mTileset = spriteDesc.getTileset();
		mAnimations = spriteDesc.getAnimations();
	}

	public void step(float elapsedTimeInSeconds) 
	{
		mCurrentAnimation.step(elapsedTimeInSeconds);
		
		if(mEntity != null) 
		{
			mPosition.set(mEntity.getPosition());
			mDimensions.set(mEntity.getDimensions());
		}
	}

	public SGAnimation getAnimation(String animationName) 
	{
		SGAnimation animation = mAnimations.get(animationName);
		
		if(animation == null) 
		{
			stringBuilder.delete(0, stringBuilder.length());
			stringBuilder.append("SGEntity.getAnimation(): A entidade '");
			stringBuilder.append(mEntity.getId());
			stringBuilder.append("' não possui uma animação de nome '");
			stringBuilder.append(animationName);
			stringBuilder.append("'");
			Log.d("SimpleGameEngine", stringBuilder.toString());
		}
		
		return animation;
	}

	public SGAnimation	getCurrentAnimation() { return mCurrentAnimation; }
	public PointF 		getDimensions() { return mDimensions; }
	public SGEntity		getEntity() { return mEntity; }
	public SGImage		getImage() { return mTileset.getImage(); }
	public PointF 		getPosition() { return mPosition; }	
	public SGTileset	getTileSet() { return mTileset; }
	
	public boolean		isVisible() { return mIsVisible; }
	
	public void setCurrentAnimation(String animationName, boolean stopCurrentAnimation) 
	{
		SGAnimation animation = mAnimations.get(animationName);
		
		if(animation != null && animation != mCurrentAnimation)
		{
			if(mCurrentAnimation != null && stopCurrentAnimation == true)
			{
				mCurrentAnimation.stop();
			}
			
			mCurrentAnimation = animation;
		}
	}
	
	public void setDimensions(PointF dimensions)
	{
		if(mEntity != null)
		{
			mDimensions = dimensions;
		}
	}
	
	public void setEntity(SGEntity entity) 
	{ 
		mEntity = entity;
	}
	
	public void setIsVisible(boolean isVisible) { mIsVisible = isVisible; }

	public void setPosition(PointF position)
	{
		if(mEntity != null)
		{
			mPosition = position;
		}
	}
}
