package com.example.englishlearning;

import static com.example.englishlearning.Controller.WordPageController.dataBaseController;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.example.englishlearning.DB.DataBaseController;

import java.sql.Time;

public class WordBook_Detail extends Activity {
    private TextView booName;
    private TextView wordNumber;
    private ImageView cover;
    private RelativeLayout load;
    String filename;
    private LinearLayout barLayout;
    private ProgressBar progressBar;
    private TextView barTitle;
    private ImageView returnButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_plan);

        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        cover=findViewById(R.id.img_plan_book);
        wordNumber=findViewById(R.id.text_plan_num);
        booName=findViewById(R.id.text_plan_name);
        load=findViewById(R.id.layout_plan_change);
        barLayout=findViewById(R.id.linear_bar);
        progressBar=findViewById(R.id.progress_wait);
        barTitle=findViewById(R.id.barTitle);
        returnButton=findViewById(R.id.return_home);
        returnButton.setOnClickListener(new myClick());


        filename=bundle.getString("filename");

        cover.setImageDrawable( getResources().getDrawable(bundle.getInt("cover")));
        booName.setText(bundle.getString("name"));
        wordNumber.setText( "单词量："+new Integer(bundle.getInt("num")).toString() );


        load.setOnClickListener(new myClick());





    }

    class myClick implements View.OnClickListener{

        @Override
        public void onClick(View view) {
            DataBaseController dataBaseController=new DataBaseController(WordBook_Detail.this);
            switch (view.getId()){
                case R.id.layout_plan_change:
                    barLayout.setVisibility(View.VISIBLE);
                    start(progressBar);
                    break;
                case R.id.return_home:
                    startActivity(new Intent(WordBook_Detail.this,
                            MainActivity.class));
                    finish();

            }
        }
    }

    // 进度条开始变化的方法
    public void start(final ProgressBar progressBar) {

        // 耗时任务放在子线程种进行
        new Thread() {
            private int nowProgress=0;
            private int maxProgress;

            public void run() {
                dataBaseController.initializeWordList(0,filename);


                // 得到进度条当前的值
                nowProgress = progressBar.getProgress();
                // 得到进度条最大值
                maxProgress = progressBar.getMax();
                // 当当前进度小于最大进度值时
                while (nowProgress < maxProgress) {
                    nowProgress++;
                    progressBar.setProgress(nowProgress);


                    // 表示在UI线程种更新TextView因为子线程不能更新UI
                    runOnUiThread(new Runnable() {

                        @Override
                        public void run() {
                            // 设置TextView的内容
                            //textView.setText(nowProgress + "/" + maxProgress);
                        }
                    });
                    try {
                        // 延时模拟加载进度
                        Thread.sleep(50);

                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }
                barTitle.setText("数据准备完毕，可以开始学习啦！");
            };
        }.start();

    }

}
