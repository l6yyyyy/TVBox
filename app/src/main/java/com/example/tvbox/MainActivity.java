package com.example.tvbox;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.leanback.app.BrowseSupportFragment;
import androidx.leanback.widget.ArrayObjectAdapter;
import androidx.leanback.widget.HeaderItem;
import androidx.leanback.widget.ListRow;
import androidx.leanback.widget.ListRowPresenter;
import androidx.leanback.widget.OnItemViewClickedListener;
import androidx.leanback.widget.OnItemViewSelectedListener;
import androidx.leanback.widget.Presenter;
import androidx.leanback.widget.Row;
import androidx.leanback.widget.RowPresenter;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (savedInstanceState == null) {
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.main_browse_fragment, new MainBrowseFragment())
                    .commitNow();
        }
    }

    public static class MainBrowseFragment extends BrowseSupportFragment {

        private ArrayObjectAdapter mRowsAdapter;

        @Override
        public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
            super.onViewCreated(view, savedInstanceState);

            setupUIElements();
            loadRows();
            setupEventListeners();
        }

        private void setupUIElements() {
            setTitle("TVBox");
            setHeadersState(HEADERS_ENABLED);
            setHeadersTransitionOnBackEnabled(true);
            setBrandColor(getResources().getColor(R.color.colorPrimary));
            setSearchAffordanceColor(getResources().getColor(R.color.colorAccent));
            setBadgeDrawable(getResources().getDrawable(R.mipmap.ic_launcher));
            setOnSettingsClickedListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 跳转到设置页面
                    Intent intent = new Intent(getContext(), SettingsActivity.class);
                    startActivity(intent);
                }
            });
        }

        private void loadRows() {
            mRowsAdapter = new ArrayObjectAdapter(new ListRowPresenter());

            // 电影分类
            ArrayObjectAdapter movieAdapter = new ArrayObjectAdapter(new CardPresenter());
            movieAdapter.add(new Movie("电影1", "https://example.com/poster1.jpg", "2023", "动作", "导演1", "演员1, 演员2", "剧情简介1"));
            movieAdapter.add(new Movie("电影2", "https://example.com/poster2.jpg", "2023", "喜剧", "导演2", "演员3, 演员4", "剧情简介2"));
            movieAdapter.add(new Movie("电影3", "https://example.com/poster3.jpg", "2023", "科幻", "导演3", "演员5, 演员6", "剧情简介3"));
            HeaderItem movieHeader = new HeaderItem(0, getString(R.string.title_movies));
            mRowsAdapter.add(new ListRow(movieHeader, movieAdapter));

            // 电视剧分类
            ArrayObjectAdapter tvAdapter = new ArrayObjectAdapter(new CardPresenter());
            tvAdapter.add(new Movie("电视剧1", "https://example.com/poster4.jpg", "2023", "古装", "导演4", "演员7, 演员8", "剧情简介4"));
            tvAdapter.add(new Movie("电视剧2", "https://example.com/poster5.jpg", "2023", "现代", "导演5", "演员9, 演员10", "剧情简介5"));
            tvAdapter.add(new Movie("电视剧3", "https://example.com/poster6.jpg", "2023", "悬疑", "导演6", "演员11, 演员12", "剧情简介6"));
            HeaderItem tvHeader = new HeaderItem(1, getString(R.string.title_tv_shows));
            mRowsAdapter.add(new ListRow(tvHeader, tvAdapter));

            // 综艺分类
            ArrayObjectAdapter varietyAdapter = new ArrayObjectAdapter(new CardPresenter());
            varietyAdapter.add(new Movie("综艺1", "https://example.com/poster7.jpg", "2023", "真人秀", "导演7", "主持人1, 嘉宾1", "节目简介1"));
            varietyAdapter.add(new Movie("综艺2", "https://example.com/poster8.jpg", "2023", "脱口秀", "导演8", "主持人2, 嘉宾2", "节目简介2"));
            varietyAdapter.add(new Movie("综艺3", "https://example.com/poster9.jpg", "2023", "竞赛", "导演9", "主持人3, 嘉宾3", "节目简介3"));
            HeaderItem varietyHeader = new HeaderItem(2, getString(R.string.title_variety));
            mRowsAdapter.add(new ListRow(varietyHeader, varietyAdapter));

            // 动漫分类
            ArrayObjectAdapter animeAdapter = new ArrayObjectAdapter(new CardPresenter());
            animeAdapter.add(new Movie("动漫1", "https://example.com/poster10.jpg", "2023", "热血", "导演10", "配音演员1, 配音演员2", "剧情简介7"));
            animeAdapter.add(new Movie("动漫2", "https://example.com/poster11.jpg", "2023", "科幻", "导演11", "配音演员3, 配音演员4", "剧情简介8"));
            animeAdapter.add(new Movie("动漫3", "https://example.com/poster12.jpg", "2023", "恋爱", "导演12", "配音演员5, 配音演员6", "剧情简介9"));
            HeaderItem animeHeader = new HeaderItem(3, getString(R.string.title_anime));
            mRowsAdapter.add(new ListRow(animeHeader, animeAdapter));

            // 直播分类
            ArrayObjectAdapter liveAdapter = new ArrayObjectAdapter(new CardPresenter());
            liveAdapter.add(new Movie("直播1", "https://example.com/poster13.jpg", "2023", "体育", "导演13", "主持人4", "直播简介1"));
            liveAdapter.add(new Movie("直播2", "https://example.com/poster14.jpg", "2023", "新闻", "导演14", "主持人5", "直播简介2"));
            liveAdapter.add(new Movie("直播3", "https://example.com/poster15.jpg", "2023", "娱乐", "导演15", "主持人6", "直播简介3"));
            HeaderItem liveHeader = new HeaderItem(4, getString(R.string.title_live));
            mRowsAdapter.add(new ListRow(liveHeader, liveAdapter));

            setAdapter(mRowsAdapter);
        }

        private void setupEventListeners() {
            setOnItemViewClickedListener(new OnItemViewClickedListener() {
                @Override
                public void onItemClicked(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
                    if (item instanceof Movie) {
                        Movie movie = (Movie) item;
                        // 跳转到详情页
                        Intent intent = new Intent(getContext(), MovieDetailActivity.class);
                        intent.putExtra("title", movie.getTitle());
                        intent.putExtra("posterUrl", movie.getPosterUrl());
                        intent.putExtra("year", movie.getYear());
                        intent.putExtra("genre", movie.getGenre());
                        intent.putExtra("director", movie.getDirector());
                        intent.putExtra("actors", movie.getActors());
                        intent.putExtra("plot", movie.getPlot());
                        startActivity(intent);
                    }
                }
            });

            setOnItemViewSelectedListener(new OnItemViewSelectedListener() {
                @Override
                public void onItemSelected(Presenter.ViewHolder itemViewHolder, Object item, RowPresenter.ViewHolder rowViewHolder, Row row) {
                    if (item instanceof Movie) {
                        Movie movie = (Movie) item;
                        // 更新详情信息
                    }
                }
            });

            setOnSearchClickedListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 跳转到搜索页面
                    Intent intent = new Intent(getContext(), SearchActivity.class);
                    startActivity(intent);
                }
            });
        }
    }
}
