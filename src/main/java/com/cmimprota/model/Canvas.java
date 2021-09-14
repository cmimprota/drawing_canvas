package com.cmimprota.model;

import com.cmimprota.exception.*;

public class Canvas {

    private static final char HORIZONTAL_CHAR = '-';
    private static final char VERTICAL_CHAR = '|';
    
    private final int width;
    private final int height;

    private char[][] content;

    public Canvas(int w, int h){
        if ((w < 1) || (h < 1)){
            throw new InvalidCanvasDimensionsException("Values for width and height must be greater than 0!");
        }
        this.width = w + 2;
        this.height = h + 2;
        this.content = new char[this.height][this.width];
        for(int x = 0; x < this.width; x++){
            this.content[0][x] = HORIZONTAL_CHAR;
            this.content[this.height-1][x] = HORIZONTAL_CHAR;
        }
        for(int y = 1; y < this.height-1; y++){
            this.content[y][0] = VERTICAL_CHAR;
            this.content[y][this.width-1] = VERTICAL_CHAR;
        }
    }

    public String render() {
        StringBuilder builder = new StringBuilder();
        for (int i = 0; i < this.height; i++) {
            for (int j = 0; j < this.width; j++) {
                builder.append(this.content[i][j] == '\u0000'?' ':this.content[i][j]);
            }
            builder.append("\n");
        }
        return builder.toString().trim();
    }

    private boolean coordinatesInContent(int x, int y) {
        return ((x > 0) && (x < (this.width-1)) && (y > 0) && (y < (this.height-1)));
    }

    public void createLine(int x1, int y1, int x2, int y2) {

        if (!coordinatesInContent(x1, y1)) {
            throw new IllegalCoordinatesException("Entered coordinates do not lie on canvas!");
        }

        if (!coordinatesInContent(x2, y2)) {
            throw new IllegalCoordinatesException("Entered coordinates do not lie on canvas!");
        }

        Line line = new Line(x1, y1, x2, y2);
        
        line.drawLine(this.content);
    }

    public void createRectangle(int x1, int y1, int x2, int y2){
        
        if ((!coordinatesInContent(x1, y1)) || (!coordinatesInContent(x2, y2))) {
            throw new IllegalCoordinatesException("Entered coordinates do not lie on canvas!");
        }
        
        Rectangle rectangle = new Rectangle(x1, y1, x2, y2);
        
        rectangle.drawRectangle(this.content);
    }

    public void doBucketFill(int x, int y, char c){
        if (!coordinatesInContent(x, y)) {
            throw new IllegalCoordinatesException("Bucket fill point does not lie on canvas!");
        }

        fill(x, y, c);
    }

    private void fill(int x, int y, char c){
        if (this.content[y][x] != '\u0000') {
            return;
        } else {
            this.content[y][x] = c;
        }
        if(coordinatesInContent(x+1, y)){
            fill(x+1,y, c);
        }
        if(coordinatesInContent(x-1, y)){
            fill(x-1,y, c);
        }
        if(coordinatesInContent(x, y+1)){
            fill(x,y+1, c);
        }
        if(coordinatesInContent(x, y-1)){
            fill(x,y-1, c);
        }
    }

    public int getHeight() {
        return height;
    }

    public int getWidth() {
        return width;
    }

    public char[][] getContent(){
        return content;
    }
    
}
