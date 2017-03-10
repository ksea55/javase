package com.ksea.collection;

import java.util.LinkedList;

/**
 * Created by mexican on 2017/3/10.
 * 用 LinkedList集合来模拟栈数据结构
 * <p>
 * 首先分析
 * 栈数据结构是先进后出的规则
 * 当数据进来会进行压栈
 * 当数据进行弹栈结束整个栈就变空了，没有数据
 */
public class LinkedListImitateStack {
    public static void main(String[] args) {
        MyStack myStack = new MyStack();
        myStack.add("hello");
        myStack.add("world");
        myStack.add("ksea");

        //我们的添加顺序是:hello,world,ksea 根据栈的数据结构，先进后出的规则，弹栈之后的数据是逆向的顺序

        while (!myStack.isEmpty()) {
            System.out.print(myStack.get() + "、"); //运行结果:ksea、world、hello
        }


    }
}

class MyStack {
    //声明LinkedList容器
    private LinkedList linkedList;

    //初始化栈的时候对容器进行初始化
    public MyStack() {
        linkedList = new LinkedList();
    }

    //添加对象
    public void add(Object obj) {
        linkedList.addFirst(obj);
    }

    //获取对象
    public Object get() {
        //这里模拟弹栈，linkedList.removeFirst()该方法会删除集合中该对象并返回此对象
        return linkedList.removeFirst();
    }

    //判定栈是否还有元素
    public boolean isEmpty() {
        return linkedList.isEmpty();
    }
}
