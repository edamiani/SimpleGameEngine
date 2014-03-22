package com.book.simplegameengine;

import java.util.ArrayList;

import android.graphics.Color;
import android.graphics.PointF;
import android.graphics.RectF;

public class SGTrigger extends SGEntity
{
	private ArrayList<SGEntity> mObservedEntities = new ArrayList<SGEntity>();
	
	public SGTrigger(SGWorld world, PointF position, PointF dimensions)
	{
		super(world, "trigger", position, dimensions);
		
		setDebugColor(Color.MAGENTA);
		setDebugDrawingStyle(DebugDrawingStyle.OUTLINE);
	}
	
	public boolean addObservedEntity(SGEntity entity)
	{
		if(mObservedEntities.contains(entity))
		{
			return false;
		}
		else
		{
			mObservedEntities.add(entity);
			return true;
		}
	}
	
	public boolean removeObservedEntity(SGEntity entity)
	{
		return mObservedEntities.remove(entity);
	}
	
	public void onHit(SGEntity entity, float elapsedTimeInSeconds) {}
	
	@Override
	public void step(float elapsedTimeInSeconds)
	{
		SGEntity currentEntity;
		SGWorld world = getWorld();
		RectF triggerBoundingBox = getBoundingBox();
		
		int arraySize = mObservedEntities.size();
		for(int i = 0; i < arraySize; i++)
		{
			currentEntity = mObservedEntities.get(i);
			
			if(world.collisionTest(triggerBoundingBox, currentEntity.getBoundingBox()))
			{
				onHit(currentEntity, elapsedTimeInSeconds);
			}
		}
		
	}
}
