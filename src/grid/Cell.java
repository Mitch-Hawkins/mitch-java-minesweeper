package grid;

public class Cell {

  private String ascii;
  private int type;
  private boolean isRevealed;

  public Cell() {
    this.ascii = "[ ]";
    this.isRevealed = false;
  }

  public Cell(int type) {
    this.ascii = "[ ]";
    this.type = type;
    this.isRevealed = false;
  }

  public Cell(String ascii, int type) {
    this.ascii = ascii; //ascii contents of the cell
    this.type = type;
    this.isRevealed = false;
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

  public boolean getIsRevealed() {
    return this.isRevealed;
  }

  public void setIsRevealed(boolean isRevealed) {
    this.isRevealed = isRevealed;
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
        return "[5]";
      case 6:
        return "[6]";
      case 7:
        return "[7]";
      case 8:
        return "[8]";
      case 9:
        return "[!]";
      case 10:
        return "[*]";
      default:
        return "[ ]";
    }
  }
}
