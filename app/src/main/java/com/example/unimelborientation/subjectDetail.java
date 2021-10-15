package com.example.unimelborientation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class subjectDetail extends AppCompatActivity {

    private String subject_name = "Mobile computing"; //TODO read the input subject info
    private String pre_Subject = "AI planning"; // TODO read the input pre subject info
    private String subject_code = "COMP90018"; // TODO read the input subject code value
    private String theory_Rate = " 4.0"; // TODO read the input theory rate value
    private String practice_Rate = " 4.2"; // TODO read the input practice rate value
    private String difficulty_Rate = " 4.4"; // TODO read the input difficulty rate value
    private Button rate_button;

    private String [] commentdata = {"This is an interesting class", "The experience of designing our own app is fantastic",
            "Hope you enjoy the class","This is an interesting class", "The experience of designing our own app is fantastic",
            "Hope you enjoy the class","This is an interesting class", "The experience of designing our own app is fantastic",
            "Hope you enjoy the class", "The experience of designing our own app is fantastic",
            "Hope you enjoy the class","This is an interesting class", "The experience of designing our own app is fantastic",
            "Hope you enjoy the class","This is an interesting class", "The experience of designing our own app is fantastic",
            "Hope you enjoy the class", "The experience of designing our own app is fantastic",
            "Hope you enjoy the class","This is an interesting class", "The experience of designing our own app is fantastic",
            "Hope you enjoy the class","This is an interesting class", "The experience of designing our own app is fantastic",
            "Hope you enjoy the class"}; // TODO read the input comment data

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_detail);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(subjectDetail.this,
                android.R.layout.simple_list_item_1, commentdata);
        ListView listView = (ListView) findViewById(R.id.Comment_list);
        listView.setAdapter(adapter);
        TextView title = findViewById(R.id.subject_title);
        title.setText(subject_name);
        TextView code = findViewById(R.id.subject_code);
        code.setText(subject_code);
        TextView pre = findViewById(R.id.Pre_class);
        pre.setText("Pre Subject: " + pre_Subject);
        TextView theory = findViewById(R.id.rateTheory);
        theory.setText("Theory rate: "+ theory_Rate);
        TextView practice = findViewById(R.id.ratePractice);
        practice.setText("Practice rate: " + practice_Rate);
        TextView difficulty = findViewById(R.id.rateDifficulty);
        difficulty.setText("Difficulity rate: " + difficulty_Rate);
        rate_button = findViewById(R.id.rate_button);
        rate_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(subjectDetail.this, RatePage.class);
                startActivity(intent);
            }
        });
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
                Intent intent_address = new Intent(subjectDetail.this, subjectDetail.class); // TODO jump to map page
                startActivity(intent_address);
                break;
            default:
        }
        return true;
    }
}