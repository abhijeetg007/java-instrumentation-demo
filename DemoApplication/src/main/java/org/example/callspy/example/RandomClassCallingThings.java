package org.example.callspy.example;

public class RandomClassCallingThings {

    public static void main(String[] args) {
        callThings(2);
    }

    private static void callThings(int a) {
        System.out.println("And we start calling things");
        /*B b = new B();
        b.setIdx(1);
        b.op("Hello world!");
        b.setIdx(1);
        b.op("A");*/

        new Maths().sum(10,20);

        System.out.println("And we're done!");
    }

}
class Maths {
    public int sum(Integer num1, Integer num2) {
        return num1 + num2;
    }
}
