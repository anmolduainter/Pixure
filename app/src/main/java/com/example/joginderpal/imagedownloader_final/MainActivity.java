package com.example.joginderpal.imagedownloader_final;

import android.app.ProgressDialog;
import android.content.Context;
import android.os.AsyncTask;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageButton;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    int pos=0;
    List<String> li;
    List<String> li1,li2,li3;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    RecyclerView recyclerView1;
    RecyclerView.LayoutManager layoutManager1;
    RecyclerView.Adapter adapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView1= (RecyclerView) findViewById(R.id.rvactivity_main);
        LayoutInflater vi = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View v = vi.inflate(R.layout.section, null);
        v.setVisibility(View.INVISIBLE);
        recyclerView= (RecyclerView) v.findViewById(R.id.rvsection);
        ViewGroup insertPoint = (ViewGroup) findViewById(R.id.rel);
        insertPoint.addView(v, 0, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));

        ImageButton im1= (ImageButton) findViewById(R.id.rot);
        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {


                if (pos==0){

                    RotateAnimation rotate1 = new RotateAnimation(180,0,
                            Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                            0.5f);
                    rotate1.setDuration(500);
                    TranslateAnimation translateAnimation=new TranslateAnimation(0.0f,0.0f,-1400.0f,0.0f);
                    translateAnimation.setDuration(500);
                    translateAnimation.setInterpolator(new LinearOutSlowInInterpolator());
                    Animation fade=new AlphaAnimation(0,1);
                    fade.setInterpolator(new DecelerateInterpolator());
                    fade.setDuration(550);
                    AnimationSet animationSet=new AnimationSet(true);
                    animationSet.addAnimation(translateAnimation);
                    animationSet.addAnimation(fade);
                    v.startAnimation(animationSet);
                    v1.startAnimation(rotate1);
                    translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {


                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {

                            v.setVisibility(View.VISIBLE);

                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {


                        }
                    });

                    pos=1;


                }


                else if (pos==1){


                    RotateAnimation rotate1 = new RotateAnimation(-180,0,
                            Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                            0.5f);
                    rotate1.setDuration(500);
                    TranslateAnimation translateAnimation=new TranslateAnimation(0,0,0,-2400);
                    translateAnimation.setDuration(500);
                    translateAnimation.setInterpolator(new LinearOutSlowInInterpolator());
                    Animation fade=new AlphaAnimation(1,0);
                    fade.setInterpolator(new DecelerateInterpolator());
                    fade.setDuration(550);
                    AnimationSet animationSet=new AnimationSet(true);
                    animationSet.addAnimation(translateAnimation);
                    animationSet.addAnimation(fade);
                    v.startAnimation(animationSet);
                    v1.startAnimation(rotate1);
                    translateAnimation.setAnimationListener(new Animation.AnimationListener() {
                        @Override
                        public void onAnimationStart(Animation animation) {


                        }

                        @Override
                        public void onAnimationEnd(Animation animation) {

                            v.setVisibility(View.INVISIBLE);

                        }

                        @Override
                        public void onAnimationRepeat(Animation animation) {


                        }
                    });

                    pos=0;




                }



            }
        });


        new doit().execute();

        new doit1().execute();

    }

    public class doit extends AsyncTask<Void, Void, Void> {
        ProgressDialog progressDialog;

        @Override
        protected void onPreExecute() {

            super.onPreExecute();
            progressDialog = new ProgressDialog(MainActivity.this);
            progressDialog.setMessage("Loading...");
            progressDialog.show();

        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                li = new ArrayList<String>();
                li1 = new ArrayList<String>();
                Document doc = Jsoup.connect("http://www.hdwallpapers.in/").get();
                Elements ul = doc.getElementsByTag("ul");
                for (Element e : ul) {
                    String class1 = e.attr("class");
                    if (class1.equals("side-panel categories")) {
                        Elements elements = e.getElementsByTag("li");
                        for (Element ele : elements) {

                            Elements elements1 = ele.getElementsByTag("a");
                            for (Element category : elements1) {
                                String links = category.attr("href");
                                li1.add(links);
                                String categories = category.text();
                                li.add(categories);


                            }
                        }
                    }
                }
            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

            @Override
            protected void onPostExecute (Void aVoid){
                super.onPostExecute(aVoid);
                progressDialog.dismiss();
                //  ArrayAdapter<String> arrayAdapter=new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,li);
                //  ls.setAdapter(arrayAdapter);
                // ls.setOnItemClickListener(new AdapterView.OnItemClickListener() {
             /*   @Override
                public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                   for(int a=0;a<28;a++){
                       if (i==a){
                           Intent intent=new Intent(MainActivity.this,second.class);
                           intent.putExtra("link",li1.get(a));
                           startActivity(intent);
                       }
                   }

                }
            });*/

                layoutManager = new LinearLayoutManager(MainActivity.this);
                recyclerView.setLayoutManager(layoutManager);
                adapter = new RecyclerAdapter(li, li1, MainActivity.this);
                recyclerView.setAdapter(adapter);

            }

        }



    public class doit1 extends AsyncTask<Void,Void,Void>{


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
                li2 = new ArrayList<>();
                li3 = new ArrayList<>();
                for (int i = 1; i <= 10; i++) {
                    Document document = Jsoup.connect("http://www.hdwallpapers.in/latest_wallpapers/page/"+i).get();
                    Elements elements = document.getElementsByTag("ul");
                    for (Element e : elements) {

                        if (e.attr("class").equals("wallpapers")) {

                            Elements a = e.getElementsByTag("a");
                            for (Element a1 : a) {

                                String href = a1.attr("href");
                                li2.add(href);
                                Element img = a1.getElementsByTag("img").first();
                                String src = img.attr("src");
                                li3.add(src);

                            }


                        }

                    }

                }

                }catch(IOException e){
                    e.printStackTrace();
                }



            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

           // layoutManager1 = new LinearLayoutManager(MainActivity.this);

            layoutManager1=new StaggeredGridLayoutManager(2,StaggeredGridLayoutManager.VERTICAL);
            layoutManager1.setAutoMeasureEnabled(true);
            recyclerView1.setLayoutManager(layoutManager1);
            adapter1 = new RecyclerAdapter_main(li2, li3, MainActivity.this);
            recyclerView1.setAdapter(adapter1);


        }

    }


}

