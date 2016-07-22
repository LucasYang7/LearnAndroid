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
                String[] htmlUrls = {"http://m.58game.com/tu/12204","http://www.mzitu.com/16993"};
                new JsoupTask().execute(htmlUrls);
            }
        });
    }

    /**
     * 使用DOM方法来遍历整个html文档
     * */
    public String loadDocumentFromURL(String[] htmlUrl){
        StringBuilder result = new StringBuilder("loadDocumentFromURL:\n");
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

    /**
     * 使用Selector语法来查找元素 注意使用手机访问mzitu.com与使用桌面浏览器访问mzitu.com得到的html文档不同
     * */
    public String selectorSyntaxInJsoup(String[] htmlUrl){
        StringBuilder result = new StringBuilder("selectorSyntaxInJsoup:\n");
        try {
            String href = "";
            String text = "";
            Document document = Jsoup.connect(htmlUrl[1]).get();
            result.append(document+"\n\n");

            result.append("所有链接:\n");
            // 获取当前页面中的所有链接
            Elements links = document.select("a[href]");
            for(Element link:links){
                href = link.attr("href");
                text = link.text();
                result.append(href + "\t" + text + "\n");
            }

            result.append("\n主要图片:\n");
            // 获取当前页面的显示图片的链接信息
            Element figureElements = document.select("figure").first();    // 获取figure标签
            Elements mainImageUrls = figureElements.select("img[src]");
            for(Element mainImageUrl:mainImageUrls){
                href = mainImageUrl.attr("src");
                text = mainImageUrl.attr("alt");
                result.append(href + "\t" + text + "\n");
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return result.toString();
    }

    /**
     * 使用Jsoup解析网页所对应的异步任务
     * */
    class JsoupTask extends AsyncTask<String,Void,String>{

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setVisibility(View.VISIBLE);
        }

        @Override
        protected String doInBackground(String... htmlUrl) {
            String result = "";
            result += loadDocumentFromURL(htmlUrl) + "\n";
            result += selectorSyntaxInJsoup(htmlUrl);
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
