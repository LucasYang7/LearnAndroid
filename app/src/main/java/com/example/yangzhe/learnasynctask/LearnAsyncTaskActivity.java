package com.example.yangzhe.learnasynctask;

import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.yangzhe.learnactivity.R;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

public class LearnAsyncTaskActivity extends AppCompatActivity {
    private static final String TAG = "LearnAsyncTaskActivity";
    private Button btnShowWebContent;
    private TextView textViewShowWebContent;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_async_task);
        textViewShowWebContent = (TextView)findViewById(R.id.textViewShowWebContent);
        btnShowWebContent = (Button)findViewById(R.id.buttonShowWebContent);
        btnShowWebContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DownloadWebPageTask().execute(new String[]{"https://www.baidu.com"});
            }
        });
    }

    private class DownloadWebPageTask extends AsyncTask<String,Void,String>{

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
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            textViewShowWebContent.setText(result);
        }
    }

}
