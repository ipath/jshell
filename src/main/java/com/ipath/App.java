package com.ipath;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        Properties properties = new Properties();
        System.out.println( "Hello World!" );
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
}
