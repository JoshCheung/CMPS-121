package com.example.joshua.homework2;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutCompat;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class SecondActivity extends AppCompatActivity {

    AppInfo appInfo;
    static final public String MYPREFS = "myprefs";
    static final public String PREF_STRING_2 = "string_2";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_second);
        appInfo = AppInfo.getInstance(this);
    }

    @Override
    protected void onResume() {
        super.onResume();

        SharedPreferences string2 = getSharedPreferences(SecondActivity.MYPREFS, 0);
        String myText2 = string2.getString(SecondActivity.PREF_STRING_2, "");
        EditText tv2 = (EditText) findViewById(R.id.editText2);
        tv2.setText(myText2);


        String myText = string2.getString(MainActivity.PREF_STRING_1, "");
        TextView edv = (TextView) findViewById(R.id.textView);
        edv.setText(myText);

        String myText3 = string2.getString(ThirdActivity.PREF_STRING_3, "");
        TextView tv3 = (TextView) findViewById(R.id.textView3);
        tv3.setText(myText3);
    }

    public void onEnter(View v){
        EditText edv = (EditText) findViewById(R.id.editText2);
        String text1 = edv.getText().toString();
        SharedPreferences settings = getSharedPreferences(MYPREFS, 0);
        SharedPreferences.Editor editor = settings.edit();
        editor.putString(PREF_STRING_2, text1);
        editor.commit();
    }
    public void goFirst(View V){
        // Go to MainActivity
        Intent intent = new Intent(this, MainActivity.class);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }

    public void goThird(View v){
        //Go to ThirdActivity
        Intent intent = new Intent(this, ThirdActivity.class);
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
