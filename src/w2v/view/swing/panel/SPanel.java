/**
  * File Name     : SPanel.java
  * Create Date   : 2017-01-05
  * Last Updated  : 2017-01-05
  * Description   : Panel to become a starting point of the process .

  @version 0.1
  @author T.Hirata
*/
package w2v.view.swing.panel;

import java.io.File;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import javax.swing.JFileChooser;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

import w2v.W2vSw;
import w2v.view.swing.MFrame;

public class SPanel {
  private JPanel[] jp;
  private JLabel msg;
  private JLabel lb;
  private JButton btn;
  private JButton btn1;
  private JTextField jt;
  private File fl;

  public SPanel(W2vSw app, MFrame fr){
    init(app, fr);
  }
  public JPanel getPanel() {
    return jp[0];
  }
  private void init(W2vSw app, MFrame fr) {
    jp = new JPanel[2];

    msg = new JLabel("使用するファイルを選択してください。");
    lb = new JLabel("ファイル パス");
    jt = new JTextField(20);
    btn = new JButton("決定");
    btn1 = new JButton("参照");

    btn1.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event){
        JFileChooser fc = new JFileChooser();
        int sled = fc.showOpenDialog(fr);
        if (sled == JFileChooser.APPROVE_OPTION){
          fl = fc.getSelectedFile();
          jt.setText(fl.getPath());
        }
      }
    });

    btn.addActionListener(new ActionListener() {
      public void actionPerformed(ActionEvent event) {
        app.getW2vModel(fl);
        fr.cPanel(app);
      }
    });

    jp[0] = new JPanel();
    jp[0].setLayout(new BorderLayout());
    jp[0].add(msg, BorderLayout.NORTH);

    jp[1] = new JPanel();
    jp[1].setLayout(new FlowLayout());
    jp[1].add(lb);
    jp[1].add(jt);
    jp[1].add(btn1);

    jp[0].add(jp[1], BorderLayout.CENTER);
    jp[0].add(btn, BorderLayout.SOUTH);
  }


}
