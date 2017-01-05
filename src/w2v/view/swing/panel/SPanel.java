/**
  * File Name     : SPanel.java
  * Create Date   : 2017-01-05
  * Last Updated  : 2017-01-05
  * Description   : Panel to become a starting point of the process .

  @version 0.1
  @author T.Hirata
*/
package w2v.view.swing.panel;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.FlowLayout;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

public class SPanel {
  private JPanel[] jp;
  private JLabel msg;
  private JLabel lb;
  private JButton btn;
  private JButton btn1;
  private JTextField jt;

  public SPanel(){
    init();
  }
  public JPanel getPanel() {
    return jp[0];
  }
  private void init() {
    jp = new JPanel[2];

    msg = new JLabel("使用するファイルを選択してください。");
    lb = new JLabel("ファイル パス");
    jt = new JTextField(20);
    btn = new JButton("決定");
    btn1 = new JButton("参照");

    btn.addActionListener(
      new ActionListener(){
        public void actionPerformed(ActionEvent event){
          System.out.println("Push! " + jt.getText());
        }
      }
    );

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
