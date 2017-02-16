package w2v.calc;

import java.util.Arrays;

public class Calc {
  private static float[][] cost;
  private static int n, max_match;
  private static float[] lx, ly;
  private static int[] xy, yx;
  private static boolean[] S, T;
  private static float[] slack;
  private static int[] slackx;
  private static int[] prev;

  public static int[] hangarian(float[][] cst, int m) {
    int i,j;
    cost = cst;
    n = m;
    init_labels();
    augment();
    return xy;
  }

  public static int[] noneN(int[] l, int n) {
    if (l == null) return null;
    int i, j = 0, count = 0;
    int[] res;
    for (int k: l)
      if (k != n) count++;
    res = new int[count];
    for (i = 0; i < l.length; i++) {
      if (l[i] != n) {
        res[j] = l[i];
        j++;
      }
    }
    return res;
  }
  public static int[] sort(float[] l, String m) {
    if (l == null || l.length <= 0) return null;
    int i, j, t;
    int[] res = new int[l.length];
    float s;

    for (i = 0; i < l.length; i++)
      res[i] = i;
    if (m.equals("y")) {
      for (i = 0; i < l.length-2; i++) {
        for (j = l.length-1; j > i+1; j--) {
          if (l[j] < l[j-1]) {
            s = l[j];
            l[j] = l[j-1];
            l[j-1] = s;
            t = res[j];
            res[j] = res[j-1];
            res[j-1] = t;
          }
        }
      }
    } else {
      for (i = 0; i < l.length-2; i++) {
        for (j = l.length-1; j > i+1; j--) {
          if (l[j] > l[j-1]) {
            s = l[j];
            l[j] = l[j-1];
            l[j-1] = s;
            t = res[j];
            res[j] = res[j-1];
            res[j-1] = t;
          }
        }
      }
    }
    return res;
  }

  public static float max(float a, float b) { return (a > b) ? a: b; }
  public static float min(float a, float b) { return (a < b) ? a: b; }
  public static float sminp(float[] v1, float[] v2) {
    if (v1 == null || v2 == null || v1.length <= 0 || v2.length <= 0) return 0.0f;
    float[] v = new float[v1.length];
    float len = 0.0f;

    for (int i = 0; i < v.length; i++)
      len += v1[i] * v2[i];
    return len;
  }
  public static float dist(float[] v1, float[] v2) {
    if (v1 == null || v2 == null || v1.length <= 0 || v2.length <= 0) return 0.0f;
    float[] v = new float[v1.length];

    for (int i = 0; i < v.length; i++)
      v[i] = v1[i] - v2[i];

    return len(v);
  }
  public static float cosr(float[] v1, float[] v2) {
    if (v1 == null || v2 == null || v1.length <= 0 || v2.length <= 0) return 0.0f;
    float len = sminp(v1, v2) / (len(v1) * len(v2));
    return len;
  }
  public static float len(float[] v) {
    if (v == null || v.length <= 0) return 0.0f;
    float len = sminp(v, v);
    return (float)Math.sqrt(len);
  }
  public static float[] toOne(float[] v) {
    if (v == null || v.length <= 0) return null;
    float len = len(v);
    for (int i = 0; i < v.length; i++)
      v[i] /= len;
    return v;
  }

  public static float[] centroid(float[][] v) {
    if (v == null || v.length <= 0) return null;
    float[] vec = new float[v[0].length];
    Arrays.fill(vec, 0.0f);
    for (int i = 0; i < v.length; i++)
      for (int j = 0; j < vec.length; j++)
        vec[j] += v[i][j];

    for (int i = 0; i < vec.length; i++)
      vec[i] /= v.length;

    return vec;
  }
  public static float[][] normaliz(float[] g, float[][] v) {
    if (g == null || v == null || g.length <= 0 || v.length <= 0) return null;
    float[][] r = new float[v.length][];
    for (int i = 0; i < v.length; i++)
      for (int j = 0; j < g.length; j++) {
        if (v[i].length != g.length) return null;
        r[i] = new float[g.length];
        r[i][j] = v[i][j] - g[j];
      }
    return r;
  }
  public static float[][] centNormaliz(float[][] v) { return normaliz(centroid(v), v); }

  public static float[][] reverseMatrix(float[][] v) {
    float[][] res = new float[v.length][];
    for (int i = 0; i < v.length; i++) {
      res[i] = new float[v[i].length];
      for (int j = 0; j < v[i].length; j++)
        res[i][j] = -v[i][j];
    }
    return res;
  }

