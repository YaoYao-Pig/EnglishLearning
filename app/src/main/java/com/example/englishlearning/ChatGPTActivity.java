package com.example.englishlearning;

import static com.example.englishlearning.chatgpt.ChatGptController.generateText;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AlertDialog;

import com.example.englishlearning.Controller.JsonController;
import com.example.englishlearning.DB.DataBaseController;
import com.example.englishlearning.enity.Word;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;

import java.io.IOException;
import java.util.List;

public class ChatGPTActivity extends Activity {
    EditText input;

    LinearLayout bottom;
    LinearLayout generate;

    private String formatString(String text){
        return text.replace(';','\n');


    }
    String store="";
    private void generateWord(){
        List<Word> wordList= new DataBaseController(ChatGPTActivity.this).getGenerateWord(5);
        StringBuffer word=new StringBuffer();
        for(int i=0;i<wordList.size();++i){
            if(i!=0){
                word.append(";");
            }
            word.append(wordList.get(i).getWord());
        }
        store=word.toString();
        input.setText(formatString(word.toString()));

    }



    private void showDialog(String title,String message,String buttonText){
        new AlertDialog.Builder(this)
                .setTitle(title)
                .setMessage(message)
                .setPositiveButton(buttonText, null)
                .show();
    }

    String output="";

    private void useChat(String prompt){
        String apiKey = "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";



        try {

            String result = generateText(apiKey, prompt);
            JsonController jsonController=new JsonController();
            JsonObject jsonObject=jsonController.readJsonByString(result);
            JsonArray choices=jsonObject.getAsJsonArray("choices");
            String text=choices.get(0).getAsJsonObject().get("text").getAsString();
            System.out.println(result);
            output=text;

        } catch (IOException e) {

            e.printStackTrace();

        }
    }

    String promt="";

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatgpt);

        input=findViewById(R.id.edit_chat_input);

        bottom=findViewById(R.id.layout_chat_bottom);
        generate=findViewById(R.id.layout_chat_top);



        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                generateWord();
            }
        });
        bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(input.getText().toString()==""){
                    showDialog("GPT ERROR","please enter word","confirm");
                }
                else {


                    if(store.split(";").length>5){
                        promt="Please write a story with less than 100 words using the following words and then please translate the story into Simple Chinese :";
                    }
                    else{
                        promt="Please write a story using the following words and translate it into Simple Chinese :";
                    }
                    promt+=store;


                    new Thread(){
                        @Override
                        public void run(){
                            useChat(promt);

                            Bundle bundle=new Bundle();
                            bundle.putString("text",output);
                            bundle.putString("words",input.getText().toString());

                            startActivity(new Intent(ChatGPTActivity.this,GPT_Detail_Activity.class).putExtras(bundle));
                        }
                    }.start();


                }


            }
        });
    }
}
