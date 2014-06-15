package com.example.test;

import java.util.ArrayList;

import android.app.Activity;
import android.content.AsyncTaskLoader;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewTreeObserver.OnPreDrawListener;

public class MainActivity1 extends Activity {

	private ArrayList<String> exceptionList;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main1);

		Thread.setDefaultUncaughtExceptionHandler(
				new CustomExceptionHandler(getApplicationContext()));

		exceptionList = new ArrayList<String>();		
		SharedPreferences preferences = getSharedPreferences("Exception", getApplicationContext().MODE_PRIVATE);

		int size = preferences.getAll().size();
		for(int i = 0;i <= size;i++){
			String exception = preferences.getString("Exception"+i,null);
			if(exception != null){
				exceptionList.add(exception);
			}
		}

		if(exceptionList.size() != 0){
			// エラーがあったら内容が保存されてる
			// exceptionListにList型に格納されてる
		}

		findViewById(R.id.button).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				int test = 0 / 0;
			}
		});
	}

	@Override
	protected void onDestroy(){
		super.onDestroy();
	}

	public interface AsyncCallback {
		void onPostExecute(String result);
	}
}
