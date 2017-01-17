package w2v.model;

import java.io.File;

import w2v.model.W2vModel;

public class DataManager {

  private W2vModel w2vm;
  private File[] f;
  private boolean sF = false;

  public DataManager(W2vModel w2vm) {
    this.w2vm = w2vm;
    f = new File[2];
  }
  public void setFilePath(String fs_path, String fa_path) {
    try {
      f[0] = new File(fs_path);
      f[1] = new File(fa_path);
      sF = true;
    } catch (Exception e) {
      e.printStackTrace();
      sF = false;
    }
  }

  public void createAslist() {
    if (!sF) {
      System.out.println("Error");
      return;
    }
    try {
      new ASthread(w2vm, f).start();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
