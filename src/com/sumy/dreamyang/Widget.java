package com.sumy.dreamyang;

import android.appwidget.AppWidgetManager;
import android.appwidget.AppWidgetProvider;
import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.widget.RemoteViews;

public class Widget extends AppWidgetProvider{

	private static final String mPerfName = "com.sumy.dreamyang";
	SharedPreferences mPref; 
	@Override
	public void onDeleted(Context context, int[] appWidgetIds) {
		// TODO Auto-generated method stub
		final int N=appWidgetIds.length;
		for(int i=0;i<N;i++)
		{
			int appWidgetId=appWidgetIds[i];
			Log.i("Yang","this is ["+appWidgetId+"] onDelete!");
		}
		super.onDeleted(context, appWidgetIds);
	}

	@Override
	public void onUpdate(Context context, AppWidgetManager appWidgetManager,
			int[] appWidgetIds) {
		// TODO Auto-generated method stub
		final int N=appWidgetIds.length;
		for(int i=0;i<N;i++){
			int appWidgetId=appWidgetIds[i];
			Log.i("yang", "this is ["+appWidgetId+"] onCreated!");
			
			mPref = context.getSharedPreferences(mPerfName, 0);   
			String YangContent = mPref.getString("DAT"+ appWidgetIds[i], "");   

			RemoteViews views = new RemoteViews(context.getPackageName(), R.layout.widget);     
	        views.setTextViewText(R.id.widgettext, YangContent);
	        appWidgetManager.updateAppWidget(appWidgetIds[i], views);    
		}	
		super.onUpdate(context, appWidgetManager, appWidgetIds);
	}

		
}
