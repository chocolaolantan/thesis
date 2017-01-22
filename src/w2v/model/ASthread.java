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
  private String base_url = "http://thesaurus.weblio.jp/antonym/content/";
  private String reg = "<span class=wtghtAntnm>([^<]+)</span>";
  private File f;

  private W2vModel w2vm;

  private Matcher mat;
  private Pattern pat;

  public ASthread(W2vModel w2vm, File f) {
    this.w2vm = w2vm;
    this.f = f;
    try {
      pat = Pattern.compile(reg);
    } catch (Exception e) {
      e.printStackTrace();
    }
  }


  public void run() {
    BufferedWriter bw = null;
    BufferedReader br = null;
    // Modelファイルに登録されている単語からリスト作成
    for(int i = 0; i < w2vm.getWords(); i++) {
      try {
        // アクセスするURLの確認
        System.out.println(base_url + w2vm.getWord(i));
        URL url = new URL(base_url + w2vm.getWord(i));
        URLConnection uc = url.openConnection();

        String cs = Arrays.asList(uc.getContentType().split(";")).get(1);
        String enc = Arrays.asList(cs.split("=")).get(1);

        br = new BufferedReader(new InputStreamReader(uc.getInputStream(), enc));
      } catch (Exception e) {
        e.printStackTrace();
      }

      String iline;
      StringBuffer res = new StringBuffer();

      try {
        while((iline = br.readLine()) != null) {
          res.append(iline+"\n");
        }
      } catch (Exception e) {
        e.printStackTrace();
      }

      try {
        mat = pat.matcher(res.toString());
        if (mat.find()) {
          bw = new BufferedWriter(new FileWriter(f, true));
          String tmp = mat.group(1);
          bw.write(w2vm.getWord(i) + " " + tmp);
          System.out.println(w2vm.getWord(i) + " " + tmp);
          while(mat.find()) {
            tmp = mat.group(1);
            bw.write(" " + tmp);
            System.out.print(" " + tmp);
          }
          bw.write("\n");
          System.out.println();
          bw.close();
        }
        br.close();
        sleep(15000);
      } catch (Exception e) {
        e.printStackTrace();
      }
    }
  }
}
