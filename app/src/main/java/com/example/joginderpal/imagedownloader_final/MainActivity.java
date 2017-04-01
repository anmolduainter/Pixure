package com.example.joginderpal.imagedownloader_final;

import android.app.DownloadManager;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Environment;
import android.support.design.widget.AppBarLayout;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.design.widget.CollapsingToolbarLayout;
import android.support.v4.view.animation.LinearOutSlowInInterpolator;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.SearchView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.support.v7.widget.Toolbar;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.view.animation.DecelerateInterpolator;
import android.view.animation.RotateAnimation;
import android.view.animation.TranslateAnimation;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    int pos=0;
    int page_load=0;
    private boolean loading = true;                                // LOAD MORE RECYCLER VIEW
    int pastVisiblesItems, visibleItemCount, totalItemCount;        // SAME ABOVE
    List<String> li;
    List<String> li1;
    LinkedList<String> li2,li3;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    RecyclerView recyclerView1;
    BottomSheetDialogFragment myBottomSheet,myBottomSheet1;
    GridLayoutManager layoutManager1;
    RecyclerView.Adapter adapter1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        recyclerView1= (RecyclerView) findViewById(R.id.rvactivity_main);
        AppBarLayout appBarLayout= (AppBarLayout) findViewById(R.id.app_bar);
        final CollapsingToolbarLayout collapsingToolbarLayout= (CollapsingToolbarLayout) findViewById(R.id.collapsinggToolbar);
        Toolbar toolbar= (Toolbar) findViewById(R.id.tooolbar);

        setSupportActionBar(toolbar);
        LayoutInflater vi = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        final View v = vi.inflate(R.layout.section, null);
        v.setVisibility(View.INVISIBLE);
        recyclerView= (RecyclerView) v.findViewById(R.id.rvsection);
        layoutManager1=new GridLayoutManager(getApplicationContext(),2);
        layoutManager1.setAutoMeasureEnabled(true);
        recyclerView1.setLayoutManager(layoutManager1);
        ViewGroup insertPoint = (ViewGroup) findViewById(R.id.rel);
        insertPoint.addView(v, 0, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        final ImageButton im1= (ImageButton) insertPoint.findViewById(R.id.rot);

        ImageButton im2= (ImageButton) v.findViewById(R.id.rot1);

        appBarLayout.addOnOffsetChangedListener(new AppBarLayout.OnOffsetChangedListener() {

               boolean a=true;
               int scroll=-1;

            @Override
            public void onOffsetChanged(AppBarLayout appBarLayout, int verticalOffset) {

                if (scroll==-1){
                    scroll=appBarLayout.getTotalScrollRange();
                }

                if (scroll+verticalOffset==0){

                    im1.setVisibility(View.VISIBLE);
                    collapsingToolbarLayout.setTitle("Pixure");
                    collapsingToolbarLayout.setCollapsedTitleTextColor(Color.WHITE);
                    Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Pacifico.ttf");
                    collapsingToolbarLayout.setCollapsedTitleTypeface(custom_font);
                    a=true;
                }
                else if (a){

                    collapsingToolbarLayout.setTitle("Pixure");
                    collapsingToolbarLayout.setExpandedTitleColor(Color.WHITE);
                    collapsingToolbarLayout.setExpandedTitleGravity(Gravity.CENTER_HORIZONTAL);
                    Typeface custom_font = Typeface.createFromAsset(getAssets(),  "fonts/Pacifico.ttf");
                    collapsingToolbarLayout.setExpandedTitleTypeface(custom_font);
                    im1.setVisibility(View.INVISIBLE);
                    a=false;
                }


            }
        });





        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v1) {


                if (pos==0){

                    RotateAnimation rotate1 = new RotateAnimation(-90,0,
                            Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                            0.5f);
                    rotate1.setDuration(500);
                    RotateAnimation translateAnimation=new RotateAnimation(-90,0, Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f);
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


                    RotateAnimation rotate1 = new RotateAnimation(0,-90,
                            Animation.RELATIVE_TO_SELF, 0.5f, Animation.RELATIVE_TO_SELF,
                            0.5f);
                    rotate1.setDuration(500);
                    RotateAnimation translateAnimation=new RotateAnimation(0,-90, Animation.RELATIVE_TO_SELF,0f,Animation.RELATIVE_TO_SELF,0f);
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



        li2 = new LinkedList<>();
        li3 = new LinkedList<>();

        recyclerView1.addOnScrollListener(new RecyclerView.OnScrollListener() {
            @Override
            public void onScrolled(RecyclerView recyclerView, int dx, int dy) {

                if(dy>0){

                    visibleItemCount=layoutManager1.getChildCount();

                    totalItemCount=layoutManager1.getItemCount();
                    pastVisiblesItems =layoutManager1.findFirstVisibleItemPosition();

                    if (loading){

                        if ((visibleItemCount+pastVisiblesItems)>=totalItemCount){


                            loading=false;
                            new doit2().execute();
                           // loading=true;

                        }

                    }

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

       ProgressDialog pd;
        int a=0;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            a=li2.size();
            page_load++;
            pd=new ProgressDialog(MainActivity.this);
            pd.setMessage("Page Number : "+page_load);
            pd.show();

        }

        @Override
        protected Void doInBackground(Void... params) {

            try {
            //    for (int i = 1; i <= 10; i++) {
                    Document document = Jsoup.connect("http://www.hdwallpapers.in/latest_wallpapers/page/"+page_load).get();
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

              //  }

                }catch(IOException e){
                    e.printStackTrace();
                }



            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);                         // CANT UNDERSTAND WHAT WENT WRONG ???????????????????????

            pd.dismiss();
           // layoutManager1 = new LinearLayoutManager(MainActivity.this);
            adapter1 = new RecyclerAdapter_main(li2, li3, MainActivity.this);
           recyclerView1.setScrollBarSize(20);
            recyclerView1.setVerticalScrollBarEnabled(true);
            recyclerView1.getChildAt(a);
            recyclerView1.setAdapter(adapter1);
           // adapter1.notifyDataSetChanged();
//            loading =true;


        }

    }


    public class doit2 extends AsyncTask<Void,Void,Void>{


        ProgressDialog pd;

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            page_load++;
            pd=new ProgressDialog(MainActivity.this);
            pd.setMessage("Page Number : "+page_load);
            pd.show();
        }


        @Override
        protected Void doInBackground(Void... params) {

            try {
                //    for (int i = 1; i <= 10; i++) {
                Document document = Jsoup.connect("http://www.hdwallpapers.in/latest_wallpapers/page/"+page_load).get();
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

                //  }

            }catch(IOException e){
                e.printStackTrace();
            }



            return null;


        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);

            pd.dismiss();
            //layoutManager1 = new LinearLayoutManager(MainActivity.this);
          //  adapter1 = new RecyclerAdapter_main(li2, li3, MainActivity.this);
            recyclerView1.setScrollBarSize(20);
            recyclerView1.setVerticalScrollBarEnabled(true);
    //        recyclerView1.getChildAt(a);
  //           recyclerView1.setAdapter(adapter1);
             adapter1.notifyDataSetChanged();
            loading =true;


        }

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_search,menu);
        MenuItem item=menu.findItem(R.id.menusearchone);
        SearchView searchView= (SearchView) item.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {

                if (query.contains("@gif") || query.contains("@ gif")){

                    myBottomSheet = MyBottomSheetDialogFragment.newInstance(query);
                    myBottomSheet.show(getSupportFragmentManager(), myBottomSheet.getTag());

                }

                else if (query.contains("@youtube")){

                    Intent intent=new Intent(MainActivity.this,youtube.class);
                    startActivity(intent);

                }

                else if (query.contains("@url")){

                    String href = query.replace("@url","");

                    File direct = new File(Environment.getExternalStorageDirectory()
                            + "/image_downloader");

                    if (!direct.exists()) {
                        direct.mkdirs();
                    }


                    DownloadManager.Request request = new DownloadManager.Request(Uri.parse(href));
                    request.setTitle("Image Downloading");
                    request.setDescription("Downloading.....");
                    //   request.setMimeType("application/jpeg");
                    request.allowScanningByMediaScanner();
                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                    String filename = URLUtil.guessFileName(href, null, MimeTypeMap.getFileExtensionFromUrl(href));
                    //      request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,filename);
                    request.setDestinationInExternalPublicDir("/image_downloader", filename);
                    DownloadManager manager = (DownloadManager)getSystemService(Context.DOWNLOAD_SERVICE);
                    manager.enqueue(request);



                }

                else if (query.contains("@tut")){

                //    Intent i=new Intent(MainActivity.this,Splashtut.class);
                //    startActivity(i);
                    //overridePendingTransition(R.anim.bounce,R.anim.bounce1);

                }

                else {
                    Intent intent = new Intent(MainActivity.this, search.class);
                    intent.putExtra("link", query);
                    // intent.putExtra("text",query);
                    startActivity(intent);

                }
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {


                return false;
            }
        });

        MenuItem item1=menu.findItem(R.id.action_gallery);
        item1.setOnMenuItemClickListener(new MenuItem.OnMenuItemClickListener() {
            @Override
            public boolean onMenuItemClick(MenuItem menuItem) {

                Intent i=new Intent(MainActivity.this,Gallery.class);
                startActivity(i);
                return false;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }


    @Override
    public void onBackPressed() {
        AlertDialog.Builder alert=new AlertDialog.Builder(this);
        alert.setMessage("Are you you sure you want to exit");
        alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

                MainActivity.this.finish();

            }
        });
        alert.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {




            }
        });

        AlertDialog alertDialog=alert.create();
        alertDialog.show();
    }
}

