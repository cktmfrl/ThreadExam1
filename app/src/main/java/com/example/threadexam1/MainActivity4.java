package com.example.threadexam1;

import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity4 extends AppCompatActivity {

    private TextView mTextView;
    private ProgressBar mProgressBar;
    private Handler mHandler = new Handler() {
        @Override
        public void handleMessage(@NonNull Message msg) {
            //super.handleMessage(msg);
            int percent = msg.arg1;
            mTextView.setText(percent + "%");
            mProgressBar.setProgress(percent);
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.textView);
        mProgressBar = findViewById(R.id.progressBar);
    }

    public void download(View view) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 100; i++) {
                    try {
                        Thread.sleep(100);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    final int percent = i;
                    Message message = Message.obtain();
                    message.arg1 = percent;
                    mHandler.sendMessage(message);
                    /**
                     mHandler.post(new Runnable() { // postAtTime, postDelayed
                        @Override
                        public void run() {

                        }
                    });*/

                }
            }
        }).start();
    }

}