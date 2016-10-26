package com.keyholesoftware.scrabbleRiddler;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;

public class ScrabbleRiddler {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new FileReader("twl06.txt"));
        List<String> words = new ArrayList<>();
        // Read TWL 
        String line = reader.readLine();
        while (line != null) {
            words.add(line);
            line = reader.readLine();
        }
        reader.close();
        
        // Sort by longest first
        words.sort(new Comparator<String>() {

            @Override
            public int compare(String o1, String o2) {
                return o2.length() - o1.length();
            }
            
        });

        // Process list
        for (int i = 0; i < words.size(); i++) {
            String word = words.get(i);
            String tmpWord = word;
            int tmpWordLength = tmpWord.length();
            List<String> wordList = new ArrayList<>();
            while (tmpWordLength > 1) {
                String nextWordStart = tmpWord.substring(0, tmpWordLength-1);
                String nextWordEnd = tmpWord.substring(1, tmpWordLength);
                if (words.contains(nextWordStart)) {
                    tmpWord = nextWordStart;
                    tmpWordLength = tmpWord.length();
                    wordList.add(nextWordStart);
                } else if (words.contains(nextWordEnd)) {
                    tmpWord = nextWordEnd;
                    tmpWordLength = tmpWord.length();
                    wordList.add(nextWordEnd);
                } else {
                    tmpWord = "";
                    tmpWordLength = 0;
                }
                if (tmpWordLength == 2) {
                    System.out.println("Solution found. Word is " + word + ". Built using " + wordList);
                    break;
                }
            }
            if (i % 10000 == 0) {
                System.out.println("Current word: " + word + "; cnt = " + i);
            } 
        }
    }

}
