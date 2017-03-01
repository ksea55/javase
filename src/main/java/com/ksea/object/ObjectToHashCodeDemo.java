package com.ksea.object;

/**
 * Created by mexican on 2017/3/1.
 * public int hashCode()
 * 返回该对象的哈希码值。支持该方法是为哈希表提供一些优点，例如，java.util.Hashtable 提供的哈希表。
 * 哈希值是根据哈希算法计算出来的一个值，这个值和对象的地址有关，但是不是实际地址值，可以理解为地址值
 */
public class ObjectToHashCodeDemo {
    public static void main(String[] args) {
        User u1 = new User();
        System.out.println("u1的hashCode：" + u1.hashCode());

        User u2 = new User();
        System.out.println("u1的hashCode：" + u2.hashCode());

        User u3 = u1;
        System.out.println("u1的hashCode：" + u3.hashCode());


        /*
        * 运行结果
        *
        *   u1的hashCode：460141958
            u1的hashCode：1163157884
            u1的hashCode：460141958
        * */
    }
}
