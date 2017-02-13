package com.ksea.thread;

/**
 * Created by mexican on 2017/2/13.
 * 设置线程守护：通过方法 setDaemon
 * public final void setDaemon(boolean on)将该线程标记为守护线程或用户线程。当正在运行的线程都是守护线程时，Java 虚拟机退出。
 * 该方法必须在启动线程前调用,也就是在start()之前
 *
 * 最好理解线程守护的含义：坦克大战，可以将绿色坦克，黄色坦克看成是守护线程，中间的堡垒老鹰是刘备一样，当堡垒爆炸之后，绿色坦克与黄色坦克就会自动毁灭，游戏结束
 */
public class ThreadDemo6 {
    public static void main(String[] args) {
        ThreadDaemon td1 = new ThreadDaemon();
        ThreadDaemon td2 = new ThreadDaemon();

        /*设置线程守护*/
        td1.setDaemon(true);
        td2.setDaemon(true);
        /*设置线程名称*/
        td1.setName("关羽");
        td2.setName("张飞");

        /*启动线程*/
        td1.start();
        td2.start();


        /*将main主线程设置名称*/
        Thread.currentThread().setName("刘备");
        for (int i = 0; i < 5; i++) {
            System.out.println("当前线程【" + Thread.currentThread().getName() + "】执行的是：" + i);
        }


        /**此处为没有设置线程守护时候的运行结果
         *
         *
         *
         * "C:\Program Files\Java\jdk1.8.0_91\bin\java" -Didea.launcher.port=7532 "-Didea.launcher.bin.path=D:\dev.ksea\dev.tools\IntelliJ IDEA 2016.3\bin" -Dfile.encoding=UTF-8 -classpath "C:\Program Files\Java\jdk1.8.0_91\jre\lib\charsets.jar;C:\Program Files\Java\jdk1.8.0_91\jre\lib\deploy.jar;C:\Program Files\Java\jdk1.8.0_91\jre\lib\ext\access-bridge-64.jar;C:\Program Files\Java\jdk1.8.0_91\jre\lib\ext\cldrdata.jar;C:\Program Files\Java\jdk1.8.0_91\jre\lib\ext\dnsns.jar;C:\Program Files\Java\jdk1.8.0_91\jre\lib\ext\jaccess.jar;C:\Program Files\Java\jdk1.8.0_91\jre\lib\ext\jfxrt.jar;C:\Program Files\Java\jdk1.8.0_91\jre\lib\ext\localedata.jar;C:\Program Files\Java\jdk1.8.0_91\jre\lib\ext\nashorn.jar;C:\Program Files\Java\jdk1.8.0_91\jre\lib\ext\sunec.jar;C:\Program Files\Java\jdk1.8.0_91\jre\lib\ext\sunjce_provider.jar;C:\Program Files\Java\jdk1.8.0_91\jre\lib\ext\sunmscapi.jar;C:\Program Files\Java\jdk1.8.0_91\jre\lib\ext\sunpkcs11.jar;C:\Program Files\Java\jdk1.8.0_91\jre\lib\ext\zipfs.jar;C:\Program Files\Java\jdk1.8.0_91\jre\lib\javaws.jar;C:\Program Files\Java\jdk1.8.0_91\jre\lib\jce.jar;C:\Program Files\Java\jdk1.8.0_91\jre\lib\jfr.jar;C:\Program Files\Java\jdk1.8.0_91\jre\lib\jfxswt.jar;C:\Program Files\Java\jdk1.8.0_91\jre\lib\jsse.jar;C:\Program Files\Java\jdk1.8.0_91\jre\lib\management-agent.jar;C:\Program Files\Java\jdk1.8.0_91\jre\lib\plugin.jar;C:\Program Files\Java\jdk1.8.0_91\jre\lib\resources.jar;C:\Program Files\Java\jdk1.8.0_91\jre\lib\rt.jar;D:\dev.ksea\dev.file\javase\target\classes;D:\dev.ksea\dev.tools\IntelliJ IDEA 2016.3\lib\idea_rt.jar" com.intellij.rt.execution.application.AppMain com.ksea.thread.ThreadDemo6
         当前线程【刘备】执行的是：0
         当前线程【关羽】正在执行：0
         当前线程【刘备】执行的是：1
         当前线程【关羽】正在执行：1
         当前线程【刘备】执行的是：2
         当前线程【刘备】执行的是：3
         当前线程【刘备】执行的是：4
         当前线程【张飞】正在执行：0
         当前线程【关羽】正在执行：2
         当前线程【关羽】正在执行：3
         当前线程【关羽】正在执行：4
         当前线程【关羽】正在执行：5
         当前线程【关羽】正在执行：6
         当前线程【关羽】正在执行：7
         当前线程【关羽】正在执行：8
         当前线程【关羽】正在执行：9
         当前线程【关羽】正在执行：10
         当前线程【关羽】正在执行：11
         当前线程【关羽】正在执行：12
         当前线程【关羽】正在执行：13
         当前线程【关羽】正在执行：14
         当前线程【关羽】正在执行：15
         当前线程【关羽】正在执行：16
         当前线程【关羽】正在执行：17
         当前线程【关羽】正在执行：18
         当前线程【关羽】正在执行：19
         当前线程【关羽】正在执行：20
         当前线程【关羽】正在执行：21
         当前线程【关羽】正在执行：22
         当前线程【关羽】正在执行：23
         当前线程【张飞】正在执行：1
         当前线程【关羽】正在执行：24
         当前线程【张飞】正在执行：2
         当前线程【关羽】正在执行：25
         当前线程【关羽】正在执行：26
         当前线程【关羽】正在执行：27
         当前线程【关羽】正在执行：28
         当前线程【关羽】正在执行：29
         当前线程【关羽】正在执行：30
         当前线程【关羽】正在执行：31
         当前线程【关羽】正在执行：32
         当前线程【关羽】正在执行：33
         当前线程【张飞】正在执行：3
         当前线程【关羽】正在执行：34
         当前线程【张飞】正在执行：4
         当前线程【关羽】正在执行：35
         当前线程【张飞】正在执行：5
         当前线程【关羽】正在执行：36
         当前线程【关羽】正在执行：37
         当前线程【关羽】正在执行：38
         当前线程【关羽】正在执行：39
         当前线程【关羽】正在执行：40
         当前线程【关羽】正在执行：41
         当前线程【关羽】正在执行：42
         当前线程【关羽】正在执行：43
         当前线程【关羽】正在执行：44
         当前线程【关羽】正在执行：45
         当前线程【张飞】正在执行：6
         当前线程【关羽】正在执行：46
         当前线程【张飞】正在执行：7
         当前线程【关羽】正在执行：47
         当前线程【张飞】正在执行：8
         当前线程【关羽】正在执行：48
         当前线程【张飞】正在执行：9
         当前线程【关羽】正在执行：49
         当前线程【关羽】正在执行：50
         当前线程【关羽】正在执行：51
         当前线程【关羽】正在执行：52
         当前线程【关羽】正在执行：53
         当前线程【关羽】正在执行：54
         当前线程【关羽】正在执行：55
         当前线程【关羽】正在执行：56
         当前线程【关羽】正在执行：57
         当前线程【关羽】正在执行：58
         当前线程【关羽】正在执行：59
         当前线程【关羽】正在执行：60
         当前线程【关羽】正在执行：61
         当前线程【关羽】正在执行：62
         当前线程【关羽】正在执行：63
         当前线程【关羽】正在执行：64
         当前线程【关羽】正在执行：65
         当前线程【关羽】正在执行：66
         当前线程【关羽】正在执行：67
         当前线程【关羽】正在执行：68
         当前线程【关羽】正在执行：69
         当前线程【关羽】正在执行：70
         当前线程【关羽】正在执行：71
         当前线程【关羽】正在执行：72
         当前线程【关羽】正在执行：73
         当前线程【关羽】正在执行：74
         当前线程【关羽】正在执行：75
         当前线程【关羽】正在执行：76
         当前线程【关羽】正在执行：77
         当前线程【关羽】正在执行：78
         当前线程【关羽】正在执行：79
         当前线程【关羽】正在执行：80
         当前线程【关羽】正在执行：81
         当前线程【关羽】正在执行：82
         当前线程【关羽】正在执行：83
         当前线程【关羽】正在执行：84
         当前线程【关羽】正在执行：85
         当前线程【关羽】正在执行：86
         当前线程【关羽】正在执行：87
         当前线程【关羽】正在执行：88
         当前线程【关羽】正在执行：89
         当前线程【关羽】正在执行：90
         当前线程【关羽】正在执行：91
         当前线程【关羽】正在执行：92
         当前线程【关羽】正在执行：93
         当前线程【关羽】正在执行：94
         当前线程【关羽】正在执行：95
         当前线程【张飞】正在执行：10
         当前线程【关羽】正在执行：96
         当前线程【关羽】正在执行：97
         当前线程【关羽】正在执行：98
         当前线程【关羽】正在执行：99
         当前线程【张飞】正在执行：11
         当前线程【张飞】正在执行：12
         当前线程【张飞】正在执行：13
         当前线程【张飞】正在执行：14
         当前线程【张飞】正在执行：15
         当前线程【张飞】正在执行：16
         当前线程【张飞】正在执行：17
         当前线程【张飞】正在执行：18
         当前线程【张飞】正在执行：19
         当前线程【张飞】正在执行：20
         当前线程【张飞】正在执行：21
         当前线程【张飞】正在执行：22
         当前线程【张飞】正在执行：23
         当前线程【张飞】正在执行：24
         当前线程【张飞】正在执行：25
         当前线程【张飞】正在执行：26
         当前线程【张飞】正在执行：27
         当前线程【张飞】正在执行：28
         当前线程【张飞】正在执行：29
         当前线程【张飞】正在执行：30
         当前线程【张飞】正在执行：31
         当前线程【张飞】正在执行：32
         当前线程【张飞】正在执行：33
         当前线程【张飞】正在执行：34
         当前线程【张飞】正在执行：35
         当前线程【张飞】正在执行：36
         当前线程【张飞】正在执行：37
         当前线程【张飞】正在执行：38
         当前线程【张飞】正在执行：39
         当前线程【张飞】正在执行：40
         当前线程【张飞】正在执行：41
         当前线程【张飞】正在执行：42
         当前线程【张飞】正在执行：43
         当前线程【张飞】正在执行：44
         当前线程【张飞】正在执行：45
         当前线程【张飞】正在执行：46
         当前线程【张飞】正在执行：47
         当前线程【张飞】正在执行：48
         当前线程【张飞】正在执行：49
         当前线程【张飞】正在执行：50
         当前线程【张飞】正在执行：51
         当前线程【张飞】正在执行：52
         当前线程【张飞】正在执行：53
         当前线程【张飞】正在执行：54
         当前线程【张飞】正在执行：55
         当前线程【张飞】正在执行：56
         当前线程【张飞】正在执行：57
         当前线程【张飞】正在执行：58
         当前线程【张飞】正在执行：59
         当前线程【张飞】正在执行：60
         当前线程【张飞】正在执行：61
         当前线程【张飞】正在执行：62
         当前线程【张飞】正在执行：63
         当前线程【张飞】正在执行：64
         当前线程【张飞】正在执行：65
         当前线程【张飞】正在执行：66
         当前线程【张飞】正在执行：67
         当前线程【张飞】正在执行：68
         当前线程【张飞】正在执行：69
         当前线程【张飞】正在执行：70
         当前线程【张飞】正在执行：71
         当前线程【张飞】正在执行：72
         当前线程【张飞】正在执行：73
         当前线程【张飞】正在执行：74
         当前线程【张飞】正在执行：75
         当前线程【张飞】正在执行：76
         当前线程【张飞】正在执行：77
         当前线程【张飞】正在执行：78
         当前线程【张飞】正在执行：79
         当前线程【张飞】正在执行：80
         当前线程【张飞】正在执行：81
         当前线程【张飞】正在执行：82
         当前线程【张飞】正在执行：83
         当前线程【张飞】正在执行：84
         当前线程【张飞】正在执行：85
         当前线程【张飞】正在执行：86
         当前线程【张飞】正在执行：87
         当前线程【张飞】正在执行：88
         当前线程【张飞】正在执行：89
         当前线程【张飞】正在执行：90
         当前线程【张飞】正在执行：91
         当前线程【张飞】正在执行：92
         当前线程【张飞】正在执行：93
         当前线程【张飞】正在执行：94
         当前线程【张飞】正在执行：95
         当前线程【张飞】正在执行：96
         当前线程【张飞】正在执行：97
         当前线程【张飞】正在执行：98
         当前线程【张飞】正在执行：99

         Process finished with exit code 0

         *
         * */


        /**
         * 此处是设置线程守护之后的线程运行结果
         * "C:\Program Files\Java\jdk1.8.0_91\bin\java" -Didea.launcher.port=7538 "-Didea.launcher.bin.path=D:\dev.ksea\dev.tools\IntelliJ IDEA 2016.3\bin" -Dfile.encoding=UTF-8 -classpath "C:\Program Files\Java\jdk1.8.0_91\jre\lib\charsets.jar;C:\Program Files\Java\jdk1.8.0_91\jre\lib\deploy.jar;C:\Program Files\Java\jdk1.8.0_91\jre\lib\ext\access-bridge-64.jar;C:\Program Files\Java\jdk1.8.0_91\jre\lib\ext\cldrdata.jar;C:\Program Files\Java\jdk1.8.0_91\jre\lib\ext\dnsns.jar;C:\Program Files\Java\jdk1.8.0_91\jre\lib\ext\jaccess.jar;C:\Program Files\Java\jdk1.8.0_91\jre\lib\ext\jfxrt.jar;C:\Program Files\Java\jdk1.8.0_91\jre\lib\ext\localedata.jar;C:\Program Files\Java\jdk1.8.0_91\jre\lib\ext\nashorn.jar;C:\Program Files\Java\jdk1.8.0_91\jre\lib\ext\sunec.jar;C:\Program Files\Java\jdk1.8.0_91\jre\lib\ext\sunjce_provider.jar;C:\Program Files\Java\jdk1.8.0_91\jre\lib\ext\sunmscapi.jar;C:\Program Files\Java\jdk1.8.0_91\jre\lib\ext\sunpkcs11.jar;C:\Program Files\Java\jdk1.8.0_91\jre\lib\ext\zipfs.jar;C:\Program Files\Java\jdk1.8.0_91\jre\lib\javaws.jar;C:\Program Files\Java\jdk1.8.0_91\jre\lib\jce.jar;C:\Program Files\Java\jdk1.8.0_91\jre\lib\jfr.jar;C:\Program Files\Java\jdk1.8.0_91\jre\lib\jfxswt.jar;C:\Program Files\Java\jdk1.8.0_91\jre\lib\jsse.jar;C:\Program Files\Java\jdk1.8.0_91\jre\lib\management-agent.jar;C:\Program Files\Java\jdk1.8.0_91\jre\lib\plugin.jar;C:\Program Files\Java\jdk1.8.0_91\jre\lib\resources.jar;C:\Program Files\Java\jdk1.8.0_91\jre\lib\rt.jar;D:\dev.ksea\dev.file\javase\target\classes;D:\dev.ksea\dev.tools\IntelliJ IDEA 2016.3\lib\idea_rt.jar" com.intellij.rt.execution.application.AppMain com.ksea.thread.ThreadDemo6
         当前线程【刘备】执行的是：0
         当前线程【张飞】正在执行：0
         当前线程【张飞】正在执行：1
         当前线程【张飞】正在执行：2
         当前线程【刘备】执行的是：1
         当前线程【刘备】执行的是：2
         当前线程【刘备】执行的是：3
         当前线程【刘备】执行的是：4
         当前线程【关羽】正在执行：0
         当前线程【张飞】正在执行：3
         当前线程【张飞】正在执行：4
         当前线程【张飞】正在执行：5
         当前线程【张飞】正在执行：6
         当前线程【张飞】正在执行：7
         当前线程【张飞】正在执行：8
         当前线程【张飞】正在执行：9
         当前线程【张飞】正在执行：10
         当前线程【张飞】正在执行：11
         当前线程【张飞】正在执行：12
         当前线程【张飞】正在执行：13
         当前线程【张飞】正在执行：14
         当前线程【张飞】正在执行：15
         当前线程【张飞】正在执行：16
         当前线程【张飞】正在执行：17
         当前线程【张飞】正在执行：18
         当前线程【张飞】正在执行：19
         当前线程【张飞】正在执行：20
         当前线程【张飞】正在执行：21
         当前线程【张飞】正在执行：22

         Process finished with exit code 0

         */
    }
}

class ThreadDaemon extends Thread {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            System.out.println("当前线程【" + this.getName() + "】正在执行：" + i);
        }
    }
}