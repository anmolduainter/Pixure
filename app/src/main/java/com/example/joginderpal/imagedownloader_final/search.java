package com.example.joginderpal.imagedownloader_final;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by joginderpal on 08-02-2017.
 */
public class search extends AppCompatActivity {


    List<String> li1,li2;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    TextView tx;

    ProgressDialog progressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.search);
        recyclerView= (RecyclerView) findViewById(R.id.rvsearch);
      //  tx= (TextView) findViewById(R.id.textsearch);
        new doit().execute();
    }

    public class doit extends AsyncTask<Void,Void,Void> {

        @Override
        protected void onPreExecute() {

             progressDialog=new ProgressDialog(search.this);
             progressDialog.setMessage("Please wait For Amazing Images....");
             progressDialog.show();

            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {

                li1 = new ArrayList<String>();
                li2 = new ArrayList<String>();
                String q = getIntent().getExtras().getString("link");
            try{
                Document doc = Jsoup.connect("http://www.hdwallpapers.in/search.html?q=" + q).get();

                Elements elements=doc.getElementsByTag("ul");
                for (Element e1:elements){

                    String clas=e1.attr("class");
                    if (clas.equals("wallpapers")){

                        Elements li=e1.getElementsByTag("li");
                        for (Element e2:li){

                            String la=e2.attr("class");
                            if (la.equals("wall")){

                                Elements a=e2.getElementsByTag("a");
                                for (Element e3: a){

                                    String href=e3.attr("href");
                                    li1.add(href);

                                    Element image=e3.select("img").first();
                                    String link=image.absUrl("src");
                                    li2.add(link);

                                }

                            }

                        }


                    }

                }



            } catch (IOException e) {
                e.printStackTrace();
            }




        /*    try {
                Document doc=Jsoup.connect("http://wallpapercave.com/search?q="+q).get();
                Elements div=doc.getElementsByTag("div");
                for (Element e1: div){

                    String id=e1.attr("id");
                    if (id.equals("popular")){

                        Element a=e1.getElementsByTag("a").first();
                        String href=a.attr("href");
                        li1.add(href);
                    }

                }



            } catch (IOException e) {
                e.printStackTrace();
            }

*/
            return null;

        }

        @Override
        protected void onPostExecute(Void aVoid) {

            super.onPostExecute(aVoid);

            progressDialog.dismiss();

  //          new doit1().execute();

          //  tx.setText(li2.get(0));

           layoutManager=new LinearLayoutManager(search.this);
            recyclerView.setLayoutManager(layoutManager);
            adapter=new RecyclerAdaptersearch(li1,li2,search.this);
            recyclerView.setAdapter(adapter);

        }
    }


   /* public class doit1 extends AsyncTask<Void,Void,Void>{


        @Override
        protected Void doInBackground(Void... voids) {

            try {
                Document doc=Jsoup.connect("http://wallpapercave.com"+li1.get(0)).get();
                Elements div=doc.getElementsByTag("div");
                for (Element e1:div){

                    String cla=e1.attr("class");
                    if (cla.equals("wallpaper")){

                        Elements image=e1.getElementsByTag("img");
                        for (Element im: image){

                            String src=im.attr("src");
                            li2.add(src);

                        }

                    }
                }


            } catch (IOException e) {
                e.printStackTrace();
            }


            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            progressDialog.dismiss();
            layoutManager=new LinearLayoutManager(search.this);
            recyclerView.setLayoutManager(layoutManager);
            adapter=new RecyclerAdaptersearch(li1,li2,search.this);
            recyclerView.setAdapter(adapter);

        }
    }

*/

}
