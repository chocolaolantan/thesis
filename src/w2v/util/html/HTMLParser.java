/**
  * File Name     : HTMLParser.java
  * Create Date   : 2016-07-28
  * Last Updated  : 2016-07-30
  * Description   : Class to parse the HTML.

  @version 0.1
  @author T.Hirata
*/
package w2v.util.html;

import java.util.ArrayList;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class HTMLParser {
  // HTMLタグの正規表現
  private static Pattern tagPattern = Pattern.compile("(<([^ >]+)([^>]*)>)([^<]*)");
  private Matcher matcher;
  private Tag tag;

  /**
    * @param src HTMLソース
    */
  public HTMLParser(String src) {
    matcher = tagPattern.matcher(src);
  }

  /**
    * 次のHTMLタグがあるかどうかを検査します
    * @return 存在する場合 true
    */
  private boolean hasNext(){
    boolean found = matcher.find();
    if(found) {
      tag = new Tag(matcher.group(1),matcher.group(2),matcher.group(3),matcher.group(4));
    }
    return found;
  }

  /**
    * 次のHTMLタグを返します
    * @return タグを表すオブジェクト
    */
  private Tag next() {
    return tag;
  }

  protected static ArrayList<Tag> parse(String html){
    HTMLParser parser = new HTMLParser(html);
    ArrayList<Tag> tagList = new ArrayList<Tag>();

    while(parser.hasNext()){
      Tag tag = parser.next();
      tagList.add(tag);
    }
    if(tagList.size()<0) tagList = null;

    return tagList;
  }
}
