package com.ipath.test;

import com.ipath.core.JshellRunner;
import com.ipath.core.banner.JshellBanner;
import com.ipath.core.container.Dispatch;

/**
 * @Author: <a href="zhangzhiming@gmail.com">IPath</a>
 * @Date: 2018/12/16 22:02
 * @Version 1.0
 */
public class ServletTest {
    public static void main(String[] args) {
        /*JshellRunner.start(1);
        Object o = Dispatch.doDispatch("my/test");
        System.out.println(o.toString());*/
        String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
        System.out.println(path);
        JshellBanner.banner(1);
    }
}
