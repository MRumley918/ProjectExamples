package com.example.clockapp;

import android.os.CountDownTimer;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class CountdownTimerActivity extends AppCompatActivity {
    private EditText editText;
    private Button button,stop;
    private TextView countdown;
    private CountDownTimer timer;
    private long timeLeft;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown_timer);
        editText = (EditText)findViewById(R.id.input);
        button = (Button)findViewById(R.id.button);
        stop = (Button)findViewById(R.id.stop);
        countdown = (TextView)findViewById(R.id.countdown);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(timer !=null){
                    timer.cancel();
                }
                if(editText.getText().toString().length()==0){
                    return;
                }
                timeLeft = Integer.parseInt(editText.getText().toString());
                timeLeft *= 1000;
                startTimer(timeLeft,1);
            }
        });
        stop.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(stop.getText().toString().equalsIgnoreCase(getString(R.string.Stop))){
                    timer.cancel();
                    stop.setText((getString(R.string.Resume)));
                }
                else{
                    startTimer(timeLeft,1);
                    stop.setText(getString(R.string.Stop));
                }
            }
        });
    }
    private void startTimer(long duration, long interval){
        timer = new CountDownTimer(duration,interval) {
            @Override
            public void onTick(long millisUntilFinished) {
                long seconds = millisUntilFinished/1000;
                long minutes = seconds/60;
                long hours = minutes/60;
                if (minutes > 0) {
                    seconds = seconds %60;
                }
                if(hours>0){
                    minutes =minutes%60;
                }
                String time = format(hours)+":"+format(minutes)+":"+format(seconds);
                countdown.setText(time);
            }

            @Override
            public void onFinish() {
                countdown.setText("00:00:00");
                animate(countdown,500,0,Animation.REVERSE,3);
            }
        };
        timer.start();
    }
    private String format(long val){
        if(val<10){
            return "0"+val;
        }
        return val +"";
    }
    private void animate(View view, long duration,int startOffset, int repeatMode, int repeatCount){
        Animation anim = new AlphaAnimation(0.0f,1.0f);
        anim.setDuration(duration);
        anim.setStartOffset(startOffset);
        anim.setRepeatMode(repeatMode);
        anim.setRepeatCount(repeatCount);
        view.startAnimation(anim);
    }
}
