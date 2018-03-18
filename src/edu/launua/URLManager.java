package edu.launua;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.*;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class URLManager{
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

    public String getPageContent(){
        String res = "";
        try(InputStream inputStream = url.openStream()) {
            byte[] buffer = new byte[inputStream.available()];
            inputStream.read(buffer);
            res = new String(buffer, "cp1251");
        }
        catch (IOException io){
            io.getMessage();
        }
        return  res;
    }

    public Object[] getPageTagsSortedSet(){
        String res = getPageContent();
        Pattern pattern = Pattern.compile("<[\\w]+");
        Matcher matcher = pattern.matcher(res);
        Set<String> tags = new TreeSet<String>();
        while(matcher.find()){
            tags.add(res.substring(matcher.start(), matcher.end()) + ">");
            res = matcher.replaceFirst("");
            matcher = pattern.matcher(res);
        }
        return tags.toArray();
    }

    public Object[] getPageTagsSortedByCountSet(){
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
            res = matcher.replaceFirst("");
            matcher = pattern.matcher(res);
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



    private void createUrl(String spec) {
        try {
            url = new URL(spec);
        }
        catch (MalformedURLException me){
            System.out.println(me.getMessage());
        }
    }
}
