package myzhufeng.mydex.com.myzhufeng.client;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;

import myzhufeng.mydex.com.myzhufeng.http.HttpUtil;

/**
 * Created by beyond on 2015/10/5.
 */

//客户端部分“发现”模块中的 “推荐”，“分类”，“直播”，“榜单”，”主播“这几个模块的API
public class DiscoverClientAPI {
    //API入口点
    private static final String API_POINT = "http://mobile.ximalaya.com/mobile";

    private DiscoverClientAPI() {
    }
    //////////////////////////////////////////
    /**
     * 接口: 10<br/>
     * 网址: /discovery/v1/tabs?device=android<br/>
     * <font color="red">功能: 获取发现模块 主Tab 的标题信息</font><br/>
     *
     * @return JSONObject 转换的 JSONObject 对象
     */
    public static JSONObject getDiscoverTabs(){
        JSONObject jsonObject=null;
        String url=API_POINT+" /discovery/v1/tabs?device=android";
        byte[] bytes= HttpUtil.doGet(url);
        if(bytes!=null){
            try {
                //字节数组转字符串
                String str=new String(bytes,"UTF-8");
                //字符串转JSONObject
                jsonObject=new JSONObject(str);

            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }
        }
        return jsonObject;
    }



    /*
    12. 发现部分的分类
URL:
    功能：获取发现模块“分类”界面的数据
    http://mobile.ximalaya.com/mobile为API入口点
	http://mobile.ximalaya.com/mobile/discovery/v1/categories?device=android&picVersion=10&scale=2

    http://mobile.ximalaya.com/mobile/discovery/v1/categories
参数部分必须按照以下顺序
	device	android
	picVersion	10
	scale	2
     */
public static JSONObject getDiscoveryCategories(){
    JSONObject jsonObject=null;
    String url=API_POINT+"/discovery/v1/categories?device=android&picVersion=10&scale=2";
    byte[] bytes=HttpUtil.doGet(url);
    if(bytes!=null){
        try {
            String str=new String(bytes,"UTF-8");
            jsonObject=new JSONObject(str);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        } catch (JSONException e) {
            e.printStackTrace();
        }

    }
    return  jsonObject;
}


}
