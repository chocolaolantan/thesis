package w2v.model;

import java.util.StringTokenizer;
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
    Tagger tagger = new Tagger();
    tagger.parse(str);
    Node node = tagger.parseToNode(str);
    node = node.getNext();
    StringTokenizer st = new StringTokenizer(node.getFeature(), ",");
    String[] res = new String[st.countTokens()];
    try {
      int i = 0;
      while(st.hasMoreTokens()) {
        res[i] = st.nextToken();
        i++;
      }
    } catch (Exception e) {
      e.printStackTrace();
      return null;
    }
    return res;
  }
  protected String getPspeech(String str) {
    Tagger tagger = new Tagger();
    tagger.parse(str);
    Node node = tagger.parseToNode(str);
    node = node.getNext();
    StringTokenizer st = new StringTokenizer(node.getFeature(), ",");
    try {
      return st.nextToken();
    } catch (Exception e) {
      e.printStackTrace();
      return "";
    }
  }
  protected String getBform(String str) {
    Tagger tagger = new Tagger();
    tagger.parse(str);
    Node node = tagger.parseToNode(str);
    node = node.getNext();
    StringTokenizer st = new StringTokenizer(node.getFeature(), ",");
    try {
      for(int i = 0; i < 6; i++)
        st.nextToken();
      return st.nextToken();
    } catch (Exception e) {
      e.printStackTrace();
      return "";
    }
  }
  protected String getPhonetic(String str) {
    Tagger tagger = new Tagger();
    tagger.parse(str);
    Node node = tagger.parseToNode(str);
    node = node.getNext();
    StringTokenizer st = new StringTokenizer(node.getFeature(), ",");
    try {
      for(int i = 0; i < 8; i++)
        st.nextToken();
      return st.nextToken();
    } catch (Exception e) {
      e.printStackTrace();
      return "";
    }
  }
}
