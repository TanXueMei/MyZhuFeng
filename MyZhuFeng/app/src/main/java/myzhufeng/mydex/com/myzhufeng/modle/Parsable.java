package myzhufeng.mydex.com.myzhufeng.modle;

import android.database.Cursor;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * Created by beyond on 2015/10/5.
 */


/*
定义一个接口，代表实现它的类可以用于数据的解析
 */
public interface Parsable {
    /**
     * 实体类解析JSON数据
     */
    void parserJSON(JSONObject json) throws JSONException;

    /*
     解析Cursor的内容，生成实体的数据字段
     */
    void parseCursor(Cursor cursor);

}
