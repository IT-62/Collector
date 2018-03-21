package edu.ladua;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CollectorTest {
    Collector collector;
    @Before
    public void setUp() {
        collector = new Collector("src/res/ladua/input.txt",
                "src/res/ladua/output.txt");

    }

    @After
    public void tearDown() {
        collector = null;
    }

    @Test
    public void testGetPathIn() {
    }

    @Test
    public void testGetPathOut() {
    }

    @Test
    public void testReadFileOld() {
        assertArrayEquals(new String[]{"Hello","world","asd"}, collector.readFileOld().toArray());
    }

    @Test
    public void testGetSortedList() {
        assertArrayEquals(new String[]{"Hello","world","asd"}, collector.readFileNew().toArray());
    }
}