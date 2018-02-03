package com.example.joshua.homework2;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by Joshua on 4/26/17.
 */

public class AppInfo {
    private static AppInfo instance = null;
    private static final String COLOR_NAME = "color2";

    protected AppInfo() {
        // Exists only to defeat instantiation.
    }

    // Here are some values we want to keep global.
    public String sharedString;

    private Context my_context;

    public static AppInfo getInstance(Context context) {
        if(instance == null) {
            instance = new AppInfo();
            instance.my_context = context;
            SharedPreferences settings = context.getSharedPreferences(MainActivity.MYPREFS, 0);
            instance.sharedString = settings.getString(COLOR_NAME, null);
        }
        return instance;
    }

    public void setColor(MainActivity mainActivity, String c) {
        instance.sharedString = c;
        SharedPreferences settings = my_context.getSharedPreferences(MainActivity.MYPREFS, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(COLOR_NAME, c);
        editor.commit();
    }


}
