# Rust入門
- Date:2017-02-16
## 環境構築
[**rustup**](https://rustup.rs/)参照。\\
以下コマンドを端末やTerminalなどで実行する。\\

curl https://sh.rustup.rs -sSf | sh

インストール後のメッセージに従ってパスを追加する。\\
$ source $HOME/.cargo/env\\
UNIX系OSなら、~/.bashrcの末尾に上記コマンドを追加すると、今後、自動でパスが追加される。

## Cargo Hello world
$ cargo new --bin <Project Name>\\
上記コマンドでプロジェクトディレクトリ作成。

プロジェクトディレクトリに入り、\\
$ cargo build\\
で実行プログラムのコンパイル。

$ ./target/debug/\\
$ ./target/debug/<Project Name>

詳細については[プログラミング言語プRust](https://rust-lang-ja.github.io/the-rust-programming-language-ja/1.6/book/)
