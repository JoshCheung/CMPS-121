package com.example.joshua.homework2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static final public String MYPREFS = "myprefs";
    static final public String PREF_STRING_1 = "string_1";

    AppInfo appInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appInfo = AppInfo.getInstance(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences settings = getSharedPreferences(MainActivity.MYPREFS, 0);
        String myText = settings.getString(MainActivity.PREF_STRING_1, "");
        EditText edv = (EditText) findViewById(R.id.editText1);
        edv.setText(myText);

        String myText2 = settings.getString(SecondActivity.PREF_STRING_2, "");
        TextView tv2 = (TextView) findViewById(R.id.textView2);
        tv2.setText(myText2);

        String myText3 = settings.getString(ThirdActivity.PREF_STRING_3, "");
        TextView tv3 = (TextView) findViewById(R.id.textView3);
        tv3.setText(myText3);
    }

    public void goSecond(View V) {
        // Go to second activity
        Intent intent = new Intent(this, SecondActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void goThird(View V) {
        // Go to ThirdActivity
        Intent intent = new Intent(this, ThirdActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void onEnter(View v){
        EditText edv = (EditText) findViewById(R.id.editText1);
        String text1 = edv.getText().toString();
        SharedPreferences settings = getSharedPreferences(MYPREFS, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(PREF_STRING_1, text1);
        editor.commit();
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

}

