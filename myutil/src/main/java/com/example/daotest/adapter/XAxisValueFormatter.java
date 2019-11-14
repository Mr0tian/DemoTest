package com.example.daotest.adapter;

import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;

/**
 * 柱形图的自定义的适配器
 * @author tian on 2019/10/17
 */

public class XAxisValueFormatter implements IAxisValueFormatter {

    private String[] xStrs = new String[]{"春","夏","秋","冬"};


    @Override
    public String getFormattedValue(float value, AxisBase axis) {

        int position = (int) value;
        if (position >= 4){
            position = 0;
        }
        return xStrs[position];

    }

}
