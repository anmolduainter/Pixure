package com.example.joginderpal.imagedownloader_final;

import android.app.ActivityOptions;
import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by joginderpal on 03-01-2017.
 */
public class RecyclerAdapterone extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
 //   int j=0;
    List<String> l1,l2,l3,l4,l5,l6;
    Context ctx;
    int i;
  ActivityOptions options;



    public RecyclerAdapterone(List<String> l1, List<String> l2, List<String> l3, List<String> l4, List<String> l5, List<String> l6, ActivityOptions options, int i, Context ctx) {

        this.l1=l1;
        this.l2=l2;
        this.l3=l3;
        this.l4=l4;
        this.l5=l5;
        this.l6=l6;
        this.ctx=ctx;
        this.i=i;
        this.options=options;
    }



    class ViewHolder0 extends RecyclerView.ViewHolder{
        private int currentitem;
        public ImageView imageview;
        int i=0;

        public ViewHolder0(final View itemView) {
            super(itemView);
            imageview= (ImageView) itemView.findViewById(R.id.imageone);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
              //      Animation anim=AnimationUtils.loadAnimation(ctx,R.anim.move);
                //    itemView.startAnimation(anim);
                  //  if (anim.hasEnded()) {
                       Intent intent = new Intent(ctx, Third.class);
                        intent.putExtra("image", l3.get(position));
                        intent.putExtra("linking",l5.get(position));
                       // ctx.startActivity(intent,options.toBundle());
                    ctx.startActivity(intent);
                    //}
                }
            });
        }
    }


    class ViewHolder2 extends RecyclerView.ViewHolder{
        private int currentitem;
        public ImageView imageview1;
        public ImageView imageView2;

        public ViewHolder2(View itemView) {
            super(itemView);
            imageview1= (ImageView) itemView.findViewById(R.id.imagetwo);
            imageView2= (ImageView) itemView.findViewById(R.id.imagethree);
            imageview1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    Intent intent=new Intent(ctx,Third.class);
                    intent.putExtra("image",l4.get(position));
                    intent.putExtra("linking",l6.get(position));
                    //ctx.startActivity(intent,options.toBundle());
                    ctx.startActivity(intent);
                }
            });

            imageView2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    Intent intent=new Intent(ctx,Third.class);
                    intent.putExtra("image",l4.get(position+1));
                     intent.putExtra("linking",l6.get(position+1));
                    ctx.startActivity(intent);
                }
            });


        }
    }

    @Override
    public int getItemViewType(int position) {
        return position%2;
    }

    @Override
    public int getItemCount() {
        return l1.size();
    }


    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        int layout_res = 0;

        View view;
        RecyclerView.ViewHolder v;


        switch (viewType) {
            case 0:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardlayout1, parent, false);
                v = new ViewHolder0(view);

                // return (RecyclerAdapterone.ViewHolder) v;
                return  v;

            case 1:
                view = LayoutInflater.from(parent.getContext()).inflate(R.layout.cardlayout2, parent, false);
                v = new ViewHolder2(view);
                  // return (RecyclerAdapterone.ViewHolder) v;
                return  v;

        }

      return null;

        //     switch (viewType){
        //        case 0:
        //       layout_res=R.layout.cardlayout1;

        //          break;
        //   case 2:
        //     layout_res=R.layout.cardlayout2;
        // }

        //  View view = LayoutInflater.from(parent.getContext()).inflate(layout_res, parent, false);
        //  RecyclerView.ViewHolder v = new ViewHolder(view);

        // return (ViewHolder) v;
    }


    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {


        int lastposition=-1;


       /* l3=new ArrayList<>();
        l4=new ArrayList<>();


     for (int i=0;i<l1.size();i++){

          if(i%2==0){
              l3.add(l1.get(i));
          }
          else{
              l4.add(l1.get(i));
          }

      }*/


           if (holder.getItemViewType()==0) {
               ViewHolder0 v = (ViewHolder0) holder;

               if (position < l3.size()) {
                   Picasso.with(ctx).load("http://www.hdwallpapers.in" + l3.get(position)).fit().into(v.imageview);
                  // Glide.with(ctx).load("http://www.hdwallpapers.in" + l3.get(position)).into(v.imageview);
               }

               if(position>lastposition){
                   //Animation anim= AnimationUtils.loadAnimation(ctx,R.anim.bounce1);
                 // v.imageview.startAnimation(anim);
                   lastposition++;
               }



              // break;
           }
            if (position<l3.size()) {
                if (holder.getItemViewType()==1) {
                    ViewHolder2 v1 = (ViewHolder2) holder;
                    //   RecyclerView.ViewHolder v=holder;
                    if (position < l4.size()) {
                        Picasso.with(ctx).load("http://www.hdwallpapers.in" + l4.get(position)).fit().into(v1.imageview1);
                    }
                    if ((position + 1) < l4.size() - 1) {
                        Picasso.with(ctx).load("http://www.hdwallpapers.in" + l4.get(position + 1)).fit().into(v1.imageView2);
                    }

                 /*   if(position>lastposition){
                        Animation anim= AnimationUtils.loadAnimation(ctx,R.anim.bounce);
                        v1.imageview1.startAnimation(anim);
                        lastposition++;
                    }
                    */


                }


            }


    }





    }




  /*  @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {

        // Picasso.with(ctx).load("http://www.hdwallpapers.in"+l1.get(position)).fit().into(holder.imageview);
        // Animation animation= AnimationUtils.loadAnimation(ctx,R.anim.bounce);
        // holder.itemView.startAnimation(animation);
        switch (holder.getItemViewType()) {
            case 0:
                Picasso.with(ctx).load("http://www.hdwallpapers.in" + l1.get(position)).fit().into(holder.imageview);
                break;

            case 2:
                RecyclerView.ViewHolder v=holder;
                Picasso.with(ctx).load("http://www.hdwallpapers.in" + l1.get(position)).fit().into(holder.);
                Picasso.with(ctx).load("http://www.hdwallpapers.in" + l1.get(position)).fit().into(holder.imageview);
                break;

        }
    }

*/
   /* @Override
    public int getItemCount() {
        return l1.size();
    }*/
  /*  public Bitmap getImage(String url) throws IOException {

        Bitmap bmp;
        InputStream in = new URL("http://www.hdwallpapers.in/thumbs/2016/paintwave_yellow-t1.jpg").openStream();
        bmp = BitmapFactory.decodeStream(in);
        return bmp;

    }*/


  /*  public static Drawable LoadImageFromWebOperations(String url) {
        try {
            InputStream is = (InputStream) new URL(url).getContent();
            Drawable d = Drawable.createFromStream(is, "http://www.hdwallpapers.in/thumbs/2016/paintwave_yellow-t1.jpg");
            return d;
        } catch (Exception e) {
            return null;
        }
    }*/



