/**
  * File Name     : MainFrame.java
  * Create Date   : 2016-07-22
  * Last Updated  : 2016-07-30
  * Description   : Main frame of this apprication.

  @version 0.1
  @author T.Hirata
*/
package frynovels.view;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JButton;

import java.awt.BorderLayout;
import java.awt.Container;

import frynovels.view.panel.TestPanel;
import frynovels.util.*;
import frynovels.util.html.*;

public class MainFrame extends JFrame {
  private Container   contentPane   = null;
  private TestPanel   tp            = null;

  public MainFrame(int i){
    init(i);
  }

  /*
    * @param i シーン
  */
  private void init(int i){
    System.out.println("init");

    setTitle("FryNovels");
    setBounds(200,150,700,400);
    setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    ImageIcon icon = new ImageIcon("image/icon.png");
    setIconImage(icon.getImage());

    tp = new TestPanel();
    contentPane = getContentPane();
    contentPane.add(tp, BorderLayout.CENTER);

    setVisible(true);
  }
}
