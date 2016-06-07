package hyp.my060703;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ProgressBar;

/**
 * Created by Administrator on 2016/6/7 0007.
 */
public class ProgressBarActivity extends AppCompatActivity{
    private ProgressBar mProgressBar;
    private MyAsyncTask mTask;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress);
        mProgressBar = (ProgressBar) findViewById(R.id.pg);
        mTask = new MyAsyncTask();
        mTask.execute();
    }

    @Override
    protected void onPause() {
        super.onPause();
        if(mTask != null && mTask.getStatus() == AsyncTask.Status.RUNNING){
            mTask.cancel(true);
        }
    }

    class MyAsyncTask extends AsyncTask<Void,Integer,Void>{
        @Override
        protected Void doInBackground(Void... params) {
            for(int i = 0; i < 100; i++){
                if(isCancelled()){
                    break;
                }
                publishProgress(i);
                try {
                    Thread.sleep(300);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
            return null;
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            if(isCancelled()){
                return;
            }
            mProgressBar.setProgress(values[0]);
        }
    }
}
