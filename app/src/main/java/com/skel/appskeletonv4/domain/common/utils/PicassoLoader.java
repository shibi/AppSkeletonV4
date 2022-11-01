package com.skel.appskeletonv4.domain.common.utils;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;
import android.widget.Toast;

import com.squareup.picasso.Picasso;
import com.squareup.picasso.Target;


public class PicassoLoader {

    public static void loadImage(Context context, String imageUrl , ImageView imageView){
        try{

            Picasso.with(context).load(imageUrl)
                    .into(imageView, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError() {
                            Toast.makeText(context, "Image loading failed", Toast.LENGTH_SHORT).show();
                        }
                    });

        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(context, "Image loading failed", Toast.LENGTH_SHORT).show();
        }
    }

    public static void loadImageWithSave(Context context, String imageUrl, ImageView imageView, int placeholder, LoadedBitmapSaver loader)
    {
        try{

            Target target = new Target() {
                @Override
                public void onBitmapLoaded(Bitmap bitmap, Picasso.LoadedFrom from) {
                    imageView.setImageBitmap(bitmap);
                    loader.onBitmapLoaded(bitmap);
                }

                @Override
                public void onBitmapFailed(Drawable errorDrawable) {
                    Toast.makeText(context, "Image loading failed", Toast.LENGTH_SHORT).show();
                }

                @Override
                public void onPrepareLoad(Drawable placeHolderDrawable) {
                }
            };


            Picasso.with(context).load(imageUrl)
                    .placeholder(placeholder)
                    .into(target);

        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(context, "Image loading failed", Toast.LENGTH_SHORT).show();
        }
    }

    public static void loadImage(Context context, String imageUrl , ImageView imageView, int placeholder){
        try{

            Picasso.with(context).load(imageUrl)
                    .placeholder(placeholder)
                    .fit()
                    .into(imageView, new com.squareup.picasso.Callback() {
                        @Override
                        public void onSuccess() {

                        }

                        @Override
                        public void onError() {
                            Toast.makeText(context, "Image loading failed", Toast.LENGTH_SHORT).show();
                        }
                    });

        }catch (Exception e){
            e.printStackTrace();
            Toast.makeText(context, "Image loading failed", Toast.LENGTH_SHORT).show();
        }
    }

    public interface LoadedBitmapSaver{
        void onBitmapLoaded(Bitmap bitmap);
    }
}
