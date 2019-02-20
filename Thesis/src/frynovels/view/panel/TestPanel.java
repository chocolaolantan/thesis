/**
  * File Name     : TestPanel.java
  * Create Date   : 2016-07-30
  * Last Updated  : 2016-08-12
  * Description   : Panel to become a starting point of the process .

  @version 0.1
  @author T.Hirata
*/
package frynovels.view.panel;

import java.util.ArrayList;
import javax.swing.*;

import java.awt.BorderLayout;
import java.awt.event.MouseEvent;
import java.awt.event.MouseAdapter;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import java.awt.Dimension;

import frynovels.util.html.HTMLBundle;
import frynovels.util.html.HTMLParser;

import frynovels.util.html.HTMLBundle;
import frynovels.util.html.Tag;

public class TestPanel extends JPanel {
  private ArrayList<Tag> tlist = null;

  public TestPanel(){
    JLabel lb = new JLabel("URL :");
    JTextField jt = new JTextField(20);
    JTextArea jta = new JTextArea(20,50);
    JScrollPane jsp = new JScrollPane(jta);
    JButton btn = new JButton("Send");
    JComboBox charCombo;
    String[] charset = {"Shift_JIS", "UTF-8"};

    charCombo = new JComboBox(charset);
    charCombo.setPreferredSize(new Dimension(80, 30));

    btn.addActionListener(
        new ActionListener(){
          public void actionPerformed(ActionEvent event){
            System.out.println("Push Button!!");
            String urL = jt.getText();
            System.out.println(urL);
            tlist = HTMLBundle.parseHTML(urL, (String)charCombo.getSelectedItem());
            for(Tag value : tlist){
              if(!rubiCheck(value))
                jta.append(value.getText());
            }
          }
        }
      );

    this.add(lb);
    this.add(jt);
    this.add(btn);
    this.add(charCombo);
    this.add(jsp, BorderLayout.CENTER);
  }

  private boolean rubiCheck(Tag tmp){
    if(tmp.getName().startsWith("rp") || tmp.getName().startsWith("rt") )
      return true;
    return false;
  }
}
