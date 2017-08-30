package com.yerbaguy.practiceexercise;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import javax.net.ssl.HttpsURLConnection;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.DataOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.net.ssl.HttpsURLConnection;

import cz.msebera.android.httpclient.message.BasicNameValuePair;

public class PracticeActivity extends AppCompatActivity {

    TextView textViewAddWords, textViewPulledTheWord, textViewTypedEnglishWord;
    Button btnPullTheWord, btnSubmitEngWord;
    EditText editTextEnglishWord, englishWord;
    String engWord;
    //String englishWord;

    //String URL = "http://10.0.2.2:8080/PracticeExercise/mymethods/getidofengword";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_practice);


        textViewAddWords = (TextView) findViewById(R.id.textViewAddWords);

        textViewPulledTheWord = (TextView) findViewById(R.id.textViewPulledTheWord);

        editTextEnglishWord  = (EditText) findViewById(R.id.editTextEnglishWord);

        btnPullTheWord = (Button) findViewById(R.id.btnPullTheWord);

        btnSubmitEngWord = (Button) findViewById(R.id.btnSubmitEngWord);

        textViewTypedEnglishWord = (TextView) findViewById(R.id.textViewTypedEnglishWord);





        btnSubmitEngWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String eng = editTextEnglishWord.getText().toString();
                engWord = editTextEnglishWord.getText().toString();

                textViewTypedEnglishWord.setText(engWord);
                //PostData postData = new PostData();
                //postData.execute();

                //new PostData().execute();

                sendDataToServer(view);


            }


            // }
        });


        btnPullTheWord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                GetData getData = new GetData();
                getData.execute();

            }
        });


        //       btnSubmitEngWord.setOnClickListener(new View.OnClickListener() {
        //           @Override
        //           public void onClick(View view) {


        //           }
        //       });


        textViewAddWords.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent addWordsIntent = new Intent(PracticeActivity.this, AddWordsActivity.class);
                PracticeActivity.this.startActivity(addWordsIntent);
            }
        });
    }


    public void submitEngWord(View view) {

        String engword = editTextEnglishWord.getText().toString();

        textViewTypedEnglishWord.setText(engword);


    }


    public void sendDataToServer(View v) {

        engWord = editTextEnglishWord.getText().toString();

       // englishword = editTextEngWord.getText().toString();
        JSONObject post_dict = new JSONObject();

        try {

           // englishword = editTextEnglishWord.getText().toString();

           // JSONObject post_dict = new JSONObject();

           // textViewTypedEnglishWord.setText(engWord);

            post_dict.put("engword", engWord);



        } catch (JSONException e) {

            e.printStackTrace();
        }

        if (post_dict.length() > 0) {
            new SendJsonDataToServer().execute(String.valueOf(post_dict));
        }
    }


 //   public void POST() {

 //       URL url = null;
        //URLConnection urlConnection = null;



 //       try {
 //           url = new URL("http://10.0.2.2:8080/PracticeExercise/mymethods/getidofengword");
            //HttpsURLConnection connection = (HttpsURLConnection) url.openConnection();
 //           HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
 //           try {
 //               urlConnection.setDoOutput(true);
 //               urlConnection.setChunkedStreamingMode(0);

//                OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
//                out.flush();

//                InputStream in = new BufferedInputStream(urlConnection.getInputStream());
//                in.read();




//            } finally {

//                urlConnection.disconnect();
//            }
      //      String urlParameters = "engword";
      //      connection.setRequestMethod("POST");
      //      connection.setRequestProperty("User-Agent", "Mozilla/5.0");
      //      connection.setRequestProperty("ACCEPT-LANGUAGE", "en-US,en;0.5");
      //      connection.setDoOutput(true);
      //      DataOutputStream dStream = new DataOutputStream(connection.getOutputStream());
      //      dStream.writeBytes(urlParameters);
      //      dStream.flush();
      //      dStream.close();

      //      int responseCode= connection.getResponseCode();

      //      final StringBuilder output = new StringBuilder("Request URL:" + url);
      //      output.append(System.getProperty("line.separator") + "Request Parameters" + urlParameters);
      //      output.append(System.getProperty("line.separator") + "Response Code" + responseCode);

      //      BufferedReader br = new BufferedReader(new InputStreamReader(connection.getInputStream()));

      //      String line = "";
      //      StringBuilder responseOutput = new StringBuilder();

      //      while (( line = br.readLine()) != null) {

      //          responseOutput.append(line);
      //      }
      //      br.close();

      //      output.append(System.getProperty("line.separator") + "Response" + System.getProperty("line.separator") + System.getProperty("line.separator") + responseOutput.toString());

            // urlConnection.setRequestMethod
 //           urlConnection;
 //           urlConnection.setDoOutput(true);
            //urlConnection.setDoOutput(true);

 //           Uri.Builder builder = new Uri.Builder()
                    //.appendQueryParameter("engword", englishword);
 //                   .appendQueryParameter("engword", String.valueOf(engWord));

 //           String query = builder.build().getEncodedQuery();

 //           OutputStream os = urlConnection.getOutputStream();
 //           BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
 //           writer.write(query);
 //           writer.close();
 //           os.close();


//        } catch (MalformedURLException e) {
//            e.printStackTrace();
//        } catch (IOException e) {
//            e.printStackTrace();
//        }


        //return null;


