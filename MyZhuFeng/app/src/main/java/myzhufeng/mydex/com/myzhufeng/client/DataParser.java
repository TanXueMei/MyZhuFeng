package myzhufeng.mydex.com.myzhufeng.client;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.LinkedList;
import java.util.List;

import myzhufeng.mydex.com.myzhufeng.modle.Category;

/**
 * Created by beyond on 2015/10/5.
 */


//用于解析实体类，讲JSON转换为业务逻辑需要用得对象

public final class DataParser {

    private DataParser(){}
    ////////////////////////////////////////////////////////////
      //解析发现中的“分类”数据
    public static List<Category> parseDiscoverCategories(JSONObject json) {
        List<Category> list=null;
       if(json!=null){
           // 使用 try...catch 为了尽可能保存正确的数据，
           // 出错之后，后续的数据不再解析
           try {
               JSONArray array=json.getJSONArray("list");
                int len=array.length();
               if(len>0){
                   list=new LinkedList<Category>();
                   for(int i=0;i<len;i++){
                       JSONObject jsonObject=array.getJSONObject(i);
                       Category category=new Category();
                       category.parserJSON(jsonObject);
                       list.add(category);
                   }
               }

           } catch (JSONException e) {
               e.printStackTrace();
           }

       }

        return list;

    }
}
