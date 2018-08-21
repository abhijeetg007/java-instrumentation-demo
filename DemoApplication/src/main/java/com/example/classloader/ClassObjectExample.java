package com.example.classloader;

//A Java program to demonstrate working of a Class type
//object created by JVM to represent .class file in
//memory.
import java.lang.reflect.Field;
import java.lang.reflect.Method;

//Java code to demonstrate use of Class object
//created by JVM
public class ClassObjectExample {
    public static void main(String[] args) {
        Student s1 = new Student();

        // Getting hold of Class object created
        // by JVM.
        Class c1 = s1.getClass();

        System.out.println("---Name of the class---\n" + c1.getName());

        System.out.println("\n---Name of the Methods---");
        Method m[] = c1.getDeclaredMethods();
        for (Method method : m)
            System.out.println(method.getName());

        System.out.println("\n---Name of the Fields--- ");
        Field f[] = c1.getDeclaredFields();
        for (Field field : f)
            System.out.println(field.getName());

        Student s2 = new Student();
        // c2 will point to same object where
        // c1 is pointing
        Class c2 = s2.getClass();
        System.out.println("\n---Only one class object created---  ");
        System.out.println(c1 == c2); // true

    }
}

// A sample class whose information is fetched above using
// its Class object.
class Student {
    private String name;
    private int rollNo;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getRollNo() {
        return rollNo;
    }

    public void setRollNo(int rollNo) {
        this.rollNo = rollNo;
    }
}
