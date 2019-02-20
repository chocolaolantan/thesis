/**
  * File Name     : FryNovels.java
  * Create Date   : 2016-07-21
  * Last Updated  : 2016-07-28
  * Description   : For graduation thesis , an application that manages the data of Aozora Bunko . This class is the Main-Class of this Project. Also, this class manages MainFrame and Properties.


  @version 0.1
  @author T.Hirata
*/
package frynovels;

import frynovels.view.MainFrame;
import frynovels.util.Log;
import frynovels.util.Properties;

public class FryNovels {
  private static MainFrame mf;

  public static void main(String args[]){
    System.out.println("Start FryNovels.");
    init();
  }

  // Initialization
  private static int init(){
    System.out.println("Start initialize.");

    mf = new MainFrame(0);

    return 0;
  }
}
