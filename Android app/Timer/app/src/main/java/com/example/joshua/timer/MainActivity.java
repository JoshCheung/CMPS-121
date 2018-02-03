package com.example.joshua.timer;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    static final private String LOG_TAG = "timer";

    private int seconds = 0;
    private int time1 = -1;
    private int time2 = -1;
    private int time3 = -1;
    private boolean plus_minus = false;


    private CountDownTimer timer  = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main);
    }

    @Override
    protected void onResume(){
        super.onResume();
        displayTime();
    }

    public void onClickPlus(View v){
        seconds  += 60;
        plus_minus = true;
        displayTime();
    };

    public void onClickMinus(View v){
        seconds = Math.max(0, seconds - 60);
        plus_minus = true;
        displayTime();
    };

    public void onReset(View v){
        seconds  = 0;
        plus_minus = false;
        cancelTimer();
        displayTime();
    }

    public void onClickStart(View v){
        if(plus_minus == true ) {
            if (seconds != time1 && seconds != time2 && seconds != time3) {
                if (time1 == -1) {
                    time1 = seconds;
                } else if (time2 == -1) {
                    time2 = time1;
                    time1 = seconds;
                } else {
                    time3 = time2;
                    time2 = time1;
                    time1 = seconds;
                }
            }
            displayButton1();
            if(time2 != -1){
                displayButton2();
            }
            if(time3!=-1) {
                displayButton2();
                displayButton3();
            }
        }

        if(seconds == 0){
            cancelTimer();
        }
        if(timer == null) {
            timer = new CountDownTimer(seconds *1000, 1000){
                @Override
                public void onTick(long millisUntilFinished){
                    Log.d(LOG_TAG, "Tick at" + millisUntilFinished);
                    seconds = Math.max(0, seconds - 1);
                    displayTime();
                }

                @Override
                public void onFinish(){
                    seconds = 0;
                    displayTime();
                }

            };
            timer.start();
        }
    }

    public void onClickStop(View v) {
        plus_minus = false;
        cancelTimer();
    }

    private void cancelTimer(){
        if(timer!= null){
            timer.cancel();
            timer = null;
        }
    }

    private void displayTime(){
        Log.d(LOG_TAG, "Displaying time " + seconds);
        TextView v = (TextView) findViewById(R.id.display);
        int m = seconds / 60;
        int s = seconds % 60;
        v.setText(String.format("%d:%02d", m, s));
    }

    public void onClickButton1(View v){
        plus_minus = false;
        seconds = time1;
        if(seconds == 0){
            cancelTimer();
        }
        if(timer == null) {
            timer = new CountDownTimer(seconds *100, 1000){
                @Override
                public void onTick(long millisUntilFinished){
                    Log.d(LOG_TAG, "Tick at" + millisUntilFinished);
                    seconds = Math.max(0, seconds - 1);
                    displayTime();
                }

                @Override
                public void onFinish(){
                    seconds = 0;
                    displayTime();
                }

            };
            timer.start();
        }
    }
    private void displayButton1(){
        Log.d(LOG_TAG, "Displaying time " + seconds);
        TextView v = (TextView) findViewById(R.id.button6);
        int m = time1 / 60;
        int s = time1 % 60;
        v.setText(String.format("%d:%02d", m, s));
    }

    public void onClickButton2(View v){
        plus_minus = false;
        seconds = time2;
        if(seconds == 0){
            cancelTimer();
        }
        if(timer == null) {
            timer = new CountDownTimer(seconds *100, 1000){
                @Override
                public void onTick(long millisUntilFinished){
                    Log.d(LOG_TAG, "Tick at" + millisUntilFinished);
                    seconds = Math.max(0, seconds - 1);
                    displayTime();
                }

                @Override
                public void onFinish(){
                    seconds = 0;
                    displayTime();
                }

            };
            timer.start();
        }
    }
    private void displayButton2(){
        Log.d(LOG_TAG, "Displaying time " + seconds);
        TextView v = (TextView) findViewById(R.id.button7);
        int m = time2 / 60;
        int s = time2 % 60;
        v.setText(String.format("%d:%02d", m, s));
    }

    public void onClickButton3(View v){
        plus_minus = false;
        seconds = time3;
        if(seconds == 0){
            cancelTimer();
        }
        if(timer == null) {
            timer = new CountDownTimer(seconds *100, 1000){
                @Override
                public void onTick(long millisUntilFinished){
                    Log.d(LOG_TAG, "Tick at" + millisUntilFinished);
                    seconds = Math.max(0, seconds - 1);
                    displayTime();
                }

                @Override
                public void onFinish(){
                    seconds = 0;
                    displayTime();
                }

            };
            timer.start();
        }
    }
    private void displayButton3(){
        Log.d(LOG_TAG, "Displaying time " + seconds);
        TextView v = (TextView) findViewById(R.id.button8);
        int m = time3 / 60;
        int s = time3 % 60;
        v.setText(String.format("%d:%02d", m, s));
    }



}
