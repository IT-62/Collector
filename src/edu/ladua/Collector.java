package edu.ladua;

import java.io.*;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class Collector {
    private String pathIn, pathOut;
    public Collector(String pathIn, String pathOut) {
        this.pathIn = pathIn;
        this.pathOut = pathOut;
    }

    public String getPathIn() {
        return pathIn;
    }

    public String getPathOut() {
        return pathOut;
    }

    public ArrayList<String> readFileOld(){
        ArrayList<String> res = new ArrayList<String>();
        try(BufferedReader bufferedReader = new BufferedReader(
                new InputStreamReader(
                        new FileInputStream(pathIn), Charset.forName("cp1251")))){
            String line;
            while((line = bufferedReader.readLine()) != null){
                res.add(line);
            }
        }
        catch (IOException ioe){
            System.out.println(ioe.getMessage());
        }
        return res;
    }

    public ArrayList<String> readFileNew(){
        ArrayList<String> res = new ArrayList<String>();
        try {
            res = (ArrayList<String>) Files.readAllLines(Paths.get(pathIn), Charset.forName("cp1251"));
        }
        catch (IOException io){
            System.out.println(io.getMessage() + "?!");
        }
        return res;
    }

    public ArrayList<String> getSortedList(ArrayList<String> strings){
        Collections.sort(strings, new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.length() - o2.length();
            }
        });
        return strings;
    }

    public void writeToFile(ArrayList<String> strings){
        try(BufferedWriter bufferedWriter =
                    new BufferedWriter(
                            new OutputStreamWriter(
                                    new FileOutputStream(pathOut), Charset.forName("cp1251")))){
            for (String string:
                 strings) {
                bufferedWriter.write(string);
            }

        }
        catch (IOException ioe){
            System.out.println(ioe.getMessage());
        }
    }
}
