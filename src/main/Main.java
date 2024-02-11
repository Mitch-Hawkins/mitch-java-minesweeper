package main;

// import grid.Cell;
import grid.Grid;
import grid.Row;
import items.Flag;
import items.Mine;
import java.util.ArrayList;
import java.util.Arrays;

public class Main {

  public static void main(String[] args) throws Exception {
    // Row row = new Row(new ArrayList<Cell>(3), 3);
    Grid grid = new Grid(5, new ArrayList<Row>(5));
    // Initialise a new grid
    System.out.println("New Grid: \n");
    System.out.println(grid.createGrid());
    System.out.println("\n");
    // Pick three random cells to be type of mine
    grid.plantMines(3);
    // Set the numbers of the rest of the cells
    grid.determineTypes();
    // Check the type of cell to see if it's a bomb or not
    if (grid.getRows().get(4).getRow().get(2).getType() == 6) {
      System.out.println("Watch out! There's a mine there!");
    }
    //test the getAdjacentCellsByType
    System.out.println(grid.getAdjacentCellsType(3, 1));
    //Reveal an individual cell
    System.out.println(grid.revealCell(3, 1));
  }

  //=======================================================================
  //=======================================================================
  //=======================================================================

  public static void oldStartup(Grid grid) {
    // Set Cell 4,2 to a mine and update grid
    grid.getRows().get(4).getRow().get(2).setAscii(new Mine().getMine());
    System.out.println("Changed Grid: \n");
    System.out.println(grid.updateGrid());
    System.out.println("\n");
    // Set Cell 3,2 to a mine and update grid
    grid.getRows().get(3).getRow().get(2).setAscii(new Mine().getMine());
    System.out.println("Changed Grid: \n");
    System.out.println(grid.updateGrid());
    System.out.println("\n");
    //Set Cell 3,0 to a flag and update grid
    grid.getRows().get(3).getRow().get(0).setAscii(new Flag().getFlag());
    System.out.println("Changed Grid: \n");
    System.out.println(grid.updateGrid());
    System.out.println("\n");
    //Check the contents of Cell 3,1 and the Horizontally Adjacent Cells (3,0 and 3,2)
    System.out.println(grid.getAdjacentCellsAscii(3, 1));
    //Get a random coordinate
    int[] randomCoordinate = Grid.generateRandomCoordinate(5, 3);
    System.out.println(Arrays.toString(randomCoordinate));
    //Find cell at that random coordinate and make it's ascii a mine
    grid
      .getRows()
      .get(randomCoordinate[0])
      .getRow()
      .get(randomCoordinate[1])
      .setAscii(new Mine().getMine());
    System.out.println("Changed Grid: \n");
    System.out.println(grid.updateGrid());
    System.out.println("\n");
  }
}
