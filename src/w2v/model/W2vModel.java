package w2v.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Random;

import w2v.calc.Calc;

public class W2vModel {
  private int size;
  private int words;
  private String[] vocab;
  private float[][] m;
  private File f;

  private boolean exm;

  protected W2vModel(File f) {
    BufferedReader br;
    String[] st;
    String line = "";
    int i, j;
    float[] vec;
    this.f = f;

    try {
      br = new BufferedReader(new FileReader(f));
      st = br.readLine().split(" ");
      words = Integer.parseInt(st[0]);
      size = Integer.parseInt(st[1]);
      vocab = new String[words];

      try {
        m = new float[words][size];
        vec = new float[size];
        exm = true;
      } catch (Exception e) {
        exm = false;
      }

      i = 0;
      while ((line = br.readLine()) != null) {
        st = line.split(" ");
        vocab[i] = st[0];
        if (exm) {
          for (j = 0; j < size; j++)
            m[i][j] = Float.parseFloat(st[j+1]);
        }
        i++;
      }
    } catch (Exception e) {
      System.out.println(line);
      e.printStackTrace();
    }
  }

  protected int getWords() { return words; }
  protected int getSize() { return size; }
  protected int exist(String tmp) {
    int i = 0;
    while(i < this.words) {
      if(vocab[i].equals(tmp))
        break;
      i++;
    }
    if (i == this.words)
      i = -1;
    return i;
  }
  protected String getWord(int i) {
    if (i > words || i < 0) return "";
    return vocab[i];
  }
  protected float[] getWordVector(int n) {
    if (n > words || n < 0) return null;
    if (exm) return m[n];
    else return getWv(n);
  }
  protected float[][] getVectors(int[] list) {
    if (list == null || list.length <= 0) return null;
    float[][] res = new float[list.length][];
    for (int i = 0; i < list.length; i++)
      res[i] = m[list[i]];
    return res;
  }
  protected float[][] getAllVector() { return m; }
  protected int[] getRandomWords(int n, int[] list) {
    int num = words;
    if (list != null && list.length > 0) num = list.length;
    int[] a = new int[n];
    Random rnd = new Random();

    for(int i = 0; i < n; i++){
      a[i] = list[rnd.nextInt(num)];

      int x = a[i];
      for( i = 0; i < n ; i++)
        if(a[i] ==x)
          break;
    }
    return a;
  }

  protected int[] getNearWords(int idx, int n, int[] excl) {
    if (n > words || n < 0) return null;
    if (exm) return gNWm(idx, n, excl);
    else return gNWnm(idx, n, excl);
  }
  protected int[] getVectorNearWords(float[] grv, int n, int[] excl) {
    if (n > words || n < 0) return null;
    if (exm) return gVNWm(grv, n, excl);
    else return gVNWnm(grv, n, excl);
  }
  protected int[] getNearWordsInList(int idx, int n, int[] list) {
    if (!exm) return null;
    if (list == null || list.length < n || n <= 0) return null;
    int i, j, k;
    int[] res = new int[n];
    float[] len = new float[n];

    for (i = 0; i < list.length; i++) {
      float d = Calc.sminp(m[idx], m[list[i]]);
      for (j = 0; j < n; j ++) {
        if (d > len[j]) {
          for (k = n - 1; k > j; k--) {
            len[k] = len[k - 1];
            res[k] = res[k-1];
          }
          len[j] = d;
          res[j] = list[i];
          break;
        }
      }
    }
    return res;
  }
  protected int[] getVectorNearWordsInList(float[] grv, int n, int[] list) {
    if (!exm) return null;
    if (grv == null || list == null || grv.length <= 0 || list.length < n || n <= 0) return null;
    int i, j, k;
    int[] res = new int[n];
    float[] len = new float[n];

    for (i = 0; i < list.length; i++) {
      float d = Calc.sminp(grv, m[list[i]]);
      for (j = 0; j < n; j ++) {
        if (d > len[j]) {
          for (k = n - 1; k > j; k--) {
            len[k] = len[k - 1];
            res[k] = res[k-1];
          }
          len[j] = d;
          res[j] = list[i];
          break;
        }
      }
    }
    return res;
  }

  protected float[][] getCentroidVectors(int[] label, int[] cnum) {
    if (label == null || cnum == null || label.length <= 0 || cnum.length <= 0)
      return null;
    int i, j;
    float[][] res = new float[cnum.length][size];
    for (i = 0; i < res.length; i++)
      Arrays.fill(res[i], 0.0f);
    for (i = 0; i < label.length; i++) {
      if (label[i] < 0 || label[i] > res.length) continue;
      for (j = 0; j < size; j++)
        res[label[i]][j] += m[i][j];
    }
    for (i = 0; i < res.length; i++)
      for (j = 0; j < res[i].length; j++)
        res[i][j] /= cnum[i];
    return res;
  }

