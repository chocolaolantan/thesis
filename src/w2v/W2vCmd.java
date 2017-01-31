package w2v;

import w2v.model.DataManager;
import w2v.calc.Calc;

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
    while(!cmd.equals("EXIT") && !cmd.equals("exit")) {
      System.out.print("何をしますか？ >");
      cmd = stdIn.nextLine();
      if (cmd.equals("help"))
        help();

      else if (cmd.equals("syn"))
        synonym();
      else if (cmd.equals("ant"))
        antonym();

      else if (cmd.equals("nw"))
        getNW();
      else if (cmd.equals("nwns"))
        getNWns();
      else if (cmd.equals("cosr"))
        cosr();
      else if (cmd.equals("dist"))
        dist();
      else if (cmd.equals("sminp"))
        sminp();

      else if (cmd.equals("wmc"))
        wmc();
      else if (cmd.equals("vc"))
        vc();
      else if (cmd.equals("gsg"))
        gsg();

      else if (cmd.equals("km"))
        km();
      else if (cmd.equals("clsl"))
        clsl();
      else if (cmd.equals("slc"))
        slc();
      else if (cmd.equals("cn"))
        cn();

      else if (cmd.equals("hgl"))
        hgl();
      else if (cmd.equals("kmhgl"))
        kmhgl();
      else if (cmd.equals("hglt"))
        hglt();
      else if (cmd.equals("kmhglt"))
        kmhglt();
    }
  }

  private static void help() {
    System.out.println("help\t: コマンドリストを表示します。");
    System.out.println();
    System.out.println("syn\t: 類似語を表示します。");
    System.out.println("ant\t: 対義語を表示します。");
    System.out.println();
    System.out.println("nw\t: 近傍単語を表示します。");
    System.out.println("nwns\t: 類似語を除いた、近傍単語を表示します。");
    System.out.println("cosr\t: 二単語のcos類似度を表示します。");
    System.out.println("dist\t: 二単語のユークリッド距離を表示します。");
    System.out.println("sminp\t: 二単語の内積総和を表示します。");
    System.out.println();
    System.out.println("wmc\t: 入力した文字列を分かち書きします。");
    System.out.println("vc\t: 単語のベクトルを表示します。");
    System.out.println("gsg\t: 入力単語の近傍単語グループの中心に近いものを表示します。");
    System.out.println();
    System.out.println("km\t: k-meansクラスタリングを行います。");
    System.out.println("clsl\t: クラスタデータを読み込みます。");
    System.out.println("slc\t: 類似語関係データを作成します。");
    System.out.println("cn\t: 単語のクラスタ情報を表示します。");
    System.out.println();
    System.out.println("hgl\t: 近傍単語でハンガリアン法を適用する。");
    System.out.println("kmhgl\t: クラスタ内の近傍単語でハンガリアン法を適用する。");
    System.out.println("hglt\t: 近傍単語でハンガリアン法を適用する。(テストモード)");
    System.out.println("kmhglt\t: クラスタ内の近傍単語でハンガリアン法を適用する。(テストモード)");

  }

  private static void synonym() {
    System.out.print("類似語を調べたい単語を入力してください >");
    cmd = stdIn.nextLine();
    int[] d = dm.gWSi(dm.exw(cmd));
    if (d == null) {
      System.out.println("不正な入力値です。");
      return ;
    }
    for(int i: d)
      System.out.println(i + " : " + dm.gWord(i));
  }
  private static void antonym() {
    System.out.print("対義語を調べたい単語を入力してください >");
    cmd = stdIn.nextLine();
    int[] d = dm.gWAi(dm.exw(cmd));
    if (d == null) {
      System.out.println("不正な入力値です。");
      return ;
    }
    for(int i: d)
      System.out.println(i + " : " + dm.gWord(i));
  }

  private static void getNW() {
    String trg;
    int n;
    int[] ner;

    System.out.print("近傍を調べたい単語を入力してください >");
    trg = stdIn.nextLine();
    System.out.print("近傍何個を調べますか？ >");
    n = Integer.parseInt(stdIn.nextLine());
    ner = dm.gNW(dm.exw(trg), n);
    if (ner == null) {
      System.out.println("不正な入力値です。");
      return ;
    }
    for (int i: ner)
      System.out.println(dm.gWord(i));
  }
  private static void getNWns() {
    String trg;
    int n;
    int[] ner;

    System.out.print("類似語を除いた近傍を調べたい単語を入力してください >");
    trg = stdIn.nextLine();
    System.out.print("近傍何個を調べますか？ >");
    n = Integer.parseInt(stdIn.nextLine());
    ner = dm.gNWnS(dm.exw(trg), n);
    if (ner == null) {
      System.out.println("不正な入力値です。");
      return ;
    }
    for (int i: ner)
      System.out.println(dm.gWord(i));
  }
  private static void cosr() {
    int i1, i2;
    System.out.println("調べたい単語を２つ入力してください。");
    System.out.print("単語１　：");
    i1 = dm.exw(stdIn.nextLine());
    System.out.print("単語２　：");
    i2 = dm.exw(stdIn.nextLine());
    if (i1 < 0 || i2 < 0) {
      System.out.println("学習データにない単語があります。");
      return ;
    }
    System.out.println("Distance : " + Calc.cosr(dm.gWV(i1), dm.gWV(i2)));
    return ;
  }
  private static void dist() {
    int i1, i2;
    System.out.println("調べたい単語を２つ入力してください。");
    System.out.print("単語１　：");
    i1 = dm.exw(stdIn.nextLine());
    System.out.print("単語２　：");
    i2 = dm.exw(stdIn.nextLine());
    if (i1 < 0 || i2 < 0) {
      System.out.println("学習データにない単語があります。");
      return ;
    }
    System.out.println("Distance : " + Calc.dist(dm.gWV(i1), dm.gWV(i2)));
    return ;
  }
  private static void sminp() {
    int i1, i2;
    System.out.println("調べたい単語を２つ入力してください。");
    System.out.print("単語１　：");
    i1 = dm.exw(stdIn.nextLine());
    System.out.print("単語２　：");
    i2 = dm.exw(stdIn.nextLine());
    if (i1 < 0 || i2 < 0) {
      System.out.println("学習データにない単語があります。");
      return ;
    }
    System.out.println("Distance : " + Calc.sminp(dm.gWV(i1), dm.gWV(i2)));
    return ;
  }

  private static void wmc() {
    System.out.println("分かち書きしたい文字列を入力してください >");
    cmd = stdIn.nextLine();
    String[] res = dm.gSS(cmd);
    System.out.println(Arrays.asList(res));
  }
  private static void vc() {
    int i = 0, j;
    float[] vec;

    System.out.print("探したい単語を入力してください >");
    cmd = stdIn.nextLine();
    i = dm.exw(cmd);

    if (i < 0)
      System.out.printf("%s はみつかりませんでした。\n", cmd);
    else {
      vec = dm.gWV(i);
      System.out.printf("%s : %d\tLength : %f\n", cmd, i, Calc.len(vec));
      for (j = 0; j < dm.gSize(); j++) {
        if (j % 10 == 0)
          System.out.println();
        System.out.printf("%f ", vec[j]);
      }
      System.out.println();
    }
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

  private static void km() {
    int n;
    String save_path;

    System.out.print("いくつのクラスターに分けますか？ >");
    n = Integer.parseInt(stdIn.nextLine());
    System.out.print("クラスタデータを保存するパスを入力してください >");
    save_path = stdIn.nextLine();
    if (!dm.kMeans(n, save_path))
      System.out.println("失敗しました。");
  }
  private static void clsl() {
    System.out.print("クラスタデータを読み込むパスを入力してください >");
    cmd = stdIn.nextLine();
    if(dm.loadC(cmd)) {
      System.out.println("読み込みに失敗しました。");
      return ;
    }

    System.out.println("学習を開始しますか？(y/n) >");
    if (stdIn.nextLine().equals("y"))
      if (dm.learnC()) {
        System.out.print("学習データをセーブしますか？(y/n) >");
        if (stdIn.nextLine().equals("y")) {
          System.out.print("保存パスを入力してください >");
          if (dm.saveC(stdIn.nextLine())) {
            System.out.println("保存完了しました。");
            return ;
          } else {
            System.out.println("保存に失敗しました。");
            return ;
          }
        } else
          return ;
      } else {
        System.out.println("学習に失敗しました。");
        return;
      }
    return ;
  }
  private static void slc() {
    int[] list;
    System.out.print("類似語関係を保存するパスを入力してください >");
    cmd = stdIn.nextLine();
    list = dm.synListCreate(cmd);
    if (list == null || list.length <= 0) {
      System.out.println("リストの作成に失敗しました。");
      return ;
    }
  }
  private static void cn() {
    int idx, cno, vol;
    System.out.print("クラスタを調べたい単語を入力してください。 >");
    idx = dm.exw(stdIn.nextLine());
    if (idx < 0) {
      System.out.println("入力された単語は辞書に存在しません。");
      return ;
    }
    cno = dm.wc(idx);
    vol = dm.wsInc(cno);
    System.out.println("No. "+ idx + "\tCluster No. " + cno + "\tVolume : " + vol);
    int[] list = dm.gCw(idx, vol);
    for (int i: list)
      System.out.print(dm.gWord(i) + " ");
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

    System.out.print("類似語は除外しますか？(y/n) >");
    if (stdIn.nextLine().equals("y")) {
      l1 = dm.gNWnS(i1, n);
      l2 = dm.gNWnS(i2, n);
    } else {
      l1 = dm.gNW(i1, n);
      l2 = dm.gNW(i2, n);
    }
    if (l1 == null || l2 == null) {
      System.out.println("コスト行列を作成できません。");
      return ;
    }

    System.out.print("コスト行列の作成にはどの指標を使いますか？(c/d/s) >");
    cmd = stdIn.nextLine();
    if (cmd.equals("c")) {
      System.out.print("各グループを、その中心で正規化しますか？(y/n) >");
      if (stdIn.nextLine().equals("y")) {
        System.out.print("最小の組み合わせを求めますか？(y/n) >");
        if (stdIn.nextLine().equals("y"))
          res = Calc.hangarian(dm.gNRCM(l1, l2), n);
        else
          res = Calc.hangarian(dm.gNCM(l1, l2), n);
      } else {
        System.out.print("最小の組み合わせを求めますか？(y/n) >");
        if (stdIn.nextLine().equals("y"))
          res = Calc.hangarian(dm.gRCM(l1, l2), n);
        else
          res = Calc.hangarian(dm.gCM(l1, l2), n);
      }
    } else if (cmd.equals("d")) {
      System.out.print("各グループを、その中心で正規化しますか？(y/n) >");
      if (stdIn.nextLine().equals("y")) {
        System.out.print("最小の組み合わせを求めますか？(y/n) >");
        if (stdIn.nextLine().equals("y"))
          res = Calc.hangarian(dm.gNRDM(l1, l2), n);
        else
          res = Calc.hangarian(dm.gNDM(l1, l2), n);
      } else {
        System.out.print("最小の組み合わせを求めますか？(y/n) >");
        if (stdIn.nextLine().equals("y"))
          res = Calc.hangarian(dm.gRDM(l1, l2), n);
        else
          res = Calc.hangarian(dm.gDM(l1, l2), n);
      }
    } else {
      System.out.print("各グループを、その中心で正規化しますか？(y/n) >");
      if (stdIn.nextLine().equals("y")) {
        System.out.print("最小の組み合わせを求めますか？(y/n) >");
        if (stdIn.nextLine().equals("y"))
          res = Calc.hangarian(dm.gNRSM(l1, l2), n);
        else
          res = Calc.hangarian(dm.gNSM(l1, l2), n);
      } else {
        System.out.print("最小の組み合わせを求めますか？(y/n) >");
        if (stdIn.nextLine().equals("y"))
          res = Calc.hangarian(dm.gRSM(l1, l2), n);
        else
          res = Calc.hangarian(dm.gSM(l1, l2), n);
      }
    }

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
    System.out.print("それぞれのクラスタの近傍何個を調べますか？");
    n = Integer.parseInt(stdIn.nextLine());

    if (n > dm.wsInc(dm.wc(i1)) || n > dm.wsInc(dm.wc(i2)) || n < 0) {
      System.out.println("nのサイズが適切ではありません。");
      System.out.println("i1 : " + dm.wsInc(dm.wc(i1)) + "\ti2 : " + dm.wsInc(dm.wc(i2)));
      return ;
    }
    l1 = dm.gCw(i1, n);
    l2 = dm.gCw(i2, n);
    if (l1 == null || l2 == null) {
      System.out.println("コスト行列を作成できません。");
      return ;
    }

    System.out.print("コスト行列の作成にはどの指標を使いますか？(c/d/s) >");
    cmd = stdIn.nextLine();
    if (cmd.equals("c")) {
      System.out.print("各グループを、その中心で正規化しますか？(y/n) >");
      if (stdIn.nextLine().equals("y")) {
        System.out.print("最小の組み合わせを求めますか？(y/n) >");
        if (stdIn.nextLine().equals("y"))
          res = Calc.hangarian(dm.gNRCM(l1, l2), n);
        else
          res = Calc.hangarian(dm.gNCM(l1, l2), n);
      } else {
        System.out.print("最小の組み合わせを求めますか？(y/n) >");
        if (stdIn.nextLine().equals("y"))
          res = Calc.hangarian(dm.gRCM(l1, l2), n);
        else
          res = Calc.hangarian(dm.gCM(l1, l2), n);
      }
    } else if (cmd.equals("d")) {
      System.out.print("各グループを、その中心で正規化しますか？(y/n) >");
      if (stdIn.nextLine().equals("y")) {
        System.out.print("最小の組み合わせを求めますか？(y/n) >");
        if (stdIn.nextLine().equals("y"))
          res = Calc.hangarian(dm.gNRDM(l1, l2), n);
        else
          res = Calc.hangarian(dm.gNDM(l1, l2), n);
      } else {
        System.out.print("最小の組み合わせを求めますか？(y/n) >");
        if (stdIn.nextLine().equals("y"))
          res = Calc.hangarian(dm.gRDM(l1, l2), n);
        else
          res = Calc.hangarian(dm.gDM(l1, l2), n);
      }
    } else {
      System.out.print("各グループを、その中心で正規化しますか？(y/n) >");
      if (stdIn.nextLine().equals("y")) {
        System.out.print("最小の組み合わせを求めますか？(y/n) >");
        if (stdIn.nextLine().equals("y"))
          res = Calc.hangarian(dm.gNRSM(l1, l2), n);
        else
          res = Calc.hangarian(dm.gNSM(l1, l2), n);
      } else {
        System.out.print("最小の組み合わせを求めますか？(y/n) >");
        if (stdIn.nextLine().equals("y"))
          res = Calc.hangarian(dm.gRSM(l1, l2), n);
        else
          res = Calc.hangarian(dm.gSM(l1, l2), n);
      }
    }

    for (int i = 0; i < n; i++)
      System.out.println(dm.gWord(l1[i]) + "\t-\t" + dm.gWord(l2[res[i]]));
  }
  private static void hglt() {
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

    System.out.print("類似語は除外しますか？(y/n) >");
    if (stdIn.nextLine().equals("y")) {
      l1 = dm.gNWnS(i1, n);
      l2 = dm.gNWnS(i2, n);
    } else {
      l1 = dm.gNW(i1, n);
      l2 = dm.gNW(i2, n);
    }
    if (l1 == null || l2 == null) {
      System.out.println("コスト行列を作成できません。");
      return ;
    }

    System.out.print("コスト行列の作成にはどの指標を使いますか？(c/d/s) >");
    cmd = stdIn.nextLine();
    if (cmd.equals("c")) {
      System.out.print("各グループを、その中心で正規化しますか？(y/n) >");
      if (stdIn.nextLine().equals("y")) {
        System.out.print("最小の組み合わせを求めますか？(y/n) >");
        if (stdIn.nextLine().equals("y"))
          res = Calc.hangarian(dm.gNRCM(l1, l2), n);
        else
          res = Calc.hangarian(dm.gNCM(l1, l2), n);
      } else {
        System.out.print("最小の組み合わせを求めますか？(y/n) >");
        if (stdIn.nextLine().equals("y"))
          res = Calc.hangarian(dm.gRCM(l1, l2), n);
        else
          res = Calc.hangarian(dm.gCM(l1, l2), n);
      }
    } else if (cmd.equals("d")) {
      System.out.print("各グループを、その中心で正規化しますか？(y/n) >");
      if (stdIn.nextLine().equals("y")) {
        System.out.print("最小の組み合わせを求めますか？(y/n) >");
        if (stdIn.nextLine().equals("y"))
          res = Calc.hangarian(dm.gNRDM(l1, l2), n);
        else
          res = Calc.hangarian(dm.gNDM(l1, l2), n);
      } else {
        System.out.print("最小の組み合わせを求めますか？(y/n) >");
        if (stdIn.nextLine().equals("y"))
          res = Calc.hangarian(dm.gRDM(l1, l2), n);
        else
          res = Calc.hangarian(dm.gDM(l1, l2), n);
      }
    } else {
      System.out.print("各グループを、その中心で正規化しますか？(y/n) >");
      if (stdIn.nextLine().equals("y")) {
        System.out.print("最小の組み合わせを求めますか？(y/n) >");
        if (stdIn.nextLine().equals("y"))
          res = Calc.hangarian(dm.gNRSM(l1, l2), n);
        else
          res = Calc.hangarian(dm.gNSM(l1, l2), n);
      } else {
        System.out.print("最小の組み合わせを求めますか？(y/n) >");
        if (stdIn.nextLine().equals("y"))
          res = Calc.hangarian(dm.gRSM(l1, l2), n);
        else
          res = Calc.hangarian(dm.gSM(l1, l2), n);
      }
    }

    for (int i = 0; i < n; i++)
      System.out.println(dm.gWord(l2[res[i]]));
  }
  private static void kmhglt() {
    int i1, i2, n;
    int[] res;
    int[] l1;
    int[] l2;

    System.out.println("調べたい単語を２つ入力してください。");
    System.out.print("単語１　：");
    i1 = dm.exw(stdIn.nextLine());
    System.out.print("単語２　：");
    i2 = dm.exw(stdIn.nextLine());
    System.out.print("それぞれのクラスタの近傍何個を調べますか？");
    n = Integer.parseInt(stdIn.nextLine());

    if (n > dm.wsInc(dm.wc(i1)) || n > dm.wsInc(dm.wc(i2)) || n < 0) {
      System.out.println("nのサイズが適切ではありません。");
      System.out.println("i1 : " + dm.wsInc(dm.wc(i1)) + "\ti2 : " + dm.wsInc(dm.wc(i2)));
      return ;
    }
    l1 = dm.gCw(i1, n);
    l2 = dm.gCw(i2, n);
    if (l1 == null || l2 == null) {
      System.out.println("コスト行列を作成できません。");
      return ;
    }

    System.out.print("コスト行列の作成にはどの指標を使いますか？(c/d/s) >");
    cmd = stdIn.nextLine();
    if (cmd.equals("c")) {
      System.out.print("各グループを、その中心で正規化しますか？(y/n) >");
      if (stdIn.nextLine().equals("y")) {
        System.out.print("最小の組み合わせを求めますか？(y/n) >");
        if (stdIn.nextLine().equals("y"))
          res = Calc.hangarian(dm.gNRCM(l1, l2), n);
        else
          res = Calc.hangarian(dm.gNCM(l1, l2), n);
      } else {
        System.out.print("最小の組み合わせを求めますか？(y/n) >");
        if (stdIn.nextLine().equals("y"))
          res = Calc.hangarian(dm.gRCM(l1, l2), n);
        else
          res = Calc.hangarian(dm.gCM(l1, l2), n);
      }
    } else if (cmd.equals("d")) {
      System.out.print("各グループを、その中心で正規化しますか？(y/n) >");
      if (stdIn.nextLine().equals("y")) {
        System.out.print("最小の組み合わせを求めますか？(y/n) >");
        if (stdIn.nextLine().equals("y"))
          res = Calc.hangarian(dm.gNRDM(l1, l2), n);
        else
          res = Calc.hangarian(dm.gNDM(l1, l2), n);
      } else {
        System.out.print("最小の組み合わせを求めますか？(y/n) >");
        if (stdIn.nextLine().equals("y"))
          res = Calc.hangarian(dm.gRDM(l1, l2), n);
        else
          res = Calc.hangarian(dm.gDM(l1, l2), n);
      }
    } else {
      System.out.print("各グループを、その中心で正規化しますか？(y/n) >");
      if (stdIn.nextLine().equals("y")) {
        System.out.print("最小の組み合わせを求めますか？(y/n) >");
        if (stdIn.nextLine().equals("y"))
          res = Calc.hangarian(dm.gNRSM(l1, l2), n);
        else
          res = Calc.hangarian(dm.gNSM(l1, l2), n);
      } else {
        System.out.print("最小の組み合わせを求めますか？(y/n) >");
        if (stdIn.nextLine().equals("y"))
          res = Calc.hangarian(dm.gRSM(l1, l2), n);
        else
          res = Calc.hangarian(dm.gSM(l1, l2), n);
      }
    }

    for (int i = 0; i < n; i++)
      System.out.println(dm.gWord(l2[res[i]]));
  }
}
