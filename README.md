# Minesweeper Java Recreation

This is a recreation of a simplified version of the game Minesweeper to be played in the java console. Written in Java 21 and is platform agnostic. Contains most features from the original application including cascading tiles, flags and customizable grid sizes.

## Demo & Snippets

- A sample 9x9 beginner grid on the Main screen.
  ![alt text](<screenshots/Screenshot 2024-03-12 at 11.52.06 am.png>)

- Midway through a 9x9 game.
  ![alt text](<screenshots/Screenshot 2024-03-12 at 11.52.48 am.png>)

- Game Over screen after revealing a mine cell and losing the game
  ![alt text](<screenshots/Screenshot 2024-03-12 at 11.53.05 am.png>)

- A much harder, larger level. The maximum grid size of the project, 30x16
  ![alt text](<screenshots/Screenshot 2024-03-12 at 11.53.21 am.png>)

- Game Over on the expert level.
  ![alt text](<screenshots/Screenshot 2024-03-12 at 11.53.39 am.png>)

---

## Requirements / Purpose

- Recreate a simplified version of the game Minesweeper to be played in the java console
  The game should be able to randomly generate 10 mines in a 10x10 grid
  The user will be able to enter a command that represents a coordinate to check a location for a mine
  The application will display a number from 0-8 depending on how many mines surround that location
  If the user selects a mine, the game will respond "boom!" and the game will be lost
  If every non-mine square has been revealed, the game is won
  Render the grid to the console after every user command
- The project is 100% written in Java (I used Java 21) this was to encourage learning and implementing Object-Oriented Programming and experiencing how an OOP language is efficient and powerful

---

## Build Steps

- The included 'mitch-java-minesweeper.jar' is a compiled project file. You can run this on any operating system with Java using the following terminal command

```
java --enable-preview -jar ./mitch-java-minesweeper.jar
```

---

## Design Goals / Approach

- Different sizes of Minesweeper grids, a prompt appears at the start of the game asking the user to implement their desired grid size (No greater than the expert level minesweeper grids, at 30x16)
- Use of different colours in the java console. Achieved using an individual ANSI code for each revealed cell. Colours may appear slightly different depending on the console/terminal used, but have been tested to be distinguishable enough from each other to create a pleasant user experience. Also enables user accessibility and provides better user feedback.
- ASCII art screens. Used for Title Screen, Winner Screen and Game Over Screen. Similar to the colours, this was an extra stretch goal implemented to ensure a pleasant user experience, and to increase the professional feel of the project.

---

## Features

- A fully functioning recreation of the original Minesweeper game
- Re-sizable grids no greater than expert level Minesweeper (30x16)
- Error handling for any out of bounds inputs by the user.
- Terminal cleanliness, clears the terminal on each user input.
- Random grid generation at the start of each new game, with custom amount of mines.
- Adaptive and Dynamic game grid. The grid is re printed on the same lines after every command, providing the illusion that the same printed lines are constantly updating upon each input.
- Colours and Title screens for a neat professional polish.

---

## Known issues

- The use of an ANSI code to clear the terminal can cause some strange visual glitches in the terminal on occasion when the users scrolls up through the terminal.
- There are not enough standard ANSI colour codes for all the cell numbers from 0-8. Therefore, the blank cell and the number 8 cell share the same colour. However, number 8 cells are rarely generated, so this colour clash will rarely be experienced by the user.
- Colour palette is the same for both Light mode and Dark mode terminal backgrounds. The colours look much more appealing in Dark mode terminals, so the Light mode colour palettes will need to be re arranged to provide a better visual experience.

---

## Future Goals

- A refactor of the terminal clear screen function, as the current usage of the ANSI code provides some occasional bugs and visual glitches. This can be prevented with a simple re-factor.
- Incorporating a GUI framework to display the game outside of the terminal console, and further polish the presentation of the project.

---

## Struggles

- The functionality of the cascading number 0 cells proved a difficult concept to implement. This was overcome by function recursion inside the revealCell function. Function recursion can be a tricky process to implement due to the volatile nature of potentially creating infinite loops and crashing the program.

---
