cd ../src
javac -encoding utf-8 -d ../bin/ -cp ./:/lib/java/jawjaw-1.0.2.jar:/lib/java/MeCab.jar w2v/model/DataManager.java
cd ../bin
java -cp ./:/lib/java/jawjaw-1.0.2.jar:/lib/java/MeCab.jar w2v/W2vCmd
