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
  protected static String[] getWnHypernyms(String word, String p) {
    Set<String> st;
    String[] res;
    POS pos = null;
    if (p.equals("形容詞") || p.equals("a")) pos =POS.a;
    else if (p.equals("名詞") || p.equals("n")) pos =POS.n;
    else if (p.equals("副詞") || p.equals("r")) pos =POS.r;
    else if (p.equals("動詞") || p.equals("v")) pos =POS.v;
    else return null;
    st = JAWJAW.findHypernyms(word, pos);
    res = new String[st.size()];
    st.toArray(res);
    return res;
  }
  protected static String[] getWnHyponyms(String word, String p) {
    Set<String> st;
    String[] res;
    POS pos = null;
    if (p.equals("形容詞") || p.equals("a")) pos =POS.a;
    else if (p.equals("名詞") || p.equals("n")) pos =POS.n;
    else if (p.equals("副詞") || p.equals("r")) pos =POS.r;
    else if (p.equals("動詞") || p.equals("v")) pos =POS.v;
    else return null;
    st = JAWJAW.findHyponyms(word, pos);
    res = new String[st.size()];
    st.toArray(res);
    return res;
  }
  protected static String[] getWnHolonyms(String word, String p) {
    Set<String> st;
    String[] res;
    POS pos = null;
    if (p.equals("形容詞") || p.equals("a")) pos =POS.a;
    else if (p.equals("名詞") || p.equals("n")) pos =POS.n;
    else if (p.equals("副詞") || p.equals("r")) pos =POS.r;
    else if (p.equals("動詞") || p.equals("v")) pos =POS.v;
    else return null;
    st = JAWJAW.findHolonyms(word, pos);
    res = new String[st.size()];
    st.toArray(res);
    return res;
  }
  protected static String[] getWnTranslations(String word, String p) {
    Set<String> st;
    String[] res;
    POS pos = null;
    if (p.equals("形容詞") || p.equals("a")) pos =POS.a;
    else if (p.equals("名詞") || p.equals("n")) pos =POS.n;
    else if (p.equals("副詞") || p.equals("r")) pos =POS.r;
    else if (p.equals("動詞") || p.equals("v")) pos =POS.v;
    else return null;
    st = JAWJAW.findTranslations(word, pos);
    res = new String[st.size()];
    st.toArray(res);
    return res;
  }
  protected static String[] getWnSeeAlso(String word, String p) {
    Set<String> st;
    String[] res;
    POS pos = null;
    if (p.equals("形容詞") || p.equals("a")) pos =POS.a;
    else if (p.equals("名詞") || p.equals("n")) pos =POS.n;
    else if (p.equals("副詞") || p.equals("r")) pos =POS.r;
    else if (p.equals("動詞") || p.equals("v")) pos =POS.v;
    else return null;
    st = JAWJAW.findSeeAlso(word, pos);
    res = new String[st.size()];
    st.toArray(res);
    return res;
  }
  protected static String[] getWnMeronyms(String word, String p) {
    Set<String> st;
    String[] res;
    POS pos = null;
    if (p.equals("形容詞") || p.equals("a")) pos =POS.a;
    else if (p.equals("名詞") || p.equals("n")) pos =POS.n;
    else if (p.equals("副詞") || p.equals("r")) pos =POS.r;
    else if (p.equals("動詞") || p.equals("v")) pos =POS.v;
    else return null;
    st = JAWJAW.findMeronyms(word, pos);
    res = new String[st.size()];
    st.toArray(res);
    return res;
  }
  protected static String[] getWnInDomains(String word, String p) {
    Set<String> st;
    String[] res;
    POS pos = null;
    if (p.equals("形容詞") || p.equals("a")) pos =POS.a;
    else if (p.equals("名詞") || p.equals("n")) pos =POS.n;
    else if (p.equals("副詞") || p.equals("r")) pos =POS.r;
    else if (p.equals("動詞") || p.equals("v")) pos =POS.v;
    else return null;
    st = JAWJAW.findInDomains(word, pos);
    res = new String[st.size()];
    st.toArray(res);
    return res;
  }
  protected static String[] getWnDomains(String word, String p) {
    Set<String> st;
    String[] res;
    POS pos = null;
    if (p.equals("形容詞") || p.equals("a")) pos =POS.a;
    else if (p.equals("名詞") || p.equals("n")) pos =POS.n;
    else if (p.equals("副詞") || p.equals("r")) pos =POS.r;
    else if (p.equals("動詞") || p.equals("v")) pos =POS.v;
    else return null;
    st = JAWJAW.findDomains(word, pos);
    res = new String[st.size()];
    st.toArray(res);
    return res;
  }
  protected static String[] getWnDefinitions(String word, String p) {
    Set<String> st;
    String[] res;
    POS pos = null;
    if (p.equals("形容詞") || p.equals("a")) pos =POS.a;
    else if (p.equals("名詞") || p.equals("n")) pos =POS.n;
    else if (p.equals("副詞") || p.equals("r")) pos =POS.r;
    else if (p.equals("動詞") || p.equals("v")) pos =POS.v;
    else return null;
    st = JAWJAW.findDefinitions(word, pos);
    res = new String[st.size()];
    st.toArray(res);
    return res;
  }
  protected static String[] getWnCauses(String word, String p) {
    Set<String> st;
    String[] res;
    POS pos = null;
    if (p.equals("形容詞") || p.equals("a")) pos =POS.a;
    else if (p.equals("名詞") || p.equals("n")) pos =POS.n;
    else if (p.equals("副詞") || p.equals("r")) pos =POS.r;
    else if (p.equals("動詞") || p.equals("v")) pos =POS.v;
    else return null;
    st = JAWJAW.findCauses(word, pos);
    res = new String[st.size()];
    st.toArray(res);
    return res;
  }
  protected static String[] getWnAttributes(String word, String p) {
    Set<String> st;
    String[] res;
    POS pos = null;
    if (p.equals("形容詞") || p.equals("a")) pos =POS.a;
    else if (p.equals("名詞") || p.equals("n")) pos =POS.n;
    else if (p.equals("副詞") || p.equals("r")) pos =POS.r;
    else if (p.equals("動詞") || p.equals("v")) pos =POS.v;
    else return null;
    st = JAWJAW.findAttributes(word, pos);
    res = new String[st.size()];
    st.toArray(res);
    return res;
  }

  protected static int getWnPathLength(String word, String p) {
    Set<String> st;
    String[] res;
    POS pos = null;
    if (p.equals("形容詞") || p.equals("a")) pos =POS.a;
    else if (p.equals("名詞") || p.equals("n")) pos =POS.n;
    else if (p.equals("副詞") || p.equals("r")) pos =POS.r;
    else if (p.equals("動詞") || p.equals("v")) pos =POS.v;
    else return -1;
    st = JAWJAW.findAttributes(word, pos);
    res = new String[st.size()];
    st.toArray(res);
    return res.length;
  }
}
