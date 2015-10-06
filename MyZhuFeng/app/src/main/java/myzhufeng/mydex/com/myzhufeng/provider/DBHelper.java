package myzhufeng.mydex.com.myzhufeng.provider;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by beyond on 2015/10/5.
 */
public class DBHelper extends SQLiteOpenHelper {

    private static final int DB_VER=1;
    public DBHelper(Context context){
        super(context,"app.db",null,DB_VER);
    }

    //创建表结构
    private static final String CREATE_DISCOVER_CATEGORIES =
            "create table "
                    + DatabaseContract.TABLE_DISCOVER_CATEGORIES
                    + "("
                    + DatabaseContract.DiscoverCategories._ID + " INTEGER PRIMARY KEY,"
                    + DatabaseContract.DiscoverCategories.NAME + " TEXT NOT NULL,"
                    + DatabaseContract.DiscoverCategories.TITLE + " TEXT NOT NULL,"
                    + DatabaseContract.DiscoverCategories.IS_CHECKED + " INTEGER,"
                    + DatabaseContract.DiscoverCategories.ORDER_NUM + " INTEGER,"
                    + DatabaseContract.DiscoverCategories.COVER_PATH + " TEXT,"
                    + DatabaseContract.DiscoverCategories.SELECTED_SWITCH + " INTEGER,"
                    + DatabaseContract.DiscoverCategories.IS_FINISHED + " INTEGER,"
                    + DatabaseContract.DiscoverCategories.CONTENT_TYPE + " TEXT"
                    + ")";

    @Override
    public void onCreate(SQLiteDatabase db) {
        //创建表
        db.execSQL(CREATE_DISCOVER_CATEGORIES);

    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }
}
