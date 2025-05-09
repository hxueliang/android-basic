package com.hxl.gmmultithreading.AsyncTask;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.AsyncTask;
import android.widget.ImageView;

public class BitmapTask extends AsyncTask<Integer, Void, Bitmap> {
    Context context;
    ImageView imageView;
    Integer res;

    public BitmapTask(Context context, ImageView imageView) {
        this.context = context;
        this.imageView = imageView;
    }

    @Override
    protected Bitmap doInBackground(Integer... integers) {
        res = integers[0];

        // 模拟从网络获取图片
        try {
            Thread.sleep(2 * 1000);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
        // 根据图片资源ID，获取到对应的bitmap
        Bitmap bitmap = BitmapFactory.decodeResource(context.getResources(), res);

        return bitmap;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        // 显示加载成功的图片
        imageView.setImageBitmap(bitmap);
    }
}
