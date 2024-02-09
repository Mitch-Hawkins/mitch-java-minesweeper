package items;

import grid.Cell;

public class Flag extends Cell {

  private String ascii;
  private int type;

  public Flag() {
    this.ascii = "[!]";
    this.type = 5;
  }

  public String getFlag() {
    return this.ascii;
  }

  public int getType() {
    return this.type;
  }
}
