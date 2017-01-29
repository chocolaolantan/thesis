# W2vModelクラス
## フィールド
### int size
学習ベクトルの次元数

### int words
学習した形態素数

### String[ ] vocab
学習した形態素の文字列

### float[ ][ ] m
学習したベクトル。  
words × size

### File f
word2vecの学習ファイル（テキスト形式）

## コンストラクタ
#### 引数 File f
word2vecの学習ファイル（テキスト形式）。  
ファイルを読み込み、フィールドを初期化する。

## メソッド
### int getWords()
return words
### int getSize()
return size
### int exist(String tmp)
tmpがvocab内に存在していれば、そのindexを返す。  
存在しなければ-1を返す。
### String getWord(int i)
vocab[i] の単語を返す。  
存在しなければ空列を返す。
### float[ ] getWordVector(int n)
vocab[n] に対応するベクトルを返す。  
存在しなければnullを返す。
### float[ ][ ] getVectors(int[ ] list)
listに格納されているindexの形態素に対応するベクトルを返す。
### float[ ][ ] getAllVector()
return m
### int[ ] getRandomWords(int n, int[ ] list)
listに格納されているindexのうち、n個をランダムに抽出する。  
listがnullの時、wordsまでの非負整数をランダムに出力する。
### int[ ] getNearWords(int idx, int n, int[ ] excl)
exclに格納されているindexを**除外**してvocab[idx]の近傍形態素のindexをn個出力する。  
exclがnullの時、除外対象なしとして処理する。
### int[ ] getVectorNearWords(float[ ] grv, int n, int[ ] excl)
exclに格納されているindexを**除外**してgrvの近傍形態素のindexをn個出力する。  
exclがnullの時、除外対象なしとして処理する。
### int[ ] getNearWordsInList(int idx, int n, int[ ] list)
listに格納されているindexの中から、vocab[idx]の近傍形態素n個を出力する。
### int[ ] getVectorNearWordsInList(float[ ] grv, int n, int[ ] list)
listに格納されているindexの中から、grvの近傍形態素n個を出力する。
