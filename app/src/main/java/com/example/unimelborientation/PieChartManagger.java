package com.example.unimelborientation;
import android.graphics.Color;
import android.graphics.Typeface;

import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.components.Legend;
import com.github.mikephil.charting.data.PieData;
import com.github.mikephil.charting.data.PieDataSet;
import com.github.mikephil.charting.data.PieEntry;
import com.github.mikephil.charting.formatter.PercentFormatter;

import java.util.List;

public class PieChartManagger {
    public PieChart pieChart;

    public PieChartManagger(PieChart pieChart) {
        this.pieChart = pieChart;
        initPieChart();
    }


    private void initPieChart() {

        pieChart.setDrawHoleEnabled(false);
        pieChart.setHoleRadius(40f);

        pieChart.setTransparentCircleRadius(30f);
        pieChart.setTransparentCircleColor(Color.WHITE);
        pieChart.setTransparentCircleAlpha(125);



        pieChart.setRotationAngle(0);
        pieChart.setRotationEnabled(true);
        pieChart.setUsePercentValues(true);
        pieChart.getDescription().setEnabled(false);


        pieChart.setDrawEntryLabels(false);
        pieChart.setEntryLabelColor(Color.BLACK);
        pieChart.setEntryLabelTextSize(14);
        pieChart.setEntryLabelTypeface(Typeface.DEFAULT_BOLD);


        pieChart.setExtraOffsets(20, 8, 75, 8);
        pieChart.setBackgroundColor(Color.TRANSPARENT);
        pieChart.setDragDecelerationFrictionCoef(0.75f);


        Legend legend = pieChart.getLegend();
        legend.setOrientation(Legend.LegendOrientation.VERTICAL);
        legend.setVerticalAlignment(Legend.LegendVerticalAlignment.TOP);
        legend.setHorizontalAlignment(Legend.LegendHorizontalAlignment.RIGHT);

        legend.setXEntrySpace(7f);
        legend.setYEntrySpace(10f);
        legend.setYOffset(10f);
        legend.setXOffset(10f);
        legend.setTextColor(Color.DKGRAY);
        legend.setTextSize(13);

    }


    /**
     * @param yvals
     * @param colors
     */
    public void showSolidPieChart(List<PieEntry> yvals, List<Integer> colors) {

        PieDataSet dataset = new PieDataSet(yvals, "");
        dataset.setColors(colors);
        dataset.setDrawValues(true);
        dataset.setValueTextSize(10);
        dataset.setValueTextColor(Color.DKGRAY);
        dataset.setValueTypeface(Typeface.DEFAULT_BOLD);


        dataset.setValueLinePart1Length(0.4f);
        dataset.setValueLinePart2Length(0.4f);
        dataset.setValueLinePart1OffsetPercentage(80f);
        dataset.setValueLineColor(Color.DKGRAY);
        dataset.setYValuePosition(PieDataSet.ValuePosition.OUTSIDE_SLICE);
        dataset.setUsingSliceColorAsValueLineColor(false);
        dataset.setSliceSpace(0);


        dataset.setSelectionShift(5f);

        PieData pieData = new PieData(dataset);

        pieData.setValueFormatter(new PercentFormatter());

        pieChart.setData(pieData);
    }

}
