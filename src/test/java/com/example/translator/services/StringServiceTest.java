/**
 * @author: Alex Gonzales
 */
package com.example.translator.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.*;

import static org.testng.Assert.*;

import org.junit.jupiter.api.Test;

@SpringBootTest
public class StringServiceTest {

    @Autowired
    private StringServiceImpl stringService;

    @Test
    void copyStanzasReverseOrder() throws IOException {
        String inputFileName = "src/test/resources/original.txt";
        String outputFileName = "src/test/resources/original_reverse.txt";
        File outputFile = new File(outputFileName);
        InputStream in = new FileInputStream(inputFileName);
        OutputStream out = new FileOutputStream("src/test/resources/original_reverse.txt");
        String str = stringService.getStringFromInputStream(in);
        stringService.copyStanzasReverseOrder(str,out);
//        assertEquals(in, out);
    }

    @Test
    void countStanzas() throws IOException {
        String inputFileName = "src/test/resources/original_reverse.txt";
        InputStream in = new FileInputStream(inputFileName);
        String str = stringService.getStringFromInputStream(in);
        int count = stringService.countStanzas(str);
        assertEquals(17,count);
    }

    @Test
    void getGreatestOccurrence() throws IOException {
        String inputFileName = "src/test/resources/original.txt";
        String outputFileName = "src/test/resources/statics.txt";
        File outputFile = new File(outputFileName);
        InputStream in = new FileInputStream(inputFileName);
        OutputStream out = new FileOutputStream("src/test/resources/statics.txt");
        String str = stringService.getStringFromInputStream(in);
        stringService.getGreatestOccurrenceWord(str,out);
    }

    @Test
    void replaceGreatestOccurrence() throws IOException {
        String inputFileName = "src/test/resources/statics.txt";
        String outputFileName = "src/test/resources/final_output.txt";
        File outputFile = new File(outputFileName);
        InputStream in = new FileInputStream(inputFileName);
        OutputStream out = new FileOutputStream("src/test/resources/final_output.txt");
        String songFileName = "src/test/resources/original_reverse.txt";
        InputStream inSong = new FileInputStream(songFileName);
        String song = stringService.getStringFromInputStream(inSong);
        stringService.replaceGreatestOccurrenceWord(in,song, out);
    }
}
