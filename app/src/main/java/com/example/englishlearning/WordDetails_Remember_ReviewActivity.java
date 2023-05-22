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
import com.example.englishlearning.DB.DataBaseController;
import com.example.englishlearning.enity.Word;
import com.example.englishlearning.enity.WordPage;

import java.util.List;

public class WordDetails_Remember_ReviewActivity extends Activity {

    Button rremember;
    ImageView rforget;
    ImageView returnButton;
    TextView word;
    TextView ps;
    TextView pos;
    TextView sentence;

    ImageView starButton;
    int currentNumber=0;


    WordPage wordPage=new WordPage();


    public void setText(){
        Word word1=wordPage.getWord();
        word.setText(word1.getWord());
        ps.setText(textFormatPs(word1.getPs()));
        pos.setText(textFormat_pos(word1.getPosToString(),word1.getTransToString()));
        sentence.setText(textFormat_sentence(word1.getSContentToString(),word1.getSCnToString()));
        setTextViewSize(word);
    }





    private void initialize(){

        rremember=(Button) findViewById(R.id.rrememberButton);
        rforget=(ImageView) findViewById(R.id.rforgetButton);
        word=(TextView) findViewById(R.id.wordsList_remember);
        ps=(TextView) findViewById(R.id.ps);//音标
        pos=(TextView) findViewById(R.id.pos);//词性
        sentence=(TextView) findViewById(R.id.sentence);//例句
        returnButton=(ImageView) findViewById(R.id.returnButton);
        starButton=((ImageView) findViewById(R.id.startButton));



        rremember.setOnClickListener(new myClick());
        rforget.setOnClickListener(new myClick());
        returnButton.setOnClickListener(new myClick());
        starButton.setOnClickListener(new myClick());

        DataBaseController dataBaseController=new DataBaseController(WordDetails_Remember_ReviewActivity.this);

        if(dataBaseController.selectWordStar(wordPage.getCurrentWordId())==true){
            starButton.setImageDrawable((getResources().getDrawable(R.drawable.icon_star_fill)));
        }
        else{
            starButton.setImageDrawable((getResources().getDrawable(R.drawable.icon_star)));
        }
    }


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_detail_remember);
        Intent intent=getIntent();
        wordPage= (WordPage) intent.getSerializableExtra("wordPage_review");
        currentNumber=(Integer) intent.getSerializableExtra("number");

        initialize();
        setText();




    }
    class myClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.rrememberButton:
                    WordPageController wordPageController=new WordPageController();

                    wordPageController.reviewWord(wordPage);

                    Bundle bundle1=new Bundle();
                    bundle1.putSerializable("wordPage_review",wordPage);
                    bundle1.putSerializable("number",currentNumber+1);
                    Intent intent1=new Intent(WordDetails_Remember_ReviewActivity.this,ReviewActivity.class);
                    intent1.putExtras(bundle1);
                    startActivity(intent1);
                    finish();
                    break;
                case R.id.rforgetButton:
                    WordPageController wordPageController1=new WordPageController();
                    wordPageController1.forgetWord(wordPage);

                    Bundle bundle2=new Bundle();
                    bundle2.putSerializable("wordPage_review",wordPage);
                    Intent intent2=new Intent(WordDetails_Remember_ReviewActivity.this,ReviewActivity.class);
                    intent2.putExtras(bundle2);
                    startActivity(intent2);
                    finish();
                    break;
                case R.id.returnButton:
                    Intent intent3=new Intent(WordDetails_Remember_ReviewActivity.this,MainActivity.class);
                    startActivity(intent3);
                    finish();
                    break;
                case R.id.startButton:
                    DataBaseController dataBaseController=new DataBaseController(WordDetails_Remember_ReviewActivity.this);
                    if(dataBaseController.selectWordStar(wordPage.getCurrentWordId())==false){
                        wordPage.getWords().get(0).setFlag(1);

                        dataBaseController.updateHasStar(wordPage.getCurrentWordId(),1);
                        starButton.setImageDrawable((getResources().getDrawable(R.drawable.icon_star_fill)));
                    }
                    else{
                        wordPage.getWords().get(0).setFlag(0);

                        dataBaseController.updateHasStar(wordPage.getCurrentWordId(),0);
                        starButton.setImageDrawable((getResources().getDrawable(R.drawable.icon_star)));
                    }

                    break;

            }
        }
    }
}
