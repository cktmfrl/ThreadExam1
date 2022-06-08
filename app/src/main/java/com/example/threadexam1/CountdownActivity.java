package com.example.threadexam1;

import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class CountdownActivity extends AppCompatActivity {

    private TextView mTextView;

    private CountTask mTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_countdown);
        mTextView = findViewById(R.id.count);
    }

    public void start(View view) {
        mTask = new CountTask();
        mTask.execute(0);
    }

    public void clear(View view) {
        mTask.cancel(true);
        mTextView.setText("0");
    }

    // 1. AsycnTask의 인스턴스는 반드시 메인 스레드에서 생성해야 한다.
    // 2. execute() 메서드는 반드시 메인 스레드에서 실행해야 한다.
    // 3. onPreExecute(), onPostExecute(Result), doInBackground(Params...), onProgressUpdate(Progress...) 콜백 메서드를 직접 호출하면 안 된다.
    // 4. AsyncTask의 인스턴스는 한 번만 실행할 수 있다. (400p)
    // 또한 AsyncTask는 순서대로 실행되므로 Thread처럼 동시해 실행해야할 때는 executeOnExecutor()를 사용한다. (Ex. new DownloadTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, 0);)
    class CountTask extends AsyncTask<Integer, Integer, Integer> { // Param, Process, Result

        @Override
        protected Integer doInBackground(Integer... param) {
            do {
                try {
                    Thread.sleep(1000);
                    param[0]++;
                    publishProgress(param[0]);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            } while (param[0] < 10);

            return param[0];
        }

        @Override
        protected void onProgressUpdate(Integer... progress) {
            mTextView.setText(String.valueOf(progress[0]));
        }

        @Override
        protected void onPostExecute(Integer result) {
            Toast.makeText(CountdownActivity.this, "완료 됨", Toast.LENGTH_SHORT).show();
            mTextView.setText(String.valueOf(result)); // 종료 시 마지막 값 텍스트뷰에 표시
        }
    }

}