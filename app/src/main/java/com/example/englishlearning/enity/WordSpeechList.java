package com.example.englishlearning.enity;

import android.content.Intent;

import java.io.Serializable;
import java.util.List;

public class WordSpeechList implements Serializable {
    private List<Word> words;
    private Integer currentWord;
    private Integer size;
    private Boolean isFull;

    public Integer getSize(){
        return size;
    }


    public Word getCurrentWord() {
        if(currentWord.equals(size)){
            isFull=true;
            return new Word();
        }
        return words.get(currentWord);
    }

    public void moveToNext(){
        currentWord++;
        if(currentWord.equals(size)){
            isFull=true;
            return ;
        }
        return ;
    }

    public Boolean isFull(){
        return size.equals(currentWord);
    }
    public WordSpeechList(List<Word> words) {
        this.words = words;
        currentWord=0;
        size=this.words.size();
        isFull=false;
    }


}
