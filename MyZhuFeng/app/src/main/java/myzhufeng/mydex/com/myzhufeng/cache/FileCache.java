package myzhufeng.mydex.com.myzhufeng.cache;

import android.content.Context;
import android.os.Environment;
import java.io.*;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

import myzhufeng.mydex.com.myzhufeng.utils.EncryptUtil;
import myzhufeng.mydex.com.myzhufeng.utils.StreamUtil;

/**
 * 文件缓存管理器
 */
public class FileCache {

    private static FileCache ourInstance;

    /**
     * 利用 Context 来初始化 FileCache 单例对象
     * @param context
     * @return
     */
    public static FileCache createInstance(Context context){
        if (ourInstance == null) {
            ourInstance = new FileCache(context);
        }
        return ourInstance;
    }

    /**
     * 获取单例对象，这个方法调用之前必须经过 createInstance() 创建实例
     * @return
     */
    public static FileCache getInstance() {
        if (ourInstance == null) {
            throw new IllegalStateException("Please invoke createInstance(Context) before this method.");
        }
        return ourInstance;
    }

    private final Context context;

    private FileCache(Context context) {
        this.context = context;
    }

    /////////////////////////////////////////

    // 1. 映射 url -> 文件名

    /**
     * 将网址应设为唯一的文件名
     * @param url
     * @return String 采用MD5算法
     */
    private static String mapFile(String url){
        String ret = null;
        if (url != null) {

            // 映射

            try {
                // 消息摘要工具
                MessageDigest digest = MessageDigest.getInstance("MD5");

                // 计算出的消息摘要
                byte[] data = digest.digest(url.getBytes());

                ret = EncryptUtil.toHex(data);

            } catch (NoSuchAlgorithmException e) {
                e.printStackTrace();
            }

        }
        return ret;
    }

    ////////////////////////////////////////////

    // 2. 文件的存储和加载

    /**
     * 获取缓存文件夹，支持 外部存储和内部存储
     * @return
     */
    private File getCacheFolder(){
        File ret = null;

        String state = Environment.getExternalStorageState();

        if(Environment.MEDIA_MOUNTED.equals(state)){
            // 外部存储有效
            ret = context.getExternalCacheDir();   // 外部存储缓存
        }else{
            ret = context.getCacheDir();           // 内部缓存
        }

        return ret;
    }

    /**
     * 加载缓存数据
     * @param url
     * @return
     */
    public byte[] load(String url){
        byte[] ret = null;
        if(url != null){
            File folder = getCacheFolder();

            String fileName = mapFile(url);

            File f = new File(folder, fileName);

            if(f.exists() && f.canRead()){
                FileInputStream fin = null;

                try {

                    // 读文件

                    fin = new FileInputStream(f);

                    ret = StreamUtil.readStream(fin);

                } catch (FileNotFoundException e) {
                    e.printStackTrace();
                } finally {
                    StreamUtil.close(fin);
                }
            }
        }
        return ret;
    }

    public void save(String url, byte[] data){
        if (url != null && data != null && data.length > 0) {

            File folder = getCacheFolder();

            String fileName = mapFile(url);

            File f = new File(folder, fileName);

            FileOutputStream fout = null;

            try {

                fout = new FileOutputStream(f);

                fout.write(data);

            } catch (IOException e) {
                e.printStackTrace();
            } finally {
                StreamUtil.close(fout);
            }

        }
    }

}
