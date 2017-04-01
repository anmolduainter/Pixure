package com.example.joginderpal.imagedownloader_final;

import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by joginderpal on 09-02-2017.
 */
public class RecyclerAdapterBottom  extends RecyclerView.Adapter<RecyclerAdapterBottom.ViewHolder> {
    int j=0;
    List<String> li1,li;
    Typeface mycustomfont;
    Context ctx;
    List<String> f = new ArrayList<String>();// list of file paths
    File[] listFile ;
    public RecyclerAdapterBottom(List<String> li, Context ctx) {

        this.li=li;
      //  this.li1=li1;
        this.ctx=ctx;
      //  this.mycustomfont=mycustomfont;
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        private int currentitem;
        public ImageView itemTitle;
        public ProgressBar pro;
        public ViewHolder(View itemView) {
            super(itemView);

            itemTitle= (ImageView) itemView.findViewById(R.id.image_gif);
            //  itemTitle.setTypeface(mycustomfont);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                   // Intent i=new Intent(ctx,second.class);
                  //  i.putExtra("link",li1.get(position));
                   // ctx.startActivity(i);
                    String href=li.get(position);
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
                    final String filename = URLUtil.guessFileName(href, null, MimeTypeMap.getFileExtensionFromUrl(href));
                    //      request.setDestinationInExternalPublicDir(Environment.DIRECTORY_DOWNLOADS,filename);
                    request.setDestinationInExternalPublicDir("/image_downloader", filename);
                    DownloadManager manager = (DownloadManager)ctx.getSystemService(Context.DOWNLOAD_SERVICE);
                    manager.enqueue(request);


                    BroadcastReceiver onComplete = null;
                    onComplete = new BroadcastReceiver() {
                        public void onReceive(Context ctxt, Intent intent) {


                            File file = new File(Environment.getExternalStorageDirectory(), "/image_downloader");

                            if (file.isDirectory()) {
                                listFile = file.listFiles();


                                for (int i = 0; i < listFile.length; i++) {

                                    if (listFile[i].getName().equals(filename)) {
                                        f.add(listFile[i].getAbsolutePath());
                                    }

                                }


                            }



                            File f1=new File(f.get(0));
                            Intent shareIntent = new Intent(android.content.Intent.ACTION_SEND);
                            shareIntent.setType("image/gif");
                            Uri uri = Uri.fromFile(f1);
                            shareIntent.putExtra(Intent.EXTRA_STREAM, uri);
                            ctx.startActivity(Intent.createChooser(shareIntent, "Share Emoji"));



                        }
                    };

                    ctx.registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));




                }
            });
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout_bottom_sheet,parent,false);
        RecyclerView.ViewHolder v=new ViewHolder(view);



        return (ViewHolder) v;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Glide.with(ctx).load(li.get(position)).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).centerCrop().into(holder.itemTitle);
     /*   for (int i=1;i<li.size();i++) {
            if (j == 0) {
                holder.itemView.setBackgroundColor(Color.CYAN);
                j=1;
            }
            else if (j==1){
                holder.itemView.setBackgroundColor(Color.GREEN);
                j=0;
            }
        }*/
        //   Animation animation= AnimationUtils.loadAnimation(ctx,R.anim.bounce1);
        // holder.itemView.startAnimation(animation);
    }

    @Override
    public int getItemCount() {
        return li.size();
    }
}

