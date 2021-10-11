package com.example.unimelborientation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class subjectDetail extends AppCompatActivity {

    private String [] commentdata = {"This is an interesting class", "The experience of designing our own app is fantastic",
            "Hope you enjoy the class","This is an interesting class", "The experience of designing our own app is fantastic",
            "Hope you enjoy the class","This is an interesting class", "The experience of designing our own app is fantastic",
            "Hope you enjoy the class", "The experience of designing our own app is fantastic",
            "Hope you enjoy the class","This is an interesting class", "The experience of designing our own app is fantastic",
            "Hope you enjoy the class","This is an interesting class", "The experience of designing our own app is fantastic",
            "Hope you enjoy the class", "The experience of designing our own app is fantastic",
            "Hope you enjoy the class","This is an interesting class", "The experience of designing our own app is fantastic",
            "Hope you enjoy the class","This is an interesting class", "The experience of designing our own app is fantastic",
            "Hope you enjoy the class"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_subject_detail);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(subjectDetail.this,
                android.R.layout.simple_list_item_1, commentdata);
        ListView listView = (ListView) findViewById(R.id.Comment_list);
        listView.setAdapter(adapter);
    }

    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main_subjectdetail,menu);
        return true;
    }

    public boolean onOptionsItemSelected(MenuItem item){
        switch(item.getItemId()){
            case R.id.rate_item:
                Intent intent = new Intent(subjectDetail.this, subjectDetail.class);//link to next Rate page
                startActivity(intent);
                break;
            case R.id.Address_item:
                Toast.makeText(subjectDetail.this, "You will turn to Address menu", Toast.LENGTH_SHORT).show();
                break;
            default:
        }
        return true;
    }
}