package items;

import grid.Cell;

public class Flag extends Cell {

  private String ascii;

  public Flag() {
    this.ascii = "[!]";
  }

  public String getFlag() {
    return this.ascii;
  }
}
