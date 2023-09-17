package com.jayus.smallMyBatis.step07.test;

import com.alibaba.fastjson.JSON;
import com.jayus.smallMyBatis.step07.reflection.MetaObject;
import com.jayus.smallMyBatis.step07.reflection.SystemMetaObject;

import java.util.ArrayList;
import java.util.List;

public class ReflectionTest {

    public static void main(String[] args) {
        Teacher teacher = new Teacher();
        List<Teacher.Student> list = new ArrayList<>();
        list.add(new Teacher.Student());
        teacher.setName("mingou");
        teacher.setStudents(list);

        MetaObject metaObject = SystemMetaObject.forObject(teacher);

        System.out.println("getGetterNames(): " + JSON.toJSONString(metaObject.getGetterNames()));
        System.out.println("getSetterNames(): " + JSON.toJSONString(metaObject.getSetterNames()));
        System.out.println("name的get方法返回值：" + JSON.toJSONString(metaObject.getGetterType("name")));
        System.out.println("students的set方法参数值" + JSON.toJSONString(metaObject.getSetterType("students")));
        System.out.println("name的hasGetter" + metaObject.hasGetter("name"));
        System.out.println("student.id（属性为对象）的hasGetter：{}" + metaObject.hasGetter("student.id"));
        System.out.println("获取name的属性值" + metaObject.getValue("name"));

        metaObject.setValue("name","lingou");
        System.out.println("设置name的属性值：{}" + metaObject.getValue("name"));

        metaObject.setValue("students[0].id","001");
        System.out.println("获取students集合的第一个元素的属性值" + JSON.toJSONString(metaObject.getValue("students[0].id")));
        System.out.println("对象的序列化：" + JSON.toJSONString(teacher));
    }

    static class Teacher{

        private String name;

        private double price;

        private List<Student> students;

        private Student student;

        public static class Student {

            private String id;

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public double getPrice() {
            return price;
        }

        public void setPrice(double price) {
            this.price = price;
        }

        public List<Student> getStudents() {
            return students;
        }

        public void setStudents(List<Student> students) {
            this.students = students;
        }

        public Student getStudent() {
            return student;
        }

        public void setStudent(Student student) {
            this.student = student;
        }
    }

}

