package com.example.englishlearning;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.Toast;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.englishlearning.Bean.WordListBean;
import com.example.englishlearning.DB.DataBaseController;
import com.example.englishlearning.adpater.WordStarAdapter;
import com.example.englishlearning.enity.ItemDecor;
import com.example.englishlearning.enity.Word;

import java.util.ArrayList;
import java.util.List;


public class WordStarActivity extends Activity {

    private List<WordListBean> wordListBeanList;
    private WordStarAdapter wordStarAdapter;
    private RecyclerView recyclerView;
    private DataBaseController dataBaseController;
    private ImageView returnButton;
    private static final String TAG = "WordStarActivity";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list);




        initData();

        setListener();
        //initialize();
    }

    private void initData() {
        dataBaseController=new DataBaseController(WordStarActivity.this);
        wordListBeanList=dataBaseController.getStarWordBean();
        recyclerView=findViewById(R.id.recycler_wf);
        returnButton=(ImageView) findViewById(R.id.star_return);


        wordStarAdapter=new WordStarAdapter(wordListBeanList);
        //关联adapter和布局manager，添加item之间间距
        LinearLayoutManager layoutManager=new LinearLayoutManager(this);


        recyclerView.addItemDecoration(new ItemDecor(3));
        recyclerView.setAdapter(wordStarAdapter);
        recyclerView.setLayoutManager(layoutManager);
        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(WordStarActivity.this,MainActivity.class);
                startActivity(intent);
            }
        });


    }

    private void setListener() {
        //set监听
        wordStarAdapter.setOnClickListener(new WordStarAdapter.OnClickListener() {
            @Override
            public void onClick(View v, int position) {
                Log.i(TAG, "点击---position = " + position);
                Intent intent=new Intent(WordStarActivity.this,WordStarDetailActivity.class);


                Word word=dataBaseController.getWordById(wordListBeanList.get(position).getWordId());

                Bundle bundle=new Bundle();
                bundle.putSerializable("word",word);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
        wordStarAdapter.setOnLongClickListener(new WordStarAdapter.OnLongClickListener() {
            @Override
            public void onLongClick(View v, int position) {
                Log.i(TAG, "长按---position = " + position);

            }
        });
    }




}
