package grid;

public class Cell {

  private String ascii;
  private int type;

  public Cell() {
    this.ascii = "[ ]";
  }

  public Cell(int type) {
    this.ascii = "[ ]";
    this.type = type;
  }

  public Cell(String ascii, int type) {
    this.ascii = ascii; //ascii contents of the cell
    this.type = type;
  }

  public String getAscii() {
    return this.ascii;
  }

  public void setAscii(String ascii) {
    this.ascii = ascii;
  }

  public int getType() {
    return this.type;
  }

  public void setType(int type) {
    this.type = type;
  }

  public static String typeToAscii(int type) {
    switch (type) {
      case 0:
        return "[0]";
      case 1:
        return "[1]";
      case 2:
        return "[2]";
      case 3:
        return "[3]";
      case 4:
        return "[4]";
      case 5:
        return "[!]";
      case 6:
        return "[*]";
      default:
        return "[ ]";
    }
  }
}
