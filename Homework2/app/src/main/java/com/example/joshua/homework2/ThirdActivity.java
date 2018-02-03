package com.example.joshua.homework2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ThirdActivity extends AppCompatActivity {

    AppInfo appInfo;
    static final public String MYPREFS = "myprefs";
    static final public String PREF_STRING_3 = "string_3";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_third);
        appInfo = AppInfo.getInstance(this);
    }

    @Override
    protected void onResume() {
        super.onResume();
        SharedPreferences string3 = getSharedPreferences(ThirdActivity.MYPREFS, 0);
        String myText3 = string3.getString(ThirdActivity.PREF_STRING_3, "");
        EditText tv3 = (EditText) findViewById(R.id.editText3);
        tv3.setText(myText3);


        String myText = string3.getString(MainActivity.PREF_STRING_1, "");
        TextView edv = (TextView) findViewById(R.id.textView);
        edv.setText(myText);


        String myText2 = string3.getString(SecondActivity.PREF_STRING_2, "");
        TextView tv2 = (TextView) findViewById(R.id.textView2);
        tv2.setText(myText2);


    }

    public void onEnter(View v){
        EditText edv = (EditText) findViewById(R.id.editText3);
        String text1 = edv.getText().toString();
        SharedPreferences settings = getSharedPreferences(MYPREFS, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(PREF_STRING_3, text1);
        editor.commit();
    }

    public void goFirst(View V){
        // Go to Main activity
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void goSecond(View V) {
        // Go to second activity
        Intent intent = new Intent(this, SecondActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    @Override
    public void onBackPressed() {
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }
}
