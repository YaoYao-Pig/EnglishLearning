package com.example.englishlearning.fragment;

import androidx.appcompat.app.AppCompatDelegate;
import androidx.lifecycle.ViewModelProvider;

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
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Switch;
import android.widget.TextView;

import com.example.englishlearning.AboutActivity;
import com.example.englishlearning.CalenderActivity;
import com.example.englishlearning.CharActivity;
import com.example.englishlearning.DB.ConfigData;
import com.example.englishlearning.DB.DataBaseController;
import com.example.englishlearning.MainPageActivity;
import com.example.englishlearning.R;
import com.example.englishlearning.ReviewActivity;
import com.example.englishlearning.SearchActivity;
import com.example.englishlearning.SelectBookActivity;
import com.example.englishlearning.SpeechConstantActivity;
import com.example.englishlearning.WordActivity;
import com.example.englishlearning.WordStarActivity;
import com.example.englishlearning.enity.MyDate;
import com.example.englishlearning.enity.User;

import java.util.List;

public class MeFragment extends Fragment {

    private MeViewModel mViewModel;

    LinearLayout select;

    ImageView analyseButton;
    ImageView calendarButton;
    private TextView consistDay;
    TextView studyWordNumber;
    TextView coin;
    TextView username;
    Switch switchNight;
    User currentUser;
    DataBaseController dataBaseController;
    RelativeLayout about;

    private void initialize(){


        select=getActivity().findViewById(R.id.selectBookButton);
        consistDay=getActivity().findViewById(R.id.text_me_days);
        studyWordNumber=getActivity().findViewById(R.id.text_me_words);
        coin=getActivity().findViewById(R.id.text_me_money);
        username=getActivity().findViewById(R.id.text_me_name);
        switchNight=getActivity().findViewById(R.id.switch_night);
        analyseButton=getActivity().findViewById(R.id.img_me_analyse);
        calendarButton=getActivity().findViewById(R.id.img_me_calendar);
        about=getActivity().findViewById(R.id.layout_me_about);

        dataBaseController=new DataBaseController(getActivity());


        currentUser=dataBaseController.getCurrentUser();
        List<MyDate> myDateList=dataBaseController.getHasLearnDateList();

        consistDay.setText(new Integer(myDateList.size()).toString());
        studyWordNumber.setText(currentUser.getHas_learn_word().toString());
        coin.setText(currentUser.getCoin().toString());
        username.setText(currentUser.getUsername());





        select.setOnClickListener(new myClick());
        analyseButton.setOnClickListener(new myClick());
        calendarButton.setOnClickListener(new myClick());
        about.setOnClickListener(new myClick());


        boolean isNight= ConfigData.getIsNight();
        if(isNight){
            switchNight.setChecked(true);
        }
        else{
            switchNight.setChecked(false);

        }

        switchNight.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES);
                    ConfigData.setIsNight(true);
                } else {
                    AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
                    ConfigData.setIsNight(false);
                }

                getActivity().recreate();
            }
        });

    }



    public static MeFragment newInstance() {
        return new MeFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {


        return inflater.inflate(R.layout.fragment_me, container, false);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        mViewModel = new ViewModelProvider(this).get(MeViewModel.class);
        // TODO: Use the ViewModel
        initialize();

    }


    private void changePage(Context packageContext, Class<?> cls){
        Intent intent=new Intent(packageContext, cls);
        startActivity(intent);
        getActivity().finish();
    }


    class myClick implements View.OnClickListener{

        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.selectBookButton:
                    changePage(getActivity(), SelectBookActivity.class);
                    break;
                case R.id.img_me_analyse:
                    changePage(getActivity(), CharActivity.class);
                    break;
                case R.id.img_me_calendar:
                    changePage(getActivity(), CalenderActivity.class);
                    break;
                case R.id.layout_me_about:
                    changePage(getActivity(), AboutActivity.class);
            }
        }
    }

}