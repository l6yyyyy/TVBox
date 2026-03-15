package com.example.tvbox;

import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.exoplayer2.ExoPlayer;
import com.google.android.exoplayer2.MediaItem;
import com.google.android.exoplayer2.Player;
import com.google.android.exoplayer2.SimpleExoPlayer;
import com.google.android.exoplayer2.ui.PlayerView;

public class PlayerActivity extends AppCompatActivity implements Player.Listener {

    private PlayerView playerView;
    private SimpleExoPlayer player;
    private ImageButton playPauseButton;
    private TextView titleTextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);

        // 初始化UI组件
        playerView = findViewById(R.id.player_view);
        playPauseButton = findViewById(R.id.play_pause_button);
        titleTextView = findViewById(R.id.player_title_text);

        // 获取传递的视频信息
        String title = getIntent().getStringExtra("title");
        String videoUrl = getIntent().getStringExtra("videoUrl");

        // 设置标题
        titleTextView.setText(title);

        // 初始化播放器
        initializePlayer(videoUrl);

        // 设置播放/暂停按钮点击事件
        playPauseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (player.isPlaying()) {
                    player.pause();
                    playPauseButton.setImageResource(R.drawable.ic_play);
                } else {
                    player.play();
                    playPauseButton.setImageResource(R.drawable.ic_pause);
                }
            }
        });
    }

    private void initializePlayer(String videoUrl) {
        // 创建播放器实例
        player = new SimpleExoPlayer.Builder(this).build();
        playerView.setPlayer(player);
        player.addListener(this);

        // 创建媒体项
        MediaItem mediaItem = MediaItem.fromUri(Uri.parse(videoUrl));
        player.setMediaItem(mediaItem);

        // 准备播放器
        player.prepare();
        player.play();
    }

    @Override
    public void onPlaybackStateChanged(int state) {
        // 处理播放状态变化
        if (state == Player.STATE_READY) {
            playPauseButton.setImageResource(R.drawable.ic_pause);
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        if (player != null) {
            player.play();
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (player != null) {
            player.pause();
        }
    }

    @Override
    protected void onStop() {
        super.onStop();
        if (player != null) {
            player.stop();
            player.release();
            player = null;
        }
    }
}
