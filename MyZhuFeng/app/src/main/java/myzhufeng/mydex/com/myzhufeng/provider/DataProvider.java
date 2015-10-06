package myzhufeng.mydex.com.myzhufeng.provider;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.net.Uri;

/**
 * Created by beyond on 2015/10/5.
 */

//用于本地数据的访问
public class DataProvider extends ContentProvider{
    private SQLiteOpenHelper dbhelper;
    public DataProvider(){}

    private static final int CODE_DISCOVER_CATEGORIES=1;

    //用于进行Uri匹配工具
    private static final UriMatcher matcher;
    static {

        matcher=new UriMatcher(0);
        //添加匹配规则

     matcher.addURI(
             DatabaseContract.BASE_AUTHORITY,//清单文件中注册的provider
             DatabaseContract.TABLE_DISCOVER_CATEGORIES,//分类表的表名
             CODE_DISCOVER_CATEGORIES  //若匹配成功，则返回码，定义为了常量
     );

    }


    @Override
    public boolean onCreate() {
        dbhelper=new DBHelper(getContext());
        return true;
    }

    @Override
    public Cursor query(Uri uri, String[] projection, String selection, String[] selectionArgs, String sortOrder) {

       Cursor cursor=null;
        if(uri!=null){
            int code=matcher.match(uri);
            SQLiteDatabase db=dbhelper.getReadableDatabase();
            switch (code){
                case CODE_DISCOVER_CATEGORIES:
                    cursor=db.query(
                            DatabaseContract.TABLE_DISCOVER_CATEGORIES,
                            projection,
                            selection,
                            selectionArgs,
                            null,
                            null,
                            sortOrder
                    );
                    break;
            }

        }


        return cursor;
    }

    @Override
    public String getType(Uri uri) {
        return null;
    }

    @Override
    public Uri insert(Uri uri, ContentValues values) {
        Uri uri1=null;
        if(uri!=null&&values!=null){
            if(values.size()>0){
                int code=matcher.match(uri);
                SQLiteDatabase db=dbhelper.getWritableDatabase();
                long rid=-1;
                switch (code){
                    case CODE_DISCOVER_CATEGORIES://发现中的“分类”
                        rid=db.replace(
                                DatabaseContract.TABLE_DISCOVER_CATEGORIES,
                                null,
                                values
                        );
                        break;

                }
                db.close();
                if(rid!=-1){
                    uri1= ContentUris.withAppendedId(uri,rid);
                }
            }

        }

        return uri1;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }
}
