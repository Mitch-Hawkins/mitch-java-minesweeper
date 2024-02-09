package items;

import grid.Cell;

public class Mine extends Cell {

  private String ascii;
  private int type;

  public Mine() {
    this.ascii = "[*]";
    this.type = 6;
  }

  public String getMine() {
    return this.ascii;
  }

  public int getType() {
    return this.type;
  }
}
