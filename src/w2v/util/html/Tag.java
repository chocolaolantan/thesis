/**
  * File Name     : Tag.java
  * Create Date   : 2016-07-30
  * Last Updated  : 2016-08-04
  * Description   : Class for tag of the HTML.

  @version 0.1
  @author T.Hirata
*/
package frynovels.util.html;

import frynovels.util.Node;

import java.util.Vector;

public class Tag implements Node {
  private String tagStr;
  private String tagName;
  private String tagAttribute;
  private String tagText;
  private Vector<Node> children;
  private Node    parent = null;

  public Tag(String tagStr, String tagName, String tagAttribute, String tagText) {
    this.tagStr       = tagStr;
    this.tagName      = tagName;
    this.tagAttribute = tagAttribute;
    this.tagText      = tagText;
  }

  public Tag(String tagStr, String tagName, String tagAttribute, String tagText, Node parent) {
    this.tagStr       = tagStr;
    this.tagName      = tagName;
    this.tagAttribute = tagAttribute;
    this.tagText      = tagText;
    this.parent       = parent;
  }

  public String getStr(){ return this.tagStr; }
  public String getName(){ return this.tagName; }
  public String getAttribute(){ return this.tagAttribute; }
  public String getText(){ return this.tagText; }
  public Vector<Node> getChildren(){ return this.children; }
  public void add(Node n){ children.addElement(n); }
}
