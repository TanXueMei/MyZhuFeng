package myzhufeng.mydex.com.myzhufeng.cache;

/**
 * Created with IntelliJ IDEA.
 * User: vhly[FR]
 * Date: 15/9/25
 * Email: vhly@163.com
 */

import android.graphics.Bitmap;
import android.support.v4.util.LruCache;

import java.lang.ref.SoftReference;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * 图片内存缓存
 */
public class ImageCache {
    private static ImageCache ourInstance;

    public static ImageCache getInstance() {

        if (ourInstance == null) {
            ourInstance = new ImageCache();
        }

        return ourInstance;
    }

    /**
     * 采用 LRU算法，存储 Url -> Bitmap的映射
     * 这个对象，允许设置 存储对象占用的最大内存空间，或者是 条目最大个数
     */
    private final LruCache<String, Bitmap> cache;

    /**
     * 使用 HashMap 来提高内存的利用率
     */
    private final HashMap<String , SoftReference<Bitmap>> hashCache;

    private ImageCache() {
        // 1. 创建 LruCache
        cache = new LruCache<String, Bitmap>(30);
        hashCache = new LinkedHashMap<String, SoftReference<Bitmap>>();
    }

    public Bitmap get(String url) {
        Bitmap ret = null;

        // TODO 获取内存对象

        if (url != null) {

            ret = cache.get(url);

            if(ret == null){ // 如果 LruCache 没有，那就看看 hashCache
                SoftReference<Bitmap> reference = hashCache.get(url);
                if (reference != null) {

                    ret = reference.get();   // 获取引用的对象

                    if(ret == null){
                        hashCache.remove(url);
                    }else{ // hashCache 有  LruCache 没有
                        cache.put(url, ret);
                    }
                }
            }

        }

        return ret;
    }

    public void put(String url, Bitmap bitmap) {
        if (url != null && bitmap != null) {
            // TODO 保存内存对象

            cache.put(url, bitmap);

            hashCache.put(url, new SoftReference<Bitmap>(bitmap));

        }
    }
}
