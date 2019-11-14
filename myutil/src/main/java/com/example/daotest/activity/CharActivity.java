package com.example.daotest.activity;

import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.example.daotest.R;
import com.example.daotest.adapter.MyAxisValueFormatter;
import com.example.daotest.adapter.XAxisValueFormatter;
import com.example.daotest.wight.XYMarkerView;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.charts.HorizontalBarChart;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.components.LimitLine;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.components.YAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.listener.OnChartValueSelectedListener;

import java.lang.reflect.Type;

/**
 * @author tian on 2019/10/17
 */
public class CharActivity extends AppCompatActivity implements OnChartValueSelectedListener {

    /**
     * 表格数据
     */
    private String[] mMonths = new String[]{
            "一月","二月","三月","四月","五月","六月","七月","八月","九月","十月","十一月","十二月"};
    private String[] mParties = new String[]{
            "Party A", "Party B", "Party C", "Party D", "Party E", "Party F", "Party G", "Party H",
            "Party I", "Party J", "Party K", "Party L", "Party M", "Party N", "Party O", "Party P",
            "Party Q", "Party R", "Party S", "Party T", "Party U", "Party V", "Party W", "Party X",
            "Party Y", "Party Z"
    };

    private Typeface mTfRegular;
    private Typeface mTfLight;
    protected BarChart mChart;
    private HorizontalBarChart hBarChart;
    private LineChart lineChart;

    /**
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_char);
        mTfRegular = Typeface.createFromAsset(getAssets(),"OpenSans-Regular.ttf");
        mTfLight = Typeface.createFromAsset(getAssets(),"OpenSans-Light.ttf");

        mChart =  findViewById(R.id.chart1);
        hBarChart = findViewById(R.id.hBarChart);
        lineChart = findViewById(R.id.lineChart);

        initBarChart();
        initHBarChart();
        initLineChart();

    }


    /**
     * 初始化柱形图控件属性
     */
    private void initBarChart() {

        mChart.setOnChartValueSelectedListener(this);

        mChart.setDrawBarShadow(false);
        mChart.setDrawValueAboveBar(true);
        mChart.getDescription().setEnabled(false);

        //if more than 60 entries are displayed in the chart, no values will be drawn
        mChart.setMaxVisibleValueCount(60);
        //scalling can now only be done on x- and y-axis separately
        mChart.setPinchZoom(false);
        mChart.setDrawGridBackground(false);
        //自定义坐标轴适配器，配置在X轴，xAxis.setValueFormatter(xAxisFormatter);
        IAxisValueFormatter  xAxisFormatter = new XAxisValueFormatter();

        XAxis xAxis = mChart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        //设置字体  可以去掉,没什么用
        xAxis.setTypeface(mTfLight);
        xAxis.setDrawAxisLine(false);
        xAxis.setGranularity(1f);
        xAxis.setValueFormatter(xAxisFormatter);

        //自定义坐标轴适配器，配置在Y轴。leftAxis.setValueFormatter(custom)
        IAxisValueFormatter custom = new MyAxisValueFormatter();

        //设置限制临界点
        LimitLine limitLine = new LimitLine(3f, "临界点");
        limitLine.setLineColor(Color.GREEN);
        limitLine.setLineWidth(1f);
        limitLine.setTextColor(Color.GREEN);

        //获取到图形左边的Y轴
        YAxis leftAxis = mChart.getAxisLeft();
        leftAxis.addLimitLine(limitLine);
        leftAxis.setTypeface(mTfLight);
        leftAxis.setLabelCount(8, false);
        leftAxis.setValueFormatter(custom);
        leftAxis.setPosition(YAxis.YAxisLabelPosition.OUTSIDE_CHART);
        leftAxis.setSpaceTop(15f);
        leftAxis.setAxisMinimum(0f);

        //获取到图形右边的Y轴, 并设置为不显示
        mChart.getAxisRight().setEnabled(false);

        //图例设置
        Legend legend = mChart.getLegend();
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.BOTTOM);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.LEFT);
        legend.setOrientation(Legend.LegendOrientation.HORIZONTAL);
        legend.setDrawInside(false);
        legend.setForm(Legend.LegendForm.SQUARE);
        legend.setFormSize(9f);
        legend.setTextSize(11f);
        legend.setXEntrySpace(4f);

        //如果点击柱形图,会弹出pop提示框,XYMarkerView为自定义弹出框
        XYMarkerView mv = new XYMarkerView(this, xAxisFormatter);




    }

    private void initHBarChart() {
    }


    private void initLineChart() {

    }



    @Override
    public void onValueSelected(Entry e, Highlight h) {

    }

    @Override
    public void onNothingSelected() {

    }
}
