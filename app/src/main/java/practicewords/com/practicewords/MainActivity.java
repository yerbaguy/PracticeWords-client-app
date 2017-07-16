package com.yerbaguy.practicewords;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;

public class MainActivity extends AppCompatActivity {

    private Button btnPullTheWord;
    private TextView txtPulledTheWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnPullTheWord = (Button) findViewById(R.id.btnPullTheWord);
        txtPulledTheWord = (TextView) findViewById(R.id.txtPulledTheWord);

        btnPullTheWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                //String x = "lkjasdf";

                //txtPulledTheWord.setText(x);

                GetData getData = new GetData();
                getData.execute();


            }
        });

    }



    private class GetData extends AsyncTask<String, String, String> {


        JSONObject jsonObject;

        HttpURLConnection urlConnection;


        @Override
        protected String doInBackground(String... args) {

            String engword;

            StringBuilder result = new StringBuilder();

            try {


                URL url = new URL("http://10.0.2.2:8080/practiceWords/engwords");

                urlConnection = (HttpURLConnection) url.openConnection();

                InputStream in = new BufferedInputStream(urlConnection.getInputStream());

                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                String line;

                while ( (line = reader.readLine() ) != null) {

                    //result.append(line);
                    result.append(line).toString();
                }

            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                urlConnection.disconnect();
            }



//            try {

//                jsonObject = new JSONObject(result);
//                engword = jsonObject.getString("engWord");


//            } catch (JSONException e) {
//                e.printStackTrace();
//            }




//            return engword;
            return result.toString();


            //return null;
        }


        @Override
        protected void onPostExecute(String result) {

            //result = "lkjasd";

            result = result;

//            String engword = "";

//            try {

//                jsonObject = new JSONObject(result);
                //engword = jsonObject.getString("engWord");
//                result = jsonObject.getString("engWord");


//            } catch (JSONException e) {
//                e.printStackTrace();
//            }



            txtPulledTheWord.setText(result);
            //txtPulledTheWord.setText(engword);


        }

        @Override
        protected void onProgressUpdate(String... text) {

           //txtPulledTheWord.setText(text[1]);

         //  try {

         //      jsonObject = new JSONObject(text);

         //  }


           txtPulledTheWord.setText(text[0]);


        }
    }


}
