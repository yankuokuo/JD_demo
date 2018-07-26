package bwei.com.jd_demo.activity;

import android.content.Intent;
import android.os.Handler;
import android.os.Message;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import bwei.com.jd_demo.R;

public class StartActivity extends AppCompatActivity {

    private TextView textView;
    private int i=3;
    private Handler handler=new Handler(){
        @Override
        public void handleMessage(Message msg) {
            int z=msg.what;
            textView.setText(z+"S");
            if (z==0){
                startActivity(new Intent(StartActivity.this,MainActivity.class));
                finish();
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        textView = findViewById(R.id.start_text);
        new Thread(){
            @Override
            public void run() {
                while (i>0){
                    i--;
                    try {
                        sleep(1000);
                        handler.sendEmptyMessage(i);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }.start();
    }
}
