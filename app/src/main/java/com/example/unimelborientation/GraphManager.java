package com.example.unimelborientation;

import android.graphics.Color;
import android.graphics.Typeface;

import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.data.BarData;
import com.github.mikephil.charting.data.BarDataSet;
import com.github.mikephil.charting.data.BarEntry;

import java.util.List;

public class GraphManager {
    public BarChart barChart;

    public GraphManager(BarChart barChart) {
        this.barChart = barChart;
        initBarChart();
    }

    private void initBarChart() {

        barChart.getDescription().setEnabled(false);
        barChart.setExtraOffsets(20, 8, 75, 8);
        barChart.setBackgroundColor(Color.TRANSPARENT);
        barChart.setDragDecelerationFrictionCoef(0.75f);

    }


    /**
     * @param yvals
     * @param colors
     */
    public void showSolidBarChart(List<BarEntry> yvals, List<Integer> colors) {

        BarDataSet dataset = new BarDataSet(yvals, "");

        dataset.setColors(colors);

        dataset.setDrawValues(true);

        dataset.setValueTextSize(10);

        dataset.setValueTextColor(Color.DKGRAY);

        dataset.setValueTypeface(Typeface.DEFAULT_BOLD);

        BarData barData = new BarData(dataset);

        barChart.setData(barData);
    }
}
