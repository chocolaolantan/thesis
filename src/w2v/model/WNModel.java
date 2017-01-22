package w2v.model;

import java.util.Set;

import edu.cmu.lti.jawjaw.pobj.POS;
import edu.cmu.lti.jawjaw.JAWJAW;

public class WNModel {

  protected static String[] getWnSynonyms(String word, String p) {
    Set<String> st;
    String[] res;
    POS pos = null;
    if (p.equals("形容詞") || p.equals("a")) pos =POS.a;
    else if (p.equals("名詞") || p.equals("n")) pos =POS.n;
    else if (p.equals("副詞") || p.equals("r")) pos =POS.r;
    else if (p.equals("動詞") || p.equals("v")) pos =POS.v;
    else return null;
    st = JAWJAW.findSynonyms(word, pos);
    res = new String[st.size()];
    st.toArray(res);
    return res;
  }
  protected static String[] getWnAntonyms(String word, String p) {
    Set<String> st;
    String[] res;
    POS pos = null;
    if (p.equals("形容詞") || p.equals("a")) pos =POS.a;
    else if (p.equals("名詞") || p.equals("n")) pos =POS.n;
    else if (p.equals("副詞") || p.equals("r")) pos =POS.r;
    else if (p.equals("動詞") || p.equals("v")) pos =POS.v;
    else return null;
    st = JAWJAW.findAntonyms(word, pos);
    res = new String[st.size()];
    st.toArray(res);
    return res;
  }
}
