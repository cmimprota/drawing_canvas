package com.cmimprota.app;

import com.cmimprota.exception.IncorrectParametersException;
import com.cmimprota.model.Canvas;

import java.util.Scanner;

public class App 
{
    private static Canvas canvas;
    private static Scanner scanner;

    public App(){}

    public static void main( String[] args )
    {
        scanner = new Scanner(System.in);

        System.out.print("Enter command: ");

        while (true) {
            read_input(scanner.nextLine());
            System.out.print("Enter command: ");
        }
    }
    
    private static void read_input(String input) {
        String[] input_split = input.split("\\s+"); 

        switch (input_split[0]) {
            case "Q":
                quit();
                break;
            case "C":
                createCanvas(input_split);
                break;
            case "L":
                addLine(input_split);
                break;
            case "R":
                addRectangle(input_split);
                break;
            case "B":
                addFill(input_split);
                break;
            default:
                System.out.println("HELP: to create a canvas enter `C w h`, with w being the width and h being the height");
                System.out.println("HELP: to create a new line enter `L x1 y1 x2 y2`");
                System.out.println("HELP: to create a new rectangle enter `R x1 y1 x2 y2`, with (x1,y1) being the upper left corner and (x2,y2) the lower right one.");
                System.out.println("HELP: to perform bucket fill enter `B x y c`, with (x,y) being the starting point and c the symbol.");
                throw new IncorrectParametersException("No action found for your command!");
        }
       
    }

    private static void addFill(String[] input) throws NumberFormatException, IncorrectParametersException{
        // Check if the number of parameters is correct
        if (input.length == 4){
            try{
                int x = Integer.parseInt(input[1]);  
                int y = Integer.parseInt(input[2]);
                char c = input[3].charAt(0); 
                try{
                    canvas.doBucketFill(x, y, c);
                    System.out.println(canvas.render());
                } catch (NullPointerException ex) {
                    System.out.println("Initialise a new canvas first!");
                }
            } catch (NumberFormatException ex) {
                System.out.println("HELP: to perform bucket fill enter `B x y c`, with (x,y) being the starting point and c the symbol.");
            }
        } else {
            System.out.println("HELP: to perform bucket fill enter `B x y c`, with (x,y) being the starting point and c the symbol.");
            throw new IncorrectParametersException("");
        }
    }

    private static void addRectangle(String[] input) throws NumberFormatException, IncorrectParametersException{
        // Check if the number of parameters is correct
        if (input.length == 5){
            try{
                int x1 = Integer.parseInt(input[1]);  
                int y1 = Integer.parseInt(input[2]);
                int x2 = Integer.parseInt(input[3]);  
                int y2 = Integer.parseInt(input[4]);
                try{
                    canvas.createRectangle(x1, y1, x2, y2);
                    System.out.println(canvas.render());
                } catch (NullPointerException ex) {
                    System.out.println("Initialise a new canvas first!");
                }
            } catch (NumberFormatException ex) {
                System.out.println("HELP: to create a new rectangle enter `R x1 y1 x2 y2`, with (x1,y1) being the upper left corner and (x2,y2) the lower right one.");
            }
        } else {
            System.out.println("HELP: to create a new rectangle enter `R x1 y1 x2 y2`, with (x1,y1) being the upper left corner and (x2,y2) the lower right one.");
            throw new IncorrectParametersException("");
        }
    }

    private static void addLine(String[] input) throws NumberFormatException, IncorrectParametersException{
        // Check if the number of parameters is correct
        if (input.length == 5){
            try{
                int x1 = Integer.parseInt(input[1]);  
                int y1 = Integer.parseInt(input[2]);
                int x2 = Integer.parseInt(input[3]);  
                int y2 = Integer.parseInt(input[4]);
                try{
                    canvas.createLine(x1, y1, x2, y2);
                    System.out.println(canvas.render());
                } catch (NullPointerException ex) {
                    System.out.println("Initialise a new canvas first!");
                }
            } catch (NumberFormatException ex) {
                System.out.println("HELP: to create a new line enter `L x1 y1 x2 y2`");
            }
        } else {
            System.out.println("HELP: to create a new line enter `L x1 y1 x2 y2`");
            throw new IncorrectParametersException("");
        }
    }

    private static void createCanvas(String[] input) throws NumberFormatException, IncorrectParametersException{
        // Check if the number of parameters is correct
        if (input.length == 3){
            try{
                int w = Integer.parseInt(input[1]);  
                int h = Integer.parseInt(input[2]);
                // limit size to a reasonable number
                if ((w <=100) && (h <=100)){
                    // Check if there is an active Canvas
                    if (canvas != null){
                        System.out.println("The old canvas is being replaced wiht the new one!");
                    }
                    canvas = new Canvas(w, h);
                    System.out.println(canvas.render());
                } else {
                    System.out.println("HELP: Let w and h being of reasonable size!");
                }
            } catch (NumberFormatException ex) {
                System.out.println("HELP: to create a canvas enter `C w h`, with w being the width and h being the height");
            
            }
        } else {
            System.out.println("HELP: to create a canvas enter `C w h`, with w being the width and h being the height");
            throw new IncorrectParametersException("");
        }
    }
    
    private static void quit() {
        scanner.close();
        System.out.println("Exiting the drawing canvas...");
        System.exit(0);
    }
}
