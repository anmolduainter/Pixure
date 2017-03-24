package com.example.joginderpal.imagedownloader_final;

import android.app.Dialog;
import android.app.DownloadManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.Color;
import android.graphics.Typeface;
import android.net.Uri;
import android.os.Environment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.MimeTypeMap;
import android.webkit.URLUtil;
import android.widget.TextView;

import com.github.johnpersano.supertoasts.library.Style;
import com.github.johnpersano.supertoasts.library.SuperToast;

import java.io.File;
import java.util.List;

/**
 * Created by joginderpal on 05-02-2017.
 */
public class RecyclerAdaptertwo extends RecyclerView.Adapter<RecyclerAdaptertwo.ViewHolder> {
        int j=0;
        List<String> li1,li,ls2;
        Typeface mycustomfont;
        Context ctx;

        Dialog dialog;
public RecyclerAdaptertwo(List<String> li, List<String> ls2, Typeface mycustomfont, Dialog dialog, Context ctx) {

        this.li=li;
       // this.li1=li1;
        this.ls2=ls2;
    this.dialog=dialog;
        this.ctx=ctx;
        this.mycustomfont=mycustomfont;
        }


class ViewHolder extends RecyclerView.ViewHolder{
    private int currentitem;
    public TextView itemTitle;

    public ViewHolder(View itemView) {
        super(itemView);
        itemTitle= (TextView) itemView.findViewById(R.id.tax3);
        itemTitle.setTypeface(mycustomfont);
        itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int position = getAdapterPosition();
                // Intent i=new Intent(ctx,second.class);
                //  i.putExtra("link",li1.get(position));
                //  ctx.startActivity(i);

                String href = "http://www.hdwallpapers.in" + ls2.get(position);

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
                DownloadManager manager = (DownloadManager) ctx.getSystemService(Context.DOWNLOAD_SERVICE);
                manager.enqueue(request);
                BroadcastReceiver onComplete = null;
                onComplete = new BroadcastReceiver() {
                    public void onReceive(Context ctxt, Intent intent) {

                 //       ctx.startActivity(new Intent(DownloadManager.ACTION_VIEW_DOWNLOADS));


                       Intent in = new Intent(Intent.ACTION_GET_CONTENT);
                        Uri uri = Uri.parse(Environment.getExternalStorageDirectory().getPath()
                                + "/image_downloader/");
                        in.setDataAndType(uri, "image/*");
                        ctx.startActivity(Intent.createChooser(in, "Open folder"));


                    }
                };
                dialog.dismiss();
                // Toast.makeText(getApplicationContext(),"DONE",Toast.LENGTH_SHORT).show();

                SuperToast supe = new SuperToast(ctx);
                supe.setDuration(Style.DURATION_MEDIUM);
                supe.setText("Please Rate us 5 stars and keep sharing");
                supe.setAnimations(Style.ANIMATIONS_FLY);
                supe.setColor(Style.deepOrange().color);
                supe.setTextColor(Color.WHITE);
                supe.setTextSize(Style.TEXTSIZE_MEDIUM);
                supe.show();
                // supe.setIconResource(Style.IconPosition.)

                SuperToast supe1 = new SuperToast(ctx);
                supe1.setDuration(Style.DURATION_LONG);
                supe1.setText("Downloaded Image goes to Download section in File Manager");
                supe1.setAnimations(Style.ANIMATIONS_POP);
                supe1.setColor(Style.purple().color);
                supe1.setTextColor(Color.WHITE);
                supe1.setTextSize(Style.TEXTSIZE_MEDIUM);
                supe1.show();




                ctx.registerReceiver(onComplete, new IntentFilter(DownloadManager.ACTION_DOWNLOAD_COMPLETE));



            }
            });



            }
        }



    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_alert,parent,false);
        RecyclerView.ViewHolder v=new ViewHolder(view);



        return (ViewHolder) v;
    }


    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.itemTitle.setText(li.get(position));

        }
        //   Animation animation= AnimationUtils.loadAnimation(ctx,R.anim.bounce1);
        // holder.itemView.startAnimation(animation);


    @Override
    public int getItemCount() {
        return li.size();
    }
}

