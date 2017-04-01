package com.example.joginderpal.imagedownloader_final;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by joginderpal on 08-02-2017.
 */
public class Gallery extends AppCompatActivity {

    List<String> f = new ArrayList<String>();// list of file paths
    File[] listFile ;
   List<Bitmap> li=new ArrayList();
    RecyclerView recyclerView;
    RecyclerView.LayoutManager layoutManager;
    RecyclerView.Adapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.gallery);
        // getFromSdcard();

        File file = new File(Environment.getExternalStorageDirectory(), "/image_downloader");

        if (file.isDirectory()) {
            listFile = file.listFiles();


            for (int i = 0; i < listFile.length; i++) {

                f.add(listFile[i].getAbsolutePath());
                BitmapFactory.Options bmOptions = new BitmapFactory.Options();
                Bitmap bitmap = BitmapFactory.decodeFile(listFile[i].getAbsolutePath(),bmOptions);
                li.add(bitmap);

            }


       //     ImageView imageView = (ImageView) findViewById(R.id.image1);

       //     Glide.with(this).load(f.get(2)).into(imageView);
            recyclerView= (RecyclerView) findViewById(R.id.rvgallery);

            layoutManager=new GridLayoutManager(Gallery.this,1);
            recyclerView.setLayoutManager(layoutManager);
            adapter=new RecyclerAdaptergallery(f,li,Gallery.this);
            recyclerView.setAdapter(adapter);

        }
    }



}


