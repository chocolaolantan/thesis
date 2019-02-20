/****************************************
  *File name    : W2vList.java
  *Date         : 2016-12-27
  *Ubdate date  : 2016-12-27
  *Discliption  :
****************************************/
package lib;

import java.io.FileInputStream;
import java.io.IOException;

public class W2vList {
  private long words;
  private long size;
  private String[] vocab;
  private float[][] M;

  private W2vList(String file_path) {
    try {
      FileInputStream fis = new FileInputStream(file_path);
    }
  }

  public W2vList getW2vList(String file_path) {
    W2vList tmp;
    tmp = new W2vList(file_path);
    return tmp;
  }
}
