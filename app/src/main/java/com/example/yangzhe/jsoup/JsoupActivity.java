package com.example.yangzhe.jsoup;

import android.os.AsyncTask;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.yangzhe.learnactivity.R;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.w3c.dom.Text;

import java.io.IOException;

public class JsoupActivity extends AppCompatActivity {

    private TextView textViewShowJsoup;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_jsoup);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        toolbar.setNavigationOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                JsoupActivity.this.finish();
            }
        });

        textViewShowJsoup = (TextView)findViewById(R.id.textViewShowJsoup);
        progressBar = (ProgressBar)findViewById(R.id.progressBarInJsoup);
        progressBar.setVisibility(View.GONE);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
               String[] htmlUrls = {"http://m.58game.com/tu/12204"};
               new JsoupTask().execute(htmlUrls);
            }
        });
    }

    public String loadDocumentFromURL(String[] htmlUrl){
        StringBuilder result = new StringBuilder();
        try {
            Document document = Jsoup.connect(htmlUrl[0]).get();
            String title = document.title();
            result = result.append(title + "\n");

            Elements divs = document.getElementsByTag("div");           //找出所有的div元素
            for(Element div:divs){
                Elements imageLinks = div.getElementsByTag("img");      //找出所有的img元素
                for(Element imageLink:imageLinks){
                    String imageLinkHref = imageLink.attr("data-src");  //找出img元素中的data-src属性，也就是美女图片的url
                    result = result.append(imageLinkHref + "\n");
                }
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();
    }

    class JsoupTask extends AsyncTask<String,Void,String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... htmlUrl) {
            String result = loadDocumentFromURL(htmlUrl);
            return result;
        }

        @Override
        protected void onPostExecute(String result) {
            super.onPostExecute(result);
            progressBar.setVisibility(View.GONE);
            textViewShowJsoup.setText(result);
        }
    }

}
