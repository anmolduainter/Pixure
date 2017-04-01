package com.example.joginderpal.imagedownloader_final;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.BottomSheetDialogFragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by joginderpal on 09-02-2017.
 */
public class MyBottomSheetDialogFragment  extends BottomSheetDialogFragment {

    String mString;
    TextView tx;
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;
    List<String> li;
    ProgressDialog d;
    ProgressBar pro;

    static MyBottomSheetDialogFragment newInstance(String string) {
        MyBottomSheetDialogFragment f = new MyBottomSheetDialogFragment();
        Bundle args = new Bundle();
        args.putString("string", string);
        f.setArguments(args);
        return f;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mString = getArguments().getString("string");
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View v = inflater.inflate(R.layout.bottomsheetmodal, container, false);

        pro= (ProgressBar) v.findViewById(R.id.pro);
        recyclerView= (RecyclerView) v.findViewById(R.id.rvbottomsheet);

      //  tx= (TextView) v.findViewById(R.id.tx10);

     //   layoutManager=new LinearLayoutManager(getContext());
     //   recyclerView.setLayoutManager(layoutManager);
     //   adapter=new RecyclerAdapterBottom(getContext());
     //   recyclerView.setAdapter(adapter);


        new doit().execute();

        return v;
    }


    public class doit extends AsyncTask<Void,Void,Void> {


        @Override
        protected void onPreExecute() {
            super.onPreExecute();
   //         d=new ProgressDialog(getContext());
     //       d.show();
        }

        @Override
        protected Void doInBackground(Void... voids) {

            try {
                li = new ArrayList<>();
                //   mString = getArguments().getString("string");
                String link = mString.replace("@gif", "");
                for (int i = 1; i <3; i++) {
                    Document doc = Jsoup.connect("http://www.reactiongifs.com/page/"+i+"/"+"?s=" + link + "&submit=Search").get();
                    Elements e1 = doc.getElementsByTag("div");
                    for (Element e2 : e1) {

                        String c = e2.attr("class");
                        if (c.equals("entry")) {
                         //   Element p = e2.getElementsByTag("p").first();
                            Element a =e2.getElementsByTag("a").first();
                            Element ig = a.getElementsByTag("img").first();
                            String src = ig.attr("src");
                            li.add(src);
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

        //    tx.setText(li.get(1));
              pro.setVisibility(View.INVISIBLE);
       //     d.dismiss();
            layoutManager=new GridLayoutManager(getContext(),2);
            recyclerView.setLayoutManager(layoutManager);
            adapter=new RecyclerAdapterBottom(li,getContext());
            recyclerView.setAdapter(adapter);


        }
    }


}