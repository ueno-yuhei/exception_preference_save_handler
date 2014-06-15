package com.example.test;

import java.io.PrintWriter;
import java.io.StringWriter;
import java.lang.Thread.UncaughtExceptionHandler;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.content.pm.PackageManager.NameNotFoundException;
import android.os.Build;
import android.text.format.Time;

public class CustomExceptionHandler implements UncaughtExceptionHandler{
	
	private static Context mContext;
	private static UncaughtExceptionHandler mDefaultUncaughtExceptionHandler;

	public CustomExceptionHandler(Context context) {
		mContext = context;
		mDefaultUncaughtExceptionHandler = Thread.getDefaultUncaughtExceptionHandler();
	}

	@Override
	public void uncaughtException(Thread thread, Throwable ex) {
		StringWriter stringWriter = new StringWriter();
		ex.printStackTrace(new PrintWriter(stringWriter));
		// エラーログの文字置き換え タブを削除
		String stackTrace = stringWriter.toString().replaceAll("\\\t","");
		
		//プレファレンスに保存 エラーログがどんどんたまっていく様になる
		SharedPreferences preferences = mContext.getSharedPreferences("Exception", mContext.MODE_PRIVATE);
		int size = preferences.getAll().size();
		preferences.edit().putString("Exception"+size, stackTrace).commit();

		//アプリを終了
		mDefaultUncaughtExceptionHandler.uncaughtException(thread, ex);
	}
}
