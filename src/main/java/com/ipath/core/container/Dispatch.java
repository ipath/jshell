package com.ipath.core.container;

import com.ipath.core.annotation.JshellController;
import com.ipath.core.annotation.JshellMapping;
import com.ipath.util.StringUtil;

import java.io.*;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.net.URL;
import java.util.*;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;
import java.util.logging.Logger;

/**
 * @Author: <a href="zhangzhiming@gmail.com">IPath</a>
 * @Date: 2018/12/15 18:53
 * @Version 1.0
 */
public class Dispatch{
    private Properties properties = new Properties();
    private List<String> classNames = new ArrayList<>();
    private Map<String,Object> ioc = new HashMap<>();
    private static Map<String,Method> handlerMapping = new HashMap<>();
    private static Map<String,Object> controllerMapping = new HashMap<>();


    public void init(int type){
        try {
            InputStream inputStream = null;
            if(type == 1) {
                String path = this.getClass().getClassLoader().getResource("").getPath();
                //String path = Thread.currentThread().getContextClassLoader().getResource("").getPath();
                //String path = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
                //String jarPath = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
                //System.out.println("path:"+jarPath);
                //jarPath = java.net.URLDecoder.decode(jarPath,"UTF-8");
                 inputStream = new BufferedInputStream(new FileInputStream(path + "/application.properties"));
            }else{
            //URL url = new URL("jar:file"+ jarPath + "!/application.properties");
             inputStream = this.getClass().getClassLoader().getResourceAsStream("application.properties");}
            properties.load(inputStream);
            String scanpackage = properties.getProperty("basescan-package");
            doScan(scanpackage,type);
            doInstance();
            initHandlerMapping();

        } catch (Exception e) {
            e.printStackTrace();
            System.out.println(e.getMessage());
        }
    }

    public void doScanController(){

    }

    public void doScan(String scanpackage,int type) throws IOException{
      /*  String jar = this.getClass().getClassLoader().getResource("/").getPath();
        System.out.println("jar路径"+jar);
      */
      if(type == 2){
      String jarPath = this.getClass().getProtectionDomain().getCodeSource().getLocation().getPath();
      //  System.out.println("jarPath路径："+jarPath);
        JarFile jarFile = new JarFile(jarPath);
        Enumeration<JarEntry> entries = jarFile.entries();
        while(entries.hasMoreElements()){
            JarEntry jarEntry = entries.nextElement();
            String entryName = jarEntry.getName();
          //  System.out.println("输出entryname:"+entryName);
            if(entryName.contains(scanpackage.replaceAll("\\.", "/")) && entryName.contains(".class")){
        //        System.out.println(entryName);
                    String className =  entryName.replace(".class","").replace("/",".");
                    classNames.add(className);
            }

        }
      }else{
        URL url = this.getClass().getClassLoader().getResource("" + scanpackage.replaceAll("\\.", "/"));
        InputStream inputStream = this.getClass().getClassLoader().getResourceAsStream("" + scanpackage.replaceAll("\\.", "/"));
        File file = new File(url.getFile());
        for(File subfile : file.listFiles()){
            if(subfile.isDirectory()){
                doScan(scanpackage + "." + subfile.getName(),type);
            }else{
                String className = scanpackage + "." + subfile.getName().replace(".class","");
                classNames.add(className);
            }
        }
      }
    }

    private void doInstance(){
        if(classNames.isEmpty()){
            return;
        }

        for(String className : classNames){
            try {
                Class<?> clazz = Class.forName(className);
                if(clazz.isAnnotationPresent(JshellController.class)){
                    ioc.put(StringUtil.toLowerFirstWord(clazz.getSimpleName()),clazz.newInstance());
                }

            } catch (Exception e) {
                e.printStackTrace();
                continue;
            }
        }
    }

    private void initHandlerMapping(){
        if(ioc.isEmpty()){
            return;
        }

        for(Map.Entry<String,Object> entry : ioc.entrySet()){
            Class<?> clazz = entry.getValue().getClass();
            if(!clazz.isAnnotationPresent(JshellController.class)){
                continue;
            }

            String baseUrl = null;

            if(clazz.isAnnotationPresent(JshellMapping.class)){
                JshellMapping annotation = clazz.getAnnotation(JshellMapping.class);
                baseUrl = annotation.value();

            }

            for(Method method : clazz.getMethods()){
                if(!method.isAnnotationPresent(JshellMapping.class)){
                    continue;
                }

                JshellMapping methodAnnotation = method.getAnnotation(JshellMapping.class);
                String url = methodAnnotation.value();
                url = baseUrl + "/" + url;

                handlerMapping.put(url,method);
                try {
                    controllerMapping.put(url,clazz.newInstance());
                } catch (InstantiationException e) {
                    e.printStackTrace();
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }

            }


        }

    }

    public static Object doDispatch(String buffer){
        Object invoke = null;
        if(handlerMapping.isEmpty()){
            return null;
        }


        if(!handlerMapping.containsKey(buffer)){
                //res.getWriter().write("404 not found");

        }

        Method method = handlerMapping.get(buffer);
        Class<?>[] parameterTypes = method.getParameterTypes();
        Object[] paramValues = new Object[parameterTypes.length];

        for(int i = 0; i < parameterTypes.length; i++){
            String requestParam = parameterTypes[i].getSimpleName();

            if(requestParam.equals("String")){
                paramValues[i] = buffer;

            }

        }

        try {
            invoke = method.invoke(controllerMapping.get(buffer),paramValues);

        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
        return invoke;
    }
}
