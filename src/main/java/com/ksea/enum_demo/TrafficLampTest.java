package com.ksea.enum_demo;

/**
 * 利用枚举实现其，交通灯，红，绿，黄
 */
public class TrafficLampTest {
    public static void main(String[] args) {

        TrafficLamp red = TrafficLamp.Red;

        //获取时间
        Integer time = red.getTime();

        //下一个亮灯的值
        TrafficLamp nextLamp = red.nextLamp();

        System.out.println(time + "秒之后将亮起" + nextLamp.name() + "灯"); //打印结果:30秒之后将亮起Green灯

    }

    //定义交通灯枚举
    public enum TrafficLamp {

        //由其匿名内部类实现其抽象方法
        Red(30) { //此刻调用其有参构造函数，将其时间带进去

            @Override
            public TrafficLamp nextLamp() {
                //红灯之后就是绿灯，因此返回Green
                return Green;
            }
        },
        Green(45) {
            @Override
            public TrafficLamp nextLamp() {
                return Yellow;
            }
        },
        Yellow(5) {
            @Override
            public TrafficLamp nextLamp() {
                return Red;
            }
        };

        //抽象方法实现其下一个灯

        public abstract TrafficLamp nextLamp();

        //定义灯的停留时间
        private Integer time;

        private TrafficLamp(Integer time) {
            this.time = time;
        }


        public Integer getTime() {
            return time;
        }

        public void setTime(Integer time) {
            this.time = time;
        }
    }
}
