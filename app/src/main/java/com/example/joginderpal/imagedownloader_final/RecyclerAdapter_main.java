package com.example.joginderpal.imagedownloader_final;

import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
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
 * Created by joginderpal on 24-03-2017.
 */
public class RecyclerAdapter_main  extends RecyclerView.Adapter<RecyclerAdapter_main.ViewHolder> {
    int j=0;
    List<String> li1,li;
    Typeface mycustomfont;
    Context ctx;
    public RecyclerAdapter_main(List<String> li, List<String> li1, Context ctx) {

        this.li=li;
        this.li1=li1;
        this.ctx=ctx;
        this.mycustomfont=mycustomfont;
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        private int currentitem;
        public ImageView im;

        public ImageButton im1;
        public ViewHolder(View itemView) {
            super(itemView);
            im= (ImageView) itemView.findViewById(R.id.image_recycler_main);
            im1= (ImageButton) itemView.findViewById(R.id.activity_main_download);
            //  itemTitle.setTypeface(mycustomfont);
            im1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    int pos=getAdapterPosition();
                     Intent i=new Intent(ctx,Third.class);

                     i.putExtra("linking",li.get(pos));
                    i.putExtra("image",li1.get(pos));
                    ctx.startActivity(i);

                }
            });
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout_main,parent,false);
        RecyclerView.ViewHolder v=new ViewHolder(view);

        return (ViewHolder) v;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {


        Picasso.with(ctx).load("http://www.hdwallpapers.in"+li1.get(position)).fit().into(holder.im);

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

