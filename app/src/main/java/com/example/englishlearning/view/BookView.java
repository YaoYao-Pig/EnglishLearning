package com.example.englishlearning.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextClock;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.englishlearning.R;

public class BookView {
    private ImageView bookCover;
    private ImageView omitButton;

    private TextView wordNumber;
    private TextView bookName;
    private TextView bookSource;

    public BookView(ImageView bookCover, TextView wordNumber, TextView bookName) {
        this.bookCover = bookCover;
        this.wordNumber = wordNumber;
        this.bookName = bookName;
    }

    public ImageView getBookCover() {
        return bookCover;
    }

    public ImageView getOmitButton() {
        return omitButton;
    }

    public TextView getWordNumber() {
        return wordNumber;
    }

    public TextView getBookName() {
        return bookName;
    }

    public TextView getBookSource() {
        return bookSource;
    }

    public void setBookCover(ImageView bookCover) {
        this.bookCover = bookCover;
    }

    public void setOmitButton(ImageView omitButton) {
        this.omitButton = omitButton;
    }

    public void setWordNumber(TextView wordNumber) {
        this.wordNumber = wordNumber;
    }

    public void setBookName(TextView bookName) {
        this.bookName = bookName;
    }

    public void setBookSource(TextView bookSource) {
        this.bookSource = bookSource;
    }
//    public BookView(Context context) {
//        super(context);
//    }
//
//    public BookView(Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
//        initView(context);
//    }
//
//    public BookView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
//        super(context, attrs, defStyleAttr);
//    }
//    private void initView(Context context){
//        LayoutInflater.from(context).inflate(R.layout.item_book, this,
//                true);
//        bookCover=(ImageView) findViewById(R.id.item_img_book);
//        omitButton=(ImageView) findViewById(R.id.item_omit);
//        bookName=(TextView) findViewById(R.id.item_text_book_name);
//        bookSource=(TextView) findViewById(R.id.item_text_book_source);
//        wordNumber=(TextView) findViewById(R.id.item_text_book_word_num);
//
//        bookCover.setImageResource(R.drawable.back);
//
//


//    }


}
