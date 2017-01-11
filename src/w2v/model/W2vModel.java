package w2v.model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.StringTokenizer;

public class W2vModel {
  private int size;
  private int words;
  private String[] vocab;
  public W2vModel(File fi) {
    BufferedReader br;
    StringTokenizer st;
    String line = "";
    int i, j;

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

        j = 0;
        while (st.hasMoreTokens() || j < size) {
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
}
