package com.example.englishlearning;

import static com.example.englishlearning.chatgpt.ChatGptController.generateText;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.englishlearning.Controller.JsonController;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;

public class GPT_Detail_Activity extends Activity {
    TextView output;
    ImageView returnButton;
    TextView words;



    String promt="";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gpt_detail);

        output=findViewById(R.id.edit_chat_output);
        returnButton=findViewById(R.id.returnButton);
        words=findViewById(R.id.gpt_detail_words);

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(GPT_Detail_Activity.this,MainActivity.class));
            }
        });

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();

        String output_text=bundle.getString("text");

        words.setText(bundle.getString("words"));
        output.setText(output_text);



    }
}
