package com.yerbaguy.practiceexercise;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.util.HashMap;
import java.util.Map;

import java.net.URL;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONObject;

import static android.provider.ContactsContract.CommonDataKinds.Website.URL;

public class AddWordsActivity extends AppCompatActivity {


    EditText textEditEngWord, textEditPlWord;
    TextView textViewTypedEngWord, textViewTypedPlWord;

    Button btnSubmit;

    //String URL = "http://10.0.2.2:8080/PracticeWords/insert/doinsert";
    String URL = "http://10.0.2.2:8080/PracticeExercise/insert/doinsert";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_words);

        textEditEngWord = (EditText) findViewById(R.id.editTextEngWord);
        textEditPlWord = (EditText) findViewById(R.id.editTextPlWord);

        textViewTypedEngWord = (TextView) findViewById(R.id.textViewTypedEngWord);
        textViewTypedPlWord = (TextView) findViewById(R.id.textViewTypedPlWord);

        btnSubmit = (Button) findViewById(R.id.btnSubmit);

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                StringRequest request = new StringRequest(Request.Method.POST, URL, new Response.Listener<String>() {
                    @Override
                    public void onResponse(String s) {
                        if (s.equals("true")) {
                            Toast.makeText(AddWordsActivity.this, "Words added successfully", Toast.LENGTH_LONG).show();
                        } else {
                            Toast.makeText(AddWordsActivity.this, "Couldn't add the words", Toast.LENGTH_LONG).show();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError volleyError) {
                        Toast.makeText(AddWordsActivity.this, "Some errors occured -> " +volleyError, Toast.LENGTH_LONG).show();
                    }
                }) {
                    @Override
                    protected Map<String, String>  getParams() throws AuthFailureError {
                        Map<String, String> parameters = new HashMap<String, String>();
                        parameters.put("engword", textEditEngWord.getText().toString());
                        parameters.put("plword", textEditPlWord.getText().toString());
                        return parameters;
                    }
                };

                RequestQueue rQueue = Volley.newRequestQueue(AddWordsActivity.this);
                rQueue.add(request);


            }
        });





    }



    // }













}


