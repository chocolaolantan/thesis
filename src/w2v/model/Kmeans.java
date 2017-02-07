package w2v.model;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.Arrays;

import w2v.calc.Calc;

public class Kmeans {
  private int i;
  private int[] cNum;
  private int[] label;
  private boolean f = false;
  private float[][] g;
  public Kmeans() { super(); }

  public boolean lear(float[][] m) { return learn(g, m); }
  public boolean learn(float[][] grv, float[][] m) {
    float[] vec = new float[grv[0].length];
    float len;
    float[][] gr;
    System.out.println("学習開始。");
    if (grv == null) {
      System.out.println("grv null.");
      return false;
    }
    gr = grv;
    do {
      g = l(gr, m);
      for (int i = 0; i < grv.length; i++) {
        for (int j = 0; j < grv[i].length; j++)
          vec[j] = gr[i][j] - g[i][j];
      }
      gr = g;
      len = Calc.len(vec);
    } while(len != 0.0f);
    f = true;
    if(f) System.out.println("学習完了。");
    else System.out.println("学習失敗。");
    return f;
  }
  public boolean saveClust(String file_path) {
    if (!f) return false;
    System.out.println("書込み開始");
    try {
      File f = new File(file_path);

      BufferedWriter bw = new BufferedWriter(new FileWriter(f));
      bw.write(String.valueOf(label.length+1) + " " + String.valueOf(cNum.length+1) + "\n");
      for (int i = 0; i < label.length; i++)
        bw.write(String.valueOf(label[i]) + '\n');
      bw.close();
      System.out.println("書込み成功");
      return true;
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("書込み失敗");
      return false;
    }
  }
  public boolean loadClust(String file_path) {
    try {
      System.out.println("読込み開始");
      File fi = new File(file_path);
      BufferedReader br =  new BufferedReader(new FileReader(fi));
      String[] st = br.readLine().split(" ");
      label = new int[Integer.parseInt(st[0])];
      cNum = new int[Integer.parseInt(st[1])];
      Arrays.fill(cNum, 0);

      for (i = 0; i < label.length; i++) {
        label[i] = Integer.parseInt(br.readLine());
        if (label[i] < 0) continue;
        cNum[label[i]]++;
      }
      br.close();
        System.out.println("読込み成功");
      f = true;
    } catch (Exception e) {
      e.printStackTrace();
      System.out.println("i : " + i + "\tlabel[i] : " + label[i] + "\tCnum length" + cNum.length);
      f = false;
      return f;
    }
    return f;
  }

  public int[] wordsInClust(int i) {
    if (!f) return null;
    if (i < 0 || i > cNum.length) return null;

    int k = 0;
    int[] res = new int[cNum[i]];
    for (int j = 0; j < label.length; j++) {
      if (label[j] == i) {
        res[k] = j;
        k++;
      }
    }
    return res;
  }
  public int wordClust(int i) {
    if (i < 0 || i > label.length) return -1;
    return label[i];
  }
  public int ClustSize(int i) {
    if (i < 0 || i > cNum.length) return 0;
    return cNum[i];
  }
  public int allClust() { return cNum.length; }
  public int[] getClust() { return label; }
  public int[] getCNum() { return cNum; }
  public float[][] getG() { return g; }
  public boolean ld() { return f; }

  private float[][] l(float[][] grv, float[][] m) {
    float[][] ng;
    float mid, tmp;
    int i, j, vSize;
    boolean flag = false;

    label = new int[m.length];
    cNum = new int[grv.length];
    Arrays.fill(cNum, 0);
    vSize = grv[0].length;
    ng = new float[cNum.length][vSize];

    for (i = 0; i < m.length; i++) {
      mid = Float.POSITIVE_INFINITY;
      for (j = 0; j < cNum.length; j++) {
        tmp = Calc.dist(m[i], grv[j]);
        if (mid > tmp) {
          mid = tmp;
          label[i] = j;
        }
      }
      cNum[label[i]]++;
    }
    for (i = 0; i < ng.length; i++)
      Arrays.fill(ng[i], 0.0f);

    for (i = 0; i < m.length; i++)
      for (j = 0; j < ng[0].length; j++)
        ng[label[i]][j] += m[i][j];

    for (i = 0; i < ng.length; i++)
      for (j = 0; j < ng[i].length; j++)
        ng[i][j] /= cNum[i];

    return ng;
  }
}
