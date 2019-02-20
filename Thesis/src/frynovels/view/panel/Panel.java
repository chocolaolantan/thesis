/**
  * File Name     : Panel.java
  * Create Date   : 2016-07-22
  * Last Updated  : 2016-07-30
  * Description   : Start page of this apprication.
*/
package frynovels.view.panel;

import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.JButton;
import javax.swing.JTextField;

import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Panel extends JPanel {
  Panel(){
    JLabel lb = new JLabel();
    JTextField jt = new JTextField(20);
    JButton btn = new JButton("決定");

    btn.addActionListener(
        new ActionListener(){
          public void actionPerformed(ActionEvent event){
            lb.setText(jt.getText());
          }
        }
      );

    this.add(lb);
    this.add(jt);
    this.add(btn);
  }
}
