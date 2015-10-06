package myzhufeng.mydex.com.myzhufeng.utils;


public final class EncryptUtil {

    static {

    }

    private EncryptUtil() {

    }

   // private static native void sayHello();

    public static String toHex(byte[] data) {
        String ret = null;

        if (data != null) {

            StringBuilder sb = new StringBuilder();

            for (byte b : data) {

                int h, l;

                h = (b >> 4) & 0x0f;

                l = b & 0x0f;

                char ch, cl;

                if(h > 9){
                    ch = (char)('A' + (h - 10));
                }else{
                    ch = (char)('0' + h);
                }

                if(l > 9){
                    cl = (char)('A' + (l - 10));
                }else{
                    cl = (char)('0' + l);
                }

                sb.append(ch).append(cl);

            }

            ret = sb.toString();
            sb = null;

        }

        return ret;
    }

}
