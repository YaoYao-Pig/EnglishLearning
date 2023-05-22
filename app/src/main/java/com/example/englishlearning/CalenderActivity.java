package com.example.englishlearning;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

import com.example.englishlearning.DB.DataBaseController;
import com.example.englishlearning.enity.MyDate;
import com.example.englishlearning.util.NormalSpan;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;

import java.util.Calendar;
import java.util.List;

public class CalenderActivity extends Activity {



    private MaterialCalendarView materialCalendarView;
    private ImageView returnButton;

    private void initialize(){
        materialCalendarView=findViewById(R.id.calendar);
        returnButton=findViewById(R.id.calender_returnHome);
        initCalender();

        returnButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CalenderActivity.this,MainActivity.class));
            }
        });
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calendar);


        initialize();
    }

    private void initCalender(){
        Calendar calendar = Calendar.getInstance();
        int currentYear = calendar.get(Calendar.YEAR);
        int currentMonth = calendar.get(Calendar.MONTH);
        int currentDate = calendar.get(Calendar.DAY_OF_MONTH);

        materialCalendarView.setDateSelected(CalendarDay.from(currentYear, currentMonth, currentDate), true);
        materialCalendarView.setWeekDayLabels(new String[]{"SUN", "MON", "TUS", "WED", "THU", "FRI", "SAT"});
        materialCalendarView.setSelectionMode(MaterialCalendarView.SELECTION_MODE_SINGLE);


        DataBaseController dataBaseController=new DataBaseController(CalenderActivity.this);
        List<MyDate> myDateList=dataBaseController.getHasLearnDateList();
        materialCalendarView.addDecorator(new DayViewDecorator() {
            @Override
            public boolean shouldDecorate(CalendarDay day) {
                for (MyDate myDate : myDateList) {
                    if (day.getDay() == myDate.getDate() && day.getMonth() == (myDate.getMonth() - 1) && day.getYear() == myDate.getYear())
                        return true;
                }
                return false;
            }

            @Override
            public void decorate(DayViewFacade view) {
                view.addSpan(new NormalSpan());
            }
        });
    }


}
