package com.example.codeclicker;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Typer {
    private String text = "";
    private String fullText = "";
    private List<String> originalTexts = new ArrayList<String>();
    private Integer whichText = 0;
    private Integer startPos = 0;
    private Integer endPos = 0;
    private Integer clickAmount = 1;
    private Integer automaticAmount = 0;
    private final Integer maxLines = 15;
    private static Typer instance;

    public void getFiles(InputStream is) {
        try {
            byte[] buffer = new byte[is.available()];
            while (is.read(buffer) != -1) {
                originalTexts.add(new String(buffer));
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void getFirstText() {
        fullText = originalTexts.get(0);
    }

    public void reset() {
        text = "";
        fullText = originalTexts.get(0);
        whichText = 0;
        startPos = 0;
        endPos = 0;
        clickAmount = 1;
        automaticAmount = 0;
    }

    public void addAutomaticText() {
        endPos += automaticAmount;
        updateText();
    }

    public void addText () {
        endPos += clickAmount;
        updateText();
    }

    public void updateText() {
        if(originalTexts.size() > 0) {
            if (endPos > fullText.length()) {
                whichText++;

                if (whichText >= originalTexts.size()) {
                    whichText = 0;
                }

                fullText = originalTexts.get(whichText);
                endPos = 0;
                startPos = 0;
            }

            text = fullText.substring(0, endPos);
            if (countLines() > maxLines) {
                cutStringLine();
            }
        }
    }

    public String getCurrentText() {
        return text;
    }

    public void addClickAmount() {
        clickAmount++;
    }

    public void addAutomaticAmount() {
        automaticAmount++;
    }

    private Integer countLines() {
        List<String> lines = Arrays.asList(text.split("\n"));
        return lines.size();
    }

    private void cutStringLine() {
        startPos = text.indexOf("\n") + 1;
        endPos -= startPos;
        fullText = fullText.substring(startPos);
    }

    public void setInstance(){
        instance = null;
    }
    public static Typer getInstance(){
        if(instance == null){
            return instance = new Typer();
        }
        return instance;
    }
}
