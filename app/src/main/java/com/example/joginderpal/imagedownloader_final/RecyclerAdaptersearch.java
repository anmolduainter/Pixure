package com.example.joginderpal.imagedownloader_final;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import java.util.List;

/**
 * Created by joginderpal on 08-02-2017.
 */
public class RecyclerAdaptersearch extends RecyclerView.Adapter<RecyclerAdaptersearch.ViewHolder> {
    int j=0;
    List<String> li1,li;
    Typeface mycustomfont;
    Context ctx;
    public RecyclerAdaptersearch(List<String> li, List<String> li1, Context ctx) {

        this.li=li;
        this.li1=li1;
        this.ctx=ctx;
        this.mycustomfont=mycustomfont;
    }


    class ViewHolder extends RecyclerView.ViewHolder{
        private int currentitem;
        public ImageView image;

        public ViewHolder(View itemView) {
            super(itemView);
         //   itemTitle= (TextView) itemView.findViewById(R.id.tx);
         //   itemTitle.setTypeface(mycustomfont);
            image= (ImageView) itemView.findViewById(R.id.imagesearch);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position=getAdapterPosition();
                    Intent i=new Intent(ctx,Third.class);
                    i.putExtra("linking",li.get(position));
                    ctx.startActivity(i);

                }
            });
        }
    }


    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {


        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.card_layout_search,parent,false);
        RecyclerView.ViewHolder v=new ViewHolder(view);



        return (ViewHolder) v;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
       // holder.itemTitle.setText(li.get(position));
      /*  for (int i=1;i<li.size();i++) {
            if (j == 0) {
                holder.itemView.setBackgroundColor(Color.CYAN);
                j=1;
            }
            else if (j==1){
                holder.itemView.setBackgroundColor(Color.GREEN);
                j=0;
            }
        }
*/        //   Animation animation= AnimationUtils.loadAnimation(ctx,R.anim.bounce1);
        // holder.itemView.startAnimation(animation);

        Picasso.with(ctx).load(li1.get(position)).fit().into(holder.image);


    }

    @Override
    public int getItemCount() {
        return li1.size();
    }
}
