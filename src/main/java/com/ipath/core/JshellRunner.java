package com.ipath.core;

import com.ipath.core.banner.JshellBanner;
import com.ipath.core.container.Dispatch;
import com.ipath.shell.JshellLineReader;

/**
 * @Author: <a href="zhangzhiming@gmail.com">IPath</a>
 * @Date: 2018/12/15 22:16
 * @Version 1.0
 */
public class JshellRunner {
    public JshellRunner(){}

    public static void start(int type){

        JshellBanner.banner(type);
         Dispatch ds = new Dispatch();
        ds.init(type);
        JshellLineReader.builder();
    }

}
