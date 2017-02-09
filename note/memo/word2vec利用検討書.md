word2vec利用検討書
=====================================
## word2vec
Terminal: $ ./word2vec
出力結果

| オプション         | 設定内容      | 日本語       |
|:-----------:|:------------:|:---------------:|
| -train `< file`>   | Use text data from `< file`> to train the model | トレーニング用テキストファイル指定（分かち書き済みデータ） |
| -output `< file`>  | Use `< file`> to save the resulting word vectors / word clusters | 単語ベクトル・クラスターデータの書き出しファイル指定 |
| -size `< int`>     | Set size of word vectors; default is 100 | 単語ベクトルの次元指定（デフォルト:100） |
| -window `< int`>   | Set max skip length between words; default is 5 | 探索窓の広さ指定（デフォルト:5） |
| -sample `< float`> | Set threshold for occurrence of words. Those that appear with higher frequency in the training data   will be randomly down-sampled; default is 1e-3, useful range is (0, 1e-5) | 単語の出現頻度に応じたしきい値。 ランダムにダウンサンプリングする |
| -hs `< int`>       | Use Hierarchical Softmax; default is 0 (not used) | 階層型ソフトマックス法利用の有無 |
| -negative `< int`> | Number of negative examples; default is 5, common values are 3 - 10 (0 = not used) | Negativeサンプリング法 |
| -threads `< int`>  | Use `< int`> threads (default 12) | 利用するスレッド数（デフォルト:12） |
| -iter `< int`>     | Run more training iterations (default 5) | 学習の反復回数（デフォルト:5） |
| -min-count `< int`> | This will discard words that appear less than `< int`> times; default is 5 | 保持する単語の最低出現回数（デフォルト:5） |
| -alpha `< float`>  | Set the starting learning rate; default is 0.025 for skip-gram and 0.05 for CBOW | 学習開始時のα値 |
| -classes `< int`>  | Output word classes rather than word vectors; default number of classes is 0 (vectors are written) | 出力を単語のクラスにするか、ベクトルにするか（デフォルト：0 ベクトル出力） |
| -debug `< int`>    | Set the debug mode (default = 2 = more info during training) | デバッグモードにするか否か |
| -binary `< int`>   | Save the resulting vectors in binary moded; default is 0 (off) | 出力ベクトルデータをバイナリで保存するか否か（デフォルト：0(off)） |
| -save-vocab `< file`> | The vocabulary will be saved to `< file`> | 指定ファイルに語彙を保存する |
| -read-vocab `< file`> | The vocabulary will be read from `< file`>, not constructed from the training data | 語彙を、トレーニングデータに構築されていないファイルから読み込みます |
| -cbow `< int`>     | Use the continuous bag of words model; default is 1 (use 0 for skip-gram model) | 連続 bug-of-wordsモデルを利用するか否か（デフォルト：１（use）） |
