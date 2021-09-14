package com.cmimprota.model;

import com.cmimprota.exception.*;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class LineTest {

    @Test
    public void testConstructor() throws Exception {
        Line line  = new Line(1, 2, 6, 2);
        assertEquals(line.getX1(), 1);
        assertEquals(line.getY1(), 2);
        assertEquals(line.getX2(), 6);
        assertEquals(line.getY2(), 2);
    }
    
    @Test(expected = IllegalCoordinatesException.class)
    public void IllegalCoordinatesException() {
        Line line  = new Line(5, 3, 6, 2);
    }
    

    
}
