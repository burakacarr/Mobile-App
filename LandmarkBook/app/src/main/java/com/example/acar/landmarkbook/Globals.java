package com.example.acar.landmarkbook;

import android.graphics.Bitmap;

public class Globals {
    private Bitmap chosenImages;
    private  static Globals instance;
    public Globals(){

    }


    public void setData(Bitmap chosenImages){
        this.chosenImages=chosenImages;
    }

    public Bitmap getData(){
        return chosenImages;
    }

    public static Globals getInstance(){
        if(instance==null){
            instance = new Globals();
        }
        return instance;
    }
}
