package com.example.englishlearning;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.englishlearning.DB.DataBaseController;

public class FinishActivity extends Activity {
    Button returnButton;
    TextView title;
    TextView today;//今日已学/已复习
    TextView num;

    private void initialize(){
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();



        returnButton=(Button) findViewById(R.id.btn_fi_back);
        title=(TextView) findViewById(R.id.text_fi_title);
        today=(TextView) findViewById(R.id.text_fi_title_tody);
        num=(TextView) findViewById(R.id.text_fi_word_num) ;
        title.setText(bundle.get("title").toString());
        today.setText(bundle.get("today").toString());
        num.setText( bundle.get("num").toString());
        Integer number= bundle.getInt("num");
        new DataBaseController(FinishActivity.this).increaseCoinNum(number);


        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(FinishActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_finish);

        initialize();
    }


}
