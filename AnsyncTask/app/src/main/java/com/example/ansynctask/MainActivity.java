package com.example.ansynctask;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;

public class MainActivity extends AppCompatActivity {

    private ImageView mImageView = null;
    private ProgressBar mProgressBar = null;
    private String URLs = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2F1812.img.pp.sohu.com.cn%2Fimages%2Fblog%2F2009%2F11%2F18%2F18%2F8%2F125b6560a6ag214.jpg&refer=http%3A%2F%2F1812.img.pp.sohu.com.cn&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1621743304&t=a7c7412d23381d4d7d3141734065ff61";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // 实例化控件
        this.mImageView = (ImageView) findViewById(R.id.imageView1);
        this.mProgressBar = (ProgressBar) findViewById(R.id.processBar1);
        // 实例化异步任务
        ImageDownloadTask task = new ImageDownloadTask();
        task.execute(URLs);
    }

    class ImageDownloadTask extends AsyncTask<String,Void, Bitmap> {
        @Override
        protected Bitmap doInBackground(String... params) {
            Bitmap bitmap = null;    //待返回的结果
            String url = params[0];  //获取URL
            URLConnection connection;   //网络连接对象
            InputStream is;    //数据输入流
            try {
                connection = new URL(url).openConnection();
                is = connection.getInputStream();   //获取输入流
                BufferedInputStream buf = new BufferedInputStream(is);
                //解析输入流
                bitmap = BitmapFactory.decodeStream(buf);
                is.close();
                buf.close();
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
            //返回给后面调用的方法
            return bitmap;
        }
        @Override
        protected void onPreExecute() {
            //显示等待圆环
            mProgressBar.setVisibility(View.VISIBLE);
        }
        @Override
        protected void onPostExecute(Bitmap result) {
            //下载完毕，隐藏等待圆环
            mProgressBar.setVisibility(View.GONE);
            mImageView.setImageBitmap(result);
        }
    }
}