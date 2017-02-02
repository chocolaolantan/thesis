package w2v.model;

import java.io.File;
import java.io.FileWriter;
import java.io.FileReader;
import java.io.BufferedWriter;
import java.io.BufferedReader;
import java.util.Arrays;
import java.util.LinkedList;

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

  public boolean setW2vm(File f) {
    try {
      this.w2vm = new W2vModel(f);
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
  }
  public int gSize() { return w2vm.getSize(); }
  public int gWords() { return w2vm.getWords(); }
  public int exw(String tmp) { return w2vm.exist(tmp); }
  public String gWord(int i) { return w2vm.getWord(i); }
  public float[] gWV(int i) { return w2vm.getWordVector(i); }
  public int[] gNW(int i, int n) { return w2vm.getNearWords(i, n, null); }
  public int[] gNWnS(int i, int n) {
    if(i < 0 || i > w2vm.getWords()) return null;
    int[] d = gWSi(i);
    return w2vm.getNearWords(i, n, d);
  }

  public float[][] gSM(int[] x, int[] y) { return Calc.sminpMatrix(w2vm.getVectors(x), w2vm.getVectors(y)); }
  public float[][] gNSM(int[] x, int[] y) { return Calc.sminpMatrix(Calc.centNormaliz(w2vm.getVectors(x)), Calc.centNormaliz(w2vm.getVectors(y))); }
  public float[][] gCM(int[] x, int[] y) { return Calc.cosrMatrix(w2vm.getVectors(x), w2vm.getVectors(y)); }
  public float[][] gNCM(int[] x, int[] y) { return Calc.cosrMatrix(Calc.centNormaliz(w2vm.getVectors(x)), Calc.centNormaliz(w2vm.getVectors(y))); }
  public float[][] gDM(int[] x, int[] y) { return Calc.distMatrix(w2vm.getVectors(x), w2vm.getVectors(y)); }
  public float[][] gNDM(int[] x, int[] y) { return Calc.distMatrix(Calc.centNormaliz(w2vm.getVectors(x)), Calc.centNormaliz(w2vm.getVectors(y))); }

  public float[][] gRSM(int[] x, int[] y) { return Calc.reverseMatrix(gSM(x, y)); }
  public float[][] gNRSM(int[] x, int[] y) { return Calc.reverseMatrix(gNSM(x, y)); }
  public float[][] gRCM(int[] x, int[] y) { return Calc.reverseMatrix(gCM(x, y)); }
  public float[][] gNRCM(int[] x, int[] y) { return Calc.reverseMatrix(gNCM(x, y)); }
  public float[][] gRDM(int[] x, int[] y) { return Calc.reverseMatrix(gDM(x, y)); }
  public float[][] gNRDM(int[] x, int[] y) { return Calc.reverseMatrix(gNDM(x, y)); }

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
  public String[] gHpe(String word) { return wnm.getWnHypernyms(word, gPs(word)); }
  public int[] gHpei(int idx) {
    int[] res;
    String[] tmp;

    if(idx < 0 || idx > w2vm.getWords()) return null;
    tmp = gHpe(gWord(idx));
    if (tmp == null) return null;
    res = new int[tmp.length];
    for (int i = 0; i < tmp.length; i++)
      res[i] = exw(tmp[i]);
    return res;
  }
  public String[] gHpo(String word) { return wnm.getWnHyponyms(word, gPs(word)); }
  public int[] gHpoi(int idx) {
    int[] res;
    String[] tmp;

    if(idx < 0 || idx > w2vm.getWords()) return null;
    tmp = gHpo(gWord(idx));
    if (tmp == null) return null;
    res = new int[tmp.length];
    for (int i = 0; i < tmp.length; i++)
      res[i] = exw(tmp[i]);
    return res;
  }
  public String[] gHl(String word) { return wnm.getWnHolonyms(word, gPs(word)); }
  public int[] gHli(int idx) {
    int[] res;
    String[] tmp;

    if(idx < 0 || idx > w2vm.getWords()) return null;
    tmp = gHl(gWord(idx));
    if (tmp == null) return null;
    res = new int[tmp.length];
    for (int i = 0; i < tmp.length; i++)
      res[i] = exw(tmp[i]);
    return res;
  }
  public String[] gDo(String word) { return wnm.getWnDomains(word, gPs(word)); }
  public int[] gDoi(int idx) {
    int[] res;
    String[] tmp;

    if(idx < 0 || idx > w2vm.getWords()) return null;
    tmp = gDo(gWord(idx));
    if (tmp == null) return null;
    res = new int[tmp.length];
    for (int i = 0; i < tmp.length; i++)
      res[i] = exw(tmp[i]);
    return res;
  }
  public String[] gDef(String word) { return wnm.getWnDefinitions(word, gPs(word)); }
  public int[] gDefi(int idx) {
    int[] res;
    String[] tmp;

    if(idx < 0 || idx > w2vm.getWords()) return null;
    tmp = gDef(gWord(idx));
    if (tmp == null) return null;
    res = new int[tmp.length];
    for (int i = 0; i < tmp.length; i++)
      res[i] = exw(tmp[i]);
    return res;
  }
  public String[] gCa(String word) { return wnm.getWnCauses(word, gPs(word)); }
  public int[] gCai(int idx) {
    int[] res;
    String[] tmp;

    if(idx < 0 || idx > w2vm.getWords()) return null;
    tmp = gCa(gWord(idx));
    if (tmp == null) return null;
    res = new int[tmp.length];
    for (int i = 0; i < tmp.length; i++)
      res[i] = exw(tmp[i]);
    return res;
  }
  public String[] gTr(String word) { return wnm.getWnTranslations(word, gPs(word)); }
  public int[] gTri(int idx) {
    int[] res;
    String[] tmp;

    if(idx < 0 || idx > w2vm.getWords()) return null;
    tmp = gTr(gWord(idx));
    if (tmp == null) return null;
    res = new int[tmp.length];
    for (int i = 0; i < tmp.length; i++)
      res[i] = exw(tmp[i]);
    return res;
  }
  public String[] gId(String word) { return wnm.getWnInDomains(word, gPs(word)); }
  public int[] gIdi(int idx) {
    int[] res;
    String[] tmp;

    if(idx < 0 || idx > w2vm.getWords()) return null;
    tmp = gId(gWord(idx));
    if (tmp == null) return null;
    res = new int[tmp.length];
    for (int i = 0; i < tmp.length; i++)
      res[i] = exw(tmp[i]);
    return res;
  }
  public String[] gMr(String word) { return wnm.getWnMeronyms(word, gPs(word)); }
  public int[] gMri(int idx) {
    int[] res;
    String[] tmp;

    if(idx < 0 || idx > w2vm.getWords()) return null;
    tmp = gMr(gWord(idx));
    if (tmp == null) return null;
    res = new int[tmp.length];
    for (int i = 0; i < tmp.length; i++)
      res[i] = exw(tmp[i]);
    return res;
  }
  public String[] gSA(String word) { return wnm.getWnSeeAlso(word, gPs(word)); }
  public int[] gSAi(int idx) {
    int[] res;
    String[] tmp;

    if(idx < 0 || idx > w2vm.getWords()) return null;
    tmp = gSA(gWord(idx));
    if (tmp == null) return null;
    res = new int[tmp.length];
    for (int i = 0; i < tmp.length; i++)
      res[i] = exw(tmp[i]);
    return res;
  }

  public int[] gSg(int i, int n) { return w2vm.getVectorNearWords(Calc.centroid(w2vm.getVectors(gWSi(i))), n, null); }

  public boolean saveC(String file_path) {
    if (!km.ld()) return false;
    System.out.println("書込み開始");
    try {
      File f = new File(file_path);

        BufferedWriter bw = new BufferedWriter(new FileWriter(f));
        bw.write(gWords());
        bw.write(" ");
        bw.write(km.allClust() + "\n");
        for (int i = 0; i < gWords(); i++)
          bw.write(gWord(i) + ' ' + km.wordClust(i) + '\n');
        bw.close();
        System.out.println("書込み成功");
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("書込み失敗");
      return false;
    }
  }
  public boolean loadC(String file_path) {
    return km.loadClust(file_path);
  }
  public boolean learnC() {
    boolean flag = false;
    try {
      flag = km.learn(w2vm.getCentroidVectors(km.getClust(), km.getCNum()), w2vm.getAllVector());
    } catch (Exception e) {
      e.printStackTrace();
    }
    return flag;
  }
  public boolean kMeans(int n, String save_file) {
    boolean flag = false;
    if (km.ld()) return true;
    try {
      flag = km.learn(w2vm.getVectors(w2vm.getRandomWords(n, null)), w2vm.getAllVector());
      if (flag) flag = saveC(save_file);
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return flag;
  }
  public boolean createC(String s_path, String file_path) {
    boolean flag = false;
    if (km.ld()) return true;
    try {
      flag = km.learn(grvS(synListCreate(s_path)), w2vm.getAllVector());
      if (flag) flag = saveC(file_path);
    } catch (Exception e) {
      e.printStackTrace();
      return false;
    }
    return flag;
  }
  public boolean createCl(String l_path, String file_path) {
    if (km.ld()) return true;
    km.learn(grvS(lSWf(l_path)), w2vm.getAllVector());
    saveC(file_path);
    return true;
  }
  public int wsInc(int i) { return km.clustSize(i); }
  public int wc(int i) { return km.wordClust(i); }
  public int[] wIc(int i) { return km.wordsInClust(i); }
  public int[] gCw(int idx, int n) {
    int[] l = km.wordsInClust(km.wordClust(idx));
    return w2vm.getNearWordsInList(idx, n, l);
  }

  public int[] synListCreate(String path) {
    int i, j, l = 0;
    int[] label = new int[gWords()];

    Arrays.fill(label, -1);
    for (i = 0; i < gWords(); i++) {
      if(label[i] >= 0) continue;

      int[] syn = gWSi(i);
      if (syn == null || syn.length == 0) continue;
      for (j = 0; j < syn.length; j++) {
        if (syn[j] < 0 || syn[j] > label.length) continue;
        try {
          label[syn[j]] = l;
        } catch (Exception e) {
          System.out.println("Error : " + j + ":" + syn[j]);
          e.printStackTrace();
          return null;
        }
      }
      l++;
    }
    try {
      File f = new File(path);
        BufferedWriter bw = new BufferedWriter(new FileWriter(f));
        bw.write(gWords());
        bw.write(' ');
        bw.write(l);
        for (i = 0; i < gWords(); i++)
          bw.write(gWord(i) + ' ' + label[i] + '\n');
        bw.close();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
    return label;
  }
  public int[] lSWf(String path) {
    int[] label;
    try {
      File fi = new File(path);
      BufferedReader br =  new BufferedReader(new FileReader(fi));
      String[] st = br.readLine().split(" ");
      label = new int[Integer.parseInt(st[0])];

      for (int i = 0; i < label.length; i++) {
        st = br.readLine().split(" ");
        label[i] = Integer.parseInt(st[1]);
      }
      br.close();
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
    return label;
  }

  private int max(int[] l) {
    if (l == null) return 0;
    int res = 0;
    for(int i = 0; i < l.length; i++)
      res = (res > l[i])? res : l[i];
    if (res < 0) return 0;
    return res;
  }
  private float[][] grvS(int[] label) {
    int i, j, l = 0;
    int mx = 0;
    int[] lNum;
    float[][] grv;

    if (label == null) return null;
    mx = max(label);
    System.out.println("mx : " + mx);
    if (mx <= 0) return null;
    lNum = new int[mx + 1];
    Arrays.fill(lNum, 0);

    for (i = 0; i < label.length; i++) {
      int t = label[i];
      if (t <= 0) continue;
      try {
        lNum[t]++;
      } catch (Exception e) {
        e.printStackTrace();
        System.out.println("t = " + t);
      }
    }

    grv = new float[mx+1][gSize()];
    for (i = 0; i < mx+1; i++)
      Arrays.fill(grv[i], 0.0f);
    for (i = 0; i < mx+1; i++) {
      if (i < 0 || label[i] < 0) continue;
      float[] v = gWV(i);
      for (j = 0; j < gSize(); j++)
        grv[label[i]][j] += v[j];
    }
    for (i = 0; i < mx+1; i++)
      for (j = 0; j < gSize(); j++)
        grv[i][j] /= lNum[i];
    return grv;
  }
  private boolean checkBeforeWritefile(File file){
    if (file.exists()){
      if (file.isFile() && file.canWrite()){
        return true;
      }
    }
    return false;
  }
}
