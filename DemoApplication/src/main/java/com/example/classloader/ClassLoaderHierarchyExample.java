package com.example.classloader;

import javafx.animation.Animation;

public class ClassLoaderHierarchyExample {
    public static void main(String[] a) {

        System.out.println(String.class.getClassLoader());
        System.out.println(ClassLoaderHierarchyExample.class.getClassLoader());
        System.out.println(Animation.class.getClassLoader());
    }
}
