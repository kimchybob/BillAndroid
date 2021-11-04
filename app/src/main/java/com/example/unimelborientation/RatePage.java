package com.example.unimelborientation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.unimelborientation.util.HttpClient;
import com.example.unimelborientation.util.SharedPreferencesUtils;
import com.loopj.android.http.RequestParams;
import com.loopj.android.http.TextHttpResponseHandler;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.util.Arrays;

import cz.msebera.android.httpclient.Header;
import cz.msebera.android.httpclient.entity.StringEntity;

public class RatePage extends AppCompatActivity {
    private RatingBar ratingBar_Theory;
    private RatingBar ratingBar_Practice;
    private RatingBar ratingBar_Difficulty;
    private Button submit_button;
    private String subject_name = "Mobile computing"; // TODO read input subject name
    private SharedPreferencesUtils local_setting;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_page);

        subject_name = getIntent().getStringExtra("subjectname");
        local_setting = new SharedPreferencesUtils(this, "setting");
        String sid_string = getIntent().getStringExtra("sid");
        int sid = Integer.parseInt(sid_string);


        TextView subtitle = findViewById(R.id.rate_page_head);
        subtitle.setText(subject_name);

        EditText com = findViewById(R.id.rate_edit_text);

        ratingBar_Theory = findViewById(R.id.Theory_rate_bar);
        ratingBar_Theory.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(RatePage.this, "Theory Rating: "+ String.valueOf(rating), Toast.LENGTH_SHORT).show();
            }
        });

        ratingBar_Practice = findViewById(R.id.Practice_rate_bar);
        ratingBar_Practice.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(RatePage.this, "Practice Rating: "+ String.valueOf(rating), Toast.LENGTH_SHORT).show();
            }
        });

        ratingBar_Difficulty = findViewById(R.id.Difficulty_rate_bar);
        ratingBar_Difficulty.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                Toast.makeText(RatePage.this, "Difficulty Rating: "+ String.valueOf(rating), Toast.LENGTH_SHORT).show();
            }
        });

        submit_button = findViewById(R.id.rate_submit_button);
        submit_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                float practiscore = ratingBar_Practice.getRating();
                float theoryscore = ratingBar_Theory.getRating();
                float diffiscore = ratingBar_Difficulty.getRating();
                Integer comuid = local_setting.getInt("uid");
                System.out.println(comuid);// TODO use the real id

                Integer subjid = sid; // TODO use the real id
                String comment = com.getText().toString();


                JSONObject params = new JSONObject();

                StringEntity entity = null;
                try {
                    params.put("subjid", subjid);
                    params.put("practiscore", practiscore);
                    params.put("theoryscore", theoryscore);
                    params.put("diffiscore", diffiscore);
                    params.put("comment", comment);
                    params.put("comuid", comuid);
                    entity = new StringEntity(params.toString());
                } catch (UnsupportedEncodingException | JSONException e) {
                    e.printStackTrace();
                }
                HttpClient.post("subject/setSubjComment", entity, "application/json", new TextHttpResponseHandler(){
                    @Override
                    public void onFailure(int statusCode, Header[] headers, String responseString, Throwable throwable) {
                        Toast.makeText(RatePage.this, "Submit fail!", Toast.LENGTH_SHORT).show();
                        System.out.println(responseString);
                    }

                    @Override
                    public void onSuccess(int statusCode, Header[] headers, String responseString) {
                        Toast.makeText(RatePage.this, "Submit successfully!", Toast.LENGTH_SHORT).show();
                    }
                });
            }
        });


    }

}