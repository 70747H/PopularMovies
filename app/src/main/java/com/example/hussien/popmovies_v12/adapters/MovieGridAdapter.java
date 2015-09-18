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
//        mObjects = objects;
    }

    public Context getContext() {
        return mContext;
    }

    public void add(Movie object) {
        synchronized (mLock) {
            mObjects.add(object);
        }
        notifyDataSetChanged();
    }

    public void clear() {
        synchronized (mLock) {
            mObjects.clear();
        }
        notifyDataSetChanged();
    }

    public void setData(List<Movie> data) {
        clear();
        for (Movie movie : data) {
            add(movie);
        }
    }

    @Override
    public int getCount() {
        return mObjects.size();
    }

    @Override
    public Movie getItem(int position) {
        return mObjects.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        ViewHolder viewHolder;

        if (view == null) {
            view = mInflater.inflate(R.layout.grid_item_movie, parent, false);
            viewHolder = new ViewHolder(view);
            view.setTag(viewHolder);
        }

        final Movie movie = getItem(position);

        String image_url = "http://image.tmdb.org/t/p/w185" + movie.getImage();

        viewHolder = (ViewHolder) view.getTag();

        Glide.with(getContext()).load(image_url).into(viewHolder.imageView);
        viewHolder.titleView.setText(movie.getTitle());

        return view;
    }

    @Override
    public View newView(Context context, Cursor cursor, ViewGroup parent) {
        View view = LayoutInflater.from(context).inflate(R.layout.grid_item_movie, parent, false);
        return view;
    }

    @Override
    public void bindView(View view, Context context, Cursor cursor) {
        ViewHolder holder = new ViewHolder(view);
        ImageView imageView = holder.imageView;
        TextView titleView = holder.titleView;
        int movieImageColumn = cursor.getColumnIndex(MovieContract.MovieEntry.COLUMN_IMAGE);
        String moviePoster = cursor.getString(movieImageColumn);
        Uri imageUri = Uri.parse("http://image.tmdb.org/t/p/").buildUpon()
                .appendPath(context.getString(R.string.api_image_size_medium))
                .appendPath(moviePoster.substring(1))
                .build();

        Glide.with(context).load(imageUri)
                .placeholder(R.drawable.loading)
                .into(imageView);
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

