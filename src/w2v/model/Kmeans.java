package w2v.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;

public class Kmeans {
  private int[] cNum;
  private int[] clust;
  private boolean f = false;

  public Kmeans() { super(); }

  protected boolean learn(float[][] grv, float[][] m) {
    float[][] g;
    float[] vec = new float[grv[0].length];
    float len;
    System.out.println("学習開始。");
    if (grv == null) {
      System.out.println("grv null");
      return false;
    }
    do {
      g = l(grv, m);
      len = 0.0f;
      for (int i = 0; i < grv.length; i++) {
        for (int j = 0; j < grv[i].length; j++) {
          vec[j] = grv[i][j] - g[i][j];
          len += vec[j] * vec[j];
        }
      }
      len = (float)Math.sqrt(len);
    } while(len == 0.0f);
    f = true;
    if(f) System.out.println("学習完了。");
    else System.out.println("学習失敗。");
    return f;
  }
  protected boolean loadClust(String file_path) {
    try {
      File fi = new File(file_path);
      BufferedReader br =  new BufferedReader(new FileReader(fi));
      String[] st = br.readLine().split(" ");
      clust = new int[Integer.parseInt(st[0])];
      cNum = new int[Integer.parseInt(st[1])];
      Arrays.fill(cNum, 0);

      for (int i = 0; i < clust.length; i++) {
        st = br.readLine().split(" ");
        clust[i] = Integer.parseInt(st[1]);
        cNum[clust[i]]++;
      }
      br.close();
      f = true;
    } catch (Exception e) {
      e.printStackTrace();
      return f;
    }
    return f;
  }
  protected int[] wordsInClust(int i) {
    if (!f) return null;
    if (i < 0 || i > cNum.length) return null;

    int k = 0;
    int[] res = new int[cNum[i]];
    for (int j = 0; j < clust.length; j++) {
      if (clust[j] == i) {
        res[k] = j;
        k++;
      }
    }
    return res;
  }
  protected int wordClust(int i) {
    if (i < 0 || i > clust.length) return -1;
    return clust[i];
  }
  protected int clustSize(int i) {
    if (i < 0 || i > cNum.length) return -1;
    return cNum[i];
  }
  protected int allClust() { return cNum.length; }
  protected boolean ld() { return f; }

  private float[][] l(float[][] grv, float[][] m) {
    float[][] ng;
    float[] vec;
    float mid, tmp;
    int i, j, k, vSize;
    boolean flag = false;

    clust = new int[m.length];
    cNum = new int[grv.length];
    Arrays.fill(cNum, 0);
    vSize = grv[0].length;
    ng = new float[cNum.length][vSize];

    for (i = 0; i < m.length; i++) {
      vec = new float[m[i].length];
      mid = Float.POSITIVE_INFINITY;
      for (j = 0; j < cNum.length; j++) {
        tmp = 0.0f;
        for (k = 0; k < vSize; k++) {
          vec[k] = m[i][k] - grv[j][k];
          tmp += vec[k] * vec[k];
        }
        if (mid > tmp) {
          mid = tmp;
          clust[i] = j;
        }
      }
      cNum[clust[i]]++;
    }
    for (i = 0; i < ng.length; i++)
      Arrays.fill(ng[i], 0.0f);

    for (i = 0; i < m.length; i++)
      for (j = 0; j < ng[0].length; j++)
        ng[clust[i]][j] += m[i][j];

    for (i = 0; i < ng.length; i++)
      for (j = 0; j < ng[i].length; j++)
        ng[i][j] /= cNum[i];

    return ng;
  }
}
