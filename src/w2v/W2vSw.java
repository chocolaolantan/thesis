package w2v;

import w2v.view.swing.MFrame;

public class W2vSw {
  private static MFrame mf;

  public static void main(String[] args) {
    System.out.println("Start W2vProject!!");
    init();
  }

  private static void init() {
    System.out.println("Initializing...");
    mf = new MFrame();
  }
}
