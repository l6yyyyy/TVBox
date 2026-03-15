package com.example.tvbox;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.bumptech.glide.request.RequestOptions;

public class MovieDetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_movie_detail);

        // 获取传递的电影信息
        String title = getIntent().getStringExtra("title");
        String posterUrl = getIntent().getStringExtra("posterUrl");
        String year = getIntent().getStringExtra("year");
        String genre = getIntent().getStringExtra("genre");
        String director = getIntent().getStringExtra("director");
        String actors = getIntent().getStringExtra("actors");
        String plot = getIntent().getStringExtra("plot");

        // 初始化UI组件
        ImageView posterImageView = findViewById(R.id.detail_poster_image);
        TextView titleTextView = findViewById(R.id.detail_title_text);
        TextView yearTextView = findViewById(R.id.detail_year_text);
        TextView genreTextView = findViewById(R.id.detail_genre_text);
        TextView directorTextView = findViewById(R.id.detail_director_text);
        TextView actorsTextView = findViewById(R.id.detail_actors_text);
        TextView plotTextView = findViewById(R.id.detail_plot_text);
        Button playButton = findViewById(R.id.detail_play_button);
        Button favoriteButton = findViewById(R.id.detail_favorite_button);

        // 设置电影信息
        titleTextView.setText(title);
        yearTextView.setText(year);
        genreTextView.setText(genre);
        directorTextView.setText(director);
        actorsTextView.setText(actors);
        plotTextView.setText(plot);

        // 加载海报图片
        Glide.with(this)
                .load(posterUrl)
                .apply(new RequestOptions()
                        .placeholder(R.drawable.ic_launcher_background)
                        .error(R.drawable.ic_launcher_background)
                        .transform(new RoundedCorners(8)))
                .into(posterImageView);

        // 设置按钮点击事件
        playButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 跳转到播放器页面
                Intent intent = new Intent(MovieDetailActivity.this, PlayerActivity.class);
                intent.putExtra("title", title);
                intent.putExtra("videoUrl", "https://example.com/play.mp4"); // 这里使用模拟的视频URL
                startActivity(intent);
            }
        });

        favoriteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MovieDetailActivity.this, "收藏成功", Toast.LENGTH_SHORT).show();
            }
        });
    }
}
