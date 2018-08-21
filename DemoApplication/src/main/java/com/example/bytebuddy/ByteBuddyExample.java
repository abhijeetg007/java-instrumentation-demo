package com.example.bytebuddy;

import net.bytebuddy.ByteBuddy;
import net.bytebuddy.dynamic.loading.ClassLoadingStrategy;
import net.bytebuddy.implementation.MethodDelegation;
import net.bytebuddy.matcher.ElementMatchers;

public class ByteBuddyExample {

    public static void main(String[] args) throws Exception {
        // System.out.println("Hello from CallSpy!");
        // RandomClassCallingsThings.callThings();

      /*  Class<?> dynamicType = new ByteBuddy().subclass(Object.class)
                .method(ElementMatchers.named("toString"))
                .intercept(FixedValue.value("Hello World!"))
                .make()
                .load(Main.class.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
                .getLoaded();

        System.out.println(dynamicType);
        System.out.println(dynamicType.newInstance());

*/

        Class<?> intercepted = new ByteBuddy().subclass(Object.class)
                .method(ElementMatchers.named("toString"))
                .intercept(MethodDelegation.to(HelloWorld.class))
                .make()
                .load(ByteBuddyExample.class.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
                .getLoaded();

        System.out.println(intercepted.newInstance());


/*
        Class<?> interceptedWithOrigin = new ByteBuddy().subclass(Object.class)
                .method(ElementMatchers.named("toString"))
                .intercept(MethodDelegation.to(OriginHelloWorld.class))
                .make()
                .load(Main.class.getClassLoader(), ClassLoadingStrategy.Default.WRAPPER)
                .getLoaded();

        System.out.println(interceptedWithOrigin.newInstance());

    */
    }
}
