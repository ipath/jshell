package com.ipath.core.banner;

import java.io.*;
import java.util.stream.Collectors;

/**
 * @Author: <a href="zhangzhiming@gmail.com">IPath</a>
 * @Date: 2018/12/16 22:21
 * @Version 1.0
 */
public class JshellBanner {
    public static void banner( int type){
        InputStream inputStream = null;
        try {
            if(type == 1){
                inputStream = Thread.currentThread().getContextClassLoader().getResource("banner.txt").openStream();
            }else{
                inputStream = Thread.currentThread().getContextClassLoader().getResourceAsStream("banner.txt");
            }

            ByteArrayOutputStream result = new ByteArrayOutputStream();
            byte[] buffer = new byte[1024];
            int length;
            while((length = inputStream.read(buffer)) != -1){
                result.write(buffer,0,length);
            }

            System.out.println(result.toString());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
