package com.example.englishlearning;


import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.navigation.NavController;
import androidx.navigation.fragment.NavHostFragment;
import androidx.navigation.ui.AppBarConfiguration;
import androidx.navigation.ui.NavigationUI;

import com.baidu.ocr.sdk.OCR;
import com.example.englishlearning.DB.DataBaseController;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainPageActivity extends AppCompatActivity {

    Button learn;
    Button review;
    Button select;
    RelativeLayout starNoteButton;
    Button ocrButton;

    BottomNavigationView bottomBar;
    NavController navController;
    AppBarConfiguration appBarConfiguration;


    private void initialize(){
        learn=(Button) findViewById(R.id.learnButton);
        review=(Button) findViewById(R.id.reviewButton);
        select=(Button) findViewById(R.id.selectBookButton);
        starNoteButton= findViewById(R.id.starNoteButton);
        ocrButton=findViewById(R.id.speechButton);

        DataBaseController dataBaseController=new DataBaseController(MainPageActivity.this);


        learn.setOnClickListener(new myClick());
        select.setOnClickListener(new myClick());
        review.setOnClickListener(new myClick());
        starNoteButton.setOnClickListener(new myClick());
        ocrButton.setOnClickListener(new myClick());



    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initialize();


    }

    private void changePage(Context packageContext, Class<?> cls){
        Intent intent=new Intent(packageContext, cls);
        startActivity(intent);
    }


    class myClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.learnButton:
                    changePage(MainPageActivity.this, WordActivity.class);
                    break;
                case R.id.reviewButton:
                    changePage(MainPageActivity.this,ReviewActivity.class);
                    break;
                case R.id.selectBookButton:
                    changePage(MainPageActivity.this, SelectBookActivity.class);
                    break;
                case R.id.starNoteButton:
                    changePage(MainPageActivity.this,WordStarActivity.class);
                    break;
                case R.id.speechButton:
                    changePage(MainPageActivity.this, SpeechConstantActivity.class);
                    break;

            }
        }
    }
}
