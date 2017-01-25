package w2v;

import w2v.model.DataManager;

import java.io.File;
import java.util.Arrays;
import java.util.Scanner;
import java.util.Set;

public class W2vCmd {
  private static DataManager dm;
  private static Scanner stdIn;
  private static String cmd;
  private static String file_path;

  public static void main(String[] args) {
    String file_path;
    boolean flag = false;

    cmd = "";
    stdIn = new Scanner(System.in);

    while(!flag) {
      System.out.print("Input file name is ... >");
      file_path = stdIn.nextLine();
      try {
        dm = new DataManager(new File(file_path));
        flag = true;
      } catch (Exception e) {
        e.printStackTrace();
        flag = false;
      }
    }
    System.out.println("Words : " + dm.gWords() + "\tSize : " + dm.gSize());
    while(!cmd.equals("EXIT")) {
      System.out.print("何をしますか？ >");
      cmd = stdIn.nextLine();
      if (cmd.equals("ex"))
        existWord();
      else if (cmd.equals("vc"))
        prvct();
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
      else if (cmd.equals("dist"))
        dist();
      else if (cmd.equals("synl"))
        synli();
      else if (cmd.equals("hgl"))
        hgl();
      else if (cmd.equals("hgln"))
        hgln();
      else if (cmd.equals("gsg"))
        gsg();
      else if (cmd.equals("kmhgln"))
        kmhgln();
      else if (cmd.equals("kmhgl"))
        kmhgl();
      else if (cmd.equals("sc"))
        sc();
      else if (cmd.equals("kmc"))
	km();
      else if (cmd.equals("kml"))
	kml();
    }
  }

  private static void kmc() {
    System.out.print("クラスタデータを保存するパスを入力してください >");
    cmd = stdIn.nextLine();
    dm.createC(cmd);
  }
  private static void kml() {
    System.out.print("クラスタデータを保存するパスを入力してください >");
    cmd = stdIn.nextLine();
    dm.loadC(cmd);
  }
  private static void gsg() {
    int i, n;
    int[] res;
    String trg;
    System.out.print("近傍を調べたい単語を入力してください >");
    trg = stdIn.nextLine();
    i = dm.exw(trg);
    System.out.print("近傍何個を調べますか？ >");
    n = Integer.parseInt(stdIn.nextLine());

    res = dm.gSg(i, n);
    System.out.println(trg + "の近傍単語の中心に最も近い単語は、近い順に");
    for (int j: res)
      System.out.println(dm.gWord(j));
  }
  private static void sc() {
    int idx;
    int[] res;
    System.out.print("同一クラスタを調べたい単語を入力してください >");
    idx = dm.exw(stdIn.nextLine());
    res = dm.wIc(idx);
    System.out.println(dm.gWord(idx) + " Cluster No. " + dm.wc(idx));
    for (int i = 0; i < res.length; i++)
      System.out.print(dm.gWord(res[i]) + " ");
    System.out.println();
  }
  private static void hgl() {
    int i1, i2, n;
    int[] res;
    int[] l1;
    int[] l2;

    System.out.println("調べたい単語を２つ入力してください。");
    System.out.print("単語１　：");
    i1 = dm.exw(stdIn.nextLine());
    System.out.print("単語２　：");
    i2 = dm.exw(stdIn.nextLine());
    System.out.print("近傍何個を調べますか？");
    n = Integer.parseInt(stdIn.nextLine());

    l1 = dm.gNWnS(i1, n);
    l2 = dm.gNWnS(i2, n);
    res = dm.hgln(dm.gCM(l1, l2), n);
    for (int i = 0; i < n; i++)
      System.out.println(dm.gWord(l1[i]) + "\t-\t" + dm.gWord(l2[res[i]]));
  }
  private static void hgln() {
    int i1, i2, n;
    int[] res;
    int[] l1;
    int[] l2;

    System.out.println("調べたい単語を２つ入力してください。");
    System.out.print("単語１　：");
    i1 = dm.exw(stdIn.nextLine());
    System.out.print("単語２　：");
    i2 = dm.exw(stdIn.nextLine());
    System.out.print("近傍何個を調べますか？");
    n = Integer.parseInt(stdIn.nextLine());

    l1 = dm.gNWnS(i1, n);
    l2 = dm.gNWnS(i2, n);
    res = dm.hgln(dm.gNCM(l1, l2), n);
    for (int i = 0; i < n; i++)
      System.out.println(dm.gWord(l1[i]) + "\t-\t" + dm.gWord(l2[res[i]]));
  }
  private static void dist() {
    int i;
    System.out.println("調べたい単語を２つ入力してください。");
    System.out.print("単語１　：");
    i = dm.exw(stdIn.nextLine());
    System.out.print("単語２　：");
    System.out.println("Distance : " + dm.d(i, dm.exw(stdIn.nextLine())));
  }
  private static void getnwn() {
    String trg;
    int n;
    int[] ner;

    System.out.print("近傍を調べたい単語を入力してください >");
    trg = stdIn.nextLine();
    System.out.print("近傍何個を調べますか？ >");
    n = Integer.parseInt(stdIn.nextLine());
    ner = dm.gNW(dm.exw(trg), n);
    for (int i: ner)
      System.out.println(dm.gWord(i));
  }
  private static void getnwns() {
    String trg;
    int n;
    int[] ner;

    System.out.print("類似語を除いた近傍を調べたい単語を入力してください >");
    trg = stdIn.nextLine();
    System.out.print("近傍何個を調べますか？ >");
    n = Integer.parseInt(stdIn.nextLine());
    ner = dm.gNWnS(dm.exw(trg), n);
    for (int i: ner)
      System.out.println(dm.gWord(i));
  }

