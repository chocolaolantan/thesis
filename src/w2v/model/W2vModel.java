package w2v.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.StringTokenizer;

public class W2vModel {
  private int size;
  private int words;
  private String[] vocab;
  private File fi;

  public W2vModel(File fi) {
    BufferedReader br;
    StringTokenizer st;
    String line = "";
    int i, j;
    this.fi = fi;

    try {
      br = new BufferedReader(new FileReader(fi));

      st = new StringTokenizer(br.readLine());
      words = Integer.parseInt(st.nextToken());
      size = Integer.parseInt(st.nextToken());

      vocab = new String[words];
      i = 0;
      while ((line = br.readLine()) != null) {
        st = new StringTokenizer(line, " ");
        vocab[i] = new String(st.nextToken().getBytes("UTF-8"));
        i++;
      }
    } catch (Exception e) {
      System.out.println(line);
      e.printStackTrace();
    }
  }

  public int getWords() {
    return words;
  }
  public int getSize() {
    return size;
  }
  public float[] getWv(int n) {
    float[] vec = new float[this.size];
    int i, j;

    BufferedReader br;
    StringTokenizer st;
    String line;

    if (n > this.words || n < 0) {
      System.out.println("存在しないインデックスです。");
      return null;
    }
    try {
      br = new BufferedReader(new FileReader(this.fi));
      br.readLine();
      for (i = 0; i < n; i++)
        br.readLine();
      st = new StringTokenizer(br.readLine(), " ");
      if (!this.vocab[n].equals(new String(st.nextToken().getBytes("UTF-8")))) {
        System.out.println("インデックスにあるはずの文字列が一致しません。");
        return null;
      }
      for (i = 0; i < size; i++)
        vec[i] = Float.parseFloat(new String(st.nextToken().getBytes("UTF-8")));
    } catch (Exception e) {
        e.printStackTrace();
      }
    return vec;
  }
  public int exist(String tmp) {
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
  public String getW(int i) {
    if (i > words) return "";
    return vocab[i];
  }

}
