package myzhufeng.mydex.com.myzhufeng.provider;

import android.net.Uri;
import android.provider.BaseColumns;

/**
 * Created by beyond on 2015/10/5.
 */

/*
数据库的各种信息定义，大部分都是常量<br/>
 同样可以用于ContentProvider
 */
public class DatabaseContract {


    public static final String BASE_AUTHORITY="com.mydex.myzhufeng.dataprovider";

   //发现部分“分类”表 的 表名
    public static final String TABLE_DISCOVER_CATEGORIES="d_categories";
    //发现“分类”表字段与定义
    public static class DiscoverCategories implements BaseColumns{
        //字段部分
        //名称字段
        public static final String NAME = "name";
        public static final String TITLE = "title";
        public static final String IS_CHECKED = "is_checked";
        public static final String ORDER_NUM = "order_num";
        public static final String COVER_PATH = "cover_path";
        public static final String SELECTED_SWITCH = "selected_switch";
        public static final String IS_FINISHED = "is_finished";
        public static final String CONTENT_TYPE = "content_type";

    }

    //Uri部分
    // content://com.mydex.myzhufeng.dataprovider/d_categories
 public static final Uri CONTENT_URI=
         Uri.parse("content://"+BASE_AUTHORITY)
         .buildUpon()
         .appendPath(TABLE_DISCOVER_CATEGORIES)
         .build();



}
