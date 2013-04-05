package com.sumy.dreamyang;

import android.app.Activity;
import android.appwidget.AppWidgetManager;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.RemoteViews;
import android.widget.TextView;
import android.widget.Toast;

public class YangSetting extends Activity{
	protected static final String mPerfName = "com.sumy.dreamyang";

	int mAppWidgetId;

	Button button_ok;
	Button button_cancle;
	TextView text_show;
	Intent resultintent;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		Log.i("Yang","ON SETTING!");
		
		setContentView(R.layout.setting);
		
		setResult(RESULT_CANCELED);
		
		Intent intent=getIntent();
		Bundle extras=intent.getExtras();
		if(extras!=null){
			mAppWidgetId=extras.getInt(AppWidgetManager.EXTRA_APPWIDGET_ID,AppWidgetManager.INVALID_APPWIDGET_ID);
		}
		
//        intent = new Intent(YangSetting.this, YangSetting.class);   
//        intent.setAction(mPerfName + mAppWidgetId);   
//        intent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID,mAppWidgetId);   
//        PendingIntent pendingIntent = PendingIntent.getActivity(YangSetting.this, 0,   
//                intent, 0);   
//        views.setOnClickPendingIntent(R.id.widgettext, pendingIntent);  

		
		resultintent = new Intent();
		resultintent.putExtra(AppWidgetManager.EXTRA_APPWIDGET_ID, mAppWidgetId);
		
		findView();
		setlistener();
		
	}
	private void findView()
	{
		button_ok=(Button)findViewById(R.id.buttonok);
		button_cancle=(Button)findViewById(R.id.buttoncancle);
		text_show=(TextView)findViewById(R.id.showtext);
	}
	private void setlistener()
	{
		button_ok.setOnClickListener(buttonclick);
		button_cancle.setOnClickListener(buttonclick);
		text_show.setOnClickListener(new OnClickListener(){ //添加OnClickListener
            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                Toast.makeText(getApplicationContext(), "不要点我啦，嘻嘻。。。", Toast.LENGTH_SHORT).show();
            }
        });
	}

	private Button.OnClickListener buttonclick = new Button.OnClickListener(){

		@Override
		public void onClick(View v) {
			// TODO Auto-generated method stub
			switch(v.getId())
			{
				case R.id.buttoncancle:
				{
					setResult(RESULT_CANCELED,resultintent);
					Log.i("Yang", "Cancle!");
					finish();
					break;
				}
				case R.id.buttonok:
				{
					setResult(RESULT_OK,resultintent);
					Log.i("Yang","Create!");
					TextView text_show = (TextView)findViewById(R.id.showtext);   
					SharedPreferences.Editor prefs = getSharedPreferences(mPerfName, 0).edit();   
					prefs.putString("DAT" + mAppWidgetId, text_show.getText().toString());   
					prefs.commit();  
					
					RemoteViews views = new RemoteViews(YangSetting.this.getPackageName(), R.layout.widget);   
			        AppWidgetManager appWidgetManager = AppWidgetManager.getInstance(YangSetting.this);   
			        views.setTextViewText(R.id.widgettext, text_show.getText().toString());
			        appWidgetManager.updateAppWidget(mAppWidgetId, views);   
			        
					finish();
					break;
				}
			}
		}
		
	};
	
}
