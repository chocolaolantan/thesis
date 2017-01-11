package w2v;

import w2v.model.W2vModel;

import java.io.File;
import java.util.Scanner;

public class W2vCmd {
  private static W2vModel w2vm;
  private static Scanner stdIn;
  private static String cmd;

  public static void main(String[] args) {
    String file_path;
    File fi = null;
    boolean flag = false;

    cmd = "";
    stdIn = new Scanner(System.in);

    while(!flag) {
      System.out.print("Input file name is ... > ");
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
      System.out.print("何をしますか？ > ");
      cmd = stdIn.nextLine();
      if (cmd.equals("ex"))
        existWord();
    }
  }

  private static void existWord() {
    int i = 0;

    System.out.print("探したい単語を入力してください > ");
    cmd = stdIn.nextLine();
    i = w2vm.exist(cmd);

    if (i < 0)
      System.out.printf("%s はみつかりませんでした。\n", cmd);
    else
      System.out.printf("%s は、インデックス %d にありました！！\n", cmd, i);
  }
}
