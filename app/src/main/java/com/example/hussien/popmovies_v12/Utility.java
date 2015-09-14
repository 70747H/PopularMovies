package com.example.hussien.popmovies_v12;

/**
 * Created by Hussien on 31-Aug-15.
 */
public class Utility {

    public static String buildImageUrl(int width, String fileName) {
        return "http://image.tmdb.org/t/p/w" + Integer.toString(width) + fileName;
    }
}
