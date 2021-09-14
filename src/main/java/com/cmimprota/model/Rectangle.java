package com.cmimprota.model;

public class Rectangle {
    private final Line top;
    private final Line bottom;
    private final Line left;
    private final Line right;

    public Rectangle(int x1, int y1, int x2, int y2){
        this.top = new Line(x1, y1, x2, y1);
        this.bottom = new Line(x2, y1, x2, y2);
        this.left = new Line(x1, y1, x1, y2);
        this.right = new Line(x1, y2, x2, y2);
    }

    public void drawRectangle(char[][] content){
        this.top.drawLine(content);
        this.bottom.drawLine(content);
        this.left.drawLine(content);
        this.right.drawLine(content);
    }
    
}
