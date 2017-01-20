package w2v;

import w2v.model.W2vModel;
import w2v.model.DataManager;

import java.io.File;
import java.util.Scanner;
import java.util.Set;

public class W2vCmd {
  private static W2vModel w2vm;
  private static DataManager dm;
  private static Scanner stdIn;
  private static String cmd;
  private static String file_path;

  public static void main(String[] args) {
    File fi = null;
    boolean flag = false;

    cmd = "";
    stdIn = new Scanner(System.in);

    while(!flag) {
      System.out.print("Input file name is ... >");
      file_path = stdIn.nextLine();
      fi = new File(file_path);
      try {
        w2vm = new W2vModel(fi);
        dm = new DataManager(file_path);
        w2vm.setDm(dm);
        flag = true;
      } catch (Exception e) {
        e.printStackTrace();
        flag = false;
      }
    }
    System.out.println("Words : " + w2vm.getWords() + "\tSize : " + w2vm.getSize());
    while(!cmd.equals("EXIT")) {
      System.out.print("何をしますか？ >");
      cmd = stdIn.nextLine();
      if (cmd.equals("ex"))
        existWord();
      else if (cmd.equals("vc"))
        prvct();
      else if (cmd.equals("hm"))
        hangarian();
      else if (cmd.equals("syn"))
        synonym();
      else if (cmd.equals("ant"))
        antonym();
      else if (cmd.equals("wmc"))
        getW_mc();
      else if (cmd.equals("nwn"))
        getnwn();
      else if (cmd.equals("nwns"))
        getnwns();
    }
  }

  private static void getnwn() {
    String trg;
    int n;
    int[] ner;

    System.out.print("近傍を調べたい単語を入力してください >");
    trg = stdIn.nextLine();
    System.out.print("近傍何個を調べますか？ >");
    n = Integer.parseInt(stdIn.nextLine());
    ner = w2vm.getNwl(w2vm.exist(trg), n);
    for (int i: ner)
      System.out.println(w2vm.getW(i));
  }
  private static void getnwns() {
    String trg;
    int n;
    int[] ner;

    System.out.print("類似語を除いた近傍を調べたい単語を入力してください >");
    trg = stdIn.nextLine();
    System.out.print("近傍何個を調べますか？ >");
    n = Integer.parseInt(stdIn.nextLine());
    ner = w2vm.getNwls(w2vm.exist(trg), n);
    for (int i: ner)
      System.out.println(w2vm.getW(i));
  }

  private static void getW_mc() {
    System.out.println("形態素解析したい文字列を入力してください >");
    cmd = stdIn.nextLine();
    dm.getWMC(cmd);
  }

  private static void synonym() {
    String target;
    System.out.print("同意語を探したい単語を入力してください >");
    target = stdIn.nextLine();
    String[] syn = dm.getWnSynonyms(target);
    if (syn == null) {
      System.out.println("不正な入力値です。");
      return ;
    }
    System.out.println(syn.toString());
    return ;
  }
  private static void antonym() {
    String target;
    System.out.print("反意語を探したい単語を入力してください >");
    target = stdIn.nextLine();
    String[] ant = dm.getWnAntonyms(target);
    if (ant == null) {
      System.out.println("不正な入力値です。");
      return ;
    }
    System.out.println(ant.toString());
    return ;
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

    System.out.println("ファイルパスの指定をしてください。");
    System.out.print("Antonym >");
    ant = stdIn.nextLine();
    dm.setFilePath(ant);
    dm.createAslist();
  }

}
