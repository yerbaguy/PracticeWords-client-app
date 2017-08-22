package com.yerbaguy.practiceexercise;

import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class PracticeActivity extends AppCompatActivity {

    TextView textViewAddWords, textViewPulledTheWord;
    Button btnPullTheWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);


        textViewAddWords = (TextView) findViewById(R.id.textViewAddWords);

        textViewPulledTheWord = (TextView) findViewById(R.id.textViewPulledTheWord);

        btnPullTheWord = (Button) findViewById(R.id.btnPullTheWord);



        btnPullTheWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GetData getData = new GetData();
                getData.execute();

            }
        });


        textViewAddWords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent addWordsIntent = new Intent(PracticeActivity.this, AddWordsActivity.class);
                PracticeActivity.this.startActivity(addWordsIntent);
            }
        });
    }


    private class GetData extends AsyncTask<String, String, String> {
        JSONObject jsonObject;
        HttpURLConnection urlConnection;

        @Override
        protected String doInBackground(String... args) {

            StringBuilder result = new StringBuilder();

            try {

                URL url = new URL("http://10.0.2.2:8080/PracticeExercise/mymethods/getinfo");

                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                String line;

                while ((line  = reader.readLine()) != null) {

                    result.append(line);
                }


            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                urlConnection.disconnect();
            }

            return result.toString();

            // return null;
        }


        @Override
        protected void onPostExecute(String result) {

            Toast.makeText(getBaseContext(), "Received", Toast.LENGTH_LONG).show();


            textViewPulledTheWord.setText(result);

        }


        @Override
        protected void onProgressUpdate(String... values) {

            textViewPulledTheWord.setText(values[2]);

        }
    }




}


