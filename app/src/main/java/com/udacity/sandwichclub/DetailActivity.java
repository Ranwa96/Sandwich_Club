package com.udacity.sandwichclub;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.udacity.sandwichclub.model.Sandwich;
import com.udacity.sandwichclub.utils.JsonUtils;

import org.w3c.dom.Text;

import java.util.List;

public class DetailActivity extends AppCompatActivity {

    public static final String EXTRA_POSITION = "extra_position";
    private static final int DEFAULT_POSITION = -1;
    public  Sandwich sandwich;
    ImageView ingredientsIv;
    TextView originTextView;
    TextView alsoKnownAsLabel;
    TextView alsoKnownAsTextView;
    TextView descriptionTextView;
    TextView ingredientsTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

       ingredientsIv = findViewById(R.id.image_iv);
        originTextView = findViewById(R.id.origin_tv);
        alsoKnownAsLabel = findViewById(R.id.also_known_as);
        alsoKnownAsTextView = findViewById(R.id.also_known_tv);
       descriptionTextView = findViewById(R.id.description_tv);
        ingredientsTextView = findViewById(R.id.ingredients_tv);

        Intent intent = getIntent();
        if (intent == null) {
            closeOnError();
        }

        int position = intent.getIntExtra(EXTRA_POSITION, DEFAULT_POSITION);
        if (position == DEFAULT_POSITION) {
            // EXTRA_POSITION not found in intent
            closeOnError();
            return;
        }

        String[] sandwiches = getResources().getStringArray(R.array.sandwich_details);
        String json = sandwiches[position];
   sandwich = JsonUtils.parseSandwichJson(json);
        if (sandwich == null) {
            // Sandwich data unavailable
            closeOnError();
            return;
        }
        else {

            populateUI();
        }

        // Enable Up Button to allow the user to navigate back to Parent Activity (MainActivity)
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void closeOnError() {
        finish();
        Toast.makeText(this, R.string.detail_error_message, Toast.LENGTH_SHORT).show();
    }

    private void populateUI() {
        Picasso.with(this)
                .load(sandwich.getImage())
                .into(ingredientsIv);

        setTitle(sandwich.getMainName());
        originTextView.setText(sandwich.getPlaceOfOrigin());
       alsoKnownAsLabel.setText(TextUtils.join(", ", sandwich.getAlsoKnownAs()));
        ingredientsTextView.setText(TextUtils.join(", ", sandwich.getIngredients()));
       descriptionTextView.setText(sandwich.getDescription());


    }
}