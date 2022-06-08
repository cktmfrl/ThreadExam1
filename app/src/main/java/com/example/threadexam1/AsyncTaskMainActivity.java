package com.example.threadexam1;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.concurrent.Executor;

public class AsyncTaskMainActivity extends AppCompatActivity {

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
        new DownloadTask().execute();
    }

    // 1. AsycnTask의 인스턴스는 반드시 메인 스레드에서 생성해야 한다.
    // 2. execute() 메서드는 반드시 메인 스레드에서 실행해야 한다.
    // 3. onPreExecute(), onPostExecute(Result), doInBackground(Params...), onProgressUpdate(Progress...) 콜백 메서드를 직접 호출하면 안 된다.
    // 4. AsyncTask의 인스턴스는 한 번만 실행할 수 있다. (400p)
    // 또한 AsyncTask는 순서대로 실행되므로 Thread처럼 동시해 실행해야할 때는 executeOnExecutor()를 사용한다. (Ex. new DownloadTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, 0);)
    class DownloadTask extends AsyncTask<Void, Integer, Void> { // Param, Process, Result

        @Override
        protected Void doInBackground(Void... voids) {
            for (int i = 0; i <= 100; i++) {
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                final int percent = i;
                publishProgress(percent);
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            mTextView.setText(values[0] + "%");
            mProgressBar.setProgress(values[0]);
        }
    }

}
