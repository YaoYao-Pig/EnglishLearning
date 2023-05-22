package com.example.englishlearning;

import static com.example.englishlearning.UIController.setTextViewSize;
import static com.example.englishlearning.UIController.textFormatPs;
import static com.example.englishlearning.UIController.textFormat_pos;
import static com.example.englishlearning.UIController.textFormat_sentence;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.englishlearning.enity.Word;
import com.example.englishlearning.enity.WordSpeechList;


public class SpeechDetailActivity extends Activity {

    private Button returnButton;
    private TextView word;
    private TextView ps;
    private TextView pos;
    private TextView sentence;
    private Button next;
    private WordSpeechList wordSpeechList;



    public void setText(){




        Word word1=wordSpeechList.getCurrentWord();
        word.setText(word1.getWord());
        ps.setText(textFormatPs(word1.getPs()));
        pos.setText(textFormat_pos(word1.getPosToString(),word1.getTransToString()));
        sentence.setText(textFormat_sentence(word1.getSContentToString(),word1.getSCnToString()));


        setTextViewSize(word);

    }



    private void initialize(){
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        wordSpeechList= (WordSpeechList) bundle.getSerializable("wordSpeechList");


        word=(TextView) findViewById(R.id.speech_word);
        ps=(TextView) findViewById(R.id.speech_ps);//音标
        pos=(TextView) findViewById(R.id.speech_pos);//词性
        sentence=(TextView) findViewById(R.id.speech_sentence);//例句
        next=findViewById(R.id.speech_next_button);



        if(wordSpeechList.isFull()){
            //完成学习

            Bundle bundle1=new Bundle();
            bundle1.putString("title","语音输入学习完成");
            bundle1.putString("today","本次已学");
            bundle1.putString("num",wordSpeechList.getSize().toString());

            startActivity(new Intent(SpeechDetailActivity.this,FinishActivity.class)
                    .putExtras(bundle1));



        }


        next.setOnClickListener(new myClick());


    }

    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word_detail_speech);

        initialize();

        setText();

    }

    class myClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.speech_next_button:
                    wordSpeechList.moveToNext();
                    if(wordSpeechList.isFull()){
                        //完成学习

                        Bundle bundle1=new Bundle();
                        bundle1.putString("title","语音输入学习完成");
                        bundle1.putString("today","本次已学");
                        bundle1.putString("num",wordSpeechList.getSize().toString());

                        startActivity(new Intent(SpeechDetailActivity.this,FinishActivity.class)
                                .putExtras(bundle1));



                    }
                    else{
                        Intent intent=new Intent(SpeechDetailActivity.this,SpeechDetailActivity.class);
                        Bundle bundle=new Bundle();
                        bundle.putSerializable("wordSpeechList",wordSpeechList);
                        intent.putExtras(bundle);
                        startActivity(intent);
                    }

                    break;
                case R.id.speech_return:
                    startActivity(new Intent(SpeechDetailActivity.this,SpeechConstantActivity.class));

            }
        }
    }
}
