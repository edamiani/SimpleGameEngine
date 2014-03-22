package com.book.simplegameengine;

import java.util.HashMap;

import android.graphics.PointF;
import android.graphics.RectF;

public class SGGuiContainer extends SGWidget
{
	protected HashMap<String, SGWidget> mChildren = new HashMap<String, SGWidget>();
	
	public SGGuiContainer(Alignment alignment, PointF position, PointF dimensions)
	{
		super("container", alignment, position, dimensions);
		
		if(dimensions.x == 0 || dimensions.y == 0)
		{
			mArea.set(Float.NaN, Float.NaN, Float.NaN, Float.NaN);
		}
	}
	
	public void addChild(String name, SGWidget widget)
	{
		mChildren.put(name, widget);
		
		widget.setSceneDimensions(mSceneDimensions);
		widget.setParent(this);
		
		_updateArea();
	}
	
	public SGWidget removeChild(String name)
	{
		SGWidget widget = mChildren.remove(name);
		widget.setParent(null);
		
		_updateArea();
		
		return widget;
	}
	
	@Override
	public boolean injectDown(PointF position)
	{
		for(HashMap.Entry<String, SGWidget> entry : mChildren.entrySet())
		{
			SGWidget widget = entry.getValue();
			
			if(widget.isEnabled())
			{
				RectF area = widget.getArea();
				if(position.x >= area.left && position.x <= area.right &&
				   position.y >= area.top && position.y <= area.bottom)
				{
					return entry.getValue().injectDown(position);
				}
			}
		}
		
		return false;
	}
	
	@Override
	public boolean injectUp(PointF position)
	{
		for(HashMap.Entry<String, SGWidget> entry : mChildren.entrySet())
		{
			SGWidget widget = entry.getValue();
			
			if(widget.isEnabled())
			{
				RectF area = widget.getArea();
				if(position.x >= area.left && position.x <= area.right &&
				   position.y >= area.top && position.y <= area.bottom)
				{
					return entry.getValue().injectUp(position);
				}
			}
		}
		
		return false;
	}
	
	@Override
	public void render(SGRenderer renderer)
	{
		for(HashMap.Entry<String, SGWidget> entry : mChildren.entrySet())
		{
			SGWidget widget = entry.getValue();
			if(widget.isVisible())
			{
				widget.render(renderer);
			}
		}
	}
	
	public SGWidget getChild(String name) { return mChildren.get(name); }
	
	@Override
	public void update()
	{
		super.update();
		
		for(HashMap.Entry<String, SGWidget> entry : mChildren.entrySet())
		{
			SGWidget widget = entry.getValue();
			widget.update();
		}
		
		_updateArea();
	}
	
	private void _updateArea()
	{
		if(!mChildren.isEmpty())
		{
			for(HashMap.Entry<String, SGWidget> entry : mChildren.entrySet())
			{
				SGWidget widget = entry.getValue();
	
				RectF childArea = widget.getArea();
				if(Float.isNaN(mArea.left) || childArea.left < mArea.left)
				{
					mArea.left = childArea.left;
				}
				
				if(Float.isNaN(mArea.right) || childArea.right > mArea.right)
				{
					mArea.right = childArea.right;
				}
				
				if(Float.isNaN(mArea.bottom) || childArea.bottom > mArea.bottom)
				{
					mArea.bottom = childArea.bottom;
				}
				
				if(Float.isNaN(mArea.top) || childArea.top < mArea.top)
				{
					mArea.top = childArea.top;
				}
			}
		}
		else
		{
			mArea.set(Float.NaN, Float.NaN, Float.NaN, Float.NaN);
		}
	}
}

