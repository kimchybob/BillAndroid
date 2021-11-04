package com.example.unimelborientation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.method.ScrollingMovementMethod;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unimelborientation.type.Subject;
import com.example.unimelborientation.util.HttpClient;
import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.loopj.android.http.JsonHttpResponseHandler;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.HashMap;

import cz.msebera.android.httpclient.Header;


public class subjectDetail extends AppCompatActivity {

    private String subject_name = "Mobile computing"; //TODO read the input subject info
    //private String pre_Subject = "AI planning"; // TODO read the input pre subject info
    private String subject_code = "COMP90018"; // TODO read the input subject code value
    private String theory_Rate = " 4.0"; // TODO read the input theory rate value
    private String practice_Rate = " 4.2"; // TODO read the input practice rate value
    private String difficulty_Rate = " 4.4"; // TODO read the input difficulty rate value
    private Button rate_button;
    private String subjectCode;
    private Subject subject;
    private String sub_description = "To do..."; // TODO read the input subject description

    private String [] commentdata = {}; // TODO read the input comment data

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        subjectCode = getIntent().getStringExtra("subjectCode");
        String sid = getIntent().getStringExtra("sid");

        HttpClient.get("subject/getCommentBySubId/" + sid, null, new JsonHttpResponseHandler(){
                    @Override
                    public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                        try {
                            System.out.println(response);
                            JSONArray data = (JSONArray) response.get("data");
                            String [] comment_list = new String[data.length()];
                            for(int i = 0; i<data.length(); i++){
                                JSONObject list = data.getJSONObject(i);
                                comment_list[i] = list.getString("comment");
                            }
                            commentdata = comment_list;

                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }

                }

        );

        HttpClient.get("subject/getSubjByCode/" + subjectCode, null, new JsonHttpResponseHandler(){
            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONObject response) {
                // If the response is JSONObject instead of expected JSONArray
                try {
                    JSONObject data = (JSONObject) response.get("data");
                    subject = new Gson().fromJson(String.valueOf(data), Subject.class);
                    difficulty_Rate = String.valueOf(subject.getDiffiscore());
                    subject_name = subject.getSubjname();
                    subject_code = subject.getSubjcode();
                    //pre_Subject = subject.getSubjname();
                    theory_Rate = String.valueOf(subject.getTheoryscore());
                    practice_Rate = String.valueOf(subject.getPractiscore());
                    sub_description = subject.getDescrip();




                    setContentView(R.layout.activity_subject_detail);
                    ArrayAdapter<String> adapter = new ArrayAdapter<String>(subjectDetail.this,
                            android.R.layout.simple_list_item_1, commentdata);
                    ListView listView = (ListView) findViewById(R.id.Comment_list);
                    listView.setAdapter(adapter);
                    TextView title = findViewById(R.id.subject_title);
                    title.setText(subject_name);
                    TextView code = findViewById(R.id.subject_code);
                    code.setText(subject_code);
                    //TextView pre = findViewById(R.id.Pre_class);
                    //pre.setText("Pre Subject: " + pre_Subject);
                    TextView theory = findViewById(R.id.rateTheory);
                    theory.setText("Theory rate: "+ theory_Rate);
                    TextView practice = findViewById(R.id.ratePractice);
                    practice.setText("Practice rate: " + practice_Rate);
                    TextView difficulty = findViewById(R.id.rateDifficulty);
                    difficulty.setText("Difficulity rate: " + difficulty_Rate);
                    // Test subject description scrolling function
                    TextView des = findViewById(R.id.subject_describe);
                    des.setMovementMethod(ScrollingMovementMethod.getInstance());
                    des.setText(sub_description);
                    //

                    rate_button = findViewById(R.id.rate_button);
                    rate_button.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Intent intent = new Intent(subjectDetail.this, RatePage.class);
                            intent.putExtra("subjectname", subject_name);
                            intent.putExtra("sid",sid);
                            startActivity(intent);
                        }
                    });

                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }



            @Override
            public void onSuccess(int statusCode, Header[] headers, JSONArray timeline) {
                // Pull out the first event on the public timeline
                try {
                    JSONObject firstEvent = (JSONObject) timeline.get(0);
                    String code = firstEvent.getString("subjcode");
                    System.out.println(code);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }

        );




    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_subjectdetail,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.rate_item:
                Intent intent_rate = new Intent(subjectDetail.this, RatePage.class);
                startActivity(intent_rate);
                break;
            case R.id.Address_item:
                int sid = subject.getSid();
                Intent intent_address = new Intent(subjectDetail.this, AddressActivity.class); // TODO jump to map page
                intent_address.putExtra("sid",sid);
                intent_address.putExtra("subject",subject_name);
                startActivity(intent_address);
                break;
            default:
        }
        return true;
    }
}