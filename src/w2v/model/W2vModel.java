package w2v.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.StringTokenizer;

public class W2vModel {
  private int size;
  private int words;
  private String[] vocab;
  private float[][] M;
  public W2vModel(File fi) {
    BufferedReader br;
    String line = "";
    int i, j;

    try {
      br = new BufferedReader(new FileReader(fi));

      words = Integer.parseInt(br.readLine());
      size = Integer.parseInt(br.readLine());
      M = new float[words][size];
      vocab = new String[words];

      i = 0;
      while ((line = br.readLine()) != null) {
        st = new StringTokenizer(line, " ");
        vocab[i] = new String(st.nextToken().getBytes("UTF-8"));

        j = 0;
        while (st.hasMoreTokens() || j < size) {
          M[i][j] = Float.parseFloat(st.nextToken());
          j++;
        }
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
  public String getVocab(int i) {
    return vocab[i];
  }
  public String[] getVlist() {
    return vocab;
  }
  public float[] getM(int i) {
    return M[i];
  }
  public float[][] getMlist() {
    return M;
  }
}
