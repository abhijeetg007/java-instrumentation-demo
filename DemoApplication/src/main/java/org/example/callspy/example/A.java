package org.example.callspy.example;

class A {
  protected int idx;

  public void op(String name) {
    if (idx <= 0) {
      System.out.println("A: " + name);
    }
    else {
      idx--;
      this.op(name + " -> " + idx);
    }
  }

  public void setIdx(int i) {
    this.idx = i;
  }
}
