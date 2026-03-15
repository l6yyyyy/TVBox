package com.example.tvbox;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.drawable.Drawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.leanback.widget.Presenter;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

public class CardPresenter extends Presenter {

    private static final int CARD_WIDTH = 313;
    private static final int CARD_HEIGHT = 460;
    private static Context mContext;

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent) {
        mContext = parent.getContext();

        View view = LayoutInflater.from(mContext).inflate(R.layout.card_view, parent, false);
        view.setFocusable(true);
        view.setFocusableInTouchMode(true);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder viewHolder, Object item) {
        Movie movie = (Movie) item;
        View rootView = viewHolder.view;

        ImageView posterImageView = rootView.findViewById(R.id.poster_image);
        TextView titleTextView = rootView.findViewById(R.id.title_text);

        titleTextView.setText(movie.getTitle());

        // 使用Glide加载海报图片
        Glide.with(mContext)
                .load(movie.getPosterUrl())
                .apply(new RequestOptions()
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_background)
                        .transform(new RoundedCorners(8)))
                .into(posterImageView);
    }

    @Override
    public void onUnbindViewHolder(ViewHolder viewHolder) {
        // 清理资源
        ImageView posterImageView = viewHolder.view.findViewById(R.id.poster_image);
        Glide.with(mContext).clear(posterImageView);
    }

    public static class ViewHolder extends Presenter.ViewHolder {
        public ViewHolder(View view) {
            super(view);
        }
    }
}
