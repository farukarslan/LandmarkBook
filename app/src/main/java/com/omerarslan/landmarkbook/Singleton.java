package com.omerarslan.landmarkbook;

import android.graphics.Bitmap;

public class Singleton {

    private Bitmap chosenImage;
    private static Singleton singleton;

    private Singleton(){ //constructor public iken private yaptık.

    }

    public Bitmap getChosenImage() {
        return chosenImage;
    }

    public void setChosenImage(Bitmap chosenImage) {
        this.chosenImage = chosenImage;
    }

    public static  Singleton getInstance(){ //sınıfın objesini al
        if (singleton == null){
            singleton = new Singleton();
        }

        return singleton;
    }
}
