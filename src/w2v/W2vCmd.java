package w2v;

import w2v.model.DataManager;
import w2v.model.Kmeans;
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
      else if (cmd.equals("exper"))
        exper();

      else if (cmd.equals("syn"))
        synonym();
      else if (cmd.equals("ant"))
        antonym();
      else if (cmd.equals("hype"))
        hypernym();
      else if (cmd.equals("hypo"))
        hyponym();
      else if (cmd.equals("holo"))
        holonym();
      else if (cmd.equals("mero"))
        meronym();
      else if (cmd.equals("dom"))
        domain();
      else if (cmd.equals("idom"))
        indomains();
      else if (cmd.equals("def"))
        definition();
      else if (cmd.equals("cau"))
        causes();
      else if (cmd.equals("sa"))
        seealso();

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
      else if (cmd.equals("fhgl"))
        fhgl();
      else if (cmd.equals("kmhgl"))
        kmhgl();
      else if (cmd.equals("hypehgl"))
        hypehgl();
      else if (cmd.equals("mrhgl"))
        mrhgl();
      else if (cmd.equals("hlhgl"))
        hlhgl();
      else if (cmd.equals("hypehgl"))
        hypehgl();
      else if (cmd.equals("hypohgl"))
        hypohgl();
      else if (cmd.equals("hglt"))
        hglt();
      else if (cmd.equals("kmhglt"))
        kmhglt();
      else if (cmd.equals("hypehglt"))
        hypehglt();
      else if (cmd.equals("hypohglt"))
        hypohglt();
      else if (cmd.equals("mrhglt"))
        mrhglt();
      else if (cmd.equals("hlhglt"))
        hlhglt();
    }
  }

  private static void help() {
    System.out.println("help\t: コマンドリストを表示します。");
    System.out.println("exper\t: 実験用コマンド。");
    System.out.println();
    System.out.println("syn\t: 類似語を表示します。");
    System.out.println("ant\t: 対義語を表示します。");
    System.out.println("hype\t: 上位語を表示します。");
    System.out.println("hypo\t: 下位語を表示します。");
    System.out.println("holo\t: 同義語を表示します。");
    System.out.println("mero\t: 同義語を表示します。");
    System.out.println("dom\t: ドメインを表示します。");
    System.out.println("idom\t: ドメインに含まれている語を表示します。");
    System.out.println("def\t: 定義を表示します。");
    System.out.println("cau\t: 原因を表示します。");
    System.out.println("sa\t: 関係がありそうな単語を表示します。");
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
    System.out.println("fhgl\t: 単語リストファイルに収録されている単語でハンガリアン法を適用する。");
    System.out.println("kmhgl\t: クラスタ内の近傍単語でハンガリアン法を適用する。");
    System.out.println("hypehgl\t: 上位語に基づいて単語を持って来て、ハンガリアン法を適用する。");
    System.out.println("hypohgl\t: 下位語に基づいて単語を持って来て、ハンガリアン法を適用する。");
    System.out.println("mrhgl\t: Meronymに基づいて単語を持って来て、ハンガリアン法を適用する。");
    System.out.println("hlhgl\t: Holonymに基づいて単語を持って来て、ハンガリアン法を適用する。");
    System.out.println();
    System.out.println("hglt\t: 近傍単語でハンガリアン法を適用する。(テストモード)");
    System.out.println("kmhglt\t: クラスタ内の近傍単語でハンガリアン法を適用する。(テストモード)");
    System.out.println("hypehglt\t: 上位語に基づいて単語を持って来て、ハンガリアン法を適用する。(テストモード)");
    System.out.println("hypohglt\t: 下位語に基づいて単語を持って来て、ハンガリアン法を適用する。(テストモード)");
    System.out.println("mrhglt\t: Meronymに基づいて単語を持って来て、ハンガリアン法を適用する。(テストモード)");
    System.out.println("hlhglt\t: Holonymに基づいて単語を持って来て、ハンガリアン法を適用する。(テストモード)");

  }
  private static void exper() {
    int i1, i2, n, c, i, j;
    int[] res;
    int[] l1;
    int[] l2;
    int[] l;
    String w, s, m;
    Kmeans km1, km2;

    System.out.println("調べたい単語を２つ入力してください。");
    System.out.print("単語１　：");
    i1 = dm.exw(stdIn.nextLine());
    System.out.print("単語２　：");
    i2 = dm.exw(stdIn.nextLine());
    System.out.print("近傍何個を調べますか？");
    n = Integer.parseInt(stdIn.nextLine());
    System.out.print("クラスター数はいくつにしますか？");
    c = Integer.parseInt(stdIn.nextLine());

    l1 = dm.gNW(i1, n);
    l2 = dm.gNW(i2, n);
    if (l1 == null || l2 == null) {
      System.out.println("ベクトルを取得できません。");
      return ;
    }

    km1 = dm.kmLearn(c, dm.gWVs(l1), l1);
    km2 = dm.kmLearn(c, dm.gWVs(l2), l2);

    System.out.print("コスト行列の作成にはどの指標を使いますか？(c/d/s) >");
    w = stdIn.nextLine();

    System.out.print("各グループを、その中心で正規化しますか？(y/n) >");
    s = stdIn.nextLine();

    System.out.print("最小の組み合わせを求めますか？(y/n) >");
    m = stdIn.nextLine();
    System.out.println("クラスター数");
    System.out.println("k1\tk2");
    for (i = 0; i < c; i++)
      System.out.println(i + " : " + km1.ClustSize(i) + "\t" + km2.ClustSize(i));
    System.out.println("Hungarian Start!!");
    res = dm.hangf(km1.getG(), km2.getG(), c, w, s, m);

    System.out.println(dm.gWord(i1) + " から");
    for (i = 0; i < c; i++) {
      l = km1.wordsInClust(i);
      System.out.println("クラスター：" + i + " ---------------------------------");
      for (j = 0; j < l.length; j++)
        System.out.println(dm.gWord(l[j]));
      System.out.println("--------------------------------------------------");
      System.out.println();
    }
    System.out.println(dm.gWord(i2) + " から");
    for (i = 0; i < c; i++) {
      l = km1.wordsInClust(i);
      System.out.println("クラスター：" + i + " ---------------------------------");
      for (j = 0; j < l.length; j++)
        System.out.println(dm.gWord(l[j]));
      System.out.println("--------------------------------------------------");
      System.out.println();
    }
    System.out.println("クラスター割当結果");
    for (i = 0; i < res.length; i++)
      System.out.println(i + "\t-\t" + res[i]);
  }

  private static void synonym() {
    System.out.print("類似語を調べたい単語を入力してください >");
    cmd = stdIn.nextLine();
    String[] d = dm.gWS(cmd);
    if (d == null) {
      System.out.println("不正な入力値です。");
      return ;
    }
    for(String i: d)
      System.out.println(i + " ");
  }
  private static void antonym() {
    System.out.print("対義語を調べたい単語を入力してください >");
    cmd = stdIn.nextLine();
    String[] d = dm.gWA(cmd);
    if (d == null) {
      System.out.println("不正な入力値です。");
      return ;
    }
    for(String i: d)
      System.out.println(i + " ");
  }
  private static void hypernym() {
    System.out.print("上位語を調べたい単語を入力してください >");
    cmd = stdIn.nextLine();
    String[] d = dm.gHpe(cmd);
    if (d == null) {
      System.out.println("不正な入力値です。");
      return ;
    }
    for(String i: d)
      System.out.println(i + " ");
  }
  private static void hyponym() {
    System.out.print("下位語を調べたい単語を入力してください >");
    cmd = stdIn.nextLine();
    int[] d = dm.gHpoi(dm.exw(cmd));
    if (d == null) {
      System.out.println("不正な入力値です。");
      return ;
    }
    for(int i: d)
      System.out.println(i + " : " + dm.gWord(i));
  }
  private static void holonym() {
    System.out.print("同義語を調べたい単語を入力してください >");
    cmd = stdIn.nextLine();
    String[] d = dm.gHl(cmd);
    if (d == null) {
      System.out.println("不正な入力値です。");
      return ;
    }
    for(String i: d)
      System.out.println(i + " ");
  }
  private static void meronym() {
    System.out.print("同義語を調べたい単語を入力してください >");
    cmd = stdIn.nextLine();
    String[] d = dm.gMr(cmd);
    if (d == null) {
      System.out.println("不正な入力値です。");
      return ;
    }
    for(String i: d)
      System.out.println(i + " ");
  }
  private static void domain() {
    System.out.print("ドメインを調べたい単語を入力してください >");
    cmd = stdIn.nextLine();
    String[] d = dm.gDo(cmd);
    if (d == null) {
      System.out.println("不正な入力値です。");
      return ;
    }
    for(String i: d)
      System.out.println(i + " ");
  }
  private static void indomains() {
    System.out.print("ドメインに含まれている語を調べたい単語を入力してください >");
    cmd = stdIn.nextLine();
    String[] d = dm.gId(cmd);
    if (d == null) {
      System.out.println("不正な入力値です。");
      return ;
    }
    for(String i: d)
      System.out.println(i + " ");
  }
  private static void definition() {
    System.out.print("定義を調べたい単語を入力してください >");
    cmd = stdIn.nextLine();
    String[] d = dm.gDef(cmd);
    if (d == null) {
      System.out.println("不正な入力値です。");
      return ;
    }
    for(String i: d)
      System.out.println(i);
  }
  private static void causes() {
    System.out.print("原因を調べたい単語を入力してください >");
    cmd = stdIn.nextLine();
    String[] d = dm.gCa(cmd);
    if (d == null) {
      System.out.println("不正な入力値です。");
      return ;
    }
    for(String i: d)
      System.out.println(i);
  }
  private static void seealso() {
    System.out.print("関係がありそうな単語を調べたい単語を入力してください >");
    cmd = stdIn.nextLine();
    String[] d = dm.gSA(cmd);
    if (d == null) {
      System.out.println("不正な入力値です。");
      return ;
    }
    for(String i: d)
      System.out.println(i + " ");
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
    Kmeans km = new Kmeans();
    System.out.print("クラスタデータを読み込むパスを入力してください >");
    cmd = stdIn.nextLine();
    if(!km.loadClust(cmd)) {
      System.out.println("読み込みに失敗しました。");
      return ;
    }

    System.out.println("学習を開始しますか？(y/n) >");
    if (stdIn.nextLine().equals("y"))
      if (km.lear(dm.gAV())) {
        System.out.print("学習データをセーブしますか？(y/n) >");
        if (stdIn.nextLine().equals("y")) {
          System.out.print("保存パスを入力してください >");
          if (km.saveClust(stdIn.nextLine())) {
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
    String w, s, m;

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
    w = stdIn.nextLine();

    System.out.print("各グループを、その中心で正規化しますか？(y/n) >");
    s = stdIn.nextLine();

    System.out.print("最小の組み合わせを求めますか？(y/n) >");
    m = stdIn.nextLine();

    res = dm.hang(l1, l2, n, w, s, m);

    hglprt(l1, l2, res, w, s, m);
  }
  private static void fhgl() {
    int[] res;
    int[] l1;
    int[] l2;
    String f1, f2, w, s, m;

    System.out.println("調べたい単語が記録されているリストファイルのパスを２つ入力してください。");
    System.out.print("単語リスト１　：");
    l1 = dm.loadMorephemeList(stdIn.nextLine());
    System.out.print("単語リスト２　：");
    l2 = dm.loadMorephemeList(stdIn.nextLine());

    if (l1 == null || l2 == null || l1.length != l2.length) {
      System.out.println("コスト行列を作成できません。");
      return ;
    }

    System.out.print("コスト行列の作成にはどの指標を使いますか？(c/d/s) >");
    w = stdIn.nextLine();

    System.out.print("各グループを、その中心で正規化しますか？(y/n) >");
    s = stdIn.nextLine();

    System.out.print("最小の組み合わせを求めますか？(y/n) >");
    m = stdIn.nextLine();

    res = dm.hang(l1, l2, l1.length, w, s, m);

    hglprt(l1, l2, res, w, s, m);
  }
  private static void kmhgl() {
    int i1, i2, n;
    int[] res;
    int[] l1;
    int[] l2;
    String w, s, m;

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
    w = stdIn.nextLine();

    System.out.print("各グループを、その中心で正規化しますか？(y/n) >");
    s = stdIn.nextLine();

    System.out.print("最小の組み合わせを求めますか？(y/n) >");
    m = stdIn.nextLine();

    res = dm.hang(l1, l2, n, w, s, m);

    hglprt(l1, l2, res, w, s, m);
  }
  private static void hypehgl() {
    int i1, i2, n;
    int[] res;
    int[] l1;
    int[] l2;
    String w, s, m;

    System.out.println("調べたい単語を２つ入力してください。");
    System.out.print("単語１　：");
    i1 = dm.exw(stdIn.nextLine());
    System.out.print("単語２　：");
    i2 = dm.exw(stdIn.nextLine());
    System.out.print("それぞれの上位語の近傍何個を調べますか？");
    n = Integer.parseInt(stdIn.nextLine());
    l1 = Calc.noneN(dm.gHpei(i1), -1);
    l2 = Calc.noneN(dm.gHpei(i2), -1);

    if (l1 == null || l2 == null) {
      System.out.println("コスト行列を作成できません。");
      return ;
    }
    if (n > l1.length || n > l2.length || n < 0) {
      System.out.println("nのサイズが適切ではありません。");
      System.out.println("i1 : " + l1.length + "\ti2 : " + l2.length);
      return ;
    }

    l1 = dm.gNWiL(i1, n, l1);
    l2 = dm.gNWiL(i2, n, l2);

    System.out.print("コスト行列の作成にはどの指標を使いますか？(c/d/s) >");
    w = stdIn.nextLine();

    System.out.print("各グループを、その中心で正規化しますか？(y/n) >");
    s = stdIn.nextLine();

    System.out.print("最小の組み合わせを求めますか？(y/n) >");
    m = stdIn.nextLine();

    res = dm.hang(l1, l2, n, w, s, m);

    hglprt(l1, l2, res, w, s, m);
  }
  private static void hypohgl() {
    int i1, i2, n;
    int[] res;
    int[] l1;
    int[] l2;
    String w, s, m;

    System.out.println("調べたい単語を２つ入力してください。");
    System.out.print("単語１　：");
    i1 = dm.exw(stdIn.nextLine());
    System.out.print("単語２　：");
    i2 = dm.exw(stdIn.nextLine());
    System.out.print("それぞれの下位語の近傍何個を調べますか？");
    n = Integer.parseInt(stdIn.nextLine());
    l1 = Calc.noneN(dm.gHpoi(i1), -1);
    l2 = Calc.noneN(dm.gHpoi(i2), -1);

    if (l1 == null || l2 == null) {
      System.out.println("コスト行列を作成できません。");
      return ;
    }
    if (n > l1.length || n > l2.length || n < 0) {
      System.out.println("nのサイズが適切ではありません。");
      System.out.println("i1 : " + l1.length + "\ti2 : " + l2.length);
      return ;
    }

    l1 = dm.gNWiL(i1, n, l1);
    l2 = dm.gNWiL(i2, n, l2);

    System.out.print("コスト行列の作成にはどの指標を使いますか？(c/d/s) >");
    w = stdIn.nextLine();

    System.out.print("各グループを、その中心で正規化しますか？(y/n) >");
    s = stdIn.nextLine();

    System.out.print("最小の組み合わせを求めますか？(y/n) >");
    m = stdIn.nextLine();

    res = dm.hang(l1, l2, n, w, s, m);

    hglprt(l1, l2, res, w, s, m);
  }
  private static void mrhgl() {
    int i1, i2, n;
    int[] res;
    int[] l1;
    int[] l2;
    String w, s, m;

    System.out.println("調べたい単語を２つ入力してください。");
    System.out.print("単語１　：");
    i1 = dm.exw(stdIn.nextLine());
    System.out.print("単語２　：");
    i2 = dm.exw(stdIn.nextLine());
    System.out.print("それぞれの構成要素の近傍何個を調べますか？");
    n = Integer.parseInt(stdIn.nextLine());
    l1 = Calc.noneN(dm.gMri(i1), -1);
    l2 = Calc.noneN(dm.gMri(i2), -1);

    if (l1 == null || l2 == null) {
      System.out.println("コスト行列を作成できません。");
      return ;
    }
    if (n > l1.length || n > l2.length || n < 0) {
      System.out.println("nのサイズが適切ではありません。");
      System.out.println("i1 : " + l1.length + "\ti2 : " + l2.length);
      return ;
    }

    l1 = dm.gNWiL(i1, n, l1);
    l2 = dm.gNWiL(i2, n, l2);

    System.out.print("コスト行列の作成にはどの指標を使いますか？(c/d/s) >");
    w = stdIn.nextLine();

    System.out.print("各グループを、その中心で正規化しますか？(y/n) >");
    s = stdIn.nextLine();

    System.out.print("最小の組み合わせを求めますか？(y/n) >");
    m = stdIn.nextLine();

    res = dm.hang(l1, l2, n, w, s, m);

    hglprt(l1, l2, res, w, s, m);
  }
  private static void hlhgl() {
    int i1, i2, n;
    int[] res;
    int[] l1;
    int[] l2;
    String w, s, m;

    System.out.println("調べたい単語を２つ入力してください。");
    System.out.print("単語１　：");
    i1 = dm.exw(stdIn.nextLine());
    System.out.print("単語２　：");
    i2 = dm.exw(stdIn.nextLine());
    System.out.print("それぞれの構成要素の近傍何個を調べますか？");
    n = Integer.parseInt(stdIn.nextLine());
    l1 = Calc.noneN(dm.gHli(i1), -1);
    l2 = Calc.noneN(dm.gHli(i2), -1);

    if (l1 == null || l2 == null) {
      System.out.println("コスト行列を作成できません。");
      return ;
    }
    if (n > l1.length || n > l2.length || n < 0) {
      System.out.println("nのサイズが適切ではありません。");
      System.out.println("i1 : " + l1.length + "\ti2 : " + l2.length);
      return ;
    }

    l1 = dm.gNWiL(i1, n, l1);
    l2 = dm.gNWiL(i2, n, l2);

    System.out.print("コスト行列の作成にはどの指標を使いますか？(c/d/s) >");
    w = stdIn.nextLine();

    System.out.print("各グループを、その中心で正規化しますか？(y/n) >");
    s = stdIn.nextLine();

    System.out.print("最小の組み合わせを求めますか？(y/n) >");
    m = stdIn.nextLine();

    res = dm.hang(l1, l2, n, w, s, m);

    hglprt(l1, l2, res, w, s, m);
  }

  private static void hglt() {
    int i1, i2, n;
    int[] res;
    int[] l1;
    int[] l2;
    String w, s, m;

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
    w = stdIn.nextLine();

    System.out.print("各グループを、その中心で正規化しますか？(y/n) >");
    s = stdIn.nextLine();

    System.out.print("最小の組み合わせを求めますか？(y/n) >");
    m = stdIn.nextLine();

    res = dm.hang(l1, l2, n, w, s, m);

    for (int i = 0; i < n; i++)
      System.out.println(dm.gWord(l2[res[i]]));
  }
  private static void kmhglt() {
    int i1, i2, n;
    int[] res;
    int[] l1;
    int[] l2;
    String w, s, m;

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
    w = stdIn.nextLine();

    System.out.print("各グループを、その中心で正規化しますか？(y/n) >");
    s = stdIn.nextLine();

    System.out.print("最小の組み合わせを求めますか？(y/n) >");
    m = stdIn.nextLine();

    res = dm.hang(l1, l2, n, w, s, m);


    for (int i = 0; i < n; i++)
      System.out.println(dm.gWord(l2[res[i]]));
  }
  private static void hypehglt() {
    int i1, i2, n;
    int[] res;
    int[] l1;
    int[] l2;
    String w, s, m;

    System.out.println("調べたい単語を２つ入力してください。");
    System.out.print("単語１　：");
    i1 = dm.exw(stdIn.nextLine());
    System.out.print("単語２　：");
    i2 = dm.exw(stdIn.nextLine());
    System.out.print("それぞれの上位語の近傍何個を調べますか？");
    n = Integer.parseInt(stdIn.nextLine());
    l1 = Calc.noneN(dm.gHpei(i1), -1);
    l2 = Calc.noneN(dm.gHpei(i2), -1);

    if (l1 == null || l2 == null) {
      System.out.println("コスト行列を作成できません。");
      return ;
    }
    if (n > l1.length || n > l2.length || n < 0) {
      System.out.println("nのサイズが適切ではありません。");
      System.out.println("i1 : " + l1.length + "\ti2 : " + l2.length);
      return ;
    }

    l1 = dm.gNWiL(i1, n, l1);
    l2 = dm.gNWiL(i2, n, l2);

    System.out.print("コスト行列の作成にはどの指標を使いますか？(c/d/s) >");
    w = stdIn.nextLine();

    System.out.print("各グループを、その中心で正規化しますか？(y/n) >");
    s = stdIn.nextLine();

    System.out.print("最小の組み合わせを求めますか？(y/n) >");
    m = stdIn.nextLine();

    res = dm.hang(l1, l2, n, w, s, m);

    for (int i = 0; i < n; i++)
      System.out.println(dm.gWord(l2[res[i]]));
  }
  private static void hypohglt() {
    int i1, i2, n;
    int[] res;
    int[] l1;
    int[] l2;
    String w, s, m;

    System.out.println("調べたい単語を２つ入力してください。");
    System.out.print("単語１　：");
    i1 = dm.exw(stdIn.nextLine());
    System.out.print("単語２　：");
    i2 = dm.exw(stdIn.nextLine());
    System.out.print("それぞれの下位語の近傍何個を調べますか？");
    n = Integer.parseInt(stdIn.nextLine());
    l1 = Calc.noneN(dm.gHpoi(i1), -1);
    l2 = Calc.noneN(dm.gHpoi(i2), -1);

    if (l1 == null || l2 == null) {
      System.out.println("コスト行列を作成できません。");
      return ;
    }
    if (n > l1.length || n > l2.length || n < 0) {
      System.out.println("nのサイズが適切ではありません。");
      System.out.println("i1 : " + l1.length + "\ti2 : " + l2.length);
      return ;
    }

    l1 = dm.gNWiL(i1, n, l1);
    l2 = dm.gNWiL(i2, n, l2);

    System.out.print("コスト行列の作成にはどの指標を使いますか？(c/d/s) >");
    w = stdIn.nextLine();

    System.out.print("各グループを、その中心で正規化しますか？(y/n) >");
    s = stdIn.nextLine();

    System.out.print("最小の組み合わせを求めますか？(y/n) >");
    m = stdIn.nextLine();

    res = dm.hang(l1, l2, n, w, s, m);


    for (int i = 0; i < n; i++)
      System.out.println(dm.gWord(l2[res[i]]));
  }
  private static void mrhglt() {
    int i1, i2, n;
    int[] res;
    int[] l1;
    int[] l2;
    String w, s, m;

    System.out.println("調べたい単語を２つ入力してください。");
    System.out.print("単語１　：");
    i1 = dm.exw(stdIn.nextLine());
    System.out.print("単語２　：");
    i2 = dm.exw(stdIn.nextLine());
    System.out.print("それぞれの構成要素の近傍何個を調べますか？");
    n = Integer.parseInt(stdIn.nextLine());
    l1 = Calc.noneN(dm.gMri(i1), -1);
    l2 = Calc.noneN(dm.gMri(i2), -1);

    if (l1 == null || l2 == null) {
      System.out.println("コスト行列を作成できません。");
      return ;
    }
    if (n > l1.length || n > l2.length || n < 0) {
      System.out.println("nのサイズが適切ではありません。");
      System.out.println("i1 : " + l1.length + "\ti2 : " + l2.length);
      return ;
    }

    l1 = dm.gNWiL(i1, n, l1);
    l2 = dm.gNWiL(i2, n, l2);

    System.out.print("コスト行列の作成にはどの指標を使いますか？(c/d/s) >");
    w = stdIn.nextLine();

    System.out.print("各グループを、その中心で正規化しますか？(y/n) >");
    s = stdIn.nextLine();

    System.out.print("最小の組み合わせを求めますか？(y/n) >");
    m = stdIn.nextLine();

    res = dm.hang(l1, l2, n, w, s, m);

    for (int i = 0; i < n; i++)
      System.out.println(dm.gWord(l2[res[i]]));
  }
  private static void hlhglt() {
    int i1, i2, n;
    int[] res;
    int[] l1;
    int[] l2;
    String w, s, m;

    System.out.println("調べたい単語を２つ入力してください。");
    System.out.print("単語１　：");
    i1 = dm.exw(stdIn.nextLine());
    System.out.print("単語２　：");
    i2 = dm.exw(stdIn.nextLine());
    System.out.print("それぞれの構成要素の近傍何個を調べますか？");
    n = Integer.parseInt(stdIn.nextLine());
    l1 = Calc.noneN(dm.gHli(i1), -1);
    l2 = Calc.noneN(dm.gHli(i2), -1);

    if (l1 == null || l2 == null) {
      System.out.println("コスト行列を作成できません。");
      return ;
    }
    if (n > l1.length || n > l2.length || n < 0) {
      System.out.println("nのサイズが適切ではありません。");
      System.out.println("i1 : " + l1.length + "\ti2 : " + l2.length);
      return ;
    }

    l1 = dm.gNWiL(i1, n, l1);
    l2 = dm.gNWiL(i2, n, l2);

    System.out.print("コスト行列の作成にはどの指標を使いますか？(c/d/s) >");
    w = stdIn.nextLine();

    System.out.print("各グループを、その中心で正規化しますか？(y/n) >");
    s = stdIn.nextLine();

    System.out.print("最小の組み合わせを求めますか？(y/n) >");
    m = stdIn.nextLine();

    res = dm.hang(l1, l2, n, w, s, m);


    for (int i = 0; i < n; i++)
      System.out.println(dm.gWord(l2[res[i]]));
  }

  private static void hglprt(int[] l1, int[] l2, int[] res, String w, String s, String m) {
    if (l1 == null || l2 == null || l1.length != l2.length || res == null) {
      System.out.println("何かがおかしいです。");
      return ;
    }
    int i, n = l1.length;
    int[] rank = new int[n];
    float[] cst = new float[n];
    float[][] x = new float[n][];
    float[][] y = new float[n][];

    for (i = 0; i < n; i++)
      rank[i] = i;

    for (i = 0; i < n; i++) {
      x[i] = dm.gWV(l1[i]);
      y[i] = dm.gWV(l2[res[i]]);
    }
    if (s.equals("y")) {
      x = Calc.normaliz(Calc.centroid(x), x);
      y = Calc.normaliz(Calc.centroid(y), y);
    }
    if (w.equals("c")) {
      for (i = 0; i < n; i++)
        cst[i] = Calc.cosr(x[i], y[i]);
    } else if (w.equals("d")) {
      for (i = 0; i < n; i++)
        cst[i] = Calc.dist(x[i], y[i]);
    } else {
      for (i = 0; i < n; i++)
        cst[i] = Calc.sminp(x[i], y[i]);
    }

    rank = Calc.sort(cst, m);

    for (i = 0; i < n; i++)
      System.out.println(dm.gWord(l1[rank[i]]) + "\t" + cst[rank[i]] + "\t" + dm.gWord(l2[res[rank[i]]]));
  }
}
