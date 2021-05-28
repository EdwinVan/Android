package com.example.camera;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private ImageView iv;//创建图像视图
    private String filePath;//定义一个文件路径

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        iv=findViewById(R.id.image);//绑定图像视图
        //获取SD卡路径
        filePath= Environment.getExternalStorageDirectory().getPath();
        filePath +="/image.png";//路径加上文件名
    }

    // 打开系统相机
    public void OpenCamera(View view)
    {
        Intent intent=new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        //新建intent并制定
        // startActivity(intent);
        startActivityForResult(intent, 0x1);//有回调的启动Activity
    }

    //调用系统相机返回原始图片
    public void CameraImage(View view)
    {
        Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        Uri picuri = Uri.fromFile(new File(filePath));//创建一个uri将路径传入进去
        intent.putExtra(MediaStore.EXTRA_OUTPUT,picuri);// 更改系统默认存储路径
        startActivityForResult(intent,0x2);//有返回数据的调用
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult(requestCode,resultCode, data);
        if(resultCode == RESULT_OK)
        {
            if(requestCode == 0x1){
                Bundle bundle = data.getExtras(); //新建bundle对象获取数据
                Bitmap bit=(Bitmap)bundle.get("data"); //从bundle对象中获取图像信息
                iv.setImageBitmap(bit); //设置图像视图显示图片
            }
            else if(requestCode==0x2)
            {
                FileInputStream fis = null;//定义一个输入流对象
                try{
                    //创建一个文件输入流并初始化
                    fis = new FileInputStream(filePath);
                    //将获取到的文件输入流转换成一个图像
                    Bitmap bitmap= BitmapFactory.decodeStream(fis);
                    iv.setImageBitmap(bitmap);//设置图像视图显示图片
                } catch (FileNotFoundException e)
                {
                    e.printStackTrace();
                } finally {
                    try{
                        fis.close();//关闭流对象
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}