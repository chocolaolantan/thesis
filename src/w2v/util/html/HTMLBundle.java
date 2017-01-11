/**
  * File Name     : HTMLBundle.java
  * Create Date   : 2016-07-28
  * Last Updated  : 2016-08-04
  * Description   : Class for the download of the HTML file.

  @version 0.1
  @author T.Hirata
*/
package w2v.util.html;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;

public class HTMLBundle {

  /**
    * @param argv the command line arguments
    * @throws java.lang.Exception
  */
  public static void main(String[] argv) throws Exception {
    String file = HTMLBundle.getFile(argv[0], argv[1]);
    System.out.println(file);
  }

  /**
    * @param page_url URL of the page you want to get.
    * @param charset  You specify the character code of the page you want to get .
    * @throws java.lang.Exception
  */
  public static String getFile(String page_url, String charset) throws Exception {
    URL url = new URL(page_url);
    URLConnection conn = url.openConnection();

    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream(), charset ));
    StringBuffer response = new StringBuffer();
    String line;

    System.out.println("HTMLBundle.getFile() Start! " + page_url);
    while ((line= in.readLine()) != null)
      response.append(line+"\n");
    in.close();

    if(response == null) return null;

    return response.toString();
  }

  public static ArrayList<Tag> parseHTML(String url, String charset) {
    ArrayList<Tag> tlst = null;
    try {
      tlst = HTMLParser.parse(HTMLBundle.getFile(url, charset));
    } catch (Exception e){
      e.printStackTrace();
      return null;
    }
    return tlst;
  }

  public static ArrayList<String> linkList(String target, ArrayList<Tag> tlist){
    return null;
  }
}
