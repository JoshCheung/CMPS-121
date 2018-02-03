package com.dealfaro.luca.androidhomephone;

import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import android.widget.Button;

import org.json.JSONArray;
import org.json.JSONObject;



import java.net.URLEncoder;
import java.util.HashMap;
import java.util.Map;

import static android.webkit.ConsoleMessage.MessageLevel.LOG;
//import static com.dealfaro.luca.androidhomephone.R.id.detailView;

public class MainActivity extends AppCompatActivity {

    private final String LOG_TAG = "androidhomephone";

    RequestQueue queue;
    Button button;
    Button button2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        queue = Volley.newRequestQueue(this);

        button = (Button) findViewById(R.id.button2);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                sendMsg();
            }
        });

        button2 = (Button) findViewById(R.id.button);
        button2.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                getMessages();
            }
        });


    }

    private void sendMsg() {

        StringRequest sr = new StringRequest(Request.Method.POST,
                "https://luca-ucsc-teaching-backend.appspot.com/hw3/request_via_post",
                new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                TextView post = (TextView)findViewById(R.id.my_text);
                post.setText(response.substring(12, response.length()-2));
                Log.d(LOG_TAG, "Got:" + response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
            }
        }){
            @Override
            protected Map<String,String> getParams(){
                Map<String,String> params = new HashMap<String, String>();
                params.put("token", "abracadabra");
                return params;
            }

            @Override
            public Map<String, String> getHeaders() throws AuthFailureError {
                Map<String,String> params = new HashMap<String, String>();
                params.put("Content-Type","application/x-www-form-urlencoded");
                return params;
            }
        };
        queue.add(sr);

    }


    public void onGet(View v){
        getMessages();
    }
    private void getMessages() {

        // Instantiate the RequestQueue.
        String url = "https://luca-ucsc-teaching-backend.appspot.com/hw4/get_news_sites";
        String my_url = url + "?token=" + URLEncoder.encode("abracadabra");


        JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, my_url, null, new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {

                        TextView post = (TextView)findViewById(R.id.my_text);
                        post.setText(response.toString());
                        // Ok, let's disassemble a bit the json object.
                    }
                }, new Response.ErrorListener() {

                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Log.d(LOG_TAG, error.toString());
                    }
                });

        // In some cases, we don't want to cache the request.
        // jsObjRequest.setShouldCache(false);

        queue.add(jsObjRequest);
    }




}
