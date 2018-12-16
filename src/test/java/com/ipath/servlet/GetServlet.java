package com.ipath.servlet;

import com.ipath.core.JshellRunner;
import com.ipath.core.container.Dispatch;
import org.junit.Test;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.util.Properties;

/**
 * @Author: <a href="zhangzhiming@gmail.com">IPath</a>
 * @Date: 2018/12/15 19:21
 * @Version 1.0
 */
public class GetServlet {
    private Properties properties = new Properties();

    @Test
    public void getProperty(){
        try {
            String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
            InputStream inputStream = new BufferedInputStream(new FileInputStream(path + "application.properties"));
            properties.load(inputStream);
            String scanpackage = properties.getProperty("basescan-package");
            System.out.println(scanpackage);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void getLocation(){
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        System.out.println(path);
    }

    @Test
    public void getUrl(){
        URL url = getClass().getClassLoader().getResource("/");
        System.out.println(url);
    }

    @Test
    public void getDispatch(){
        JshellRunner.start(1);
        Object o = Dispatch.doDispatch("my/test");
        System.out.println(o.toString());
    }
}
