package com.example.englishlearning.fragment;

import static com.example.englishlearning.chatgpt.ChatGptController.generateText;

import androidx.lifecycle.ViewModelProvider;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.ui.AppBarConfiguration;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.example.englishlearning.ChatGPTActivity;
import com.example.englishlearning.DB.DataBaseController;
import com.example.englishlearning.MainPageActivity;
import com.example.englishlearning.R;
import com.example.englishlearning.ReviewActivity;
import com.example.englishlearning.SelectBookActivity;
import com.example.englishlearning.SpeechConstantActivity;
import com.example.englishlearning.WordActivity;
import com.example.englishlearning.WordStarActivity;
import com.example.englishlearning.enity.Word;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.io.IOException;
import java.util.List;

public class ReviewFragment extends Fragment {

    private ReviewViewModel mViewModel;


    RelativeLayout review;
    RelativeLayout chat;

    RelativeLayout speechButton;



    private void initialize(){
        review= getActivity().findViewById(R.id.reviewButton);
        speechButton=getActivity().findViewById(R.id.speechButton);
        chat=getActivity().findViewById(R.id.chatgpt);

        DataBaseController dataBaseController=new DataBaseController(getActivity());



        review.setOnClickListener(new myClick());
        speechButton.setOnClickListener(new myClick());
        chat.setOnClickListener(new myClick());



    }


    public static ReviewFragment newInstance() {
        return new ReviewFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        System.out.println("1111");
        return inflater.inflate(R.layout.fragment_review, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(ReviewViewModel.class);

        initialize();
        // TODO: Use the ViewModel
    }


    private void changePage(Context packageContext, Class<?> cls){
        Intent intent=new Intent(packageContext, cls);
        startActivity(intent);
    }



    class myClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){

                case R.id.reviewButton:
                    changePage(getActivity(), ReviewActivity.class);
                    break;

                case R.id.speechButton:
                    changePage(getActivity(), SpeechConstantActivity.class);
                    break;
                case R.id.chatgpt:
                    changePage(getActivity(), ChatGPTActivity.class);
                    break;


            }
        }
    }

}