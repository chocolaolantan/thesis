package w2v.model;

import java.util.LinkedList;

import org.chasen.mecab.MeCab;
import org.chasen.mecab.Tagger;
import org.chasen.mecab.Model;
import org.chasen.mecab.Lattice;
import org.chasen.mecab.Node;

public class MCModel {

  protected MCModel() {
    try {
       System.loadLibrary("MeCab");
       System.out.println(MeCab.VERSION);
    } catch (UnsatisfiedLinkError e) {
       System.err.println("Cannot load the example native code.\nMake sure your LD_LIBRARY_PATH contains \'.\'\n" + e);
       System.exit(1);
    }
  }

  protected Node getNode(String str) {
     Tagger tagger = new Tagger();
     tagger.parse(str);
     Node node = tagger.parseToNode(str);
     return node;
  }
  protected String[] getSentenceSegmentation(String str) {
    Tagger tagger = new Tagger();
    tagger.parse(str);
    Node node = tagger.parseToNode(str);
    node = node.getNext();
    LinkedList<String> tmp = new LinkedList<String>();

    for (;node != null; node = node.getNext())
      tmp.add(node.getSurface());
    return (String[])tmp.toArray(new String[0]);
  }
  protected String[] getWmcFeature(String str) {
    String[] st;
    try {
      Tagger tagger = new Tagger();
      tagger.parse(str);
      Node node = tagger.parseToNode(str);
      node = node.getNext();
      st = node.getFeature().split(",");
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
    return st;
  }
  protected String getPspeech(String str) {
    try {
      Tagger tagger = new Tagger();
      tagger.parse(str);
      Node node = tagger.parseToNode(str);
      node = node.getNext();
      String[] st = node.getFeature().split(",");
      return st[0];
    } catch (Exception e) {
      e.printStackTrace();
      return "";
    }
  }
  protected String getBform(String str) {
    try {
      Tagger tagger = new Tagger();
      tagger.parse(str);
      Node node = tagger.parseToNode(str);
      node = node.getNext();
      String[] st = node.getFeature().split(",");
      return st[6];
    } catch (Exception e) {
      e.printStackTrace();
      return "";
    }
  }
  protected String getPhonetic(String str) {
    try {
      Tagger tagger = new Tagger();
      tagger.parse(str);
      Node node = tagger.parseToNode(str);
      node = node.getNext();
      String[] st = node.getFeature().split(",");
      return st[8];
    } catch (Exception e) {
      e.printStackTrace();
      return "";
    }
  }
}
