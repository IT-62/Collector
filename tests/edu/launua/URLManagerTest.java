package edu.launua;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.*;
import java.util.regex.Pattern;

import static org.junit.Assert.*;

public class URLManagerTest {
    URLManager urlManager;
    @Before
    public void testSetUp() throws Exception {
        urlManager = new URLManager("file:///C:/Users/Air/IdeaProjects/IT-62/Collector/index.html");
       // urlManager = new URLManager("http://kinogo.cc/7232-astral-4-posledniy-klyuchs-2018.html");
    }

    @After
    public void testTearDown() throws Exception {
        urlManager = null;
    }

    @Test
    public void testGetUrl() {
        assertEquals("file:///C:/Users/Air/IdeaProjects/IT-62/Collector/index.html", urlManager.getUrl());
        assertNotEquals("file:///C:/Users/Air/IdeaProjects/IT-62/Colector/index.html", urlManager.getUrl());
    }

    @Test
    public void testSetUrl() {
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
        assertEquals(new Object[]{"<html>", "<body>", "<table>", "<tr>", "<td>", "<td>", "<tr>", "<td>"}, urlManager.getPageTags());
        assertNotEquals(new Object[]{"<html>", "<body>", "<table>", "<tr>", "<td>", "<tr>", "<td>"}, urlManager.getPageTags());
    }

    @Test
    public void getPageTagsSortedSet() {
        assertEquals(new Object[]{"<body>", "<html>", "<table>", "<td>", "<tr>"}, urlManager.getPageTagsSortedSet());
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
        assertEquals(list.toArray(), urlManager.getPageTagsSortedByCountSet());
        assertNotEquals(new Object[]{"<table>=1", "<html>=2", "<body>=1", "<tr>=2", "<td>=3"}, urlManager.getPageTagsSortedByCountSet());
    }
}