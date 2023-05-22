package com.example.englishlearning;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.hardware.lights.LightsManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.englishlearning.Controller.JsonParser;
import com.example.englishlearning.DB.DataBaseController;
import com.example.englishlearning.enity.Word;
import com.example.englishlearning.enity.WordPage;
import com.example.englishlearning.enity.WordSpeechList;
import com.iflytek.cloud.InitListener;
import com.iflytek.cloud.RecognizerResult;
import com.iflytek.cloud.SpeechConstant;
import com.iflytek.cloud.SpeechError;
import com.iflytek.cloud.SpeechRecognizer;
import com.iflytek.cloud.SpeechUtility;
import com.iflytek.cloud.ui.RecognizerDialog;
import com.iflytek.cloud.ui.RecognizerDialogListener;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.TreeSet;

public class SpeechConstantActivity extends Activity {

    /// 进行基本设置
    private static final String TAG = "SpeechConstantActivity";

    private SpeechRecognizer mSpeak;// 语音_对象
    private RecognizerDialog mSpeakDialog;// 语音_对话框(UI)

    // 用HashMap存储听写结果
    private HashMap<String, String> mSpeakResults = new LinkedHashMap<String, String>();

    private SharedPreferences mSharedPreferences;//缓存

    private InitListener mInitListener = new InitListener() {
        @Override
        public void onInit(int code) {
            Log.d(TAG, "SpeechRecognizer init() code = " + code);
        }
    };


    private EditText SpeakResult;//识别结果
    private TextView btnStart;//开始识别
    private TextView speakTitle;
    private LinearLayout speakBottom;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.speech);
        SpeechUtility.createUtility(SpeechConstantActivity.this, SpeechConstant.APPID +"=a5fc62c9");

        SpeakResult = findViewById(R.id.speak_result);
        btnStart = findViewById(R.id.start_button);
        speakTitle=findViewById(R.id.speech_title);
        speakBottom=findViewById(R.id.layout_speak_bottom);


        btnStart.setOnClickListener(new myClick());
        speakBottom.setOnClickListener(new myClick());



        mSpeak = SpeechRecognizer.createRecognizer(SpeechConstantActivity.this, mInitListener);

        mSpeakDialog = new RecognizerDialog(SpeechConstantActivity.this, mInitListener);
        mSharedPreferences = getSharedPreferences("ASR",
                Activity.MODE_PRIVATE);
    }



    //参数设置
    public void setParam() {
        // 设置听写引擎
        mSpeak.setParameter(SpeechConstant.ENGINE_TYPE, SpeechConstant.TYPE_CLOUD);
        // 设置返回结果格式
        mSpeak.setParameter(SpeechConstant.RESULT_TYPE, "json");

        // 设置语言
        Log.e(TAG, "language:" + "en_us");
        mSpeak.setParameter(SpeechConstant.LANGUAGE, "en_us");
        // 设置语言区域
        String lag = mSharedPreferences.getString("iat_language_preference",
                "mandarin");
        mSpeak.setParameter(SpeechConstant.ACCENT, lag);
        Log.e(TAG, "last language:" + mSpeak.getParameter(SpeechConstant.LANGUAGE));


        mSpeak.setParameter(SpeechConstant.VAD_BOS, mSharedPreferences.getString("iat_vadbos_preference", "5000"));
        mSpeak.setParameter(SpeechConstant.VAD_EOS, mSharedPreferences.getString("iat_vadeos_preference", "1000"));
        mSpeak.setParameter(SpeechConstant.ASR_PTT,"0");
    }

    String[] words={};
    private RecognizerDialogListener recognizerDialogListener = new RecognizerDialogListener() {
        @Override
        public void onResult(RecognizerResult results, boolean isLast) {
            String json = JsonParser.parseIatResult(results.getResultString());
            String sn = null;
            // 读取json结果中的sn字段
            JSONObject resultJson = null;
            try {
                resultJson = new JSONObject(results.getResultString());
            } catch (JSONException e) {
                e.printStackTrace();
            }

            sn = resultJson.optString("sn");
            mSpeakResults.put(sn, json);

            StringBuffer resultBuffer = new StringBuffer();
            for (String key : mSpeakResults.keySet()) {
                resultBuffer.append(mSpeakResults.get(key));
            }

            String result=bufferDeal(resultBuffer.toString());
            words=filterString(result);
            System.out.println(result);


            speakTitle.setText("语音识别内容：\n"+resultBuffer.toString());
            SpeakResult.setText("分离单词：\n"+mergeStringList(words));
        }
        @Override
        public void onError(SpeechError error) {
            ;
        }







    };

    public String mergeStringList(String[] strings){
        StringBuffer stringBuffer=new StringBuffer("");
        for(int i=0;i<strings.length;++i){
            if(i!=0){
                stringBuffer.append("\n");
            }
            stringBuffer.append(strings[i]);
        }
        return stringBuffer.toString();
    }
    public String[] filterString(String s){
        String[] strings=s.split("\n");

        Set<String> stringSet= new TreeSet<>();
        for(int i=0;i<strings.length;++i){
            stringSet.add(strings[i]);
        }
        return stringSet.toArray(new String[stringSet.size()]);

    }
    public String bufferDeal(String s){
        String t0=s.toLowerCase();
        String t=t0.replace('.',' ');
        String t2=t.replace(',',' ');
        String t3=t2.replace(' ','\n');
        return t3;

    }
    class myClick implements View.OnClickListener{
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.start_button:
                    mSpeakResults.clear();
                    //设置参数
                    setParam();
                    //监听器
                    mSpeakDialog.setListener(recognizerDialogListener);
                    mSpeakDialog.show();
                    break;
                case R.id.layout_speak_bottom:
                    if(words.length==0){
                        Toast.makeText(getApplicationContext(), "单词列表为空，请先进行语音识别", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        DataBaseController dataBaseController=new DataBaseController(SpeechConstantActivity.this);
                        List<Word> wordList=dataBaseController.getWordsByWord(words);
                        if(wordList.size()==0){
                            Toast.makeText(getApplicationContext(), "有效单词列表为空，请先进行语音识别", Toast.LENGTH_SHORT).show();
                        }
                        else{
                            Intent intent=new Intent(SpeechConstantActivity.this,SpeechDetailActivity.class);
                            Bundle bundle=new Bundle();
                            WordSpeechList wordSpeechList=new WordSpeechList(wordList);
                            bundle.putSerializable("wordSpeechList",wordSpeechList);
                            intent.putExtras(bundle);
                            startActivity(intent);
                        }

                    }
                    break;

            }

        }
    }
}
