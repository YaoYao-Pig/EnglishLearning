package com.example.englishlearning.Controller;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.englishlearning.DB.DataBaseController;

public class BookController {
    public static SQLiteDatabase sqLiteDatabase;
    public static String[] colums;//BOOK_ID,BOOK_NAME, BOOK_COVER,CONTENT
    private static final String TABLENAME_BOOK = "t_book";
    public String getContentUrl(Integer id){
        Cursor cursor=sqLiteDatabase.query(TABLENAME_BOOK,
                new String[]{colums[3]},
                colums[0]+"=?",
                new String[]{id.toString()},
                null,null,null,null);
        cursor.moveToFirst();
        if(cursor.getCount()==0){
            throw new RuntimeException("The book didn't exist");
        }
        else{
            return cursor.getString(0);
        }
    }
    public String getTitle(Integer id){
        return "cet_4";
    }
}
