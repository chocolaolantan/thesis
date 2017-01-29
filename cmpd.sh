export LD_LIBRARY_PATH=/usr/local/lib/mecab/dic:/usr/local/lib:$LD_LIBRARY_PATH

javac -encoding utf-8 -d ./bin/ -cp ./src:./bin:/lib/java/jawjaw-1.0.2.jar:/lib/java/MeCab.jar src/w2v/model/DataManager.java
javac -encoding utf-8 -d ./bin/ -cp ./bin src/w2v/W2vCmd.java

java -cp ./bin:/lib/java/jawjaw-1.0.2.jar:/lib/java/MeCab.jar w2v.W2vCmd
