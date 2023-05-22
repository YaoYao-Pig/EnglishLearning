package com.example.englishlearning;

import android.graphics.Paint;
import android.util.TypedValue;
import android.widget.TextView;

import java.util.List;

public class UIController {
    public static void setTextViewSize(TextView tv){
        Paint testPaint=tv.getPaint();
        String text=tv.getText().toString();
        int textWidth=tv.getMeasuredWidth();
        if(textWidth>0){
            int availableWidth=154;
            float trySize=tv.getTextSize();
            testPaint.setTextSize(trySize);
            while ((testPaint.measureText(text)>availableWidth)){
                trySize-=2;
                tv.setTextSize(TypedValue.COMPLEX_UNIT_PX,trySize);
            }
            System.out.println(trySize);
            tv.setTextSize(trySize);
        }
    }

    public static String textFormat_pos(List<String> posToString, List<String> transToString) {
        String result="";
        int len=posToString.size();
        for(int i=0;i<len;++i){
            if(i!=0)
                result+='\n';
            result+=posToString.get(i)+'.'+transToString.get(i);

        }
        return result;
    }
    public static String textFormat_sentence(List<String> content,List<String> cn){
        String result="";
        int len=content.size();
        for(int i=0;i<len;++i){
            if(i!=0)
                result+='\n';
            result+=content.get(i)+'\n'+cn.get(i);

        }
        return result;
    }
    public static String textFormatPs(String ps){
        return new String("/"+ps+"/");
    }
}
