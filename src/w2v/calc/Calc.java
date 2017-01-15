package w2v.calc;

import java.util.Arrays;

public class Calc {
  private double[][] cost;
  private int n, max_match;
  private double[] lx, ly;
  private int[] xy, yx;
  private boolean[] S, T;
  private double[] slack;
  private int[] slackx;
  private int[] prev;

  public Calc(double[][] cost, int n) {
    this.cost = cost;
    this.n = n;
    init_labels();
  }

  private double max(double a, double b) {
    return (a > b) ? a: b;
  }
  private double min(double a, double b) {
    return (a < b) ? a: b;
  }

  private void init_labels() {
    int x, y;

    this.max_match = 0;
    this.lx = new double[n];
    Arrays.fill(lx, 0.0);
    this.ly = new double[n];
    Arrays.fill(ly, 0.0);
    this.xy = new int[n];
    Arrays.fill(xy, -1);
    this.yx = new int[n];
    Arrays.fill(yx, -1);
    this.S = new boolean[n];
    Arrays.fill(S, false);
    this.T = new boolean[n];
    Arrays.fill(T, false);
    this.slack = new double[n];
    this.slackx = new int[n];
    this.prev = new int[n];
    Arrays.fill(prev, -1);

    for (x = 0; x < n; x++)
      for (y = 0; y < n; y++)
        lx[x] = max(lx[x], cost[x][y]);
  }

  private void augment() {
    if (max_match == n) return;
    int x, y, root, cx, cy, ty;
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

  void update_labels() {
    int x, y, delta = INF;

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


  void add_to_tree(int x, int prevx) {
    int y;
    S[x] = true;
    prev[x] = prevx;
    for (y = 0; y < n; y++)
      if (lx[x] + ly[y] - cost[x][y] < slack[y]) {
	       slack[y] = lx[x] + ly[y] - cost[x][y];
	        slackx[y] = x;
	    }
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

  public int[] hangarian(N[][] mtrx, int n) {
      int i,j;
      augment();
      return this.xy;
  }
}
