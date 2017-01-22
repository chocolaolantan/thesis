package w2v.model;

import java.io.File;

import w2v.calc.Calc;

public class DataManager {

  private W2vModel w2vm;
  private MCModel mcm;
  private WNModel wnm;

  public DataManager(File f) throws Exception{
    try {
      this.w2vm = new W2vModel(f);
      this.mcm = new MCModel();
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
}
