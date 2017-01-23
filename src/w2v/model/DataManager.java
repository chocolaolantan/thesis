package w2v.model;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedWriter;

import w2v.calc.Calc;

public class DataManager {

  private W2vModel w2vm;
  private MCModel mcm;
  private WNModel wnm;
  private Kmeans km;

  public DataManager(File f) throws Exception{
    try {
      this.w2vm = new W2vModel(f);
      this.mcm = new MCModel();
      this.km = new Kmeans();
    } catch (UnsatisfiedLinkError e) {
      e.printStackTrace();
    }
  }

  public int gSize() { return w2vm.getSize(); }
  public int gWords() { return w2vm.getWords(); }
  public int exw(String tmp) { return w2vm.exist(tmp); }
  public String gWord(int i) { return w2vm.getWord(i); }
  public float[] gWV(int i) { return w2vm.getWordVector(i); }
  public int[] gNW(int i, int n) { return w2vm.getNearWords(i, n); }
  public int[] gNWnS(int i, int n) {
    if(i < 0 || i > w2vm.getWords()) return null;
    int[] d = gWSi(i);
    return w2vm.getNearWordsnS(i, n, d);
  }
  public float d(int i1, int i2) { return w2vm.dist(i1, i2); }
  public float[][] gCM(int[] x, int[] y) { return w2vm.getCostMatrix(x, y); }
  public float[][] gNCM(int[] x, int[] y) { return w2vm.getNmCostMatrix(x, y); }

  public String[] gWF(String str) { return mcm.getWmcFeature(str); }
  public String gPs(String str) { return mcm.getPspeech(str); }
  public String gB(String str) { return mcm.getBform(str); }
  public String gPh(String str) { return mcm.getPhonetic(str); }
  public String[] gSS(String str) { return mcm.getSentenceSegmentation(str); }

  public String[] gWS(String word) { return wnm.getWnSynonyms(word, gPs(word)); }
  public int[] gWSi(int idx) {
    int[] res;
    String[] tmp;

    if(idx < 0 || idx > w2vm.getWords()) return null;
    tmp = gWS(gWord(idx));
    if (tmp == null) return null;
    res = new int[tmp.length];
    for (int i = 0; i < tmp.length; i++)
      res[i] = exw(tmp[i]);
    return res;
  }
  public String[] gWA(String word) { return wnm.getWnAntonyms(word, gPs(word)); }
  public int[] gWAi(int idx) {
    int[] res;
    String[] tmp;

    if(idx < 0 || idx > w2vm.getWords()) return null;
    tmp = gWA(gWord(idx));
    if (tmp == null) return null;
    res = new int[tmp.length];
    for (int i = 0; i < tmp.length; i++)
      res[i] = exw(tmp[i]);
    return res;
  }

  public int[] gSg(int i, int n) { return w2vm.grvNm(w2vm.grv(gWSi(i)), n); }

  public int[] hgln(float[][] mrx, int n) {
    Calc clc = new Calc(mrx, n);
    return clc.hangarian();
  }

  public boolean saveC(String file_path) {
    if (!km.ld()) return false;
    try {
      File f = new File(file_path);
      if (checkBeforeWritefile(f)) {
        BufferedWriter bw = new BufferedWriter(new FileWriter(f));
        bw.write(gWords() + ' ' + km.allClust());
        for (int i = 0; i < km.allClust(); i++)
          bw.write(gWord(i) + ' ' + km.wordClust(i) + '\n');
        bw.close();
      } else { return false; }
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }
  public boolean loadC(String file_path) { return km.loadClust(file_path); }

  private boolean checkBeforeWritefile(File file){
    if (file.exists()){
      if (file.isFile() && file.canWrite()){
        return true;
      }
    }
    return false;
  }
}
