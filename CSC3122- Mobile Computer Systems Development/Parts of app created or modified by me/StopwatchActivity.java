package com.example.clockapp;

import android.os.Handler;
import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class StopwatchActivity extends AppCompatActivity {
    TextView textView;
    Button start,pause,reset,lap;
    long MillisecondTime, StartTime, TimeBuff, UpdateTime = 0L;
    Handler handler;
    int Seconds, Minutes, MilliSeconds;
    ListView listView;
    String[] ListElems = new String[]{ };
    List<String> ListElemsArrayList;
    ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stopwatch);
        textView = (TextView)findViewById(R.id.StopwatchTime);
        listView = (ListView)findViewById(R.id.SavedTimes);
        start = (Button)findViewById(R.id.Start);
        pause = (Button)findViewById(R.id.Pause);
        reset = (Button)findViewById(R.id.Reset);
        lap = (Button)findViewById(R.id.SaveLap);
        handler = new Handler();
        ListElemsArrayList = new ArrayList<String>(Arrays.asList(ListElems));
        adapter = new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,ListElemsArrayList);
        listView.setAdapter(adapter);
        lap.setEnabled(false);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                StartTime = SystemClock.uptimeMillis();
                handler.postDelayed(runnable,0);
                reset.setEnabled(false);
                lap.setEnabled(true);
                start.setEnabled(false);
                pause.setEnabled(true);
            }
        });
        pause.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TimeBuff += MillisecondTime;
                handler.removeCallbacks(runnable);
                reset.setEnabled(true);
                pause.setEnabled(false);
                start.setEnabled(true);
            }
        });
        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                MillisecondTime = 0L;
                StartTime = 0L;
                TimeBuff = 0L;
                UpdateTime = 0L;
                Seconds = 0;
                Minutes = 0;
                MilliSeconds = 0;
                textView.setText("00:00:00");
                ListElemsArrayList.clear();
                adapter.notifyDataSetChanged();
                lap.setEnabled(false);
                start.setEnabled(true);
            }
        });
        lap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ListElemsArrayList.add(textView.getText().toString());
                adapter.notifyDataSetChanged();
            }
        });
    }
    public Runnable runnable = new Runnable() {
        @Override
        public void run() {
            MillisecondTime = SystemClock.uptimeMillis()-StartTime;
            UpdateTime = TimeBuff + MillisecondTime;
            Seconds = (int)(UpdateTime/1000);
            Minutes = Seconds/60;
            Seconds = Seconds%60;
            MilliSeconds = (int)(UpdateTime%1000);
            textView.setText("" + Minutes + ":" + String.format("%02d", Seconds) + ":" + String.format("%03d",MilliSeconds));
            handler.postDelayed(this,0);
        }
    };
}
