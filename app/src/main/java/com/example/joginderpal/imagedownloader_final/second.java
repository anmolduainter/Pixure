package com.example.joginderpal.imagedownloader_final;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.ActivityOptions;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.graphics.Bitmap;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import dmax.dialog.SpotsDialog;

/**
 * Created by joginderpal on 24-03-2017.
 */
public class second extends Activity {


    List<String> l1;
    List<String> l2, l3, l4, l5, l6;
    Bitmap bmp;
    int i = 0;
    int i1 = 0;
    private SwipeRefreshLayout swipeContainer;
    Dialog dialog;
    // ListView listview;
    private SpotsDialog pro;
    RecyclerView recyclerView;
    ActivityOptions options;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second);
        recyclerView = (RecyclerView) findViewById(R.id.rvsecond);
        swipeContainer = (SwipeRefreshLayout) findViewById(R.id.swipeContainer);
        swipeContainer.setOnRefreshListener(new SwipeRefreshLayout.OnRefreshListener() {
            @Override
            public void onRefresh() {
                // Your code to refresh the list here.
                i = 1;
                i1++;
                new doit().execute();

                // once the network request has completed successfully.
                //  fetchTimelineAsync(0);
            }
        });
        // Configure the refreshing colors
        swipeContainer.setColorSchemeResources(android.R.color.holo_blue_bright,
                android.R.color.holo_green_light,
                android.R.color.holo_orange_light,
                android.R.color.holo_red_light);

        //  options=ActivityOptions.makeSceneTransitionAnimation(second.this);

        //  listview= (ListView) findViewById(R.id.listView2);
        new doit().execute();
    }

    public class doit extends AsyncTask<Void, Void, Void> {

        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {
            //       progressDialog=new ProgressDialog(second.this);
            //      progressDialog.setMessage("Loading");
            if (i == 0) {

                //   pro = new SpotsDialog(second.this, R.style.Custom);
                //  pro.show();
                dialog = new Dialog(second.this);
                dialog.setContentView(R.layout.custom_progress);
                dialog.show();
                // progressView.setVisibility(View.VISIBLE);

            }
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            int i = 0;
            try {
                l1 = new ArrayList<String>();
                l2 = new ArrayList<String>();
                l3 = new ArrayList<>();
                l4 = new ArrayList<>();
                l5 = new ArrayList<>();
                l6 = new ArrayList<>();
                String link = getIntent().getExtras().getString("link");
                while (i <= 2) {
                    Document doc = Jsoup.connect("http://www.hdwallpapers.in" + link + "/page/" + i).get();
                    Elements elements = doc.getElementsByTag("ul");
                    for (Element e1 : elements) {
                        String a = e1.attr("class");
                        if (a.equals("wallpapers")) {
                            Elements el1 = e1.getElementsByTag("li");
                            for (Element el2 : el1) {
                                Elements el3 = el2.getElementsByTag("img");
                                for (Element el4 : el3) {
                                    String image = el4.attr("src");
                                    l1.add(image);

                                }
                                Elements e14 = el2.getElementsByTag("a");
                                for (Element e15 : e14) {
                                    String llink = e15.attr("href");
                                    l2.add(llink);
                                }
                            }

                        }
                    }
                    i++;
                }

            /*    else if (i==1){

                    clear();
                   // i=1;
                    Document doc = Jsoup.connect("http://www.hdwallpapers.in" + link + "/page/"+"2").get();
                    Elements elements = doc.getElementsByTag("ul");
                    for (Element e1 : elements) {
                        String a = e1.attr("class");
                        if (a.equals("wallpapers")) {
                            Elements el1 = e1.getElementsByTag("li");
                            for (Element el2 : el1) {
                                Elements el3 = el2.getElementsByTag("img");
                                for (Element el4 : el3) {
                                    String image = el4.attr("src");
                                    l3.add(image);

                                }
                                Elements e14 = el2.getElementsByTag("a");
                                for (Element e15 : e14) {
                                    String llink = e15.attr("href");
                                    l4.add(llink);
                                }
                            }

                        }
                    }


                }

*/

            } catch (IOException e) {
                e.printStackTrace();
            }

            // InputStream in = new URL(IMAGE_URL).openStream();
            //  bmp = BitmapFactory.decodeStream(in);


            return null;
        }

        @TargetApi(Build.VERSION_CODES.LOLLIPOP)
        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //    if (i==0) {
            //   pro.dismiss();

            for (int i = 0; i < l1.size(); i++) {

                if (i % 2 == 0) {
                    l3.add(l1.get(i));
                    l5.add(l2.get(i));
                } else {
                    l4.add(l1.get(i));
                    l6.add(l2.get(i));
                }

            }
            new doit2().execute();
            layoutManager = new LinearLayoutManager(second.this);
            recyclerView.setLayoutManager(layoutManager);
            adapter = new RecyclerAdapterone(l1, l2, l3, l4, l5, l6, options, i, second.this);
            recyclerView.setAdapter(adapter);
            dialog.dismiss();
            //  }
      /*      else if (i==1){
                clear();
              //  pro.dismiss();
                swipeContainer.setRefreshing(false);
                addAll(l3);
                layoutManager = new LinearLayoutManager(second.this);
                recyclerView.setLayoutManager(layoutManager);
                adapter = new RecyclerAdapterone(l3, l4,i, second.this);
                adapter.notifyDataSetChanged();
                recyclerView.setAdapter(adapter);


            }*/

        }

        public void clear() {
            l1.clear();
            l2.clear();
            // adapter.notifyDataSetChanged();
        }

        // Add a list of items
        public void addAll(List<String> list) {
            l1.addAll(list);
            adapter.notifyDataSetChanged();
        }
    }


    public class doit2 extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                i=3;
                while (i<= 20) {
                    String link=getIntent().getExtras().getString("link");
                    Document doc = Jsoup.connect("http://www.hdwallpapers.in" + link + "/page/" + i).get();
                    Elements elements = doc.getElementsByTag("ul");
                    for (Element e1 : elements) {
                        String a = e1.attr("class");
                        if (a.equals("wallpapers")) {
                            Elements el1 = e1.getElementsByTag("li");
                            for (Element el2 : el1) {
                                Elements el3 = el2.getElementsByTag("img");
                                for (Element el4 : el3) {
                                    String image = el4.attr("src");
                                    l1.add(image);

                                }
                                Elements e14 = el2.getElementsByTag("a");
                                for (Element e15 : e14) {
                                    String llink = e15.attr("href");
                                    l2.add(llink);
                                }
                            }

                        }
                    }


                 /*   for (int i1 = 0; i1 < l1.size(); i1++) {

                        if (i1 % 2 == 0) {
                            l3.add(l1.get(i1));
                            l5.add(l2.get(i1));
                        } else {
                            l4.add(l1.get(i1));
                            l6.add(l2.get(i1));
                        }

                    }
*/

                    i++;
                }
            }
            /*    else if (i==1){

                    clear();
                   // i=1;
                    Document doc = Jsoup.connect("http://www.hdwallpapers.in" + link + "/page/"+"2").get();
                    Elements elements = doc.getElementsByTag("ul");
                    for (Element e1 : elements) {
                        String a = e1.attr("class");
                        if (a.equals("wallpapers")) {
                            Elements el1 = e1.getElementsByTag("li");
                            for (Element el2 : el1) {
                                Elements el3 = el2.getElementsByTag("img");
                                for (Element el4 : el3) {
                                    String image = el4.attr("src");
                                    l3.add(image);

                                }
                                Elements e14 = el2.getElementsByTag("a");
                                for (Element e15 : e14) {
                                    String llink = e15.attr("href");
                                    l4.add(llink);
                                }
                            }

                        }
                    }


                }

*/

            catch(IOException e)

            {
                e.printStackTrace();
            }

            // InputStream in = new URL(IMAGE_URL).openStream();
            //  bmp = BitmapFactory.decodeStream(in);


            return null;


        }


        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            for (int i = 0; i < l1.size(); i++) {

                if (i % 2 == 0) {
                    l3.add(l1.get(i));
                    l5.add(l2.get(i));
                } else {
                    l4.add(l1.get(i));
                    l6.add(l2.get(i));
                }

            }
         /*   layoutManager = new LinearLayoutManager(second.this);
            recyclerView.setLayoutManager(layoutManager);
            adapter.notifyDataSetChanged();
            adapter = new RecyclerAdapterone(l1, l2, l3, l4, l5, l6, options, i, second.this);
            recyclerView.setAdapter(adapter);*/


        }
    }



}




