package com.example.bytebuddy;

public class HelloWorld {
	public static String intercept() {
	    int tmp = 1 + 2;
		return "Intercepted! Hello World " + tmp;
	}
}