  private boolean contain(int idx, int[] d) {
    if (d == null) return false;
    for(int i = 0; i < d.length; i++)
      if(idx == d[i]) return true;
    return false;
  }
  private float[] getWv(int n) {
    float[] vec = new float[this.size];
    int i, j;

    BufferedReader br;
    String[] st;
    String line;

    try {
      br = new BufferedReader(new FileReader(this.f));
      br.readLine();
      for (i = 0; i < n; i++)
        br.readLine();
      st = br.readLine().split(" ");
      if (!this.vocab[n].equals(st[0])) {
        System.out.println("インデックスにあるはずの文字列が一致しません。");
        return null;
      }
      for (i = 0; i < size; i++)
        vec[i] = Float.parseFloat(st[i+1]);
    } catch (Exception e) {
        e.printStackTrace();
    }
    return vec;
  }

  private int[] gNWm(int i, int n, int[] d) {
    if (i < 0 || i > words) return null;
    int j, k, l;
    int[] ans = new int[n];
    float[] base = m[i];
    float[] best = new float[n];
    float sig;

    Arrays.fill(ans, -1);
    Arrays.fill(best, 0.0f);

    for (j = 0; j < words; j++) {
      if (j == i || contain(j, d)) continue;
      sig = Calc.cosr(base, Calc.toOne(m[j]));
      for (k = 0; k < n; k++) {
        if (sig > best[k]) {
          for (l = n - 1; l > k; l--) {
            best[l] = best[l - 1];
            ans[l] = ans[l-1];
          }
          best[k] = sig;
          ans[k] = j;
          break;
        }
      }
    }
    return ans;
  }
  private int[] gVNWm(float[] grv, int n, int[] d) {
    int j, k, l;
    int[] ans = new int[n];
    float[] base = grv;
    float[] best = new float[n];
    float sig;

    Arrays.fill(ans, -1);
    Arrays.fill(best, 0.0f);

    for (j = 0; j < words; j++) {
      sig = Calc.cosr(base, m[j]);
      for (k = 0; k < n; k++) {
        if (sig > best[k]) {
          for (l = n - 1; l > k; l--) {
            best[l] = best[l - 1];
            ans[l] = ans[l-1];
          }
          best[k] = sig;
          ans[k] = j;
          break;
        }
      }
    }
    return ans;
  }
  private int[] gNWnm(int i, int n, int[] d) {
    if (i < 0 || i > words) return null;
    int j, k, l;
    int[] ans = new int[n];
    float[] base = getWv(i);
    float[] target = new float[size];
    float[] best = new float[n];
    float sig;

    Arrays.fill(ans, -1);
    Arrays.fill(best, 0.0f);
    Arrays.fill(target, 0.0f);

    BufferedReader br;
    String[] st;
    String line = "";

    try {
      br = new BufferedReader(new FileReader(f));
      st = br.readLine().split(" ");
      j = 0;
      while ((line = br.readLine()) != null) {
        if (j == i || contain(j, d)) continue;

        j++;
        st = line.split(" ");

        for (k = 0; k < size; k++)
          target[k] = Float.parseFloat(st[k+1]);

        sig = Calc.cosr(base, target);
        for (k = 0; k < n; k++) {
          if (sig > best[k]) {
            for (l = n - 1; l > k; l--) {
              best[l] = best[l - 1];
              ans[l] = ans[l-1];
            }
            best[k] = sig;
            ans[k] = j;
            break;
          }
        }
      }
    } catch (Exception e) {
      System.out.println(line);
      e.printStackTrace();
    }
    return ans;
  }
  private int[] gVNWnm(float[] grv, int n, int[] d) {
    int j, k, l;
    int[] ans = new int[n];
    float[] base = grv;
    float[] target = new float[size];
    float[] best = new float[n];
    float sig;

    Arrays.fill(ans, -1);
    Arrays.fill(best, 0.0f);
    Arrays.fill(target, 0.0f);

    BufferedReader br;
    String[] st;
    String line = "";

    try {
      br = new BufferedReader(new FileReader(f));
      st = br.readLine().split(" ");
      j = 0;
      while ((line = br.readLine()) != null) {
        if (contain(j, d)) {
          j++;
          continue;
        }
        j++;
        st = line.split(" ");

        for (k = 0; k < size; k++)
          target[k] = Float.parseFloat(st[k+1]);
        sig = Calc.cosr(base, target);
        for (k = 0; k < n; k++) {
          if (sig > best[k]) {
            for (l = n - 1; l > k; l--) {
              best[l] = best[l - 1];
              ans[l] = ans[l-1];
            }
            best[k] = sig;
            ans[k] = j;
            break;
          }
        }
      }
    } catch (Exception e) {
      System.out.println(line);
      e.printStackTrace();
    }
    return ans;
  }
}
