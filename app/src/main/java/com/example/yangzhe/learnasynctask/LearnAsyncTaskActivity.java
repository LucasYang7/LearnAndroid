package com.example.yangzhe.learnasynctask;

import android.os.AsyncTask;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
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

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LearnAsyncTaskActivity extends AppCompatActivity {
    private static final String TAG = "LearnAsyncTaskActivity";
    private static final String TAG_Baidu = "BaiduContent";
    private Button btnShowWebContent;
    private TextView textViewShowWebContent1;
    private TextView textViewShowWebContent2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_learn_async_task);
        textViewShowWebContent1 = (TextView)findViewById(R.id.textViewShowWebContent1);
        textViewShowWebContent2 = (TextView)findViewById(R.id.textViewShowWebContent2);
        btnShowWebContent = (Button)findViewById(R.id.buttonShowWebContent);
        btnShowWebContent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DownloadWebPageTask().execute(new String[]{"http://www.mzitu.com/japan"});
                new DownloadWebPageTask2().execute(new String[]{"http://www.mzitu.com/japan"});
            }
        });
    }

    /**
     * 使用URL来请求 baidu.com 的首页内容
     * */
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
                    //Log.e(TAG,strLine);
                    stringBuilder.append(strLine);
                    stringBuilder.append("\n");
                }
                bufferedReader.close();
                result = stringBuilder.toString();
                //Log.e(TAG,result);
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
            result = "From HttpURLConnection:\n" + result;
            textViewShowWebContent1.setText(result+"\n\n");
            //Log.e(TAG_Baidu,result);
        }
    }

    /**
     * 使用OkHttp来请求baidu.com的首页内容
     * */
    private class DownloadWebPageTask2 extends AsyncTask<String,Void,String>{

        @Override
        protected String doInBackground(String... url) {
            String responseBody = run(url[0]);
            return responseBody;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            result = "From OKHttp:\n" + result;
            textViewShowWebContent2.setText(result);
            //Log.e(TAG_Baidu,result);
        }
    }

    /**
     * 使用OKHttp GET 方法来请求百度baidu.com的内容
     * */
    String run(String url){
        String responseBody = "nothing";
        OkHttpClient okHttpClient = new OkHttpClient();
        Request request = new Request.Builder()
                .url(url)
                .build();
        try{
            Response response = okHttpClient.newCall(request).execute();
            responseBody = response.body().string();
        }catch (IOException e){
            e.printStackTrace();
        }

        return responseBody;
    }

}
