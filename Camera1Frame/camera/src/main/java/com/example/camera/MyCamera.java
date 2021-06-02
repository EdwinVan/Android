package com.example.camera;

import android.app.Activity;
import android.content.Intent;
import android.graphics.ImageFormat;

import android.hardware.Camera;

import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

public class MyCamera extends Activity implements SurfaceHolder.Callback{
    private Button btn;        //定义按钮对象
    private Camera mCamera;    //定义camera对象
    private SurfaceView surfaceView; //定义surfaceview对象
    private SurfaceHolder mHolder;   //定义surfaceholder对象
    //创建一个相机拍照回调

    private Camera.PictureCallback mCallback = new Camera.PictureCallback() {
        @Override
        public void onPictureTaken(byte[] data, Camera camera) {
            //获取SD卡根目录
            File appDir = new File(Environment.getExternalStorageDirectory(),"/DCIM/Camera/");
            if (!appDir.exists()){
                appDir.mkdir();
            }
            String fileName = System.currentTimeMillis() + ".jpg";//将获取当前系统设置为照片名称
            File file = new File(appDir, fileName);//创建文件对象

            try{
                // 创建一个输出流对象
                FileOutputStream fos = new FileOutputStream(file);
                // byte数组写入输出流对象
                fos.write(data);
                fos.close();
            } catch (FileNotFoundException e){
                e.printStackTrace();
            } catch (IOException e){
                e.printStackTrace();
            }

            // 将照片插入到系统图库
            try {
                MediaStore.Images.Media.insertImage(MyCamera.this.getContentResolver(),
                        file.getAbsolutePath(),fileName,null);
            } catch (FileNotFoundException e){
                e.printStackTrace();
            }
            MyCamera.this.sendBroadcast(new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE,
                    Uri.parse("file://"+"")));
            Toast.makeText(getApplication(),"照片保存至: "+file,Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MyCamera.this,SeeView.class);

            intent.putExtra("picPath",file.getAbsolutePath());
            startActivity(intent);
            MyCamera.this.finish();
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_camera);

        btn = findViewById(R.id.btn);
        surfaceView=findViewById(R.id.id_pic);
        mHolder = surfaceView.getHolder();
        mHolder.addCallback(this);
        surfaceView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mCamera.autoFocus(null);//点击屏幕实现自动对焦
            }
        });
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
            Camera.Parameters parameters = mCamera.getParameters();
            parameters.setPictureFormat(ImageFormat.JPEG);
            parameters.setPictureSize(800,400);
            parameters.setFocusMode(Camera.Parameters.FOCUS_MODE_AUTO);
            mCamera.takePicture(null,null,mCallback);
            }
        });
    }

    //设置相机预览 设置两个参数
    private void setStartPreview(Camera camera,SurfaceHolder holder)
    {
        try{
            mCamera.setPreviewDisplay(holder);
            mCamera.setDisplayOrientation(90);
            mCamera.startPreview();
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }
    // 释放相机资源
    private void ReleaseCamera(){
        if(mCamera!=null){
            mCamera.stopPreview();
            mCamera.setPreviewCallback(null);
            mCamera.release();
            mCamera = null;
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        // 如果camera对象为空获取到camera
        if (mCamera == null){
            mCamera = Camera.open();

            //判断holder对象不为空 启动预览
            if (mHolder != null)
                setStartPreview(mCamera,mHolder);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        //当主活动暂停时 清空camera
        if (mCamera!=null)
            ReleaseCamera();
    }

    @Override
    public void surfaceCreated(@NonNull SurfaceHolder holder) {
        setStartPreview(mCamera,mHolder);
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {
        mCamera.stopPreview(); //停止预览
        setStartPreview(mCamera,mHolder);
    }

    @Override
    public void surfaceDestroyed(@NonNull SurfaceHolder holder) {
        ReleaseCamera();//销毁时释放camera
    }
}

