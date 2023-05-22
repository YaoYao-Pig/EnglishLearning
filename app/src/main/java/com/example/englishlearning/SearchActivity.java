package com.example.englishlearning;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
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


public class SearchActivity extends Activity {

    EditText searchText;
    LinearLayout emptyImage;
    WordStarAdapter wordStarAdapter;
    TextView cancel;
    List<Word> words;
    List<WordListBean> wordListBeanList;
    DataBaseController dataBaseController;
    RecyclerView recyclerView;
    private static final String TAG = "SearchActivity";



    private void initialize(){


        searchText=findViewById(R.id.edit_search);
        emptyImage=findViewById(R.id.no_word_layout);
        cancel=findViewById(R.id.text_search_cancel);
        recyclerView=findViewById(R.id.recycler_search);
        wordListBeanList=new ArrayList<>();
        words=new ArrayList<>();



    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);

        initialize();

        searchText.setOnKeyListener(new View.OnKeyListener(){

            @Override
            public boolean onKey(View view, int keyCode, KeyEvent keyEvent) {
                if(keyCode==KeyEvent.KEYCODE_ENTER){
                    try {
                        words=searchText();
                        dealDate();
                        return true;
                    } catch (Exception e) {
                        Toast.makeText(SearchActivity.this,e.getMessage(), Toast.LENGTH_SHORT).show();
                    }

                }

                return false;
            }
        });


    }

    public List<Word> searchText() throws Exception {
        dataBaseController=new DataBaseController(SearchActivity.this);
        String search_text=searchText.getText().toString();
        if(search_text==null||search_text.isEmpty()){

            throw new Exception("单词输入为空");
        }
        return dataBaseController.searchWordByWord(search_text);
    }

    public void dealDate(){
        int len=words.size();
        if(len==0){
            emptyImage.setVisibility(View.VISIBLE);
        }
        else{
            for (int i=0;i<len;++i){
                Word word= words.get(i);
                wordListBeanList.add(new WordListBean(word.getWord_id(),
                        word.getWord(),
                        word.getTrans().get(0).getTranCn()));
            }

            wordStarAdapter=new WordStarAdapter(wordListBeanList);
            LinearLayoutManager layoutManager=new LinearLayoutManager(this);

            recyclerView.addItemDecoration(new ItemDecor(3));
            recyclerView.setAdapter(wordStarAdapter);
            recyclerView.setLayoutManager(layoutManager);
            setListener();

        }


    }

    private void setListener() {
        //set监听
        wordStarAdapter.setOnClickListener(new WordStarAdapter.OnClickListener() {
            @Override
            public void onClick(View v, int position) {
                Log.i(TAG, "点击---position = " + position);
                Intent intent=new Intent(SearchActivity.this,SearchDetailActivity.class);


                Word word=words.get(position);

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
