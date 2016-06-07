package hyp.my060703;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

/**
 * 异步任务 AsyncTask
 *
 * Android 单线程模型
 * 耗时操作挡在非主线程中执行
 *
 * 子线程中更新UI
 * 封装、简化异步操作
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loadImage(View view){
        Log.i("TAG", "loadImage: 123");
        startActivity(new Intent(this,ImageActivity.class));
    }
    public void loadProgress(View view){
        Log.i("TAG", "loadImage: 123");
        startActivity(new Intent(this,ProgressBarActivity.class));
    }
}
