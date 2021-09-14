package com.cmimprota.model;

import com.cmimprota.exception.*;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class CanvasTest {

    @Test
    public void testConstructor() throws Exception {
        Canvas canvas  = new Canvas(20, 4);
        String expected_canvas = "----------------------\n" +
                                 "|                    |\n" +
                                 "|                    |\n" +
                                 "|                    |\n" +
                                 "|                    |\n" +
                                 "----------------------";
        assertEquals(canvas.render(), expected_canvas);
    }

    @Test(expected = InvalidCanvasDimensionsException.class)
    public void testInvalidCanvasDimensionsException() {
        Canvas canvas  = new Canvas(-1, 4);
    }

    @Test
    public void testDrawLine() throws Exception {
        Canvas canvas  = new Canvas(20, 4);
        canvas.createLine(1, 2, 6, 2);
        String expected_canvas = "----------------------\n" +
                                 "|                    |\n" +
                                 "|xxxxxx              |\n" +
                                 "|                    |\n" +
                                 "|                    |\n" +
                                 "----------------------";
        assertEquals(canvas.render(), expected_canvas);
    }

    @Test(expected = IllegalCoordinatesException.class)
    public void testIllegalCoordinatesExceptionInLine() {
        Canvas canvas  = new Canvas(20, 4);
        canvas.createLine(18, 2, 24, 2);
    }

    @Test(expected = IllegalCoordinatesException.class)
    public void testIllegalCoordinatesInLine() {
        Canvas canvas  = new Canvas(20, 4);
        canvas.createLine(18, 2, 24, 2);
        for(int x = 17; x < canvas.getWidth(); x++){
            assertEquals(canvas.getContent()[2][x], 'x');
        }
        
    }

    @Test
    public void testDrawLine2() throws Exception {
        Canvas canvas  = new Canvas(20, 4);
        canvas.createLine(1, 2, 6, 2);
        canvas.createLine(6, 3, 6, 4);
        String expected_canvas = "----------------------\n" +
                                 "|                    |\n" +
                                 "|xxxxxx              |\n" +
                                 "|     x              |\n" +
                                 "|     x              |\n" +
                                 "----------------------";
        assertEquals(canvas.render(), expected_canvas);
    }

    @Test
    public void testCreateRectangle() throws Exception {
        Canvas canvas  = new Canvas(20, 4);
        canvas.createLine(1, 2, 6, 2);
        canvas.createLine(6, 3, 6, 4);
        canvas.createRectangle(14, 1, 18, 3);
        String expected_canvas = "----------------------\n" +
                                 "|             xxxxx  |\n" +
                                 "|xxxxxx       x   x  |\n" +
                                 "|     x       xxxxx  |\n" +
                                 "|     x              |\n" +
                                 "----------------------";
        assertEquals(canvas.render(), expected_canvas);
    }

    @Test(expected = IllegalCoordinatesException.class)
    public void testCreateRectangleException() throws Exception {
        Canvas canvas  = new Canvas(20, 4);
        canvas.createRectangle(14, 1, 24, 3);
    }

    @Test
    public void testBucketFill() throws Exception {
        Canvas canvas  = new Canvas(20, 4);
        canvas.createLine(1, 2, 6, 2);
        canvas.createLine(6, 3, 6, 4);
        canvas.createRectangle(14, 1, 18, 3);
        canvas.doBucketFill(10, 3, 'o');
        String expected_canvas = "----------------------\n" +
                                 "|oooooooooooooxxxxxoo|\n" +
                                 "|xxxxxxooooooox   xoo|\n" +
                                 "|     xoooooooxxxxxoo|\n" +
                                 "|     xoooooooooooooo|\n" +
                                 "----------------------";
        assertEquals(canvas.render(), expected_canvas);
    }


    
}
