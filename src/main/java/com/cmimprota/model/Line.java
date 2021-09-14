package com.cmimprota.model;

import com.cmimprota.exception.*;

public class Line {

    private static final char LINE_CHAR = 'x';

    private final int x1;
    private final int y1;
    private final int x2;
    private final int y2;

    public Line(int x1, int y1, int x2, int y2) {
        if ((x1 < 1) || (x2 < 1) || (y1 < 1) || (y2 < 1)) {
            throw new IllegalCoordinatesException("Entered coordinates must be greater than 0!");
        }

        if ((x1 != x2) && (y1 != y2)) {
            throw new IllegalCoordinatesException("Only horizontal or vertical lines are currently supported!");
        }

        if ((x1 == x2 && y1 > y2) || (y1 == y2 && x1 > x2)) {
            this.x1 = x2;
            this.y1 = y2;
            this.x2 = x1;
            this.y2 = y1;
        } else {
            this.x1 = x1;
            this.y1 = y1;
            this.x2 = x2;
            this.y2 = y2;
        }
    }

    public void drawLine(char[][] content){
        for (int y = this.y1; y <= this.y2; y++) {
            for (int x = this.x1; x <= this.x2; x++) {
                if (content[y][x] == '\u0000'){
                    content[y][x] = LINE_CHAR;
                }
            }
        }
    }

    public int getY2() {
        return y2;
    }

    public int getX2() {
        return x2;
    }

    public int getY1() {
        return y1;
    }

    public int getX1() {
        return x1;
    }
    
}
