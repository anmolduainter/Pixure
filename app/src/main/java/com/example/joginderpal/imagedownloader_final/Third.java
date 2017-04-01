package com.example.joginderpal.imagedownloader_final;

import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.transition.Fade;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by joginderpal on 23-12-2016.
 */
public class Third extends Activity {
    List<String> ls1;
    TextView tx;
    ImageView b1;
    List<String> ls2;
    Context ctx=Third.this;
   ImageView img;
    Typeface mycustomfont;
    ProgressDialog progressDialog1;
    TextView tx1;
    ProgressDialog progressDialog;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);

        setContentView(R.layout.third);
       // TextView tx = (TextView) findViewById(R.id.textView);
        tx1= (TextView) findViewById(R.id.tx);
        mycustomfont = Typeface.createFromAsset(getAssets(), "fonts/Pacifico.ttf");
         tx1.setTypeface(mycustomfont);
        b1 = (ImageView) findViewById(R.id.button);
       // tx.setText(a);
        img= (ImageView) findViewById(R.id.imageView);
        initanimation();
        String linkimage=getIntent().getExtras().getString("image");
     //   Animation anim= AnimationUtils.loadAnimation(getApplicationContext(),R.anim.move);
     ///   img.startAnimation(anim);
        Picasso.with(Third.this).load("http://www.hdwallpapers.in"+linkimage).into(img);
        b1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                     new doit().execute();

            }
        });
    }

    public class doit extends AsyncTask<Void, Void, Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressDialog1=new ProgressDialog(Third.this);
            progressDialog1.setMessage("LOADING");
            progressDialog1.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            try {
                String a = getIntent().getExtras().getString("linking");
                ls1 = new ArrayList<String>();
                ls2=new ArrayList<String>();
                Document document = Jsoup.connect("http://www.hdwallpapers.in"+a).get();
                Elements elements = document.getElementsByTag("div");
                for (Element el1 : elements) {
                    String resolution = el1.attr("class");
                    if (resolution.equals("wallpaper-resolutions")){
                        Elements elements1=el1.getElementsByTag("a");
                        for (Element e2:elements1){
                            String re=e2.attr("title");
                            String re1=e2.attr("href");
                            ls1.add(re);
                            ls2.add(re1);
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
            progressDialog1.dismiss();
            String[] array = new String[ls1.size()];
            for (int i = 0; i < ls1.size(); i++) {
                array[i] = ls1.get(i);
            }

      /*      AlertDialog.Builder builder = new AlertDialog.Builder(Third.this);
            builder.setTitle("Resolution")
                    .setItems(array, new DialogInterface.OnClickListener() {
                        public void onClick(DialogInterface dialog, int which) {

                            for (int i=0;i<ls2.size();i++){
                                if (which==i){
                                  //  new download().execute(ls2.get(which));
                                    //Toast.makeText(getApplicationContext(),i,Toast.LENGTH_LONG).show();

                                    String href="http://www.hdwallpapers.in"+ls2.get(which);

                                DownloadManager.Request request=new DownloadManager.Request(Uri.parse(href));
                                   request.setTitle("FILE DOWNLOADED");
                                    request.setDescription("FILE DOWNLOADING......");
                                    request.allowScanningByMediaScanner();
                                    request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
                                    String filename=URLUtil.guessFileName(href,null,MimeTypeMap.getFileExtensionFromUrl(href));
                                    request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,filename);
                                    DownloadManager manager= (DownloadManager) getApplicationContext().getSystemService(Context.DOWNLOAD_SERVICE);
                                    manager.enqueue(request);
                                   // Toast.makeText(getApplicationContext(),"DONE",Toast.LENGTH_SHORT).show();

                                    SuperToast supe=new SuperToast(Third.this);
                                    supe.setDuration(Style.DURATION_MEDIUM);
                                    supe.setText("Please Rate us 5 stars and keep sharing");
                                   supe.setAnimations(Style.ANIMATIONS_FLY);
                                    supe.setColor(Style.deepOrange().color);
                                    supe.setTextColor(Color.WHITE);
                                    supe.setTextSize(Style.TEXTSIZE_MEDIUM);
                                    supe.show();
                                   // supe.setIconResource(Style.IconPosition.)

                                    SuperToast supe1=new SuperToast(Third.this);
                                    supe1.setDuration(Style.DURATION_LONG);
                                    supe1.setText("Downloaded Image goes to Download section in File Manager");
                                    supe1.setAnimations(Style.ANIMATIONS_POP);
                                    supe1.setColor(Style.purple().color);
                                    supe1.setTextColor(Color.WHITE);
                                    supe1.setTextSize(Style.TEXTSIZE_MEDIUM);
                                    supe1.show();

                                }
                            }

                        }
                    });

            AlertDialog alertDialog=builder.create();
            alertDialog.show();*/


            final Dialog dialog=new Dialog(ctx);
            dialog.setContentView(R.layout.alertdialog);
            TextView text = (TextView) dialog.findViewById(R.id.tex2);
            text.setTypeface(mycustomfont);
            dialog.show();
            recyclerView= (RecyclerView) dialog.findViewById(R.id.rvalert);
            layoutManager=new LinearLayoutManager(Third.this);
            recyclerView.setLayoutManager(layoutManager);
            adapter=new RecyclerAdaptertwo(ls1,ls2,mycustomfont,dialog,Third.this);
            recyclerView.setAdapter(adapter);

        }
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    private void initanimation() {

        Fade enter=new Fade();
        enter.setDuration(1000);
        getWindow().setEnterTransition(enter);
    }


 /*   public class download extends AsyncTask<String,Integer,Void>{


    @Override
    protected Void doInBackground(String... voids) {


        String url="http://www.hdwallpapers.in"+voids[0];
        try {
            URL myurl=new URL(url);
            HttpURLConnection connection= (HttpURLConnection) myurl.openConnection();
            connection.setDoOutput(true);
            int file_length=connection.getContentLength();
            connection.setRequestMethod("GET");
            connection.connect();
            File rootDirectory=new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_PICTURES),"My Pictures");
            if (!rootDirectory.exists()){
                rootDirectory.mkdir();
            }

            String nameOfFile=URLUtil.guessFileName(url,null,
                    MimeTypeMap.getFileExtensionFromUrl(url));
            File file=new File(rootDirectory,nameOfFile);
            file.createNewFile();

            InputStream inputStream=connection.getInputStream();
            FileOutputStream output=new FileOutputStream(file);
            byte[] buffer=new byte[1024];
            int byteCount=0;
            int total=0;
            while((byteCount=inputStream.read(buffer))>0){
                total+=byteCount;
                output.write(buffer,0,byteCount);
                int progress=total*100/file_length;
                publishProgress(progress);
            }
            output.close();

            Intent intent=new Intent(Intent.ACTION_MEDIA_SCANNER_SCAN_FILE);
            intent.setData(Uri.fromFile(file));
            getApplicationContext().sendBroadcast(intent);

        } catch (MalformedURLException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    protected void onPostExecute(Void aVoid) {
        super.onPostExecute(aVoid);
        progressDialog.hide();
        Toast.makeText(getApplicationContext(),"Done ...",Toast.LENGTH_LONG).show();
    }

    @Override
    protected void onProgressUpdate(Integer... values) {
        super.onProgressUpdate(values);
        progressDialog.setProgress(values[0]);
    }

    @Override
    protected void onPreExecute() {
        super.onPreExecute();
        progressDialog=new ProgressDialog(Third.this);
        progressDialog.setTitle("Image is downloading......");
        progressDialog.setProgressStyle(progressDialog.STYLE_HORIZONTAL);
        progressDialog.setMax(0);
        progressDialog.setProgress(0);
        progressDialog.show();
    }*/

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        //finish();
    }
}




