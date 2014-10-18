package com.book.simplegameengine_v4;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;

public class SGPreferences 
{
	private SharedPreferences.Editor	mEditor = null;
	private SharedPreferences			mPreferences = null;
	
	public SGPreferences(Activity activity) 
	{
		mPreferences = activity.getPreferences(Context.MODE_PRIVATE);
		mEditor = mPreferences.edit();
	}
	
	public boolean getBoolean(String key, boolean defaultValue) 
	{
		try 
		{
			return mPreferences.getBoolean(key, defaultValue);
		}
		catch(ClassCastException e) 
		{
			Log.d(SGActivity.TAG, "SGPreferences.getBoolean(): Valor possui um tipo diferente!");
			return defaultValue;
		}
	}

	public float getFloat(String key, float defaultValue) 
	{ 
		try 
		{
			return mPreferences.getFloat(key, defaultValue);
		}
		catch(Exception e) 
		{
			Log.d(SGActivity.TAG, "SGPreferences.getFloat(): Valor possui um tipo diferente!");
			return defaultValue;
		}
	}
	
	public int getInt(String key, int defaultValue) 
	{ 
		try 
		{
			return mPreferences.getInt(key, defaultValue);
		}
		catch(Exception e) 
		{
			Log.d(SGActivity.TAG, "SGPreferences.getInt(): Valor possui um tipo diferente!");
			return defaultValue;
		}
	}
	
	public long getLong(String key, long defaultValue) 
	{
		try 
		{
			return mPreferences.getLong(key, defaultValue);
		}
		catch(Exception e) {
			Log.d(SGActivity.TAG, "SGPreferences.getLong(): Valor possui um tipo diferente!");
			return defaultValue;
		}
	}
	
	public String getString(String key, String defaultValue) 
	{
		try 
		{
			return mPreferences.getString(key, defaultValue);
		}
		catch(Exception e) 
		{
			Log.d(SGActivity.TAG, "SGPreferences.getString(): Valor possui um tipo diferente!");
			return defaultValue;
		}
	}

	public SGPreferences begin() 
	{
		mEditor = mPreferences.edit();
		return this;
	}

	public SGPreferences putBoolean(String key, boolean value) 
	{
		mEditor.putBoolean(key, value);
		return this;
	}

	public SGPreferences putFloat(String key, float value) 
	{
		mEditor.putFloat(key, value);
		return this;
	}
	
	public SGPreferences putInt(String key, int value) 
	{
		mEditor.putInt(key, value);
		return this;
	}
	
	public SGPreferences putLong(String key, long value) 
	{
		mEditor.putLong(key, value);
		return this;
	}
	
	public SGPreferences putString(String key, String value) 
	{
		mEditor.putString(key, value);
		return this;
	}

	public SGPreferences remove(String key) 
	{
		mEditor.remove(key);
		return this;
	}

	public void end() {
		mEditor.commit();
	}
}
