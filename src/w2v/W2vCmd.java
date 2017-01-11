package w2v;

import w2v.model.W2vModel;

import java.io.File;
import java.io.Scanner;

public class W2vCmd {
  private W2vModel w2vm;
  private Scanner stdIn;
  private String cmd;

  public static void main(String[] args) {
    String file_path;
    File fi = null;

    cmd = "";
    stdIn = new Scanner(System.in);

    while(!fi) {
      System.out.print("Input file name is ... > ");
      try {
        file_path = stdIn.nextLine();
        fi = new File(file_path);
      } catch (Exception e) {
        System.out.println("Error.");
      }
    }
    w2vm = new W2vModel(fi);

    while(cmd.equals("EXIT")) {
      System.out.print("何をしますか？ > ");
      cmd = stdIn.nextLine();
      if (cmd.equals("ex"))
        existWord();
    }
  }

  private void existWord() {
    int i;

    System.out.print("探したい単語を入力してください > ");
    cmd = stdIn.nextLine();

    if (i < 0)
      System.out.printf("%sはみつかりませんでした。\n", cmd);
    else
      System.out.printf("%sは、インデックス%dにありました！！", cmd, i);
  }
}
