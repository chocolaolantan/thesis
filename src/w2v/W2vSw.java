package w2v;

import w2v.model.W2vModel;
import w2v.view.swing.MFrame;
import java.io.File;

public class W2vSw {
  private static MFrame mf;

  public static void main(String[] args) {
    System.out.println("Start W2vProject!!");
    W2vSw app = new W2vSw();
    init(app);
  }

  private static void init(W2vSw app) {
    System.out.println("Initializing...");
    mf = new MFrame(app);
  }

  public void getW2vModel(File f) {
    System.out.println("getW2vModel");
  }
}
