/**
  * File Name     : Node.java
  * Create Date   : 2016-08-04
  * Last Updated  : 2016-08-04
  * Description   : InterFace of Node.

  @version 0.1
  @author T.Hirata
*/
package w2v.util;

import java.util.Vector;

public interface Node{
  public Vector<Node> getChildren();
  public void add(Node n);
}
