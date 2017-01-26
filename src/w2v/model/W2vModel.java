package w2v.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.Arrays;
import java.util.Random;

public class W2vModel {
  private int size;
  private int words;
  private String[] vocab;
  private float[][] m;
  private File fi;

  private boolean exm;

  protected W2vModel(File fi) {
    BufferedReader br;
    String[] st;
    String line = "";
    int i, j;
    float len;
    float[] vec;
    this.fi = fi;

    try {
      br = new BufferedReader(new FileReader(fi));

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
          len = 0.0f;
          for (j = 0; j < size; j++) {
            m[i][j] = Float.parseFloat(st[j+1]);
            len += m[i][j] * m[i][j];
          }
          len = (float)Math.sqrt(len);
          for (j = 0; j < size; j++)
            m[i][j] /= len;
        }
        i++;
      }
    } catch (Exception e) {
      System.out.println(line);
      e.printStackTrace();
    }
  }

  protected int getWords() {
    return words;
  }
  protected int getSize() {
    return size;
  }
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
  protected float[][] getAllVector() { return m; }
  protected int[] getRandomWords(int n) {
    int a[] = new int[n];
    Random rnd = new Random();

    for(int i = 0; i < n; i++){
      a[i] = rnd.nextInt(words)+1;

      int x = a[i];
      for( i = 0; i < n ; i++)
        if(a[i] ==x)
          break;
    }
    return a;
  }
  protected int[] getRandomWordsInList(int n, int[] list) {
    int[] a = new int[n];
    Random rnd = new Random();

    for(int i = 0; i < n; i++){
      a[i] = list[rnd.nextInt(list.length)];

      int x = a[i];
      for( i = 0; i < n ; i++)
        if(a[i] ==x)
          break;
    }
    return a;
  }
  protected float[][] getRandomVectors(int n) {
    int[] a = getRandomWords(n);
    float[][] res = new float[n][];
    for (int i = 0; i < n; i ++)
      res[i] = m[a[i]];
    return res;
  }

  protected int[] getNearWordsInList(int idx, int[] list, int n) {
    if (!exm) return null;
    int i, j, k;
    int[] res = new int[n];
    float[] len = new float[n];

    for (i = 0; i < list.length; i++) {
      float d = 0.0f;
      for (j = 0; j < size; j++)
        d += m[idx][j] * m[list[i]][j];
      for (j = 0; j < n; j ++) {
        if (d > len[j]) {
          for (k = n - 1; k > j; k--) {
            len[k] = len[k - 1];
            res[k] = res[k-1];
          }
          len[j] = d;
          res[j] = i;
          break;
        }
      }
    }
    return res;
  }
  protected int[] getNearWords(int i, int n) {
    if (n > words || n < 0) return null;
    if (exm) return getNwlm(i, n);
    else return getNwl(i, n);
  }
  protected int[] getNearWordsnS(int i, int n, int[] d) {
    if (i > words || i < 0) return null;
    if (exm) return getNwlmnS(i, n, d);
    else return getNwlnS(i, n, d);
  }
  protected float[][] getCostMatrix(int[] x, int[] y) {
    if (exm) return gcmx(x, y);
    else return null;
  }
  protected float[][] getNmCostMatrix(int[] x, int[] y) {
    if (exm) return ngcmx(x, y);
    else return null;
  }

  protected float[] grv(int[] x) {
    float[] vec = new float[size];
    Arrays.fill(vec, 0.0f);
    for (int j = 0; j < size; j++) {
      vec[j] = 0.0f;
      for (int i = 0; i < x.length; i++) {
        if (x[i] < 0 || x[i] > words) continue;
        vec[j] += m[x[i]][j];
      }
      vec[j] /= x.length;
    }
    return vec;
  }
  protected int[] grvNm(float[] grv, int n) {
    int j, k, l;
    int[] ans = new int[n];
    float[] base = grv;
    float[] best = new float[n];
    float sig;

    Arrays.fill(ans, -1);
    Arrays.fill(best, 0.0f);

    for (j = 0; j < words; j++) {
      sig = 0.0f;
      for (k = 0; k < size; k++)
        sig += base[k] * m[j][k];
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
  protected int[] grvNmnS(float[] grv, int n, int[] d) {
    int j, k, l;
    int[] ans = new int[n];
    float[] base = grv;
    float[] best = new float[n];
    float sig;

    Arrays.fill(ans, -1);
    Arrays.fill(best, 0.0f);

    for (j = 0; j < words; j++) {
      sig = 0.0f;
      for (k = 0; k < size; k++)
        sig += base[k] * m[j][k];
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
  protected int[] grvNnm(float[] grv, int n) {
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
      br = new BufferedReader(new FileReader(fi));
      st = br.readLine().split(" ");
      j = 0;
      while ((line = br.readLine()) != null) {
        j++;
        sig = 0.0f;
        st = line.split(" ");

        for (k = 0; k < size; k++) {
          target[k] = Float.parseFloat(st[k+1]);
          sig += target[k] * target[k];
        }
        sig = (float)Math.sqrt(sig);
        for (k = 0; k < size; k++)
          target[k] /= sig;
        sig = 0.0f;
        for (k = 0; k < size; k++)
          sig += base[k] * target[k];
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
  protected int[] grvNnmnS(float[] grv, int n, int[] d) {
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
      br = new BufferedReader(new FileReader(fi));
      st = br.readLine().split(" ");
      j = 0;
      while ((line = br.readLine()) != null) {
        j++;
        sig = 0.0f;
        st = line.split(" ");

        for (k = 0; k < size; k++) {
          target[k] = Float.parseFloat(st[k+1]);
          sig += target[k] * target[k];
        }
        sig = (float)Math.sqrt(sig);
        for (k = 0; k < size; k++)
          target[k] /= sig;
        sig = 0.0f;
        for (k = 0; k < size; k++)
          sig += base[k] * target[k];
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
  protected float[][] nml(int[] x) {
    float[] grv = grv(x);
    float[][] res = new float[x.length][size];

    for (int i = 0; i < x.length; i++)
      for (int j = 0; j < size; j++)
        res[i][j] = m[x[i]][j] - grv[j];
    return res;
  }

  private float[][] ngcmx(int[] x, int[] y) {
    float[][] cost = new float[x.length][y.length];
    float[][] xm = nml(x);
    float[][] ym = nml(y);
    for (int i = 0; i < x.length; i++) {
      for (int j = 0; j < y.length; j++) {
        float len = 0.0f;
        for (int k = 0; k < size; k++)
          len += xm[i][k] * ym[j][k];
        cost[i][j] = len;
      }
    }
    return cost;
  }
  private float[][] gcmx(int[] x, int[] y) {
    float[][] cost = new float[x.length][y.length];
    for (int i = 0; i < x.length; i++) {
      float[] vx = m[x[i]];
      for (int j = 0; j < y.length; j++) {
        float[] vy = m[y[j]];
        float len = 0.0f;
        for (int k = 0; k < size; k++) {
          len += vx[k] * vy[k];
        }
        cost[i][j] = len;
      }
    }
    return cost;
  }
  private boolean contain(int idx, int[] d) {
    if (d == null) return false;
    for(int i = 0; i < d.length; i++)
      if(idx == d[i]) return true;
    return false;
  }
  private float[] getWv(int n) {
    float[] vec = new float[this.size];
    float len = 0.0f;
    int i, j;

    BufferedReader br;
    String[] st;
    String line;

    try {
      br = new BufferedReader(new FileReader(this.fi));
      br.readLine();
      for (i = 0; i < n; i++)
        br.readLine();
      st = br.readLine().split(" ");
      if (!this.vocab[n].equals(st[0])) {
        System.out.println("インデックスにあるはずの文字列が一致しません。");
        return null;
      }
      for (i = 0; i < size; i++) {
        vec[i] = Float.parseFloat(st[i+1]);
        len += vec[i] * vec[i];
      }
    } catch (Exception e) {
        e.printStackTrace();
      }
      len = (float)Math.sqrt(len);
      for (float item: vec)
        item /= len;
    return vec;
  }

  private int[] getNwlm(int i, int n) {
    int j, k, l;
    int[] ans = new int[n];
    float[] base = m[i];
    float[] best = new float[n];
    float sig;

    Arrays.fill(ans, -1);
    Arrays.fill(best, 0.0f);

    for (j = 0; j < words; j++) {
      if (j == i) continue;
      sig = 0.0f;
      for (k = 0; k < size; k++)
        sig += base[k] * m[j][k];
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
  private int[] getNwlmnS(int i, int n, int[] d) {
    int j, k, l;
    int[] ans = new int[n];
    float[] base = m[i];
    float[] best = new float[n];
    float sig;

    Arrays.fill(ans, -1);
    Arrays.fill(best, 0.0f);

    for (j = 0; j < words; j++) {
      if (j == i || contain(j, d)) {}
      else {
        sig = 0.0f;
        for (k = 0; k < size; k++)
          sig += base[k] * m[j][k];
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
    }
    return ans;
  }
  private int[] getNwl(int i, int n) {
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
      br = new BufferedReader(new FileReader(fi));
      st = br.readLine().split(" ");
      j = 0;
      while ((line = br.readLine()) != null) {
        if (j == i) continue;
        j++;
        sig = 0.0f;
        st = line.split(" ");

        for (k = 0; k < size; k++) {
          target[k] = Float.parseFloat(st[k+1]);
          sig += target[k] * target[k];
        }
        sig = (float)Math.sqrt(sig);
        for (k = 0; k < size; k++)
          target[k] /= sig;
        sig = 0.0f;
        for (k = 0; k < size; k++)
          sig += base[k] * target[k];
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
  private int[] getNwlnS(int i, int n, int[] d) {
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
      br = new BufferedReader(new FileReader(fi));
      st = br.readLine().split(" ");
      j = 0;
      while ((line = br.readLine()) != null) {
        if (j == i || contain(j, d)) {}
        else {
          j++;
          sig = 0.0f;
          st = line.split(" ");

          for (k = 0; k < size; k++) {
            target[k] = Float.parseFloat(st[k+1]);
            sig += target[k] * target[k];
          }
          sig = (float)Math.sqrt(sig);
          for (k = 0; k < size; k++)
            target[k] /= sig;
          sig = 0.0f;
          for (k = 0; k < size; k++)
            sig += base[k] * target[k];
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
      }
    } catch (Exception e) {
      System.out.println(line);
      e.printStackTrace();
    }
    return ans;
  }
  private int[] getNwlms(int i, int n) {
    int j, k, l;
    int[] ans = new int[n];
    float[] base = m[i];
    float[] best = new float[n];
    float sig;

    Arrays.fill(ans, -1);
    Arrays.fill(best, 0.0f);

    for (j = 0; j < words; j++) {
      if (j == i) continue;
      sig = 0.0f;
      for (k = 0; k < size; k++)
        sig += base[k] * m[j][k];
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
  private int[] getNwls(int i, int n) {
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
      br = new BufferedReader(new FileReader(fi));
      st = br.readLine().split(" ");
      j = 0;
      while ((line = br.readLine()) != null) {
        if (j == i) continue;
        j++;
        sig = 0.0f;
        st = line.split(" ");

        for (k = 0; k < size; k++) {
          target[k] = Float.parseFloat(st[k+1]);
          sig += target[k] * target[k];
        }
        sig = (float)Math.sqrt(sig);
        for (k = 0; k < size; k++)
          target[k] /= sig;
        sig = 0.0f;
        for (k = 0; k < size; k++)
          sig += base[k] * target[k];
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