  public static float[][] cosrMatrix(float[][] x, float[][] y) {
    if (x == null || y == null || x.length <= 0 || y.length <= 0) return null;
    float[][] cost = new float[x.length][y.length];
    for (int i = 0; i < x.length; i++)
      for (int j = 0; j < y.length; j++)
        cost[i][j] = cosr(x[i], y[j]);
    return cost;
  }
  public static float[][] distMatrix(float[][] x, float[][] y) {
    if (x == null || y == null || x.length <= 0 || y.length <= 0) return null;
    float[][] cost = new float[x.length][y.length];
    for (int i = 0; i < x.length; i++)
      for (int j = 0; j < y.length; j++)
        cost[i][j] = dist(x[i], y[j]);
    return cost;
  }
  public static float[][] sminpMatrix(float[][] x, float[][] y) {
    if (x == null || y == null || x.length <= 0 || y.length <= 0) return null;
    float[][] cost = new float[x.length][y.length];
    for (int i = 0; i < x.length; i++)
      for (int j = 0; j < y.length; j++)
        cost[i][j] = sminp(x[i], y[j]);
    return cost;
  }

  private static void init_labels() {
    int x, y;

    max_match = 0;
    lx = new float[n];
    Arrays.fill(lx, 0.0f);
    ly = new float[n];
    Arrays.fill(ly, 0.0f);
    xy = new int[n];
    Arrays.fill(xy, -1);
    yx = new int[n];
    Arrays.fill(yx, -1);
    S = new boolean[n];
    Arrays.fill(S, false);
    T = new boolean[n];
    Arrays.fill(T, false);
    slack = new float[n];
    slackx = new int[n];
    prev = new int[n];
    Arrays.fill(prev, -1);

    for (x = 0; x < n; x++)
      for (y = 0; y < n; y++)
        lx[x] = max(lx[x], cost[x][y]);
  }
  private static void update_labels() {
    int x, y;
    float delta = (float)Double.POSITIVE_INFINITY;

    for (y = 0; y < n; y++)
      if (!T[y])
	     delta = min(delta, slack[y]);
    for (x = 0; x < n; x++)
      if (S[x]) lx[x] -= delta;
    for (y = 0; y < n; y++)
      if (T[y]) ly[y] += delta;
    for (y = 0; y < n; y++)
      if (!T[y])
	  slack[y] -= delta;
  }
  private static void add_to_tree(int x, int prevx) {
    int y;
    S[x] = true;
    prev[x] = prevx;
    for (y = 0; y < n; y++)
      if (lx[x] + ly[y] - cost[x][y] < slack[y]) {
	       slack[y] = lx[x] + ly[y] - cost[x][y];
	        slackx[y] = x;
	    }
  }
     if (max_match == n) return;
    int x, y, root = 0, cx, cy, ty;
    int[] q = new int[n];
    int wr = 0, rd = 0;

    for (x = 0; x < n; x++)
      if (xy[x] == -1) {
	       q[wr++] = root = x;
	        prev[x] = -2;
	         S[x] = true;
	          break;
      }

    for (y = 0; y < n; y++) {
      slack[y] = lx[root] + ly[y] - cost[root][y];
      slackx[y] = root;
    }

    while (true) {
      while (rd < wr) {
        x = q[rd++];
        for (y = 0; y < n; y++)
          if (cost[x][y] == lx[x] + ly[y] && !T[y]) {
		        if (yx[y] == -1) break;
		        T[y] = true;
		        q[wr++] = yx[y];
		        add_to_tree(yx[y], x);
	        }
	      if (y < n) break;
	    }
      if (y < n) break;

      update_labels();
      wr = rd = 0;
      for (y = 0; y < n; y++)
	      if (!T[y] && slack[y] == 0) {
          if (yx[y] == -1) {
            x = slackx[y];
		        break;
	        } else {
		        T[y] = true;
		        if (!S[yx[y]]) {
		          q[wr++] = yx[y];
		          add_to_tree(yx[y], slackx[y]);
		        }
	        }
	      }
      if (y < n) break;
    }

    if (y < n) {
      max_match++;
      for (cx = x, cy = y; cx != -2; cx = prev[cx], cy = ty) {
        ty = xy[cx];
        yx[cy] = cx;
	      xy[cx] = cy;
	    }
      augment();
    }
  }
}
