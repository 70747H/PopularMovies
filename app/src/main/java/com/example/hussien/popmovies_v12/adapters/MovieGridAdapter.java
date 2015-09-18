package com.example.hussien.popmovies_v12.adapters;

import android.content.Context;
import android.database.Cursor;
import android.net.Uri;
import android.support.v4.widget.CursorAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.hussien.popmovies_v12.R;
import com.example.hussien.popmovies_v12.data.MovieContract;
import com.example.hussien.popmovies_v12.model.Movie;

import java.util.List;

/**
 * Created by Hussien on 02-Sep-15.
 */
public class MovieGridAdapter extends  CursorAdapter {

    private final Context mContext;
    private final LayoutInflater mInflater;

    private final Movie mLock = new Movie();

    private List<Movie> mObjects=null;

    public MovieGridAdapter(Context context,Cursor c,int flags) {
        super(context,c,flags);
        mContext = context;
        mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.grid_item_movie, parent, false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
String image_url = "http://image.tmdb.org/t/p/w185" + movie.getImage();

        viewHolder = (ViewHolder) view.getTag();

        Glide.with(getContext()).load(image_url).into(viewHolder.imageView);
        viewHolder.titleView.setText(movie.getTitle());
    }

    public static class ViewHolder {
        public final ImageView imageView;
        public final TextView titleView;

        public ViewHolder(View view) {
            imageView = (ImageView) view.findViewById(R.id.grid_item_image);
            titleView = (TextView) view.findViewById(R.id.grid_item_title);
        }
    }
}

