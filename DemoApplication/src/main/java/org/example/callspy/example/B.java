package org.example.callspy.example;

class B extends A {

  public void op(String name) {
    super.op("B is for BCN! (" + name + ")");
    System.out.println("B: " + name);
  }
}
