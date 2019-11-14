package com.example.daotest.adapter;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

import java.text.DecimalFormat;

/**
 * @author tian on 2019/10/18
 */
public class MyAxisValueFormatter implements IAxisValueFormatter {

    private DecimalFormat mFormat;

    public MyAxisValueFormatter() {
        mFormat = new DecimalFormat("###,###,###,##0.000");
    }

    @Override
    public String getFormattedValue(float value, AxisBase axis) {
        return mFormat.format(value) + " $";
    }
}
