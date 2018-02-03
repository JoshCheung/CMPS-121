package com.dealfaro.luca.listviewexample;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;



import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class MainActivity extends AppCompatActivity {

    private static final String LOG_TAG = "lv-ex";
    RequestQueue queue;
    JSONArray jsonArray = new JSONArray();
    ArrayList<String> urls = new ArrayList<String>();
    ArrayList<String> subtitle = new ArrayList<String>();
    ArrayList<String> title = new ArrayList<String>();

    private class ListElement {
        ListElement() {
        }

        ;

        ListElement(String title, String subTitle, String urls) {
            textLabel = title;
            subtextLabel = subTitle;
            urlLabel = urls;
        }

        public String textLabel;
        public String subtextLabel;
        public String urlLabel;
    }

    private ArrayList<ListElement> aList;

    private class MyAdapter extends ArrayAdapter<ListElement> {

        int resource;
        Context context;

        public MyAdapter(Context _context, int _resource, List<ListElement> items) {
            super(_context, _resource, items);
            resource = _resource;
            context = _context;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            LinearLayout newView;

            final ListElement w = getItem(position);

            // Inflate a new view if necessary.
            if (convertView == null) {
                newView = new LinearLayout(getContext());
                LayoutInflater vi = (LayoutInflater)
                        getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
                vi.inflate(resource, newView, true);
            } else {
                newView = (LinearLayout) convertView;
            }

            // Fills in the view.
            TextView tv = (TextView) newView.findViewById(R.id.itemText);
            TextView st = (TextView) newView.findViewById(R.id.subLabel);

            tv.setText(w.textLabel);
            st.setText(w.subtextLabel);


            // Set a listener for the whole list item.
            newView.setTag(w.urlLabel);
            newView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String s = v.getTag().toString();
                    int duration = Toast.LENGTH_SHORT;
                    Toast toast = Toast.makeText(context, s, duration);
                    toast.show();
                    goToSite(s);

                }
            });

            return newView;
        }
    }


    public void goToSite(String s){
        // Go to newSite
        Intent intent = new Intent(this, NewsSites.class);
        intent.putExtra("URL", s);
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
        startActivity(intent);
    }


    private MyAdapter aa;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        aList = new ArrayList<ListElement>();
        aa = new MyAdapter(this, R.layout.list_element, aList);
        ListView myListView = (ListView) findViewById(R.id.listView);
        queue = Volley.newRequestQueue(this);
        myListView.setAdapter(aa);
        display();
        aa.notifyDataSetChanged();
    }

    public void clickRefresh(View v) {
        display();
    }



    public void display(){
        urls.clear();
        subtitle.clear();
        title.clear();
        String url = "https://luca-ucsc-teaching-backend.appspot.com/hw4/get_news_sites";
        final JsonObjectRequest jsObjRequest = new JsonObjectRequest
                (Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            jsonArray = response.getJSONArray("news_sites");
                            aList.clear();
                            for (int i = 0; i < jsonArray.length(); i++) {
                                if((((jsonArray.getJSONObject(i).getString("url").equals("null")))||(jsonArray.getJSONObject(i).getString("title").equals("null")))) {
                                    continue;
                                }
                                else {
                                    urls.add(jsonArray.getJSONObject(i).getString("url"));
                                    title.add(jsonArray.getJSONObject(i).getString("title"));
                                    if ((((jsonArray.getJSONObject(i).getString("subtitle").equals("null"))))) {
                                        subtitle.add("");
                                    } else {
                                        subtitle.add(jsonArray.getJSONObject(i).getString("subtitle"));
                                    }
                                }
                            }
                            for(int i = 0; i < urls.size(); i++){
                                aList.add(new ListElement(title.get(i), subtitle.get(i), urls.get(i)));
                            }
                            aa.notifyDataSetChanged();
                        } catch (JSONException e) {
                            e.printStackTrace();
                        }
                    }
                }, new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        // TODO Auto-generated method stub
                        Log.d(LOG_TAG, error.toString());
                    }
                });
        queue.add(jsObjRequest);

    }



}
