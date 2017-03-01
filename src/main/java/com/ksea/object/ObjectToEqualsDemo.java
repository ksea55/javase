package com.ksea.object;

/**
 * Created by mexican on 2017/3/1.
 * equals 默认比较的是2个对象的引用地址
 * public boolean equals(Object obj) {
 * return (this == obj);
 * } 刚方法是Object中的，可以看出默认比较的是2个对象的地址
 */
public class ObjectToEqualsDemo {
    public static void main(String[] args) {
        Student s1 = new Student("孙悟空", "男");
        Student s2 = new Student("孙悟空", "男");
        Student s3 = s1;

        System.out.println(s1.equals(s1)); //true
        System.out.println(s1 == s1);//true

        System.out.println(s1.equals(s2)); //true
        System.out.println(s1 == s2);//false

        System.out.println(s2.equals(s3)); //true
        System.out.println(s2 == s3);//false

    }
}

class Student {
    private String stuName;
    private String stuSex;

    public String getStuName() {
        return stuName;
    }

    public void setStuName(String stuName) {
        this.stuName = stuName;
    }

    public String getStuSex() {
        return stuSex;
    }

    public void setStuSex(String stuSex) {
        this.stuSex = stuSex;
    }

    public Student() {
    }

    public Student(String stuName, String stuSex) {
        this.stuName = stuName;
        this.stuSex = stuSex;
    }

    @Override //这里的equals也是idea开发工具自动生成的
    public boolean equals(Object o) {
        //这里首先比较2个对象的引用地址是否是同一个，如果是同一个就是自己和自己比较直接返回true
        if (this == o) return true;
        //这里首先判定比较的对象是否null，然后比较2个对象的Class是否相等
        if (o == null || getClass() != o.getClass()) return false;

        Student student = (Student) o;

        if (stuName != null ? !stuName.equals(student.stuName) : student.stuName != null) return false;
        return stuSex != null ? stuSex.equals(student.stuSex) : student.stuSex == null;
    }

    @Override //这里是idea自动生成的hashCode
    public int hashCode() {
        int result = stuName != null ? stuName.hashCode() : 0;
        result = 31 * result + (stuSex != null ? stuSex.hashCode() : 0);
        return result;
    }
}