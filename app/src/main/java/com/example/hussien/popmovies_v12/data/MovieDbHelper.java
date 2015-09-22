package com.example.hussien.popmovies_v12.data;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by Hussien on 31-Aug-15.
 */
public class MovieDbHelper extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;

    static final String DATABASE_NAME = "movie.db";

    public MovieDbHelper(Context context) {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        final String SQL_CREATE_MOVIE_TABLE = "CREATE TABLE " + com.example.hussien.popmovies_v12.data.MovieContract.MovieEntry.TABLE_NAME + " (" +
                com.example.hussien.popmovies_v12.data.MovieContract.MovieEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, " +
                com.example.hussien.popmovies_v12.data.MovieContract.MovieEntry.COLUMN_MOVIE_ID + " INTEGER NOT NULL, " +
                com.example.hussien.popmovies_v12.data.MovieContract.MovieEntry.COLUMN_TITLE + " TEXT NOT NULL, " +
                com.example.hussien.popmovies_v12.data.MovieContract.MovieEntry.COLUMN_IMAGE + " TEXT, " +
                com.example.hussien.popmovies_v12.data.MovieContract.MovieEntry.COLUMN_IMAGE2 + " TEXT, " +
                com.example.hussien.popmovies_v12.data.MovieContract.MovieEntry.COLUMN_OVERVIEW + " TEXT, " +
                com.example.hussien.popmovies_v12.data.MovieContract.MovieEntry.COLUMN_RATING + " INTEGER, " +
                com.example.hussien.popmovies_v12.data.MovieContract.MovieEntry.COLUMN_DATE + " TEXT);";

        db.execSQL(SQL_CREATE_MOVIE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + com.example.hussien.popmovies_v12.data.MovieContract.MovieEntry.TABLE_NAME);
        onCreate(db);
    }
}
