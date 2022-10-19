package com.example.unimelborientation;

import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.unimelborientation.util.HttpClient;
import com.github.mikephil.charting.charts.BarChart;
import com.github.mikephil.charting.components.AxisBase;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.BarEntry;
import com.github.mikephil.charting.formatter.ValueFormatter;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class GraphFragment extends Fragment  {
    private BarChart barChart;

//    private List<Float> usage = new ArrayList<Float>();
//    float[] x={3,7,5,9,8,10,3,23,10,2};

    private List<Float> u = new ArrayList<>();



    public static GraphFragment newInstance() {
        return new GraphFragment();
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.graph_fragment, container, false);
        barChart = (BarChart) view.findViewById(R.id.bar_charthome);
        u.add(10.0F);
        u.add(5.0F);
        u.add(7.0F);
        u.add(9.0F);
        u.add(2.0F);
        float[] m = new float[u.size()];
        showhodleBarChart();


//        for(int i=0;i<x.length;i++){
//            usage.add(x[i]);
//        }


//
//        for(int i = 0; i < usage.size();i++){
//            newv[i] = usage.get(i);
//        }


        setXAxis();
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
//        mViewModel = new ViewModelProvider(this).get(HomeViewModel.class);
//        // TODO: Use the ViewModel
    }



    private void setXAxis() {

        XAxis xAxis = barChart.getXAxis();

        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);

//        xAxis.setLabelRotationAngle(0);
//
//        xAxis.setAxisMinimum(-0.5f);
//
//        xAxis.setAxisMaximum(6.5f);
//        xAxis.setLabelCount(20);


        xAxis.setDrawGridLines(false);




        xAxis.setValueFormatter(new ValueFormatter() {
            @Override
            public String getAxisLabel(float value,AxisBase axis) {
//                for(int i=0;i<usage.size();i++) {
//                    if (value == i) {
//                        return "Day" + " " + (i + 1);
//                    }
//                }
                for(int i=0;i<u.size();i++) {
                    if (value == i) {
                        return "Day" + " " + (i + 1);
                    }
                }


                return "";
            }
        });

        xAxis.setYOffset(20f);

        xAxis.setTextSize(10f);

        xAxis.setTextColor(Color.DKGRAY);
        xAxis.setGranularity(1);
    }




    private void showhodleBarChart() {

        //------------------------------------------------------------
        HttpClient.get("subject/getLastSubjects", null, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    JSONArray data = (JSONArray) response.get("data");
                    for (int i =0; i<data.length(); i++){
                        JSONObject item = data.getJSONObject(i);
                        if(item.getString("name").equals("...")){
                            //u.add();
                        }
                        else if(item.getString("type").equals("lab")){

                        }
                    }

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                Log.d("getTrend", "onFailure: "+ responseString);
                Toast.makeText(getContext(),
                        responseString,
                        Toast.LENGTH_SHORT).show();
            }
        });


        //------------------------------------------------------------

        List<BarEntry> yvals = new ArrayList<>();
//        for(int i=0;i<x.length;i++){
//            yvals.add(new BarEntry(i,x[i]));
//        }
//        for(int i=0;i<usage.size();i++){
//            yvals.add(new BarEntry(i, usage.get(i)));
//        }
        for(int i=0;i<u.size();i++){
            yvals.add(new BarEntry(i, u.get(i)));
        }



        List<Integer> colors = new ArrayList<>();
        colors.add(Color.parseColor("#00c9b1"));
        colors.add(Color.parseColor("#005d6c"));
        colors.add(Color.parseColor("#8ef6e4"));
        colors.add(Color.parseColor("#ffa3ac"));
        colors.add(Color.parseColor("#BBB2DE"));
        colors.add(Color.parseColor("#9CCEDE"));
        colors.add(Color.parseColor("#DE8DAF"));
        colors.add(Color.parseColor("#DEC998"));
        colors.add(Color.parseColor("#ff7d52"));
        colors.add(Color.parseColor("#d452ff"));
        colors.add(Color.parseColor("#fac7fd"));
        colors.add(Color.parseColor("#0fa5a9"));
        colors.add(Color.parseColor("#d1e0fd"));





        GraphManager GraphChartManager=new GraphManager(barChart);
        GraphChartManager.showSolidBarChart(yvals,colors);
    }



}
