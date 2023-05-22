package com.example.englishlearning;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.englishlearning.Controller.TimeController;
import com.example.englishlearning.DB.DataBaseController;
import com.example.englishlearning.enity.Word;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.github.mikephil.charting.interfaces.datasets.IBarDataSet;

import java.util.ArrayList;
import java.util.List;

public class CharActivity extends Activity {

    BarChart wordBarChar;
    private ArrayList<BarEntry> learnData;
    private ArrayList<BarEntry> reviewData;

    private ImageView returnHome;
    private Integer todayLearnWord,todayReviewWord;

    private TextView text_todayLearn,text_todayReview;
    private void initialize(){
        wordBarChar=findViewById(R.id.chart_word);
        returnHome=findViewById(R.id.icon_home_black);
        text_todayLearn=findViewById(R.id.text_chart_todayLearn);
        text_todayReview=findViewById(R.id.text_chart_todayReview);


        returnHome.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(CharActivity.this,MainActivity.class));
            }
        });
        learnData=new ArrayList<>();
        reviewData=new ArrayList<>();



    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chart);


        initialize();
        initBarChart();

        loadWordData();

        configBarWordDate();

    }
    private void loadWordData() {
        DataBaseController dataBaseController=new DataBaseController(CharActivity.this);
        learnData.add(new BarEntry(1,dataBaseController.getLearnWordDate(TimeController.getPastDateWithYear(5))));
        learnData.add(new BarEntry(2,dataBaseController.getLearnWordDate(TimeController.getPastDateWithYear(4))));
        learnData.add(new BarEntry(3,dataBaseController.getLearnWordDate(TimeController.getPastDateWithYear(3))));
        learnData.add(new BarEntry(4,dataBaseController.getLearnWordDate(TimeController.getPastDateWithYear(2))));
        learnData.add(new BarEntry(5,dataBaseController.getLearnWordDate(TimeController.getPastDateWithYear(1))));
        todayLearnWord = dataBaseController.getLearnWordDate(TimeController.getPastDateWithYear(0));
        learnData.add(new BarEntry(6, todayLearnWord));
        text_todayLearn.setText(todayLearnWord.toString());

        reviewData.add(new BarEntry(1,dataBaseController.getReviewWordDate(TimeController.getPastDateWithYear(5))));
        reviewData.add(new BarEntry(2,dataBaseController.getReviewWordDate(TimeController.getPastDateWithYear(4))));
        reviewData.add(new BarEntry(3,dataBaseController.getReviewWordDate(TimeController.getPastDateWithYear(3))));
        reviewData.add(new BarEntry(4,dataBaseController.getReviewWordDate(TimeController.getPastDateWithYear(2))));
        reviewData.add(new BarEntry(5,dataBaseController.getReviewWordDate(TimeController.getPastDateWithYear(1))));
        todayReviewWord = dataBaseController.getReviewWordDate(TimeController.getPastDateWithYear(0));
        reviewData.add(new BarEntry(6, todayReviewWord));
        text_todayReview.setText(todayReviewWord.toString());
    }
    private void configBarWordDate(){
        BarDataSet barDataSet1 = new BarDataSet(learnData, "当日学习");
        barDataSet1.setColor(Color.parseColor("#4cb4e7"));
        BarDataSet barDataSet2 = new BarDataSet(reviewData, "当日复习");
        barDataSet2.setColor(Color.parseColor("#1F6FB5"));
        ArrayList<IBarDataSet> iBarDataSets = new ArrayList<>();

        iBarDataSets.add(barDataSet1);
        iBarDataSets.add(barDataSet2);


        BarData barData = new BarData(iBarDataSets);
        barData.setValueFormatter(new ValueFormatter() {
            @Override
            public String getFormattedValue(float value) {
                return (int) value + "";
            }
        });

        barData.setDrawValues(true);//是否显示柱子的数值
        barData.setValueTextSize(10f);//柱子上面标注的数值的字体大小
        barData.setBarWidth(0.3f);//每个柱子的宽度
        wordBarChar.setData(barData);
        wordBarChar.invalidate();
        //如果不设置组直接的距离的话，那么两个柱子会公用一个空间，即发生重叠；另外，设置了各种距离之后，X轴方向会自动调整距离，以保持“两端对齐”
        wordBarChar.groupBars(0.45f/*从X轴哪个位置开始显示，这个参数具体啥意思。。。*/, 0.32f/*组与组之间的距离*/, 0.05f/*组中每个柱子之间的距离*/);

    }

    private void initBarChart() {
        wordBarChar.setDrawBarShadow(false);
        wordBarChar.setDrawValueAboveBar(true);
        wordBarChar.getDescription().setEnabled(false);
        Legend legend = wordBarChar.getLegend();

        legend.setForm(Legend.LegendForm.CIRCLE);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);

        // 集双指缩放
        wordBarChar.setPinchZoom(false);

        // 动画
        wordBarChar.animateY(2000);


        XAxis xAxis = wordBarChar.getXAxis();

        xAxis.setDrawLabels(true);//是否显示x坐标的数据
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);//设置x坐标数据的位置
        xAxis.setDrawGridLines(false);//是否显示网格线中与x轴垂直的网格线

        final List<String> xValue = new ArrayList<>();
        xValue.add("zero");//index = 0 的位置的数据是否显示，跟barChart.groupBars中的第一个参数有关。
        xValue.add(TimeController.getPastDate(5));
        xValue.add(TimeController.getPastDate(4));
        xValue.add(TimeController.getPastDate(3));
        xValue.add(TimeController.getPastDate(2));
        xValue.add(TimeController.getPastDate(1));
        xValue.add(TimeController.getPastDate(0));
        xAxis.setValueFormatter(new IndexAxisValueFormatter(xValue));//设置x轴标签格式化器

        YAxis rightYAxis = wordBarChar.getAxisRight();

        rightYAxis.setDrawGridLines(false);

        rightYAxis.setEnabled(true);//设置右侧的y轴是否显示。包括y轴的那一条线和上面的标签都不显示
        rightYAxis.setDrawLabels(false);//设置y轴右侧的标签是否显示。只是控制y轴处的标签。控制不了那根线。
        rightYAxis.setDrawAxisLine(false);//这个方法就是专门控制坐标轴线的

        YAxis leftYAxis = wordBarChar.getAxisLeft();

        leftYAxis.setEnabled(true);

        leftYAxis.setDrawLabels(true);

        leftYAxis.setDrawAxisLine(true);

        leftYAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);

        leftYAxis.setDrawGridLines(false);//只有左右y轴标签都设置不显示水平网格线，图形才不会显示网格线
        leftYAxis.setDrawGridLinesBehindData(true);//设置网格线是在柱子的上层还是下一层（类似Photoshop的层级）
        leftYAxis.setGranularity(1f);//设置最小的间隔，防止出现重复的标签。这个得自己尝试一下就知道了。
        leftYAxis.setAxisMinimum(0);//设置左轴最小值的数值。如果IndexAxisValueFormatter自定义了字符串的话，那么就是从序号为2的字符串开始取值。
        leftYAxis.setSpaceBottom(0);//左轴的最小值默认占有10dp的高度，如果左轴最小值为0，一般会去除0的那部分高度
    }
}