  private static void getW_mc() {
    System.out.println("分かち書きしたい文字列を入力してください >");
    cmd = stdIn.nextLine();
    String[] res = dm.gSS(cmd);
    System.out.println(Arrays.asList(res));
  }

  private static void synonym() {
    String target;
    System.out.print("同意語を探したい単語を入力してください >");
    target = stdIn.nextLine();
    String[] syn = dm.gWS(target);
    if (syn == null) {
      System.out.println("不正な入力値です。");
      return ;
    }
    System.out.println(Arrays.asList(syn));
    return ;
  }
  private static void synli() {
    System.out.print("類似語を調べたい単語を入力してください >");
    cmd = stdIn.nextLine();
    int[] d = dm.gWSi(dm.exw(cmd));
    for(int i: d)
      System.out.println(i + " : " + dm.gWord(i));
  }
  private static void antonym() {
    String target;
    System.out.print("反意語を探したい単語を入力してください >");
    target = stdIn.nextLine();
    String[] ant = dm.gWA(target);
    if (ant == null) {
      System.out.println("不正な入力値です。");
      return ;
    }
    System.out.println(Arrays.asList(ant));
    return ;
  }

  private static void existWord() {
    int i = 0;

    System.out.print("探したい単語を入力してください >");
    cmd = stdIn.nextLine();
    i = dm.exw(cmd);

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
    i = dm.exw(cmd);

    if (i < 0)
      System.out.printf("%s はみつかりませんでした。\n", cmd);
    else {
      System.out.printf("%s : %d\n", cmd, i);
      vec = dm.gWV(i);
      for (j = 0; j < dm.gSize(); j++) {
        if (j % 10 == 0) System.out.println();
        System.out.printf("%f ", vec[j]);
      }
      System.out.println();
    }
  }

  private static void kmhgln() {
    int i1, i2, n;
    int[] res;
    int[] l1;
    int[] l2;

    System.out.println("調べたい単語を２つ入力してください。");
    System.out.print("単語１　：");
    i1 = dm.exw(stdIn.nextLine());
    System.out.print("単語２　：");
    i2 = dm.exw(stdIn.nextLine());
    System.out.print("近傍何個を調べますか？");
    n = Integer.parseInt(stdIn.nextLine());

    if (n > dm.wsInc(i1) || n > dm.wsInc(i2) || n < 0) {
      System.out.println("nのサイズが適切ではありません。");
      System.out.println("i1 : " + dm.wsInc(i1) + "\ti2 : " + dm.wsInc(i2));
      return ;
    }
    l1 = dm.gCw(i1, n);
    l2 = dm.gCw(i2, n);
    res = dm.hgln(dm.gNCM(l1, l2), n);
    for (int i = 0; i < n; i++)
      System.out.println(dm.gWord(l1[i]) + "\t-\t" + dm.gWord(l2[res[i]]));
  }
  private static void kmhgl() {
    int i1, i2, n;
    int[] res;
    int[] l1;
    int[] l2;

    System.out.println("調べたい単語を２つ入力してください。");
    System.out.print("単語１　：");
    i1 = dm.exw(stdIn.nextLine());
    System.out.print("単語２　：");
    i2 = dm.exw(stdIn.nextLine());
    System.out.print("近傍何個を調べますか？");
    n = Integer.parseInt(stdIn.nextLine());

    if (n > dm.wsInc(i1) || n > dm.wsInc(i2) || n < 0) {
      System.out.println("nのサイズが適切ではありません。");
      System.out.println("i1 : " + dm.wsInc(i1) + "\ti2 : " + dm.wsInc(i2));
      return ;
    }
    l1 = dm.gCw(i1, n);
    l2 = dm.gCw(i2, n);
    res = dm.hgln(dm.gCM(l1, l2), n);
    for (int i = 0; i < n; i++)
      System.out.println(dm.gWord(l1[i]) + "\t-\t" + dm.gWord(l2[res[i]]));
  }

}
