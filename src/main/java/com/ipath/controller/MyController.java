package com.ipath.controller;

import com.ipath.core.annotation.JshellController;
import com.ipath.core.annotation.JshellMapping;
import com.ipath.shell.Shell;
import org.apache.commons.logging.impl.Log4JLogger;
import org.apache.log4j.Logger;

/**
 * @Author: <a href="zhangzhiming@gmail.com">IPath</a>
 * @Date: 2018/12/15 22:01
 * @Version 1.0
 */
@JshellController
@JshellMapping(value = "my")
public class MyController {

    public static Logger logger = Logger.getLogger(MyController.class);
    @JshellMapping(value = "test")
    public String myTest(){

        System.out.println("Welcome to jshell beta");
        return "return test result successful ! the result code is programming..";
    }


}
