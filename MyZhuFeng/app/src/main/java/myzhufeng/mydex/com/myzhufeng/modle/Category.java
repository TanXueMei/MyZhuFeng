package myzhufeng.mydex.com.myzhufeng.modle;

import android.content.ContentValues;
import android.database.Cursor;

import org.json.JSONException;
import org.json.JSONObject;

import myzhufeng.mydex.com.myzhufeng.provider.DatabaseContract;

/**
 * Created by beyond on 2015/10/5.
 */


//发现“分类”数据的实体数据

public class Category implements Parsable {
    /*
    {
      "id": 2,
      "name": "music",
      "title": "音乐",
      "isChecked": false,
      "orderNum": 2,
      "coverPath": "http://fdfs.xmcdn.com/group12/M08/17/A0/wKgDXFVxM-LyPrvZAAAGGBNsGas270.png",
      "selectedSwitch": false,
      "isFinished": false,
      "contentType": "album"
    },
     */
    private long id;
    private String name;
    private String title;
    private boolean isCheched;
    //分类中的各个选项在列表中的排序
    private int orderNum;
    //分类中的各个选项对应的图标
    private String coverPath;
    private boolean selectedSwitch;
    private boolean isFinished;
    private String contentType;

    /**
     * 解析实体类数据
     *
     * @param json
     * @throws JSONException
     */
    @Override
    public void parserJSON(JSONObject json) throws JSONException {
        if (json != null) {
            id = json.getLong("id");
            name = json.getString("name");
            title = json.getString("title");
            //可选的boolean值，如果字段不存在，则返回默认的false
            isCheched = json.optBoolean("isCheched");
            orderNum = json.getInt("orderNum");
            //可选的String值，如果不存在，那么返回参数2的值
            coverPath = json.optString("coverPath", null);
            selectedSwitch = json.optBoolean("selectedSwitch");
            isFinished = json.optBoolean("isFinished");
            contentType = json.getString("contentTye");

        }
    }
    /**
     * 解析 Cursor 的内容，生成实体的数据字段
     *
     * @param cursor
     */
    @Override
    public void parseCursor(Cursor cursor) {

        if (cursor != null) {

            int index = cursor.getColumnIndex(DatabaseContract.DiscoverCategories._ID);
            if (index != -1) {
                id = cursor.getLong(index);
            }

            index = cursor.getColumnIndex(DatabaseContract.DiscoverCategories.NAME);
            if (index != -1) {
                name = cursor.getString(index);
            }

            index = cursor.getColumnIndex(DatabaseContract.DiscoverCategories.TITLE);
            if (index != -1) {
                title = cursor.getString(index);
            }

            index = cursor.getColumnIndex(DatabaseContract.DiscoverCategories.IS_CHECKED);
            if (index != -1) {
                isCheched = cursor.getInt(index) == 1;  // = 1 为 true
            }

            index = cursor.getColumnIndex(DatabaseContract.DiscoverCategories.ORDER_NUM);
            if (index != -1) {
                orderNum = cursor.getInt(index);
            }

            index = cursor.getColumnIndex(DatabaseContract.DiscoverCategories.COVER_PATH);
            if (index != -1) {
                coverPath = cursor.getString(index);
            }

            index = cursor.getColumnIndex(DatabaseContract.DiscoverCategories.SELECTED_SWITCH);
            if (index != -1) {
                selectedSwitch = cursor.getInt(index) == 1;
            }

            index = cursor.getColumnIndex(DatabaseContract.DiscoverCategories.IS_FINISHED);
            if (index != -1) {
                isFinished = cursor.getInt(index) == 1;
            }

            index = cursor.getColumnIndex(DatabaseContract.DiscoverCategories.CONTENT_TYPE);
            if (index != -1) {
                contentType = cursor.getString(index);
            }

        }

    }
    /**
     * 为数据库的添加准备 ContentValues
     *
     * @return
     */
    public ContentValues prepareValues() {
        ContentValues ret = new ContentValues();
        ret.put(DatabaseContract.DiscoverCategories._ID, id);
        ret.put(DatabaseContract.DiscoverCategories.NAME, name);
        ret.put(DatabaseContract.DiscoverCategories.TITLE, title);
        ret.put(DatabaseContract.DiscoverCategories.IS_CHECKED, isCheched? 1 : 0);
        ret.put(DatabaseContract.DiscoverCategories.ORDER_NUM, orderNum);
        ret.put(DatabaseContract.DiscoverCategories.COVER_PATH, coverPath);
        ret.put(DatabaseContract.DiscoverCategories.SELECTED_SWITCH, selectedSwitch ? 1 : 0);
        ret.put(DatabaseContract.DiscoverCategories.IS_FINISHED, isFinished ? 1 : 0);
        ret.put(DatabaseContract.DiscoverCategories.CONTENT_TYPE, contentType);
        return ret;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public boolean isCheched() {
        return isCheched;
    }

    public void setIsCheched(boolean isCheched) {
        this.isCheched = isCheched;
    }

    public int getOrderNum() {
        return orderNum;
    }

    public void setOrderNum(int orderNum) {
        this.orderNum = orderNum;
    }

    public String getCoverPath() {
        return coverPath;
    }

    public void setCoverPath(String coverPath) {
        this.coverPath = coverPath;
    }

    public boolean isSelectedSwitch() {
        return selectedSwitch;
    }

    public void setSelectedSwitch(boolean selectedSwitch) {
        this.selectedSwitch = selectedSwitch;
    }

    public boolean isFinished() {
        return isFinished;
    }

    public void setIsFinished(boolean isFinished) {
        this.isFinished = isFinished;
    }

    public String getContentType() {
        return contentType;
    }

    public void setContentType(String contentType) {
        this.contentType = contentType;
    }
}
