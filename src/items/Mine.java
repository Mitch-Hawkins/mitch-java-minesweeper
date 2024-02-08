package items;

import grid.Cell;

public class Mine extends Cell {

  private String ascii;

  public Mine() {
    this.ascii = "[*]";
  }

  public String getMine() {
    return this.ascii;
  }
}
