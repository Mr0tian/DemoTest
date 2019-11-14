package com.example.daotest.wight;

import android.content.Context;
import android.widget.TextView;

import com.example.daotest.R;
import com.github.mikephil.charting.components.MarkerView;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.formatter.IAxisValueFormatter;
import com.github.mikephil.charting.highlight.Highlight;
import com.github.mikephil.charting.utils.MPPointF;

import java.text.DecimalFormat;

/**
 * @author tian on 2019/10/18
 */
public class XYMarkerView extends MarkerView {

    private TextView tvContent;
    private IAxisValueFormatter xAxisValueFormatter;
    private DecimalFormat format;


    /**
     * Constructor. Sets up the MarkerView with a custom layout resource.
     *
     * @param context
     * @param xAxisValueFormatter the layout resource to use for the MarkerView
     */
    public XYMarkerView(Context context, IAxisValueFormatter  xAxisValueFormatter) {
        super(context, R.layout.custom_marker_view);
        this.xAxisValueFormatter = xAxisValueFormatter;
        tvContent = findViewById(R.id.tvContent);
        format = new DecimalFormat("###.000");

    }

    @Override
    public void refreshContent(Entry e, Highlight highlight) {
        tvContent.setText("x：" + xAxisValueFormatter.getFormattedValue(e.getX(), null) + "，y：" + format.format(e.getY()));
        super.refreshContent(e, highlight);
    }

    @Override
    public MPPointF getOffset() {
        return new MPPointF(-(getWidth() / 2), -getHeight());
    }



}
