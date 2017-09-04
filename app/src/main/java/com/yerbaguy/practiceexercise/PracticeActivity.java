package com.yerbaguy.practiceexercise;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.util.Iterator;

import javax.net.ssl.HttpsURLConnection;

public class PracticeActivity extends AppCompatActivity {

    TextView textViewPulledTheWord, textViewTypedEnglishWord, textViewAddWords, textViewTypedEngWord;
    Button btnPullTheWord, btnSubmitEngWord;
    EditText editTextEnglishWord;
    String engWord;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);

        editTextEnglishWord = (EditText) findViewById(R.id.editTextEnglishWord);
        btnSubmitEngWord = (Button) findViewById(R.id.btnSubmitEngWord);
        textViewTypedEnglishWord = (TextView) findViewById(R.id.textViewTypedEnglishWord);
        textViewTypedEngWord = (TextView) findViewById(R.id.textViewTypedEngWord);

        btnSubmitEngWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

               String engWord = editTextEnglishWord.getText().toString();

                sendDataToServer(view, engWord);

                textViewTypedEngWord.setText(engWord);
            }
        });
    }



    public void sendDataToServer(View view, String engWord) {

             new SendJsonDataToServer().execute(engWord);
    }




    class SendJsonDataToServer extends AsyncTask<String, String, String> {
        private static final String TAG = "asdfasd";

        // String engword = "engword";


        // EditText editTextEngWord = (EditText) findViewById(R.id.editTextEngWord);

        // public EditText getEditTextEngWord() {
        //     return editTextEngWord;
        // }


        //String englishword = editTextEngWord.getText().toString();


        //   URL url = null;
        //   HttpURLConnection urlConnection = null;

        //   @Override
        //   protected void onPreExecute() {


        // }


        @Override
        protected String doInBackground(String... params) {
            String JsonResponse = null;
            String JsonDATA = params[0];

            // HttpURLConnection urlConnection = null;
            //BufferedReader reader = null;

            //StringBuffer chaine = new StringBuffer("");

            try {

                //URL url = new URL("http://10.0.2.2:8080/PracticeExercise/mymethods/getidofengword");
                //  URL url = new URL("http://10.0.2.2:8080/PracticeExercise/mymethods/getidofengword");
                //          URL url = new URL("http://10.0.2.1:8080/PracticeExercise/mymethods/getidofengword");
                URL url = new URL("http://10.0.2.2:8080/PracticeExercise/mymethods/getidofengword");

                JSONObject postDataParams = new JSONObject();
                postDataParams.put("engword", JsonDATA);
                Log.e("params", postDataParams.toString());

                HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                conn.setReadTimeout(15000);
                conn.setConnectTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setDoInput(true);
                conn.setDoOutput(true);

                OutputStream os = conn.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(getPostDataString(postDataParams));
                writer.flush();
                writer.close();
                os.close();


                int responseCode = conn.getResponseCode();

                if (responseCode == HttpsURLConnection.HTTP_OK) {

                    BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));

                    StringBuffer sb = new StringBuffer("");
                    String line = "";

                    while ((line = in.readLine()) != null) {

                        sb.append(line);
                        break;
                    }
                    in.close();

                    return sb.toString();

                } else {
                    return new String("false:" + responseCode);
                }
            }
            catch (Exception e) {
                return new String("Exception:" + e.getMessage());
            }
            //return null;
        }


        @Override
        protected void onProgressUpdate(String...params) {

            String engword = params[0];

            textViewTypedEnglishWord.setText(engWord);

        }



        @Override
        //  protected void onPostExecute(String... results) {
        protected void onPostExecute(String result) {

            //super.onPostExecute(result);

            //String engWord = results[1];
            //        String result = results[0];


            textViewTypedEnglishWord.setText(engWord);

            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();


        }



        public String getPostDataString(JSONObject  params) throws JSONException, UnsupportedEncodingException {

            StringBuilder result = new StringBuilder();
            boolean first = true;

            Iterator<String> itr = params.keys();

            while (itr.hasNext()) {

                String key = itr.next();
                Object value = params.get(key);

                if (first)
                    first = false;
                else
                    result.append("&");

                result.append(URLEncoder.encode(key, "UTF-8"));
                result.append("=");
                result.append(URLEncoder.encode(value.toString(), "UTF-8"));

            }

            return result.toString();
        }








    }
















}
