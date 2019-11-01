package com.fsc.mynotificationdemo1;

import android.os.SystemClock;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.TextClock;
import android.widget.ViewFlipper;

public class MainActivity extends AppCompatActivity {

    private TextClock textClock;

    private Chronometer ch;

    private Button start;

    private ViewFlipper view_flipper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textClock = findViewById(R.id.date_window);

        textClock.setFormat12Hour("yyyy-MM-dd hh:mm:ss, EEEE");
        System.out.println("***xyc*=="+textClock.getFormat12Hour());

        ch = findViewById(R.id.chronometer);
        start = findViewById(R.id.button);
        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //设置开始计时时间
                ch.setBase(SystemClock.elapsedRealtime());
                //启动计时器
                ch.start();
            }
        });
        //为计时器绑定监听事件
        ch.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener()
        {
            @Override
            public void onChronometerTick(Chronometer ch)
            {
                // 如果从开始计时到现在超过了60s
                if (SystemClock.elapsedRealtime() - ch.getBase() > 60 * 1000)
                {
                    ch.stop();
                    start.setEnabled(true);
                }
            }
        });

        view_flipper = (ViewFlipper) findViewById(R.id.view_flipper);
        for(int i=0;i<3;i++){
            View view = getLayoutInflater().inflate(R.layout.item_flipper,null);
            view_flipper.addView(view);
        }
        view_flipper.setFlipInterval(2000);
        view_flipper.startFlipping();


    }
}
