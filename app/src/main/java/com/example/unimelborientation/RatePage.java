package com.example.unimelborientation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

public class RatePage extends AppCompatActivity {
    private RatingBar ratingBar_Theory;
    private RatingBar ratingBar_Practice;
    private RatingBar ratingBar_Difficulty;
    private Button submit_button;
    private String subject_name = "Mobile computing"; // TODO read input subject name

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rate_page);

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
                Toast.makeText(RatePage.this, "Submit successfully!", Toast.LENGTH_SHORT).show();
                // TODO post data to backend
            }
        });

        TextView subtitle = findViewById(R.id.rate_page_head);
        subtitle.setText(subject_name);
    }

}