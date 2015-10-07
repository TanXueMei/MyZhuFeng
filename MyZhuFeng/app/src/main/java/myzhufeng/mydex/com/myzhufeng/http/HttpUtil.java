package myzhufeng.mydex.com.myzhufeng.http;

import android.os.Build;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;

import java.net.URL;
import java.util.HashMap;
import java.util.zip.GZIPInputStream;

import myzhufeng.mydex.com.myzhufeng.utils.StreamUtil;

/**
 * Created by beyond on 2015/10/6.
 */

/*
   HTTP 网络请求工具类，支持GET POST PUT DELETE

 */
//把网址url，进行内容的读取，返回字节数组
            //不进行内容的读取，直接返回输入流
public final class HttpUtil {
    //设置网络连接超时 5s的常量
    private static final int TIMEOUT_CONNECT=5000;
    //设置读取网络连接超时 1min的常量
    private static  final int TIMEOUT_READ=60000;
    //设置User-Agent字段“ting_4.1.7(手机型号，AndroidAPILevel)
    private static final String USER_AGENT="ting_4.1.7("+",Android"+ Build.VERSION.SDK_INT+")";

    private HttpUtil(){}

//////////////////////////////////////////////////////

    /**
     * GET请求，不进行内容的读取，直接返回输入流InputStream
     * @param url
     * @return InputStream
     */
  public static InputStream doGetInputStream(String url){
      InputStream inputStream=null;
     if(url!=null){
         try {
             //加载网络数据
             URL u=new URL(url);
             HttpURLConnection conn=(HttpURLConnection)u.openConnection();
             conn.setRequestMethod("GET");
             conn.connect();
             InputStream in=null;
             int code=conn.getResponseCode();
             if(code==HttpURLConnection.HTTP_OK){
                //响应部分
                 in=conn.getInputStream();
                 //检查内容是否经过压缩
                 String encoding=conn.getContentEncoding();
                 if("gzip".equals(encoding)){
                     in=new GZIPInputStream(in);
                 }
                 inputStream=in;
             }

         } catch (IOException e) {
             e.printStackTrace();
         }
     //因为返回的是输入流，所以不能关闭输入流
     }
      return  inputStream;
  }
/////////////////////////////////////////////////////

    /**
     * GET请求，返回字节数组。
     * 实现HttpURLConnetion的简单操作
     * @param url
     * @return byte[]
     */
    public static byte[] doGet(String url){
        byte[] bytes=null;
        HttpURLConnection conn=null;
        InputStream inputStream=null;
      if(url!=null){
          try {
              URL u=new URL(url);
              conn= (HttpURLConnection)u.openConnection();
              conn.setRequestMethod("GET");
              int code=conn.getResponseCode();
              if(code==HttpURLConnection.HTTP_OK){
                  inputStream=conn.getInputStream();
                  String encoding=conn.getContentEncoding();
                  if("gzip".equals(encoding)){
                      inputStream=new GZIPInputStream(inputStream);
                  }
                  bytes= StreamUtil.readStream(inputStream);

              }

          }  catch (IOException e) {
              e.printStackTrace();
          }finally {
              StreamUtil.close(inputStream);
              inputStream=null;
              StreamUtil.close(conn);
              conn=null;

          }
      }
        return bytes;
    }
//////////////////////////////////////////////////////////

    /**
     * HTTP POST 请求， 参数 params 用于提交数据
     * 只能够提交 key=value&key=value 的形式
     *
     * @param url
     * @param params
     * @return
     */
    public static byte[] doPost(String url,HashMap<String,String> params){
        byte[] bytes=null;
        if(url!=null){
            //TODO 进行POST请求
        }
        return bytes;
    }
//////////////////////////////////////////////////////////
    /**
     * 实现 HttpURLConnection 的简单的 Delete 请求
     *
     * @param url
     * @return
     */
    public static byte[] doDelete(String url) {
        byte[] ret = null;
        if (url != null) {
            // TODO 加载网络数据
        }
        return ret;
    }
    //////////////////////////////////////////////////

    /**
     * HTTP PUT 请求， 参数 params 用于提交数据
     * 只能够提交 key=value&key=value 的形式
     *
     * @param url
     * @param params
     * @return
     */
    public static byte[] doPut(String url, HashMap<String, String> params) {
        byte[] ret = null;
        if (url != null) {
            // TODO 进行 Put 请求
        }
        return ret;
    }

    ////////////////////////////////////////////////////////
      //私有方法
    /**
     * 设置通用的 Http 请求信息：超时、请求头
     *
     * @param conn
     */
    private static void setCommonHttpInfo(HttpURLConnection conn){
     //网络超时设置
        conn.setConnectTimeout(TIMEOUT_CONNECT);
        conn.setReadTimeout(TIMEOUT_READ);
      //设置HTTP请求头信息

        /*设置 User-Agent 字段 "ting_4.1.7(手机型号, AndroidAPILevel)"
                            ting_4.1.7(MI2, Android17)
          听书服务器用这个字段，判断是否是正常软件的请求
        */
        conn.setRequestProperty("User-Agent",USER_AGENT);

        // 代表客户端能够接受服务器传回的什么内容类型的格式
        conn.setRequestProperty("Accept", "application/json, application/*, image/*, text/*, */*");

        // 设置客户端支持的压缩格式（内容编码 对应服务器返回的字段 Content-Encoding ）
        conn.setRequestProperty("Accept-Encoding", "gzip");

    }

}
