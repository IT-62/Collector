package edu.launua;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.net.MalformedURLException;
import java.util.Map;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class URLManagerTest {
    String url;
    URLManager urlManager;

    @Before
    public void testSetUp() throws Exception {
        url = "file:////C://Users//San//IdeaProjects//Collector//index.html";
        urlManager = new URLManager(url);
    }

    @After
    public void testTearDown() throws Exception {
    }

    @Test
    public void testGetUrl() {
        assertEquals(url, urlManager.getUrl());
    }

    @Test
    public void testSetUrl() {
        urlManager.setUrl("something wrong");
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