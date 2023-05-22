package com.example.englishlearning;

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
import com.example.englishlearning.enity.WordPage;

public class ReviewActivity extends Activity {

    Button remember;
    Button forget;
    TextView word;
    ImageView returnButton;

    WordPage wordPage=new WordPage();
    WordPageController wordPageController;

    int currentNumber=0;


    private void initialize(){
        remember=(Button) findViewById(R.id.rememberButton_review);
        forget=(Button) findViewById(R.id.forgetButton_review);
        word=(TextView) findViewById(R.id.wordsList_review);
        returnButton=(ImageView) findViewById(R.id.returnButton);

        returnButton.setOnClickListener(new myClick());

        DataBaseController dataBaseController=new DataBaseController(ReviewActivity.this);
        if(dataBaseController.isReviewEmpty()){
            System.out.println("1111111111111111111");
            dataBaseController.initializeReviewTable();
            if(dataBaseController.isReviewEmpty()){
                System.out.println("已经复习完啦已经复习完啦");
                Intent intent=new Intent(ReviewActivity.this,FinishActivity.class);
                Bundle bundle=new Bundle();
                bundle.putString("title","");
                bundle.putString("today","本次已复习");
                Bundle bundle2=getIntent().getExtras();
                if(bundle2==null)   currentNumber=0;
                else
                    currentNumber=(Integer) bundle2.getSerializable("number");
                bundle.putInt("num",currentNumber);
                intent.putExtras(bundle);
                startActivity(intent);
            }

        }


        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        wordPageController= new WordPageController();
        if(bundle!=null){
            wordPage= (WordPage) bundle.getSerializable("wordPage_review");
            currentNumber=(Integer) bundle.getSerializable("number");

        }else
        {

        }

        int flag=0;
        word.setText(wordPage.getWord_review(flag).getWord());
        if(flag==1){
            Intent intent1=new Intent(ReviewActivity.this,MainActivity.class);
            startActivity(intent1);
        }
        remember.setOnClickListener(new myClick());
        forget.setOnClickListener(new myClick());


    }
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.review);
        initialize();
        System.out.println("222222");
    }

    class myClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.rememberButton_review:
                    Bundle bundle1=new Bundle();
                    bundle1.putSerializable("wordPage_review",wordPage);
                    bundle1.putSerializable("number",currentNumber);
                    Intent intent1=new Intent(ReviewActivity.this, WordDetails_Remember_ReviewActivity.class);
                    intent1.putExtras(bundle1);
                    startActivity(intent1);
                    break;
                case R.id.forgetButton_review:
                    Bundle bundle2=new Bundle();
                    bundle2.putSerializable("wordPage",wordPage);
                    bundle2.putSerializable("number",currentNumber);
                    Intent intent2=new Intent(ReviewActivity.this, WordDetails_Forget_ReviewActivity.class);
                    intent2.putExtras(bundle2);
                    startActivity(intent2);
                    break;
                case R.id.returnButton:
                    Intent intent3=new Intent(ReviewActivity.this,MainActivity.class);
                    startActivity(intent3);
                    break;

            }
        }
    }
}
