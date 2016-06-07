package hyp.my060703;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.net.URLConnection;

/**
 * Created by Administrator on 2016/6/7 0007.
 */
public class ImageActivity extends AppCompatActivity {
    private ImageView mImageView;
    private ProgressBar mProgressBar;
    private static String URL = "http://static.xxj365.com/upload/product/12.JPG";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.image);
        mImageView = (ImageView) findViewById(R.id.image);
        mProgressBar = (ProgressBar) findViewById(R.id.progressbar);
        Log.i("TAG", "onCreate: "+URL);
        new MyAsyncTask().execute(URL);
    }

    class MyAsyncTask extends AsyncTask<String,Void,Bitmap>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mProgressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected void onPostExecute(Bitmap bitmap) {
            Log.i("TAG", "onPostExecute: ");
            super.onPostExecute(bitmap);
            mProgressBar.setVisibility(View.GONE);
            mImageView.setImageBitmap(bitmap);
        }

        @Override
        protected Bitmap doInBackground(String... params) {
            //获取传递进来的参数
            String url = params[0];
            Bitmap bitmap = null;
            URLConnection connection;
            InputStream is;
            try{
                connection = new URL(url).openConnection();
                is = connection.getInputStream();
                BufferedInputStream bis = new BufferedInputStream(is);
                Thread.sleep(3000);
                //需要通过decodeStream解析输入流
                bitmap = BitmapFactory.decodeStream(bis);
                is.close();
                bis.close();
            }catch (IOException e){
                e.printStackTrace();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //将bitmap作为返回值
            return bitmap;
        }
    }
}
