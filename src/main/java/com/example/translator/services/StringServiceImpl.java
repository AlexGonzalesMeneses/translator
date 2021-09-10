/**
 * @author: Alex Gonzales
 */

package com.example.translator.services;

import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;

import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.StringWriter;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

@Service
public class StringServiceImpl{

    public void copyStanzasReverseOrder(String str,OutputStream out) throws IOException {
        String songReverse = stanzasReverseOrder(str);
        StreamUtils.copy(songReverse, StandardCharsets.UTF_8,out);
    }

    private String stanzasReverseOrder(String str){
        StringBuilder stanzas = new StringBuilder();
        StringBuilder song = new StringBuilder();
        Scanner in = new Scanner(str);
        while (in.hasNextLine()){
            String line = in.nextLine();
            if(line.equals("")){
                stanzas.append("\n");
                song.insert(0,stanzas.toString());
                stanzas = new StringBuilder();
            }else{
                stanzas.append(line+"\n");
            }
        }
        stanzas.append("\n");
        song.insert(0,stanzas.toString());
        return song.toString();
    }

    public String getStringFromInputStream(InputStream input) throws IOException {
        StringWriter writer = new StringWriter();
        IOUtils.copy(input, writer, "UTF-8");
        return writer.toString();
    }

    public int countStanzas(String str){
        int count = 0;
        Scanner in = new Scanner(str);
        while (in.hasNextLine()){
            String line = in.nextLine();
            if(line.equals("")){
                count++;
            }
        }
        return count;
    }

    public void getGreatestOccurrenceWord(String str, OutputStream out) throws IOException {
        Map<String, Integer> occurrences = new HashMap<String, Integer>();
        String[] splitWords = str.split("[\\s,]+");
        for ( String Word : splitWords ) {
            Integer oldCount = occurrences.get(Word);
            if ( oldCount == null ) {
                oldCount = 0;
            }
            occurrences.put(Word, oldCount + 1);
        }
        Integer value = occurrences.entrySet().stream().max((entry1, entry2) -> entry1.getValue() - entry2.getValue()).get().getValue();
        String key = occurrences.entrySet().stream().max((entry1, entry2) -> entry1.getValue() - entry2.getValue()).get().getKey();
        String static_str = "Word: "+key+" occurrence: "+value;
        StreamUtils.copy(static_str, StandardCharsets.UTF_8,out);
    }

    public void replaceGreatestOccurrenceWord(InputStream in,String song, OutputStream out) throws IOException {
        String str = getStringFromInputStream(in);
        String[] splitWords = str.split("[\\s,]+");
        String word = splitWords[1];
        String song_replace = song.replace(word,"you");
        StreamUtils.copy(song_replace, StandardCharsets.UTF_8,out);
    }
}
