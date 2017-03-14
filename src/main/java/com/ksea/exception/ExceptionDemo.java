package com.ksea.exception;

/**
 * Created by mexican on 2017/3/14.
 */
public class ExceptionDemo {
    public static void main(String[] args) {
        System.out.println(myTest());

    }

    public static int myTest() {
        try {
            int a = 10;
            int b = 2;
            int c = 0;
            return (c = a * b);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            System.out.println("finally被执行!");
        }

        return 0;
    }

    /*
    * 执行结果:
    *           finally被执行!
                20
    * */
}
