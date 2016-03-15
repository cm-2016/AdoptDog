package com.example.karim.mascotasapp.api.models;

import android.net.Uri;
import android.os.Parcelable;

/**
 * Created by Karim on 09/03/16.
 */
public class Dog  {

    private String name;
    private String breed;
    private Uri image;

    public Dog(String name, String breed) {
        this.name = name;
        this.breed = breed;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBreed() {
        return breed;
    }

    public void setBreed(String breed) {
        this.breed = breed;
    }

    public Uri getImage() {
        return image;
    }

    public void setImage(Uri image) {
        this.image = image;
    }
}
