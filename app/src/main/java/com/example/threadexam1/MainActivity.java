package com.example.threadexam1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private TextView mTextView;
    private ProgressBar mProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mTextView = findViewById(R.id.textView);
        mProgressBar = findViewById(R.id.progressBar);
    }

    // 스레드를 쓰지 않아 화면이 멈춰 있는 ANR 발생
    public void download(View view) throws InterruptedException {
        for (int i = 0; i <= 100; i++) {
            Thread.sleep(100);
            mTextView.setText(i + "%");
            mProgressBar.setProgress(i);
        }
    }

}