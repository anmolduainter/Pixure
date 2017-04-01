package com.example.joginderpal.imagedownloader_final;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.os.AsyncTask;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import net.bohush.geometricprogressview.GeometricProgressView;

import java.io.IOException;
import java.util.List;

/**
 * Created by joginderpal on 08-02-2017.
 */
public class RecyclerAdaptergallery extends RecyclerView.Adapter<RecyclerAdaptergallery.ViewHolder> {
    int j=0;
    List<String> li1,li;
//    Typeface mycustomfont;
    List<Bitmap> bitmaps;

    Context ctx;
//    ProgressDialog pd;
GeometricProgressView progressView;
    public RecyclerAdaptergallery(List<String> li, List<Bitmap> bitmaps, Context ctx) {

        this.li=li;
  //      this.li1=li1;
        this.ctx=ctx;
        this.bitmaps=bitmaps;
    //    this.mycustomfont=mycustomfont;
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        private int currentitem;
        public ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
            image= (ImageView) itemView.findViewById(R.id.imagegallery);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {

                    int pos=getAdapterPosition();


                    new doit(pos).execute();




                }
            });


            //itemView.setOn

      //      itemTitle.setTypeface(mycustomfont);
           /* itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    Intent i=new Intent(ctx,second.class);
                    i.putExtra("link",li1.get(position));
                    ctx.startActivity(i);

                }
            });*/
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout_gallery,parent,false);
        RecyclerView.ViewHolder v=new ViewHolder(view);



        return (ViewHolder) v;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {

        Glide.with(ctx).load(li.get(position)).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return li.size();
    }


    class doit extends AsyncTask<Void,Void,Void> {

        int pos;
        public doit(int pos) {

            this.pos=pos;

        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
           /* pd=new ProgressDialog(ctx);
            pd.setMessage("SETTING WALLPAPER");
            pd.setTitle("WALLPAPER");
            pd.show();
*/
            progressView=new GeometricProgressView(ctx);
            progressView.setType(GeometricProgressView.TYPE.KITE);
            progressView.setNumberOfAngles(6);
            progressView.setColor(Color.parseColor("#00897b"));
            progressView.setDuration(1000);
            progressView.setVisibility(View.VISIBLE);

        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                WallpaperManager wallpaperManager= WallpaperManager.getInstance(ctx);
                wallpaperManager.setBitmap(bitmaps.get(pos));

            } catch (IOException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(Void aVoid) {
            super.onPostExecute(aVoid);
            //pd.dismiss();
            progressView.setVisibility(View.GONE);
        }
    }



}
