package com.example.englishlearning;

import static com.example.englishlearning.UIController.setTextViewSize;
import static com.example.englishlearning.UIController.textFormatPs;
import static com.example.englishlearning.UIController.textFormat_pos;
import static com.example.englishlearning.UIController.textFormat_sentence;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.englishlearning.enity.Word;

public class SearchDetailActivity extends Activity {
    private TextView word;
    private TextView ps;
    private TextView pos;
    private TextView sentence;
    private Word mWord;
    private ImageView starReturn;

    private void initialize(){
        word=findViewById(R.id.wordStar_word);
        ps=findViewById(R.id.star_ps);
        pos=findViewById(R.id.star_pos);
        sentence=findViewById(R.id.star_sentence);
        starReturn=findViewById(R.id.star_return);

        starReturn.setOnClickListener(new myClick());
    }



    public void setText(){


        word.setText(mWord.getWord());
        ps.setText(textFormatPs(mWord.getPs()));
        pos.setText(textFormat_pos(mWord.getPosToString(),mWord.getTransToString()));
        sentence.setText(textFormat_sentence(mWord.getSContentToString(),mWord.getSCnToString()));


        setTextViewSize(word);
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_detail_star);

        Intent intent=getIntent();
        mWord = (Word) intent.getSerializableExtra("word");

        initialize();
        setText();


    }

    class myClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.star_return:
                    Intent intent=new Intent(SearchDetailActivity.this,SearchActivity.class);
                    startActivity(intent);
                    break;
            }
        }
    }

}
