# プログラム 利用方法

  1. WordNet利用のためのライブラリJAWJAW.jarを導入する。
  2. MeCab利用のためのライブラリMeCab.jarを導入する。
  3. コンパイルする。（jarファイルのパスは環境によって異なる。）  
  $ javac -encoding utf-8 -d ../bin/ -cp ./:/lib/java/jawjaw-1.0.2.jar:/lib/java/MeCab.jar w2v/W2vCmd.java
  4. 実行する。（jarファイルのパスは環境によって異なる。）   
  $ java -cp ./:/lib/java/jawjaw-1.0.2.jar:/lib/java/MeCab.jar w2v/W2vCmd
  5. word2vecの学習済みファイルパスを入力する。  
  この時、入力するファイルはテキストデータ形式であること。
  6. helpを打ち込むと、コマンド一覧が表示される。
  7. コマンド入力後、指示の通りに単語などを入力する。

## 動かないときは
  - $ export LD_LIBRARY_PATH=/usr/local/lib/mecab/dic:/usr/local/lib:$LD_LIBRARY_PATH  
  で、MeCab.jarが参照する辞書のパスを設定する。（CentOS・Ubuntu）

## パッケージ・クラス説明
  - w2v.W2vCmd：メインクラス。
  - w2v.W2vFX/W2vSw：GUIアプリ作成を試みた残骸。
    - w2v.view：上記クラス用ファイル。残骸なので無視推奨。
  - w2v.calc.Calc：ハンガリアン法、その他、ベクトル・行列演算メソッドの集合クラス。
  - w2v.model.Kmeans：k-means実装クラス。
  - w2v.model.MCModel：MeCab利用のためのクラス(コンパイル・実行にMeCab.jarが必要)。
  - w2v.model.W2vModel：word2vecの学習データ利用のためのクラス。
  - w2v.model.WNModel：WordNetデータ利用のためのクラス(コンパイル・実行にJAWJAW.jarが必要)。
  - w2v.model.DataManager：w2v.model内クラスを統括的に扱うために用意。カプセル化には失敗。
  - w2v.util：青空文庫のデータ習得を考えていた時に作成したが、使わなかったので、無視して構わない。

※word2vecの学習ファイルは別途用意。バイナリデータは使えないので注意。
