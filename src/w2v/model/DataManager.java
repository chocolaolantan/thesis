package w2v.model;

import java.io.File;
import java.util.Set;
import java.util.StringTokenizer;

import org.chasen.mecab.MeCab;
import org.chasen.mecab.Tagger;
import org.chasen.mecab.Model;
import org.chasen.mecab.Lattice;
import org.chasen.mecab.Node;

import edu.cmu.lti.jawjaw.pobj.POS;
import edu.cmu.lti.jawjaw.JAWJAW;

import w2v.model.W2vModel;

public class DataManager {

  private W2vModel w2vm;
  private File f_ant;
  private boolean sF = false;

  public DataManager(String w2vm_path) throws Exception{
    this.w2vm = new W2vModel(new File(w2vm_path));
    try {
       System.loadLibrary("MeCab");
    } catch (UnsatisfiedLinkError e) {
       System.err.println("Cannot load the example native code.\nMake sure your LD_LIBRARY_PATH contains \'.\'\n" + e);
       System.exit(1);
    }
  }

  public void getWMC(String str) {
     System.out.println(MeCab.VERSION);
     Tagger tagger = new Tagger();
     tagger.parse(str);
     Node node = tagger.parseToNode(str);
     for (;node != null; node = node.getNext()) {
       StringTokenizer st = new StringTokenizer(node.getFeature(), ",");
       try {
         System.out.println(node.getSurface() + "\t" + st.nextToken());
       } catch (Exception e) {
         e.printStackTrace();
       }
     }
  }

  public static Set<String> getWnSynonyms(String word, String p) {
    POS pos = null;
    if (p.equals("形容詞") || p.equals("a")) pos =POS.a;
    else if (p.equals("名詞") || p.equals("n")) pos =POS.n;
    else if (p.equals("副詞") || p.equals("r")) pos =POS.r;
    else if (p.equals("動詞") || p.equals("v")) pos =POS.v;
    else return null;
    return JAWJAW.findSynonyms(word, pos);
  }
  public static Set<String> getWnAntonyms(String word, String p) {
    POS pos = null;
    if (p.equals("形容詞")) pos =POS.a;
    else if (p.equals("名詞")) pos =POS.n;
    else if (p.equals("副詞")) pos =POS.r;
    else if (p.equals("動詞")) pos =POS.v;
    else return null;
    return JAWJAW.findAntonyms(word, pos);
  }

  public void setFilePath(String fa_path) {
    try {
      f_ant = new File(fa_path);
      sF = true;
    } catch (Exception e) {
      e.printStackTrace();
      sF = false;
    }
  }
  public void createAslist() {
    if (!sF) {
      System.out.printf("Error.:ファイルパスが設定されていません。\n");
      return;
    }
    try {
      new ASthread(w2vm, f_ant).start();
    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}
