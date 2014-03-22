package com.book.simplegameengine;

import java.util.HashMap;

import android.graphics.PointF;
import android.util.Log;

public class SGSprite 
{
	protected static StringBuilder stringBuilder = new StringBuilder();
	
	protected HashMap<String, SGAnimation> 
							mAnimations = new HashMap<String, SGAnimation>();
	protected SGAnimation	mCurrentAnimation;
	protected PointF 		mDimensions = new PointF();
	protected SGEntity		mEntity;
	protected boolean		mIsVisible = true;
	protected PointF 		mPosition = new PointF();
	protected SGTileset		mTileset = null;
	
	public SGSprite()
	{		
	}
	
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
	public PointF 		getDimensions() { /*return mDimensions;*/ return mEntity.getDimensions(); }
	public SGEntity		getEntity() { return mEntity; }
	public SGImage		getImage() { return mTileset.getImage(); }
	public PointF 		getPosition() { /*return mPosition;*/ return mEntity.getPosition(); }	
	public SGTileset	getTileSet() { return mTileset; }
	
	public boolean		isVisible() { return mIsVisible; }
	
	public void setCurrentAnimation(String animationName) 
	{
		SGAnimation animation = mAnimations.get(animationName);
		
		if(animation != null)
			mCurrentAnimation = animation;
	}
	
	public void setCurrentAnimationAndReset(String animationName) 
	{
		SGAnimation animation = mAnimations.get(animationName);
		
		if(animation != null)
		{
			mCurrentAnimation = animation;
			mCurrentAnimation.reset();
		}
	}
	
	/*public void setDimensions(float x, float y) 
	{
		mDimensions.set(x, y);
	}
	
	public void setDimensions(PointF dimensions) 
	{
		mDimensions.set(dimensions);
	}*/
	
	public void setEntity(SGEntity entity) 
	{ 
		mEntity = entity;
		
		/*mDimensions.set(entity.getDimensions());
		mPosition.set(entity.getPosition());*/
	}
	
	public void setIsVisible(boolean isVisible) { mIsVisible = isVisible; }
	
	/*public void setPosition(float x, float y) 
	{
		mPosition.x = x;
		mPosition.y = y;
	}
	
	public void setPosition(PointF position) 
	{
		mPosition.set(position);
	}*/
}
