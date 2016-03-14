package com.ithenu.lawyer.utils;

import android.widget.Toast;

import com.ithenu.lawyer.App;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.InputStream;

/**
 * Created by shy on 2015/11/8.
 */
public class Utils {
    public static String Token = "76C2vqAL9ULRGy7kurufuYDTL6bbmoho6MzK4f8uoCHZTnmHjShSOX/F+P4WkVp5hXuUwVQjT4MFWocfOyYBVw==";
    public static String url = "http://192.168.6.33:8080/lawyer/service/";
    private static Toast toast;

    /*
    Toast
    @param text
    单例Toast,新的Toast会替换旧的
     */
    public static void Toast(String text) {
        if (toast == null) {
            synchronized (Toast.class) {
                toast = Toast.makeText(App.lawyerApplication, null, Toast.LENGTH_LONG);
            }
        }
        toast.setText(text);
        toast.show();
    }
    public static String readInputStream(InputStream is) {
        byte[] b = new byte[1024];
        int len = 0;
        //创建字节数组输出流，读取输入流的文本数据时，同步把数据写入数组输出流
        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        try {
            while ((len = is.read(b)) != -1) {
                bos.write(b, 0, len);
            }
            //把字节数组输出流里的数据转换成字节数组
            String text = new String(bos.toByteArray());
            return text;
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }
}
