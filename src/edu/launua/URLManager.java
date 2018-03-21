package edu.launua;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLManager {
    private String spec;
    private URL url;
    public URLManager() { }
    public URLManager(String spec) {
        this.spec = spec;
        createUrl(spec);
    }

    public String getUrl() {
        return spec;
    }

    public void setUrl(String spec) {
        this.spec = spec;
        createUrl(spec);
    }

    public String getPageContent() {
        String res = "";
        try(InputStream inputStream = url.openStream()) {
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            res = new String(buffer, "cp1251");
        }
        catch (IOException io) {
            io.getMessage();
        }
        return  res;
    }

    public Object[] getPageTags() {
        String res = getPageContent();
        Pattern pattern = Pattern.compile("<[\\w]+");
        Matcher matcher = pattern.matcher(res);
        ArrayList<Object> tags = new ArrayList<>();
        while(matcher.find()) {
            tags.add(res.substring(matcher.start(), matcher.end()) + ">");
        }
        return tags.toArray();
    }

    public Object[] getPageTagsSortedSet() {
        String res = getPageContent();
        Pattern pattern = Pattern.compile("<[\\w]+");
        Matcher matcher = pattern.matcher(res);
        Set<String> tags = new TreeSet<>();
        while(matcher.find())
            tags.add(res.substring(matcher.start(), matcher.end()) + ">");
        return tags.toArray();
    }

    public Object[] getPageTagsSortedByCountSetOld() {
        String res = getPageContent();
        Pattern pattern = Pattern.compile("<[\\w]+");
        Matcher matcher = pattern.matcher(res);
        Map<String, Integer> tagsCount= new HashMap<>();

        while(matcher.find()){
            if(tagsCount.containsKey(res.substring(matcher.start(), matcher.end()) + ">"))
                tagsCount.put(res.substring(matcher.start(), matcher.end()) + ">",
                        tagsCount.get(res.substring(matcher.start(), matcher.end()) + ">") + 1);
            else
                tagsCount.put(res.substring(matcher.start(), matcher.end()) + ">", 1);
//            res = matcher.replaceFirst("");
//            matcher = pattern.matcher(res);
        }
        List list = new ArrayList(tagsCount.entrySet());
        Collections.sort(list, new Comparator<Map.Entry<String, Integer>>() {
            @Override
            public int compare(Map.Entry<String, Integer> a, Map.Entry<String, Integer> b) {
                return a.getValue() - b.getValue();
            }
        });
        return list.toArray();
    }

    // More clean version
    public String[] getPageTagsSortedByCountSet() {
        Object[] tags = getPageTags();
        Map<Object, Integer> tagsCount= new HashMap<>();
        for (Object tag : tags) {
            if(tagsCount.containsKey(tag))
                tagsCount.put(tag, tagsCount.get(tag) + 1);
            else
                tagsCount.put(tag, 1);
        }
        List<Map.Entry<Object, Integer>> list = new ArrayList<>(tagsCount.entrySet());
        // list.sort((a, b) -> a.getValue() - b.getValue());
        list.sort(Comparator.comparing(Map.Entry<Object, Integer>::getValue));
        return list.toArray(new String[0]);
    }

    private void createUrl(String spec) {
        try {
            url = new URL(spec);
        }
        catch (MalformedURLException me) {
            System.out.println(me.getMessage());
        }
    }
}
