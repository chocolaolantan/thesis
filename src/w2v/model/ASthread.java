package w2v.model;

import java.io.File;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.regex.Pattern;
import java.util.regex.Matcher;
import java.net.URL;
import java.net.URLConnection;

import w2v.model.W2vModel;

public class ASthread extends Thread {
  private String base_url;
  private String reg;
  private W2vModel w2vm;
  private BufferedWriter bw;

  public ASthread(W2vModel w2vm, String base_url, File f) {
    this.w2vm = w2vm;
    this.base_url = base_url;
    if (base_url.indexOf("antonym") != -1) {
      this.reg = "<span class=\"wtghtAntnm\">([^<]+)</span>";
    } else {
      this.reg = "<a href=\"http://thesaurus.weblio.jp/content/[^\"]+\" title=\"[^\"]+の類語・同義語\" class=\"crosslink\">([^<]+)</a>";
    }
    try {
      this.bw = new BufferedWriter(new FileWriter(f));
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  public void run() {
    int i, j;
    try {
    for(i = 0; i < w2vm.getWords(); i++) {
      URL url = new URL(base_url + w2vm.getW(i));
      URLConnection ucon = url.openConnection();

      String charset = Arrays.asList(ucon.getContentType().split(";")).get(1);
      String encoding = Arrays.asList(charset.split("=")).get(1);

      BufferedReader in = new BufferedReader(new InputStreamReader(ucon.getInputStream(), encoding));
      StringBuffer response = new StringBuffer();
      StringBuffer cont = new StringBuffer();
      String iline;

      while((iline = in.readLine()) != null)
        response.append(iline + "\n");
      in.close();

      Pattern elem = Pattern.compile(reg, Pattern.CASE_INSENSITIVE);
      Matcher matc = elem.matcher(response.toString());

      j = 0;
      try {
        while(matc.find()) {
          j++;
          if (j == 1) {
            bw.write(w2vm.getW(i));
          }
          bw.write(matc.group(1));
        }
        bw.write("\n");
        sleep(500);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
    bw.close();
  } catch (Exception e) {
    e.printStackTrace();
  }
  }
}
