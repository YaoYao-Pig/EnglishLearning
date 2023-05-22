package com.example.englishlearning.fragment;

import static com.example.englishlearning.UIController.textFormatPs;
import static com.example.englishlearning.UIController.textFormat_pos;
import static com.example.englishlearning.UIController.textFormat_sentence;

import androidx.lifecycle.ViewModelProvider;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.englishlearning.DB.DataBaseController;
import com.example.englishlearning.MainPageActivity;
import com.example.englishlearning.R;
import com.example.englishlearning.ReviewActivity;
import com.example.englishlearning.SearchActivity;
import com.example.englishlearning.SelectBookActivity;
import com.example.englishlearning.SpeechConstantActivity;
import com.example.englishlearning.WordActivity;
import com.example.englishlearning.WordStarActivity;
import com.example.englishlearning.enity.Word;

import java.text.SimpleDateFormat;
import java.util.Date;

public class WordFragment extends Fragment {

    TextView learn;


    RelativeLayout starNoteButton;


    private WordViewModel mViewModel;

    public static WordFragment newInstance() {
        return new WordFragment();
    }

    public Word randomWord;

    private TextView ran_word;
    private TextView ran_mean;
    private ImageView refreshButton;
    DataBaseController dataBaseController;
    private RelativeLayout search;

    TextView date,month;

    String[] months=new String[]{
            "JAN",
            "FEB",
            "MAR",
            "APR",
            "MAY",
            "JUN",
            "JUL",
            "SEP",
            "OCT",
            "NOV",
            "DEC"
    };

    private void initialize(){


        learn=getActivity().findViewById(R.id.frag_learnButton);

        starNoteButton= getActivity().findViewById(R.id.frag_starNoteButton);
        ran_word=getActivity().findViewById(R.id.text_main_show_word);
        ran_mean=getActivity().findViewById(R.id.text_main_show_word_mean);
        refreshButton=getActivity().findViewById(R.id.img_refresh);
        search=getActivity().findViewById(R.id.search_icon);
        date=getActivity().findViewById(R.id.text_main_date);
        month=getActivity().findViewById(R.id.text_main_month);


        dataBaseController=new DataBaseController(getActivity());


        learn.setOnClickListener(new myClick());

        starNoteButton.setOnClickListener(new myClick());

        refreshButton.setOnClickListener(new myClick());
        search.setOnClickListener(new myClick());


        setRandomWord();

        initDateFlag();




    }


    private void setRandomWord(){
        try {
            randomWord=dataBaseController.getRandomWord();
            ran_word.setText(randomWord.getWord());
            ran_mean.setText(textFormat_pos(randomWord.getPosToString(),randomWord.getTransToString()));
        }
        catch (Exception e){
            ran_word.setText("WordList is empty");
        }


    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        System.out.println("222");


        return inflater.inflate(R.layout.fragment_word, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(WordViewModel.class);

        // TODO: Use the ViewModel

        initialize();
    }

    private void changePage(Context packageContext, Class<?> cls){
        Intent intent=new Intent(packageContext, cls);
        startActivity(intent);
    }

    private String getFormatDate(Date date){
        SimpleDateFormat simpleDateFormat=new SimpleDateFormat("yyyy-MM-dd");
        return simpleDateFormat.format(date);
    }



    private void initDateFlag(){
        String[] tmp=getFormatDate(new Date()).split("-");
        date.setText(tmp[2]);
        month.setText(months[Integer.parseInt(tmp[1])]);
    }
    class myClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.frag_learnButton:

                    if(dataBaseController.isWordTableEmpty()){

                        Toast.makeText(getContext(),"单词列表为空，请先从“我”页面获取单词书", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        changePage(getActivity(), WordActivity.class);
                    }

                    break;
                case R.id.frag_starNoteButton:
                    changePage(getActivity(), WordStarActivity.class);
                    break;
                case R.id.img_refresh:
                    setRandomWord();
                    break;
                case R.id.search_icon:
                    changePage(getActivity(), SearchActivity.class);
                    break;


            }
        }
    }

}