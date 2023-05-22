package com.example.englishlearning;

import android.app.Activity;
import android.os.Bundle;
import android.widget.Toast;

import com.baidu.ocr.sdk.OCR;
import com.baidu.ocr.sdk.OnResultListener;
import com.baidu.ocr.sdk.exception.OCRError;
import com.baidu.ocr.sdk.model.AccessToken;

public class OCRActivity extends Activity {
    private boolean hasGotToken = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chatgpt);
        initAccessTokenWithAkSk();
        checkTokenStatus();

    }

    private boolean checkTokenStatus() {
        if (!hasGotToken) {
            Toast.makeText(getApplicationContext(), "token未获取到", Toast.LENGTH_SHORT).show();
        }
        else{
            System.out.println("yes yes");
        }
        return hasGotToken;
    }

    /**
     * 使用明文方式初始化token
     */
    private void initAccessToken() {
        OCR.getInstance(OCRActivity.this).initAccessToken(new OnResultListener<AccessToken>() {
            @Override
            public void onResult(AccessToken accessToken) {
                String token = accessToken.getAccessToken();
                hasGotToken = true;
            }

            @Override
            public void onError(OCRError error) {
                hasGotToken=false;
                error.printStackTrace();
                System.out.println("licence方式获取token失败"+error.getMessage());
            }
        }, getApplicationContext());
    }

    private void initAccessTokenWithAkSk() {
        OCR.getInstance(getApplicationContext()).initAccessTokenWithAkSk(new OnResultListener<AccessToken>() {
            @Override
            public void onResult(AccessToken accessToken) {
                String token = accessToken.getAccessToken();
                hasGotToken = true;
            }

            @Override
            public void onError(OCRError error) {
                hasGotToken=false;
                error.printStackTrace();
                System.out.println("111方式获取token失败"+error.getMessage());
            }
        }, getApplicationContext(),
                "chtk1rwTgPrBTSogOfhfoQcF","GqGm70EuDRSXA0q4z5TablVPRc3Kcusb");
    }
}
