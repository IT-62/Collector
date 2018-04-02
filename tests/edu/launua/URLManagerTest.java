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
        urlManager = null;
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
        assertEquals("   <html>\r\n" +
                "<body param = asda, par = 2>\r\n" +
                "\t<table>\r\n" +
                "\t\t<tr>\r\n" +
                "\t\t\t<td></td>\r\n" +
                "\t\t\t<td></td>\r\n" +
                "\t\t</tr>\r\n" +
                "\t\t<tr>\r\n" +
                "\t\t\t<td></td>\r\n" +
                "\t\t</tr>\r\n" +
                "\t</table>\r\n" +
                "</body>\r\n" +
                "</html>", urlManager.getPageContent());
        assertNotEquals("   <html>\n" +
                "<body param = asda, par = 2>\n" +
                "\t<table>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td></td>\n" +
                "\t\t\t<td></td>\n" +
                "\t\t</tr>\n" +
                "\t\t<tr>\n" +
                "\t\t\t<td></td>\n" +
                "\t\t</tr>\n" +
                "\t</table>\n" +
                "</body>\n" +
                "</html>", urlManager.getPageContent());
    }

    @Test
    public void testGetPageTags() {
        assertArrayEquals(new Object[]{"<html>", "<body>", "<table>", "<tr>", "<td>", "<td>", "<tr>", "<td>"}, urlManager.getPageTags());
        assertNotEquals(new Object[]{"<html>", "<body>", "<table>", "<tr>", "<td>", "<tr>", "<td>"}, urlManager.getPageTags());
    }

    @Test
    public void getPageTagsSortedSet() {
        assertArrayEquals(new Object[]{"<body>", "<html>", "<table>", "<td>", "<tr>"}, urlManager.getPageTagsSortedSet());
        assertNotEquals(new Object[]{"<html>", "<body>", "<table>", "<tr>", "<td>", "<tr>", "<td>"}, urlManager.getPageTagsSortedSet());
    }
    //"nice" code
    @Test
    public void testGetPageTagsSortedByCountSetOld() {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("<table>", 1);
        map.put("<html>", 1);
        map.put("<body>", 1);
        map.put("<tr>", 2);
        map.put("<td>", 3);
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(Comparator.comparing(Map.Entry<String, Integer>::getValue));
        assertEquals(list.toArray(), urlManager.getPageTagsSortedByCountSetOld());
        assertNotEquals(new Object[]{"<table>=1", "<html>=2", "<body>=1", "<tr>=2", "<td>=3"}, urlManager.getPageTagsSortedByCountSetOld());
    }

    @Test
    public void testGetPageTagsSortedByCountSet() {
        HashMap<String, Integer> map = new HashMap<String, Integer>();
        map.put("<table>", 1);
        map.put("<html>", 1);
        map.put("<body>", 1);
        map.put("<tr>", 2);
        map.put("<td>", 3);
        List<Map.Entry<String, Integer>> list = new ArrayList<>(map.entrySet());
        list.sort(Comparator.comparing(Map.Entry<String, Integer>::getValue));
        assertArrayEquals(list.toArray(), urlManager.getPageTagsSortedByCountSet());
        assertNotEquals(new String[]{"<table>=1", "<html>=2", "<body>=1", "<tr>=2", "<td>=3"}, urlManager.getPageTagsSortedByCountSet());
    }
}
