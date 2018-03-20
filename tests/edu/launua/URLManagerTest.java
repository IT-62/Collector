package edu.launua;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.Map;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class URLManagerTest {
    URLManager urlManager;
    @Before
    public void testSetUp() throws Exception {
        urlManager = new URLManager("file:////C://Users//San//IdeaProjects//Collector//index.html");
    }

    @After
    public void testTearDown() throws Exception {
    }

    @Test
    public void testGetUrl() {
    }

    @Test
    public void testSetUrl() {
    }

    @Test
    public void testGetPageContent() {
        // assertEquals("", urlManager.getPageContent());
        for (Object tag : urlManager.getPageTagsSortedSet()) {
            System.out.println((String) tag);
        }

        for (Object tag : urlManager.getPageTagsSortedByCountSet()) {
            Map.Entry<Object, Integer> entry = (Map.Entry<Object, Integer>) tag;
            System.out.println(entry.getKey() + " " + entry.getValue());
        }
    }
}