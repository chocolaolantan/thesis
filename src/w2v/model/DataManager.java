package w2v.model;

import java.io.File;

import w2v.model.W2vModel;

public class DataManager {
  private String base_url = "http://thesaurus.weblio.jp/content/";
  private String base_antonym_url = "http://thesaurus.weblio.jp/antonym/content/";

  private W2vModel w2vm;
  private File f_synonym;
  private File f_antonym;
  private boolean sF = false;

  public DataManager(W2vModel w2vm) {
    this.w2vm = w2vm;
  }
  public void setFilePath(String fs_path, String fa_path) {
    try {
      f_synonym = new File(fs_path);
      f_antonym = new File(fa_path);
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
      if (!f_synonym.exists())
        new ASthread(w2vm, base_url, f_synonym).start();
      if (!f_antonym.exists())
        new ASthread(w2vm, base_antonym_url, f_antonym).start();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
