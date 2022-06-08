package com.example.threadexam1;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity5 extends AppCompatActivity {

    private TextView mTextView;
    private ProgressBar mProgressBar;

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
                    runOnUiThread(new Runnable() { // MainThread 에서 동작, Handler 대신 사용 가능
                        @Override
                        public void run() {
                            mTextView.setText(percent + "%");
                            mProgressBar.setProgress(percent);
                        }
                    });
                }
            }
        }).start();
    }

}