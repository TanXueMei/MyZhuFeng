package myzhufeng.mydex.com.myzhufeng.tasks;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.os.AsyncTask;
import android.view.View;
import android.widget.ImageView;

import java.lang.ref.WeakReference;

import myzhufeng.mydex.com.myzhufeng.cache.FileCache;
import myzhufeng.mydex.com.myzhufeng.cache.ImageCache;
import myzhufeng.mydex.com.myzhufeng.http.HttpUtil;

/**
 * Created by beyond on 2015/10/6.
 */
public class ImageLoadTask extends AsyncTask<String, Void, Bitmap> {

    // 使用弱引用包裹 ImageView ，支持GC，释放之后，不会在设置图片
    // 支持一部分释放操作：Activity 结束、ListView 销毁
    private final WeakReference<View> imageViewReference;
    private String url;

    public ImageLoadTask(View imageView) {
        imageViewReference = new WeakReference<View>(imageView);
    }

    @Override
    protected Bitmap doInBackground(String... params) {
        Bitmap ret = null;

        if (params != null && params.length > 0) {
            url = params[0];

            ////////////////////////////////////////////

            int picWidth = 0;

            int picHeight = 0;

            int argc = params.length;

            try {
                // 可选参数的执行方式
                switch (argc) {
                    case 2:     // url , width
                        picWidth = Integer.parseInt(params[1]);
                        picHeight = picWidth;
                        break;
                    case 3:     // url , width , height
                        picWidth = Integer.parseInt(params[1]);
                        picHeight = Integer.parseInt(params[2]);
                        break;
                }
            } catch (Exception ex) {
                ex.printStackTrace();
                picWidth = 0;
                picHeight = 0;
            }

            ////////////////////////////////////////////


            // 0. 先从内存查找

            ImageCache imageCache = ImageCache.getInstance();

            Bitmap bitmap = imageCache.get(url);

            if (bitmap == null) { // 内存缓存不存在

                // 1. 文件缓存，是否存在
                byte[] bytes = FileCache.getInstance().load(url);

                if (bytes == null) {  // 文件不存在，需要从网络下载

                    bytes = HttpUtil.doGet(url);

                    // 2. 存文件缓存
                    FileCache.getInstance().save(url, bytes);

                } // else 存在，直接解码

                // 解码字节数组
                // TODO 检查图片尺寸是否过大，过大就缩小；减少内存
                ret = loadScaledBitmap(bytes, picWidth, picHeight);

                // 释放内存，字节数组
                bytes = null;

                imageCache.put(url, ret);

            } else {  // 内存缓存存在

                ret = bitmap;

            }

        }
        return ret;
    }

    /**
     * 针对图片数据进行缩小转换，（二次采样）
     *
     * @param data     实际数据
     * @param toWidth  转换后的宽度
     * @param toHeight 转换后的高度
     * @return Bitmap 经过缩小的Bitmap
     */
    private static Bitmap loadScaledBitmap(byte[] data, int toWidth, int toHeight) {
        Bitmap ret = null;

        // 1. 只获取尺寸，不生成图片 (使用 Options 来设置)

        // Options 用来进行解码的配置，对象内部的变量，就会传给底层解码
        BitmapFactory.Options options = new BitmapFactory.Options();

        // 告诉解码器，只进行图片尺寸的获取，并不会在 decode方法，返回 Bitmap， 不会占用内存；
        options.inJustDecodeBounds = true;

        BitmapFactory.decodeByteArray(data, 0, data.length, options);


        int picWidth = options.outWidth;
        int picHeight = options.outHeight;

        // 2. 缩小图片 (使用 Options 进行缩小的采样)

        options.inJustDecodeBounds = false; // 设置为false，代表真实解码图片，返回 Bitmap

        // 图片采样比率，要求 >= 1，
        // 当 设置为 2 的时候，代表宽度 变为 二分之一
        //                  代表高度 变为 二分之一
        //                  代表Bitmap 缩小为 原始图片的 四分之一；
        // 如果设置为 4，内容缩小为 十六分之一
//        options.inSampleSize = 2;

        options.inSampleSize = calculateInSampleSize(options, toWidth, toHeight);

        // TODO 其他的设置优化策略

        options.inPurgeable = true;

        options.inPreferredConfig = Bitmap.Config.RGB_565; // 降低一个像素的颜色字节数

        // 已经指定了 采样比率。
        ret = BitmapFactory.decodeByteArray(data, 0, data.length, options);


        return ret;
    }

    /**
     * 计算缩放比率
     *
     * @param options
     * @param reqWidth
     * @param reqHeight
     * @return
     */
    public static int calculateInSampleSize(
            BitmapFactory.Options options, int reqWidth, int reqHeight) {
        // Raw height and width of image
        final int height = options.outHeight;
        final int width = options.outWidth;
        int inSampleSize = 1;

        if(reqWidth > 0 && reqHeight > 0) {

            if (height > reqHeight || width > reqWidth) {

                final int halfHeight = height / 2;
                final int halfWidth = width / 2;

                // Calculate the largest inSampleSize value that is a power of 2 and keeps both
                // height and width larger than the requested height and width.
                while ((halfHeight / inSampleSize) >= reqHeight
                        && (halfWidth / inSampleSize) >= reqWidth) {
                    inSampleSize *= 2;
                }
            }
        }

        return inSampleSize;
    }

    @Override
    protected void onPostExecute(Bitmap bitmap) {
        if (bitmap != null) {
            View view = imageViewReference.get();
            if (view != null) {

                Object tag = view.getTag();

                boolean match = true;

                if (tag != null) {
                    if (tag instanceof String) {
                        String str = (String) tag;
                        match = url.equals(str);
                    }
                }

                if (match) {
                    if (view instanceof ImageView) {
                        // TODO 检测是否需要Tag处理错位
                        ((ImageView) view).setImageBitmap(bitmap);
                    } else {
                        //noinspection deprecation
                        view.setBackgroundDrawable(new BitmapDrawable(bitmap));
                    }
                }
            }
        }
    }
}

