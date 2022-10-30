package com.example.unimelborientation;

import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import com.example.unimelborientation.util.HttpClient;
import com.example.unimelborientation.util.SharedPreferencesUtils;
import com.github.mikephil.charting.charts.PieChart;
import com.github.mikephil.charting.data.PieEntry;
import com.loopj.android.http.JsonHttpResponseHandler;
import com.loopj.android.http.RequestParams;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.Header;

public class PieFragment extends Fragment {
//    float a,b,c,d,e;

    List<Float> list=new ArrayList<Float>();
    private List<String> labels = new ArrayList<>();
    private PieChart pieChart;
    private Button cb;
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                                @Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        View view = inflater.inflate(R.layout.pie_fragment, container, false);
        SharedPreferencesUtils local_setting = new SharedPreferencesUtils(getContext(), "setting");
        Boolean isLandlord = local_setting.getInt("landlord")==1;
        if(!isLandlord){
            return view;
        }
        pieChart = (PieChart) view.findViewById(R.id.pie_chart);
        initChartData();
        cb=(Button) view.findViewById(R.id.checkbill);
        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //查看最新的bill
                //获取数据库最新的账单的图片
            }
        });
//        initView();

        return view;
    }
    private void initView() {
        showhodlePieChart();
    }

    private void initChartData() {
        RequestParams params = new RequestParams();
        SharedPreferencesUtils local_setting = new SharedPreferencesUtils(getContext(), "setting");
        params.put("uid",local_setting.getInt("uid"));
        HttpClient.get("bill/searchPercentage",params,new JsonHttpResponseHandler() {
            @Override
            public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                showToast(responseString);
            }

            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                try {
                    if (response.get("code").equals("0")) {
                        JSONArray bill = response.getJSONArray("data");
                        for(int i =0;i<bill.length();i++){
                            JSONObject item = bill.getJSONObject(i);
                            float output = (float) item.getInt("percentage");
                            labels.add(item.getString("uname"));
                            list.add(output);
                        }
                        float[] m = new float[list.size()];
                        showhodlePieChart();
                    } else {
                        System.out.println(response);
                        showToast(response.toString());
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        });
    }


    public void showToast(String msg) {
        getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getContext(), msg, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void showhodlePieChart() {
        List<PieEntry> yvals = new ArrayList<>();
        for (int i=0;i<list.size();i++){
            yvals.add(new PieEntry(list.get(i), labels.get(i)));
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

        PieChartManagger pieChartManagger=new PieChartManagger(pieChart);
        pieChartManagger.showSolidPieChart(yvals,colors);
    }
}

