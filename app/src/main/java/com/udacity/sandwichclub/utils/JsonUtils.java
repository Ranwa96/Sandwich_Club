package com.udacity.sandwichclub.utils;

import android.text.TextUtils;
import android.util.Log;

import com.udacity.sandwichclub.model.Sandwich;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class JsonUtils {

    /**
     * Tag for the log messages
     */
    public static final String LOG_TAG = JsonUtils.class.getSimpleName();

    /**
     * An empty private constructor
     * We don't need something here
     */
    private JsonUtils() {
    }

    /**
     * Return a {@link Sandwich} object that has been built up from
     * parsing the given JSON.
     */
    public static Sandwich parseSandwichJson(String sandwichJson) {

        // If the JSON string is empty or null, then return early.
        if (TextUtils.isEmpty(sandwichJson)) {
            return null;
        }

        // Initialize a Sandwich Object
        Sandwich sandwichObject = null;

        // Try to parse the JSON string. If there's a problem with the way the JSON
        // is formatted, a JSONException exception object will be thrown.
        // Catch the exception so the app doesn't crash, and print the error message to the logs.
        try {


            JSONObject baseJsonObject = new JSONObject(sandwichJson);


            JSONObject name = baseJsonObject.getJSONObject("name");


            String mainName = name.getString("mainName");

            List<String> alsoKnownAsList = new ArrayList<>();

            JSONArray alsoKnownAsArray = name.getJSONArray("alsoKnownAs");
            int countAlsoKnownAsArray = alsoKnownAsArray.length();

            for (int i = 0; i < countAlsoKnownAsArray; i++) {
                String otherName = alsoKnownAsArray.getString(i);
                alsoKnownAsList.add(otherName);
            }


            String placeOfOrigin = baseJsonObject.getString("placeOfOrigin");


            String description = baseJsonObject.getString("description");


            String image = baseJsonObject.getString("image");

            List<String> ingredientsList = new ArrayList<>();

            JSONArray ingredientsArray = baseJsonObject.getJSONArray("ingredients");
            int countIngredientsArray = ingredientsArray.length();


            for (int j = 0; j < countIngredientsArray; j++) {
                String ingredient = ingredientsArray.getString(j);
                ingredientsList.add(ingredient);
            }


            sandwichObject = new Sandwich(mainName, alsoKnownAsList, placeOfOrigin, description, image, ingredientsList);


        } catch (JSONException e) {


        }


        return sandwichObject;
    }
}
