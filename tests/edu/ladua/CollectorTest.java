package edu.ladua;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class CollectorTest {
    Collector collector;
    @Before
    public void setUp() throws Exception {
        collector = new Collector("src/res/ladua/input.txt",
                "src/res/ladua/output.txt");

    }

    @After
    public void tearDown() throws Exception {
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
        for (String string : collector.readFileNew()) {
            System.out.println(string);
        }
    }

    @Test
    public void testGetSortedList(){
        for (String string : collector.getSortedList(collector.readFileOld())) {
            System.out.println(string);
        }
    }
}