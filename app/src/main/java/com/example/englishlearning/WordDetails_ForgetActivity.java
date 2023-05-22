package com.example.englishlearning;

import static com.example.englishlearning.UIController.*;


import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;

import com.example.englishlearning.Controller.WordPageController;
import com.example.englishlearning.enity.Word;
import com.example.englishlearning.enity.WordPage;

import java.util.List;

public class WordDetails_ForgetActivity extends Activity {

    Button rforget;
    TextView word;
    TextView ps;
    TextView pos;
    TextView sentence;
    WordPage wordPage=new WordPage();
    ImageView returnButton;



    public void setText(){
        Word word1=wordPage.getWord();

        word.setText(word1.getWord());
        ps.setText(textFormatPs(word1.getPs()));
        pos.setText(textFormat_pos(word1.getPosToString(),word1.getTransToString()));
        sentence.setText(textFormat_sentence(word1.getSContentToString(),word1.getSCnToString()));


        setTextViewSize(word);
    }



    private void initialize(){
        rforget=(Button) findViewById(R.id.rforgetButton_forget);
        word=(TextView) findViewById(R.id.wordsList_forget);
        ps=(TextView) findViewById(R.id.ps_forget);//音标
        pos=(TextView) findViewById(R.id.pos_forget);//词性
        sentence=(TextView) findViewById(R.id.sentence_forget);//例句
        returnButton=(ImageView) findViewById(R.id.returnButton);


    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_detail_forget);
        Intent intent=getIntent();
        wordPage= (WordPage) intent.getSerializableExtra("wordPage");

        initialize();
        setText();



        rforget.setOnClickListener(new myClick());

    }
    class myClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.rforgetButton_forget:
                    WordPageController wordPageController=new WordPageController();
                    wordPageController.forgetWord(wordPage);

                    Bundle bundle1=new Bundle();
                    bundle1.putSerializable("wordPage",wordPage);
                    Intent intent1=new Intent(WordDetails_ForgetActivity.this,WordActivity.class);
                    intent1.putExtras(bundle1);
                    startActivity(intent1);
                    finish();
                    break;
                case R.id.returnButton:
                    Intent intent3=new Intent(WordDetails_ForgetActivity.this,MainActivity.class);
                    startActivity(intent3);
                    finish();
                    break;
            }
        }
    }
}
