package com.example.yangzhe.learnlayout;

import android.os.AsyncTask;

import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.yangzhe.learnactivity.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;

public class LearnSwipeRereshLayoutActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {
    private final String TAG = "LearnSwipeRefreshLayout";
    private SwipeRefreshLayout mSwipeRefreshLayout;
    private ListView mListView;
    private ArrayList<String> list = new ArrayList<String>();
    private String htmlContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_swipe_reresh_layout);

        mListView = (ListView)findViewById(R.id.listviewInSwipeRefreshLayout);
        mListView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,
                list));

        mSwipeRefreshLayout = (SwipeRefreshLayout)findViewById(R.id.swipeRefreshLayoutTest);
        mSwipeRefreshLayout.setOnRefreshListener(this);
        mSwipeRefreshLayout.setColorSchemeResources(R.color.colorSwipeRefreshLayoutRed,
                R.color.colorSwipeRefreshLayoutGreen,
                R.color.colorSwipeRefreshLayoutBlue);
    }

    private ArrayList<String> getData(String htmlContent){
        ArrayList<String> listHtmlContent = new ArrayList<String>();
        String[] htmlContentLines = htmlContent.split("\n");
        int line = 1;
        for(String htmlContentLine : htmlContentLines){
            listHtmlContent.add(htmlContentLine);
            Log.e(TAG,"line " + String.valueOf(line++) + htmlContentLine);
        }
        return listHtmlContent;
    }

    @Override
    public void onRefresh() {
        String[] urlStrings = new String[]{"https://www.baidu.com"};
        new SwipeRefreshTask().execute(urlStrings);
    }

    /**
     * SwipeRefreshTask is an inner class
     * */
    private class SwipeRefreshTask extends AsyncTask<String,Void,String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            mSwipeRefreshLayout.setRefreshing(true);  //let the SwipeRefreshLayout show refresh progress
        }

        @Override
        protected String doInBackground(String... urlString) {
            String result = "nothing";

            try {
                URL url = new URL(urlString[0]);
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                httpURLConnection.connect();
                InputStream inputStream = httpURLConnection.getInputStream();
                InputStreamReader inputStreamReader = new InputStreamReader(inputStream,"UTF-8");
                BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
                StringBuilder stringBuilder = new StringBuilder();
                String strLine;
                while((strLine = bufferedReader.readLine()) != null){
                    Log.e(TAG,strLine);
                    stringBuilder.append(strLine);
                    stringBuilder.append("\n");
                }
                bufferedReader.close();
                result = stringBuilder.toString();
                Log.e(TAG,result);
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            return result;
        }

        @Override
        protected void onPostExecute(String htmlContent) {
            super.onPostExecute(htmlContent);
            mSwipeRefreshLayout.setRefreshing(false); // stop showing SwipeRefreshLayout's progress
            mListView.setAdapter(new ArrayAdapter<String>(LearnSwipeRereshLayoutActivity.this,android.R.layout.simple_list_item_1,
                    getData(htmlContent)));
        }
    }
}
