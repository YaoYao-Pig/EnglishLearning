package com.example.englishlearning;

import static com.example.englishlearning.UIController.setTextViewSize;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.util.TypedValue;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.englishlearning.Controller.WordPageController;
import com.example.englishlearning.enity.WordPage;

public class WordActivity extends Activity {
    Button remember;
    Button forget;
    TextView word;
    ImageView returnButton;
    WordPage wordPage=new WordPage();
    private final int num=10;
    WordPageController wordPageController;



    private void initialize(){
        remember=(Button) findViewById(R.id.rememberButton);
        forget=(Button) findViewById(R.id.forgetButton);
        word=(TextView) findViewById(R.id.wordsList);
        returnButton=(ImageView) findViewById(R.id.returnButton);





        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        wordPageController= new WordPageController();
        if(bundle!=null){
            wordPage= (WordPage) bundle.getSerializable("wordPage");

        }
//        else{
//            if (wordPage.getSize() == 0) {
//
//                wordPage = wordPageController.getWordPage(num);
//            }
//
//        }
        System.out.println(wordPage.getWord().toString());
        System.out.println(wordPage.getSize());

        word.setText(wordPage.getWord().getWord());
        //setTextViewSize(word);


        returnButton.setOnClickListener(new myClick());
        remember.setOnClickListener(new myClick());
        forget.setOnClickListener(new myClick());
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.word);




        initialize();


    }


    class myClick implements View.OnClickListener {
        @Override
        public void onClick(View view) {
            switch (view.getId()) {
                case R.id.rememberButton:

                    Bundle bundle1=new Bundle();
                    bundle1.putSerializable("wordPage",wordPage);
                    Intent intent1=new Intent(WordActivity.this, WordDetails_RememberActivity.class);
                    intent1.putExtras(bundle1);
                    startActivity(intent1);
                    finish();
                    break;
                case R.id.forgetButton:
                    Bundle bundle2=new Bundle();
                    bundle2.putSerializable("wordPage",wordPage);
                    Intent intent2=new Intent(WordActivity.this, WordDetails_ForgetActivity.class);
                    intent2.putExtras(bundle2);
                    startActivity(intent2);
                    finish();
                    break;
                case R.id.returnButton:
                    Intent intent3=new Intent(WordActivity.this,MainActivity.class);
                    startActivity(intent3);
                    finish();
                    break;
            }
        }
    }
}
