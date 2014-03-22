package com.book.simplegameengine;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;

public class SGDialog 
{
	private AlertDialog mDialog;
	
	public SGDialog(Context context, int messageId, int okId, int cancelId)
	{
		AlertDialog.Builder builder = new AlertDialog.Builder(context);
		builder.setMessage(messageId)		       
		       .setPositiveButton(okId, new DialogInterface.OnClickListener() {
		    	   public void onClick(DialogInterface dialog, int id) {
		    		   onTouchOk(); 
		    	   }
		       })
			   .setNegativeButton(cancelId, new DialogInterface.OnClickListener() {
				   public void onClick(DialogInterface dialog, int id) {
					   onTouchCancel();
				   }
			   });
		mDialog = builder.create();
		mDialog.setCancelable(false);
		mDialog.setCanceledOnTouchOutside(false);
	}
	
	public void onTouchOk()
	{
		
	}
	
	public void onTouchCancel()
	{
		
	}
	
	public void show()
	{
		mDialog.show();
	}
}
