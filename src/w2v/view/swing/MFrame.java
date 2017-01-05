/**
  * File Name     : MFrame.java
  * Create Date   : 2017-01-05
  * Last Updated  : 2017-01-05
  * Description   : Main frame of this apprication.

  @version 0.1
  @author T.Hirata
*/
package w2v.view.swing;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import java.awt.Container;

import w2v.view.swing.panel.SPanel;

public class MFrame extends JFrame {
  private Container cp = null;
  private SPanel sp = null;

  public MFrame(){
    init();
  }

  private void init(){
    System.out.println("MFrame initializing ...");
    setTitle("W2vProject ~ For Swing ~");
    setBounds(200,150,700,400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    sp = new SPanel();
    cp = getContentPane();
    cp.add(sp.getPanel(), BorderLayout.CENTER);

    setVisible(true);
  }
}
