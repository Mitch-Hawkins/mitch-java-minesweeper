package grid;

public class Cell {

  private String ascii;

  public Cell() {
    this.ascii = "[ ]";
  }

  public Cell(String ascii) {
    this.ascii = ascii; //ascii contents of the cell
  }

  public String getAscii() {
    return this.ascii;
  }
}
