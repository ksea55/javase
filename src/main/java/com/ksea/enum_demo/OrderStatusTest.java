package com.ksea.enum_demo;

/**
 * 案列使用枚举来定义个订单状态
 */
public class OrderStatusTest {
    public static void main(String[] args) {

        //获取到订单已经开启的订单
        OrderStatus open = OrderStatus.OPEN;
        System.out.println(open); //打印结果:OPEN

        //获取订单编号
        Integer code = open.getCode();
        System.out.println(code); //打印结果:1

        //获取订单描述
        String desc = open.getDesc();
        System.out.println(desc);//打印结果：已开启


        //根据编号获取对应的枚举
        OrderStatus orderStatus = OrderStatus.getOrderStatus(2);
        System.out.println("code:" + orderStatus.getCode() + ",desc:" + orderStatus.getDesc());  //打印结果:code:2,desc:已禁用

        //根据描述获取对应的枚举
        orderStatus = OrderStatus.getOrderStatus("已开启");
        System.out.println("code:" + orderStatus.getCode() + ",desc:" + orderStatus.getDesc()); //打印结果：code:1,desc:已开启


    }

    //订单状态枚举
    public enum OrderStatus {

        OPEN(1, "已开启"),
        CLOSE(2, "已禁用");
        private Integer code;
        private String desc;


        OrderStatus(Integer code, String desc) {
            this.code = code;
            this.desc = desc;
        }


        //根据编号或者描述获取该枚举

        public static OrderStatus getOrderStatus(Object obj) {
            OrderStatus result = null;
            //获取到整个枚举
            OrderStatus[] values = OrderStatus.values();

            for (OrderStatus orderStatus : values) {

                if (orderStatus.getCode().equals(obj) || orderStatus.getDesc().equals(obj)) {
                    result = orderStatus;
                    break;
                }

            }

            return result;
        }


        public Integer getCode() {
            return code;
        }

        public void setCode(Integer code) {
            this.code = code;
        }

        public String getDesc() {
            return desc;
        }

        public void setDesc(String desc) {
            this.desc = desc;
        }
    }

}
