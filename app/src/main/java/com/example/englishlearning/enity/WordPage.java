package com.example.englishlearning.enity;

import android.content.Context;
import android.content.Intent;
import android.os.Parcel;
import android.os.Parcelable;

import com.example.englishlearning.Controller.WordPageController;
import com.example.englishlearning.MainPageActivity;
import com.example.englishlearning.WordDetails_Remember_ReviewActivity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class WordPage  implements Serializable{
    private List<Word> words;
    private int currentWord=0;
    private int size=0;





    public Word getWord(){
        System.out.println("cur:"+currentWord);
        if(size==0){
            WordPageController wordPageController=new WordPageController();
            words=wordPageController.getWordPage(10).getWords();
            size=10;
        }
        try {
            return words.get(currentWord);
        }
        catch (Exception exception){
            System.out.println("WordPage.java:Has learn over");
            return new Word();
        }
    }

    public Word getWord_review(int flag){
        System.out.println("cur:"+currentWord);
        if(size==0){
            WordPageController wordPageController=new WordPageController();
            words=wordPageController.getWordPage_review(10).getWords();
            size=10;

        }
        try {
            flag=0;
            return words.get(currentWord);
        }
        catch (Exception exception){
           flag=1;

            return new Word();
        }

    }

    public int getCurrentWordId(){
        return words.get(currentWord).getWord_id();
    }
    public WordPage() {
        size=0;
        currentWord=0;
        words=new ArrayList<>();
    }

    protected WordPage(Parcel in) {
        currentWord = in.readInt();
        size = in.readInt();
    }



    public List<Word> getWords() {
        return words;
    }

    public void setWords(List<Word> words) {
        this.words = words;
    }

    public int getCurrentWord() {
        return currentWord;
    }

    public void setCurrentWord(int currentWord) {
        this.currentWord = currentWord;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public WordPage(List<Word> words, int currentWord, int size) {
        this.words = words;
        this.currentWord = currentWord;
        this.size = size;
    }


}
