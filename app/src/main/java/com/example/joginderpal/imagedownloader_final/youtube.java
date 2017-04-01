package com.example.joginderpal.imagedownloader_final;

import android.content.Intent;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

/**
 * Created by joginderpal on 13-02-2017.
 */
public class youtube extends AppCompatActivity {

   EditText ed1;
   Button b1;
    String[] names=new String[100];

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.youtube);
        ed1= (EditText) findViewById(R.id.ed_you);
        b1= (Button) findViewById(R.id.you_button);

        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String message=ed1.getText().toString();
                names=message.split("\n");
                new LongOperation().execute("");

            }
        });
    }

    private class LongOperation extends AsyncTask<String,Integer,String> {

        @Override
        protected String doInBackground(String... strings) {
            String string ="http://www.youtube.com/watch_videos?video_ids=";
            String a="";
            for (int i=0;i<names.length;i++){
                try {
                    a+=link(names[i])+",";

                } catch (IOException e) {
                    e.printStackTrace();
                }

            }
            a=a.substring(0,a.length()-1);
            string+=a;
            return string;
        }

        @Override
        protected void onPostExecute(final String s) {
            super.onPostExecute(s);
       //     pb.setVisibility(View.INVISIBLE);
         //   ImageButton b2= (ImageButton) findViewById(R.id.button2);
        //    b2.setVisibility(View.VISIBLE);
        //    b2.setOnClickListener(new View.OnClickListener() {
          //      @Override
        //        public void onClick(View view) {
                    Intent i=new Intent(Intent.ACTION_VIEW, Uri.parse(s));
                    startActivity(i);
        //        }
       //     });

        }
    }
    public static String link(String name) throws IOException {
        String url="https://www.youtube.com/results?search_query="+name;
        Document doc= Jsoup.connect(url).userAgent("Mozilla/5.0 (Windows NT 5.1) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/27.0.1453.110 Safari/537.36").get();
        Element list=doc.select("h3.yt-lockup-title > a").first();
        String ok=list.attr("href");
        return ok.substring(9);
    }
}



