package main;

import grid.Cell;
import grid.Grid;
import grid.Row;
import items.Flag;
import items.Mine;
import java.util.ArrayList;

public class Main {

  public static void main(String[] args) throws Exception {
    // Row row = new Row(new ArrayList<Cell>(3), 3);
    Grid grid = new Grid(5, new ArrayList<Row>(5));
    // Initialise a new grid
    System.out.println("New Grid: \n");
    System.out.println(grid.createGrid());
    System.out.println("\n");
    // Set Cell 4,2 to a bomb and update grid
    grid.getRows().get(4).getRow().get(2).setAscii(new Mine().getMine());
    System.out.println("Changed Grid: \n");
    System.out.println(grid.updateGrid());
    System.out.println("\n");
    // Set Cell 3,2 to a bomb and update grid
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
    System.out.println(grid.getAdjacentCells(3, 1));
  }
}
