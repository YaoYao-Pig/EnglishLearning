package com.example.englishlearning.Controller;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.englishlearning.DB.DataBaseController;
import com.example.englishlearning.enity.Word;
import com.example.englishlearning.enity.WordPage;

import java.util.List;

public class WordPageController {
    public static DataBaseController dataBaseController;
    public WordPage getWordPage(int num){
        List<Word> words=dataBaseController.getNotLearnedWord(num);
        //System.out.println(words.size());
        return new WordPage(words,0,num);

    }
    public WordPage getWordPage_review(int num){
        List<Word> words=dataBaseController.getReviewWord(num);
        //System.out.println(words.size());
        return new WordPage(words,0,num);
    }

    private void deleteWordInList(WordPage wordPage,int pos){
        if(wordPage==null){
            throw new RuntimeException("wordList is null");
        }
        wordPage.getWords().remove(pos);
        if(pos==wordPage.getSize()){
            wordPage.setCurrentWord(0);
        }

        wordPage.setSize(wordPage.getSize()-1);


    }

    private void deleteWordInList_review(WordPage wordPage,int pos){
        if(wordPage==null){
            throw new RuntimeException("wordList is null");
        }
        wordPage.getWords().remove(pos);
        if(pos==wordPage.getSize()){
            wordPage.setCurrentWord(0);
        }

        wordPage.setSize(wordPage.getSize()-1);

    }


    public void reviewWord(WordPage wordPage){
        int currentPos=wordPage.getCurrentWord();
        int currentId=wordPage.getCurrentWordId();

        deleteWordInList_review(wordPage,currentPos);

        dataBaseController.updateHasReview(currentId);

        dataBaseController.deleteReviewed(currentId);


    }

    public void forgetWordReview(WordPage wordPage){
        int currentPos=wordPage.getCurrentWord();
        int currentId=wordPage.getCurrentWordId();
        deleteWordInList_review(wordPage,currentPos);
    }

    public void learnWord(WordPage wordPage) {
        int currentPos=wordPage.getCurrentWord();
        int currentId=wordPage.getCurrentWordId();

        //在WordPage的List中删除这个单词
        deleteWordInList(wordPage,currentPos);


        try {
            //添加到learn表中
            dataBaseController.addLearn(wordPage.getWords().get(0));


        }catch (Exception e){
            WordPageController wordPageController=new WordPageController();
            wordPage=wordPageController.getWordPage(10);
            //添加到learn表中
            dataBaseController.addLearn(wordPage.getWords().get(0));
        }
        //word表中的hasLearn标记为1;
        dataBaseController.updateWordHasLearn(currentId);

        dataBaseController.increaseRememberNumber();
    }
    public void forgetWord(WordPage wordPage){
        int currentPos=wordPage.getCurrentWord();
        int currentId=wordPage.getCurrentWordId();
        deleteWordInList(wordPage,currentPos);
    }
}
