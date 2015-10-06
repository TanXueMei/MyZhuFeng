package myzhufeng.mydex.com.myzhufeng.utils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.Reader;
import java.io.Writer;

/**
 * Created by beyond on 2015/10/6.
 */

//IO流操作工具类
public class StreamUtil {

    private StreamUtil(){}
    ///////////////////////////////////////////////////
    //关流的总和类
    public static void close(Object stream){
      if(stream!=null){
          try {
                 if(stream instanceof InputStream){
                  ((InputStream) stream).close();
                 } else if(stream instanceof OutputStream){
                     ((OutputStream) stream).close();
                 }else if(stream instanceof Reader){
                     ((Reader) stream).close();
                 }else if(stream instanceof Writer){
                     ((Writer) stream).close();
                 }

          }catch (IOException e) {
              e.printStackTrace();
          }
      }
    }
   //////////////////////////////////////////////////////
      //读字节流，返回字节数组
  public static byte[] readStream(InputStream in){
      byte[] bytes=null;
      if (in!=null){
          ByteArrayOutputStream bout=new ByteArrayOutputStream();
          try {
              readStream(in,bout);
          } catch (IOException e) {
              e.printStackTrace();
          }
          bytes=bout.toByteArray();
          close(bout);
          bout=null;
      }

      return bytes;
  }
   //输入流与字节流的转换
   @SuppressWarnings("WeakerAccess")
    private static long readStream(InputStream in, ByteArrayOutputStream bout) throws IOException {
        long lg=0;
        if(in!=null&&bout!=null){
            byte[] buf=new byte[128];
            int len=0;
            while (true){
                len=in.read(buf);
                if(len==-1){
                    break;
                }
                bout.write(buf,0,len);
                lg+=len;
            }
            buf=null;

        }
        return lg;
    }

}
