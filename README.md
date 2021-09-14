# Drawing program

Simple Java application of a drawing canvas.

## How to run
### java -cp target/classes com.cmimprota.app.App 

## Requirements
JavaSE-1.7, Maven, JUnit 4.

## Usage

Command 		Description
C w h           Create a new canvas of width w and height h.
L x1 y1 x2 y2   Create a new line of 'x' from (x1,y1) to (x2,y2). Only supports 
                horizontal or vertical lines.
R x1 y1 x2 y2   Create a new rectangle, with (x1,y1) being the upper left corner and (x2,y2) being the 
                lower right corner.
B x y c         Fill the entire area around (x,y) with "colour" c.
                Same as that of the "bucket fill" tool in paint programs.
Q               Quit.
