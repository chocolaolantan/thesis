package w2v;

import w2v.model.W2vModel;
import w2v.model.DataManager;

import java.io.File;
import java.util.Scanner;

public class W2vCmd {
  private static W2vModel w2vm;
  private static DataManager dm;
  private static Scanner stdIn;
  private static String cmd;

  public static void main(String[] args) {
    String file_path;
    File fi = null;
    boolean flag = false;

    cmd = "";
    stdIn = new Scanner(System.in);

    while(!flag) {
      System.out.print("Input file name is ... >");
      try {
        file_path = stdIn.nextLine();
        fi = new File(file_path);
        flag = fi.exists();
      } catch (Exception e) {
        System.out.println("Error.");
      }
    }
    w2vm = new W2vModel(fi);

    while(!cmd.equals("EXIT")) {
      System.out.print("何をしますか？ >");
      cmd = stdIn.nextLine();
      if (cmd.equals("ex"))
        existWord();
      else if (cmd.equals("vc"))
        prvct();
      else if (cmd.equals("hm"))
        hangarian();
    }
  }

  private static void existWord() {
    int i = 0;

    System.out.print("探したい単語を入力してください >");
    cmd = stdIn.nextLine();
    i = w2vm.exist(cmd);

    if (i < 0)
      System.out.printf("%s はみつかりませんでした。\n", cmd);
    else
      System.out.printf("%s は、インデックス %d にありました！！\n", cmd, i);
  }

  private static void prvct() {
    int i = 0, j;
    float[] vec;

    System.out.print("探したい単語を入力してください >");
    cmd = stdIn.nextLine();
    i = w2vm.exist(cmd);

    if (i < 0)
      System.out.printf("%s はみつかりませんでした。\n", cmd);
    else {
      System.out.printf("%s : %d\n", cmd, i);
      vec = w2vm.getWv(i);
      for (j = 0; j < w2vm.getSize(); j++) {
        if (j % 10 == 0) System.out.println();
        System.out.printf("%f ", vec[j]);
      }
      System.out.println();
    }
  }

  private static void hangarian() {
    String syn;
    String ant;
    dm = new DataManager(w2vm);

    System.out.println("ファイルパスの指定をしてください。");
    System.out.print("Synonym >");
    syn = stdIn.nextLine();
    System.out.print("Antonym >");
    ant = stdIn.nextLine();
    dm.setFilePath(syn, ant);
    dm.createAslist();
  }

}