//    }


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

                while ((line = reader.readLine()) != null) {

                    result.append(line);
                }


            } catch (Exception e) {
                e.printStackTrace();
            } finally {
                urlConnection.disconnect();
            }

            return result.toString();

            // return null;

            // return POST();
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


    //private class PostData extends AsyncTask<String, Void, String> {
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

            StringBuffer chaine = new StringBuffer("");

            try {

                //URL url = new URL("http://10.0.2.2:8080/PracticeExercise/mymethods/getidofengword");
              //  URL url = new URL("http://10.0.2.2:8080/PracticeExercise/mymethods/getidofengword");
                URL url = new URL("http://10.0.2.6:8080/PracticeExercise/mymethods/getidofengword");
                HttpURLConnection connection = (HttpURLConnection) url.openConnection();

                connection.setRequestProperty("User-Agent", "");
                connection.setRequestMethod("POST");
                connection.setDoOutput(true);

                Uri.Builder builder = new Uri.Builder()
                        .appendQueryParameter("engword", JsonDATA);

                String query = builder.build().getEncodedQuery();
                OutputStream os = connection.getOutputStream();
                BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTF-8"));
                writer.write(query);
                writer.flush();
                writer.close();
                os.close();
                connection.connect();

                System.out.println(url);


                InputStream inputStream = connection.getInputStream();

                BufferedReader rd = new BufferedReader(new InputStreamReader(inputStream));

                String line = "";

                while ((line = rd.readLine()) != null) {
                    chaine.append(line);
                }

                return chaine.toString();

         //       HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();

         //       try {

         //           urlConnection.setDoOutput(true);
         //           urlConnection.setChunkedStreamingMode(0);


                    //OutputStream out = new BufferedOutputStream(urlConnection.getOutputStream());
                    //out.flush();

          //          OutputStream os = urlConnection.getOutputStream();
          //                    BufferedWriter bufferedWriter = new BufferedWriter(new OutputStreamWriter(os, "UTf-8"));

          //            Writer writer = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream()));
          //                    writer.write(JsonDATA);
          //                    writer.flush();
          //                    writer.close();





                    //InputStream in = new BufferedInputStream(urlConnection.getInputStream());
          //          BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

          //          StringBuffer sb = new StringBuffer();
          //          String line = "";


          //          while (( line = in.readLine()) != null) {

          //              sb.append(line);
          //          }

          //          return sb.toString();


                   // int responseCode = urlConnection.getResponseCode();

                    //          if (responseCode == HttpURLConnection.HTTP_OK) {

                    //              BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

                    //              StringBuffer sb = new StringBuffer();
                    //              String line = "";

                    //              while ((line = in.readLine()) != null) {

                    //                  sb.append(line);
                    //                  break;
                    //              }

                    //              in.close();

                    //              return sb.toString();





                    //in.read();


                } catch (MalformedURLException e1) {
                e1.printStackTrace();
            } catch (IOException e1) {
                e1.printStackTrace();
            }
     //       } finally {

                // connection.disconnect();

     //           }

                //JSONObject postDataParams = new JSONObject();
                //postDataParams.put("engword", engword);
                //Log.e("params", postDataParams.toString());

      //          urlConnection = (HttpURLConnection) url.openConnection();
               // urlConnection.setDoInput(true);
               // urlConnection.setDoOutput(true);
      //          urlConnection.setRequestMethod("POST");
                //urlConnection.setRequestProperty("Content-Type", "application/json");
                //urlConnection.setRequestProperty("Accept", "application/json");

      //          OutputStream os = urlConnection.getOutputStream();
      //          BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(os, "UTf-8"));

                //  Writer writer = new BufferedWriter(new OutputStreamWriter(urlConnection.getOutputStream()));
      //          writer.write(JsonDATA);
      //          writer.flush();
      //          writer.close();
      //          os.close();
                //  urlConnection.connect();


      //          int responseCode = urlConnection.getResponseCode();

      //          if (responseCode == HttpURLConnection.HTTP_OK) {

      //              BufferedReader in = new BufferedReader(new InputStreamReader(urlConnection.getInputStream()));

      //              StringBuffer sb = new StringBuffer();
      //              String line = "";

      //              while ((line = in.readLine()) != null) {

      //                  sb.append(line);
      //                  break;
      //              }

      //              in.close();

      //              return sb.toString();
      //          } else {

      //              return new String(" false:" + responseCode);
      //          }
   //         }
   //         catch (Exception e) {
   //             return new String("Exception:" + e.getMessage());
   //         }







       //       InputStream inputStream = urlConnection.getInputStream(); //here

               // InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());


       //         StringBuffer buffer = new StringBuffer();
       //         if (inputStream == null) {
       //             return null;
       //         }

       //         reader = new BufferedReader(new InputStreamReader(inputStream));

       //         String inputLine;

       //         while (( inputLine = reader.readLine()) != null)
       //             buffer.append(inputLine + "\n");

       //         if (buffer.length() == 0) {
       //             return null;
       //         }

       //         JsonResponse = buffer.toString();

       //         Log.i(TAG, JsonResponse);

       //         return JsonResponse;


                //return urlConnection;


       //     } catch (IOException e) {
       //         e.printStackTrace();
       //     } finally {
       //         if (urlConnection != null) {
       //             urlConnection.disconnect();
       //         }
       //         if (reader != null) {
       //             try {
       //                 reader.close();
       //             } catch (final IOException e) {
       //                 String TAG = "";
       //                 Log.e(TAG, "Error closing stream", e);
       //             }
       //         }
       //     }
       //     return null;


            return chaine.toString();
        }


        @Override
        protected void onPostExecute(String result) {

            //super.onPostExecute(result);

            Toast.makeText(getApplicationContext(), result, Toast.LENGTH_LONG).show();


        }


    }
}