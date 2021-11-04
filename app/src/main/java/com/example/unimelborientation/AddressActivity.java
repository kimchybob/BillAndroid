package com.example.unimelborientation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unimelborientation.util.HttpClient;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import cz.msebera.android.httpclient.Header;

public class AddressActivity extends AppCompatActivity {
    private String subjectName = "null";
    private Map<String,String> lecture = new HashMap<>();
    private Map<String,String> tutorial = new HashMap<>();
    private int sid=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_address);
        sid = getIntent().getIntExtra("sid",0);
        subjectName = getIntent().getStringExtra("subject");
        TextView title = findViewById(R.id.subject_name);
        title.setText(subjectName);

        HttpClient.get("address/" + String.valueOf(sid), null, new JsonHttpResponseHandler(){
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        try{
                            System.out.println(response);
                            JSONArray data = (JSONArray) response.get("data");
                            if (data.length()==0){
                                System.out.println("this subject has no address");
                            }
                            for (int i =0; i<data.length(); i++){
                                JSONObject item = data.getJSONObject(i);
                                if(item.getString("type").equals("lec")){
                                    String address = item.getString("address") + ","+ item.getString("buildname");
                                    lecture.put(address, item.getString("latlng"));
                                }
                                else if(item.getString("type").equals("lab")){
                                    String address = item.getString("address") + ","+ item.getString("buildname");
                                    tutorial.put(address, item.getString("latlng"));
                                }
                            }
                            List<String> lectures = new ArrayList<>();
                            lectures.addAll(lecture.keySet());
                            List<String> tutorials = new ArrayList<>();
                            tutorials.addAll(tutorial.keySet());
                            ArrayAdapter<String> lectureAdapter = new ArrayAdapter<String>(AddressActivity.this,
                                    android.R.layout.simple_list_item_1,lectures);
                            ListView lecListView = (ListView) findViewById(R.id.lecture_address);
                            lecListView.setAdapter(lectureAdapter);
                            ArrayAdapter<String> tutorialAdapter = new ArrayAdapter<String>(AddressActivity.this,
                                    android.R.layout.simple_list_item_1,tutorials);
                            ListView tutListView = (ListView) findViewById(R.id.tutorial_address);
                            tutListView.setAdapter(tutorialAdapter);
                            lecListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    String clickedAddress = String.valueOf(((TextView) view).getText());
                                    Intent intent_address = new Intent(AddressActivity.this, FindRouteActivity.class); // TODO jump to map page
                                    intent_address.putExtra("destination",clickedAddress);
                                    intent_address.putExtra("latlng",lecture.get(clickedAddress));
                                    startActivity(intent_address);
                                }
                            });
                            tutListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
                                @Override
                                public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                                    String clickedAddress = String.valueOf(((TextView) view).getText());
                                    Intent intent_address = new Intent(AddressActivity.this, FindRouteActivity.class); // TODO jump to map page
                                    intent_address.putExtra("destination",clickedAddress);
                                    intent_address.putExtra("latlng",tutorial.get(clickedAddress));
                                    startActivity(intent_address);
                                }
                            });
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
        });
    }
}


///address/{sid}