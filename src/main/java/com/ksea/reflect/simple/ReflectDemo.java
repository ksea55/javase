package com.ksea.reflect.simple;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.lang.reflect.Method;
import java.sql.Ref;
import java.util.Properties;
import java.util.StringTokenizer;

/**
 * Created by mexican on 2017/3/21.
 */
public class ReflectDemo {
    public static void main(String[] args) throws Exception {

        //首先在配置文件,读取配置文件信息
        Properties properties = new Properties();


        //获取配置文件path
        String path = ReflectDemo.class.getClassLoader().getClass().getResource("/").getPath();
        path += "com/ksea/reflect/simple/config.properties";
        System.out.println(path); /*此刻的运行结果: /D:/dev.ksea/dev.file/javase/target/classes/com/ksea/reflect/simple/config.properties*/

        //去除前面的/
        path = path.substring(1);
        System.out.println(path); //运行结果: D:/dev.ksea/dev.file/javase/target/classes/com/ksea/reflect/simple/config.properties

        properties.load(new FileReader(path));

        String className = properties.getProperty("className");
        String methodName = properties.getProperty("methodName");


        //开始进行反射
        Class clz = Class.forName(className);
        //实例化对象
        Object object = clz.newInstance();

        //获取方法
        Method method = clz.getDeclaredMethod(methodName, String.class, String.class);

        //执行方法
        method.invoke(object, "张学友", "唱歌"); //大家好,我叫:张学友、我喜欢:唱歌运行结果

    }
}
